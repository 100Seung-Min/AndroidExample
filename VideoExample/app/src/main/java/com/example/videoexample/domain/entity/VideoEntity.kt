package com.example.videoexample.domain.entity

data class VideoEntity(
    val videos: List<VideoItem>
) {
    data class VideoItem(
        val id: Long,
        val description: String,
        val sources: String,
        val subtitle: String,
        val thumb: String,
        val title: String
    )
}