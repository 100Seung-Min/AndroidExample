package com.example.musicexample.data.remote.api

import com.example.musicexample.data.remote.response.MusicResponse
import retrofit2.Response
import retrofit2.http.GET

interface MusicAPI {
    @GET("/v3/e4db045a-23a9-4b49-a3fc-78cf51f3f964")
    suspend fun getMustList(): Response<MusicResponse>
}