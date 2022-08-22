package com.example.musicexample.presentation.ui

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicexample.R
import com.example.musicexample.databinding.FragmentListBinding
import com.example.musicexample.presentation.adapter.ListAdapter
import com.example.musicexample.presentation.ui.base.BaseFragment
import com.example.musicexample.presentation.viewmodel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: BaseFragment<FragmentListBinding>(R.layout.fragment_list) {
    private val musicViewModel: MusicViewModel by activityViewModels()
    lateinit var adapter: ListAdapter
    override fun init() {
        musicViewModel.getMusicList()
        initRecyclerView()
        observeMusicList()
    }

    private fun initRecyclerView() {
        adapter = ListAdapter {
            findNavController().navigate(R.id.action_listFragment_to_playerFragment)
            musicViewModel.setMusic(it)
        }
        binding.musicList.adapter = adapter
        binding.musicList.layoutManager = LinearLayoutManager(context)
    }

    private fun observeMusicList() {
        musicViewModel.musicList.observe(this) {
            adapter.submitList(it)
        }
    }
}