package com.example.videoexample.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoexample.R
import com.example.videoexample.databinding.ActivityMainBinding
import com.example.videoexample.databinding.FragmentVideoBinding
import com.example.videoexample.presentation.extesion.onTransitionChange
import com.example.videoexample.presentation.ui.base.BaseFragment
import com.example.videoexample.presentation.viewmodel.VideoViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import com.example.videoexample.presentation.adapter.ListAdapter

@AndroidEntryPoint
class VideoFragment: BaseFragment<FragmentVideoBinding>(R.layout.fragment_video) {
    private lateinit var mainBinding: ActivityMainBinding
    private val viewModel: VideoViewModel by activityViewModels()
    private var player: SimpleExoPlayer? = null
    lateinit var adapter: ListAdapter

    override fun init() {
        viewModel.getVideoList()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        initMotionLayout()
        initExoPlayer()
        initRecyclerView()
        observeCurrentVideo()
        observeVideoList()
        play()
    }

    private fun initMotionLayout() = binding.videoMotionLayout.apply {
        onTransitionChange { _, _, _, progress ->
            (activity as MainActivity).also { mainActivity ->
                mainActivity.findViewById<MotionLayout>(mainBinding.mainMotionLayout.id).progress = abs(progress)
            }
        }
    }

    private fun initExoPlayer() = with(binding){
        context?.let {
            player = SimpleExoPlayer.Builder(it).build()
        }
        videoPlayer.player = player
    }

    private fun initRecyclerView() {
        adapter = ListAdapter {
            viewModel.setCurrentVideo(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun play() {
        context?.let {
            val dataSourceFactory = DefaultDataSourceFactory(it)
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(viewModel.currentVideo.value!!.sources)))
            player?.apply {
                setMediaSource(mediaSource)
                prepare()
                play()
            }
        }
        binding.apply {
            videoMotionLayout.transitionToEnd()
        }
    }

    private fun observeCurrentVideo() = viewModel.currentVideo.observe(this) {
        play()
    }

    private fun observeVideoList() = viewModel.videoList.observe(this) {
        adapter.submitList(it)
    }
}