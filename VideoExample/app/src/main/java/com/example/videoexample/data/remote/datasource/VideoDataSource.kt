package com.example.videoexample.data.remote.datasource

import com.example.videoexample.data.remote.response.VideoResponse

interface VideoDataSource {
    suspend fun getVideoList(): VideoResponse
}