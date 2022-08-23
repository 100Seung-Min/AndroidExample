package com.example.videoexample.domain.repository

import com.example.videoexample.domain.entity.VideoEntity

interface VideoRepository {
    suspend fun getVideoList(): VideoEntity
}