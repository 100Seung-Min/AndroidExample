package com.example.musicexample.domain.repository

import com.example.musicexample.domain.entity.MusicEntity

interface MusicRepository {
    suspend fun getMusicList(): MusicEntity
}