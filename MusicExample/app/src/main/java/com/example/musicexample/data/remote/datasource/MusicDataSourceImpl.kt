package com.example.musicexample.data.remote.datasource

import com.example.musicexample.data.remote.api.MusicAPI
import com.example.musicexample.data.remote.response.MusicResponse
import com.example.musicexample.data.utils.BaseDataSource
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicAPI: MusicAPI
): MusicDataSource, BaseDataSource() {
    override suspend fun getMusicList(): MusicResponse {
        return safeApiCall { musicAPI.getMustList() }?.body() ?: MusicResponse(musics = listOf())
    }
}