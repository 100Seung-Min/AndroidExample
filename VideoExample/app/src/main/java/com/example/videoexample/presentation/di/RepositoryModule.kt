package com.example.videoexample.presentation.di

import com.example.videoexample.data.remote.datasource.VideoDataSourceImpl
import com.example.videoexample.data.repository.VideoRepositoryImpl
import com.example.videoexample.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideVideoRepository(dataSource: VideoDataSourceImpl): VideoRepository = VideoRepositoryImpl(dataSource)
}