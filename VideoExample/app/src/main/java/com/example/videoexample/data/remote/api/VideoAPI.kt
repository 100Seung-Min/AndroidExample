package com.example.videoexample.data.remote.api

import com.example.videoexample.data.remote.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

interface VideoAPI {
    @GET("/v3/66f12ec7-a2e6-4070-b7cc-7f3563fbe962")
    suspend fun getVideoList(): Response<VideoResponse>
}