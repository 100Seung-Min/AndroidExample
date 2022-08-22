package com.example.musicexample.presentation.di

import com.example.musicexample.data.remote.datasource.MusicDataSourceImpl
import com.example.musicexample.data.repository.MusicRepositoryImpl
import com.example.musicexample.domain.repository.MusicRepository
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
    fun provideMusicRepository(dataSource: MusicDataSourceImpl): MusicRepository = MusicRepositoryImpl(dataSource)
}