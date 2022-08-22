package com.example.musicexample.data.repository

import com.example.musicexample.data.remote.datasource.MusicDataSourceImpl
import com.example.musicexample.data.remote.response.toEntity
import com.example.musicexample.domain.entity.MusicEntity
import com.example.musicexample.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSourceImpl
): MusicRepository {
    override suspend fun getMusicList(): MusicEntity {
        return musicDataSource.getMusicList().toEntity()
    }
}