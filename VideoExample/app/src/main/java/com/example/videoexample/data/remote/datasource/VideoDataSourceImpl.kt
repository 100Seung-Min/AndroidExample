package com.example.videoexample.data.remote.datasource

import com.example.videoexample.data.remote.api.VideoAPI
import com.example.videoexample.data.remote.response.VideoResponse
import com.example.videoexample.data.utils.BaseDataSource
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(
    private val videoAPI: VideoAPI
): VideoDataSource, BaseDataSource() {
    override suspend fun getVideoList(): VideoResponse {
        return safeApiCall { videoAPI.getVideoList() }?.body() ?: VideoResponse(listOf())
    }
}