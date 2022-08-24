package com.example.videoexample.data.remote.response

import com.example.videoexample.domain.entity.VideoEntity
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("videos")
    val videos: List<VideoItem>
) {
    data class VideoItem(
        @SerializedName("description")
        val description: String,
        @SerializedName("sources")
        val sources: String,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("thumb")
        val thumb: String,
        @SerializedName("title")
        val title: String
    )

    fun VideoItem.toEntity(id: Long) = VideoEntity.VideoItem(
        id = id,
        description = description,
        sources = sources,
        subtitle = subtitle,
        thumb = thumb,
        title = title
    )
}

fun VideoResponse.toEntity() = VideoEntity(
    videos = videos.mapIndexed { index, videoItem -> videoItem.toEntity(index.toLong()) }
)