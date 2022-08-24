package com.example.videoexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.videoexample.databinding.ItemVideoBinding
import com.example.videoexample.domain.entity.VideoEntity.VideoItem
import com.example.videoexample.presentation.adapter.ListAdapter.ViewHolder

class ListAdapter(private val callback: (VideoItem) -> Unit): ListAdapter<VideoItem, ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoItem) = binding.apply {
            videoData = video
            coverImg.apply {
                load(video.thumb) {
                    transformations(RoundedCornersTransformation(0f, 0f, 0f, 0f))
                }
                setOnClickListener { callback(video) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(
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
        val diffUtil = object : DiffUtil.ItemCallback<VideoItem>() {
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}