package com.example.videoexample.presentation.ui

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoexample.R
import com.example.videoexample.databinding.FragmentListBinding
import com.example.videoexample.presentation.adapter.ListAdapter
import com.example.videoexample.presentation.ui.base.BaseFragment
import com.example.videoexample.presentation.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: BaseFragment<FragmentListBinding>(R.layout.fragment_list) {
    private val viewModel: VideoViewModel by activityViewModels()
    lateinit var adapter: ListAdapter
    override fun init() {
        viewModel.getVideoList()
        initRecyclerView()
        observeVideoList()
    }

    private fun initRecyclerView() {
        adapter = ListAdapter {
        }
        binding.videoList.apply {
            adapter = this@ListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeVideoList() {
        viewModel.videoList.observe(this) {
            adapter.submitList(it)
        }
    }
}