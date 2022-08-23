package com.example.videoexample.presentation.di

import com.example.videoexample.data.remote.api.VideoAPI
import com.example.videoexample.data.remote.datasource.VideoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideVideoSource(service: VideoAPI) = VideoDataSourceImpl(service)
}