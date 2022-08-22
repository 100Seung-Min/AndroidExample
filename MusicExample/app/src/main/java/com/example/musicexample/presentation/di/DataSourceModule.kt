package com.example.musicexample.presentation.di

import com.example.musicexample.data.remote.api.MusicAPI
import com.example.musicexample.data.remote.datasource.MusicDataSourceImpl
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
    fun provideMusicDataSource(musicAPI: MusicAPI) = MusicDataSourceImpl(musicAPI)
}