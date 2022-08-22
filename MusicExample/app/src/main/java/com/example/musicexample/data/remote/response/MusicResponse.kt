package com.example.musicexample.data.remote.response

import com.example.musicexample.domain.entity.MusicEntity
import com.google.gson.annotations.SerializedName

data class MusicResponse(
    val musics: List<MusicItem>
) {
    data class MusicItem(
        @SerializedName("track")
        val track: String,
        @SerializedName("streamUrl")
        val streamUrl: String,
        @SerializedName("artist")
        val artist: String,
        @SerializedName("coverUrl")
        val coverUrl: String
    )

    fun MusicItem.toEntity(id: Long) = MusicEntity.MusicItem(
        id = id,
        track = track,
        streamUrl = streamUrl,
        artist = artist,
        coverUrl = coverUrl
    )
}

fun MusicResponse.toEntity() = MusicEntity(
    musics = musics.mapIndexed { index, it -> it.toEntity(index.toLong()) }
)