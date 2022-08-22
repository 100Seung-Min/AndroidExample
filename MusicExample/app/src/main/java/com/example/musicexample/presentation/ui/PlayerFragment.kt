package com.example.musicexample.presentation.ui

import androidx.fragment.app.activityViewModels
import coil.load
import com.example.musicexample.R
import com.example.musicexample.databinding.FragmentPlayerBinding
import com.example.musicexample.presentation.extension.onStopTrackingTouch
import com.example.musicexample.presentation.extension.setTime
import com.example.musicexample.presentation.ui.base.BaseFragment
import com.example.musicexample.presentation.viewmodel.MusicViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition
import java.time.Duration

@AndroidEntryPoint
class PlayerFragment: BaseFragment<FragmentPlayerBinding>(R.layout.fragment_player) {
    private val musicViewModel: MusicViewModel by activityViewModels()
    private var player: SimpleExoPlayer? = null

    private val updateRunnable = Runnable {
        update()
    }

    override fun init() {
        initExoPlayer()
        initMusicList()
        initController()
        initSeekBar()
        observeView()
    }

    private fun observeView() = binding.apply {
        musicViewModel.currentMusic.observe(this@PlayerFragment) {
            music = it
            coverImg.load(it.coverUrl)
            this@PlayerFragment.player?.seekTo(musicViewModel.currentPosition.value!!, 0)
            this@PlayerFragment.player?.play()
        }
    }

    private fun initExoPlayer() {
        context?.let {
            player = SimpleExoPlayer.Builder(it).build()
        }
        binding.playerView.player = player
        player?.addListener(object : Player.EventListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                binding.playBtn.setImageResource(if(isPlaying) R.drawable.ic_play else R.drawable.ic_stop)
            }

            override fun onPlaybackStateChanged(state: Int) {
                super.onPlaybackStateChanged(state)
                update()
            }
        })
    }

    private fun initMusicList() {
        player ?: return
        context?.let {
            player?.addMediaItems(musicViewModel.musicList.value!!.map {
                MediaItem.Builder()
                    .setMediaId(it.id.toString())
                    .setUri(it.streamUrl)
                    .build()
            })
            player?.prepare()
        }
    }

    private fun initController() = binding.apply {
        playBtn.setOnClickListener {
            val player = this@PlayerFragment.player ?: return@setOnClickListener
            if(player.isPlaying) player.pause()
            else player.play()
        }
        nextBtn.setOnClickListener {
            musicViewModel.nextMusic()
        }
        prevBtn.setOnClickListener {
            musicViewModel.prevMusic()
        }
    }
    
    private fun initSeekBar() = binding.apply {
        musicProgress.onStopTrackingTouch { this@PlayerFragment.player?.seekTo(it.progress * 1000L) }
    }

    private fun update() {
        val player = player ?: return
        val duration = if(player.duration >= 0) player.duration else 0
        val position = player.currentPosition
        updateUI(duration, position)
        val state = player.playbackState
        view?.removeCallbacks(updateRunnable)
        if (state != Player.STATE_IDLE && state != Player.STATE_ENDED) {
            view?.postDelayed(updateRunnable, 1000)
        }
    }

    private fun updateUI(duration: Long, position: Long) = binding.apply {
        musicProgress.setTime(duration, position)
        progressTime.setTime(position)
        allTime.setTime(duration)
    }

    override fun onDetach() {
        super.onDetach()
        player?.pause()
        view?.removeCallbacks(updateRunnable)
    }
}