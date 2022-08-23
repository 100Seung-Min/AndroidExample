package com.example.videoexample.presentation.ui

import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.videoexample.R
import com.example.videoexample.databinding.ActivityMainBinding
import com.example.videoexample.presentation.ui.base.BaseActivity
import com.example.videoexample.presentation.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: VideoViewModel by viewModels()
    override fun init() {
        initNav()
    }

    private fun initNav() {
        NavigationUI.setupWithNavController(binding.bottomNav, findNavController(R.id.frame_layout))
    }
}