package com.example.videoexample.data.repository

import com.example.videoexample.data.remote.datasource.VideoDataSourceImpl
import com.example.videoexample.data.remote.response.toEntity
import com.example.videoexample.domain.entity.VideoEntity
import com.example.videoexample.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoDataSource: VideoDataSourceImpl
): VideoRepository {
    override suspend fun getVideoList(): VideoEntity {
        return  videoDataSource.getVideoList().toEntity()
    }
}