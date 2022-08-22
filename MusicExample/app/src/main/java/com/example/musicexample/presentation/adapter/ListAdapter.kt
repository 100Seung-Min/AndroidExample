package com.example.musicexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicexample.databinding.ItemMusicBinding
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musicexample.domain.entity.MusicEntity.MusicItem
import com.example.musicexample.presentation.adapter.ListAdapter.ViewHolder

class ListAdapter(private val callback: (MusicItem) -> Unit): ListAdapter<MusicItem, ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemMusicBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(music: MusicItem) = binding.apply {
            this.music = music
            musicLayout.setOnClickListener { callback(music) }
            coverImg.load(music.coverUrl)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicItem>() {
            override fun areItemsTheSame(
                oldItem: MusicItem,
                newItem: MusicItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MusicItem,
                newItem: MusicItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}