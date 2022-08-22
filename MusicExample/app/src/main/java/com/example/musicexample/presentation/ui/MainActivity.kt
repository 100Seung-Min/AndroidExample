package com.example.musicexample.presentation.ui

import androidx.activity.viewModels
import com.example.musicexample.R
import com.example.musicexample.databinding.ActivityMainBinding
import com.example.musicexample.presentation.ui.base.BaseActivity
import com.example.musicexample.presentation.viewmodel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MusicViewModel by viewModels()
    override fun init() {
        viewModel.getMusicList()
    }
}