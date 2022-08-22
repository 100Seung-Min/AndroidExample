package com.example.musicexample.domain.entity

data class MusicEntity(
    val musics: List<MusicItem>
) {
    data class MusicItem(
        val track: String,
        val streamUrl: String,
        val artist: String,
        val coverUrl: String
    )
}
