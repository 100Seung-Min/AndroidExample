package com.example.videoexample.presentation.ui

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.videoexample.R
import com.example.videoexample.databinding.ActivityMainBinding
import com.example.videoexample.databinding.FragmentVideoBinding
import com.example.videoexample.presentation.extesion.onTransitionChange
import com.example.videoexample.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class VideoFragment: BaseFragment<FragmentVideoBinding>(R.layout.fragment_video) {
    private lateinit var mainBinding: ActivityMainBinding
    override fun init() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        initMotionLayout()
    }

    private fun initMotionLayout() = binding.videoMotionLayout.apply {
        onTransitionChange { _, _, _, progress ->
            (activity as MainActivity).also { mainActivity ->
                mainActivity.findViewById<MotionLayout>(mainBinding.mainMotionLayout.id).progress = abs(progress)
            }
        }
    }
}