package com.example.musicexample.data.remote.datasource

import com.example.musicexample.data.remote.response.MusicResponse

interface MusicDataSource {
    suspend fun getMusicList(): MusicResponse
}