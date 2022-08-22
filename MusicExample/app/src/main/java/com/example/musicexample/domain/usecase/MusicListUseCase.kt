package com.example.musicexample.domain.usecase

import com.example.musicexample.domain.repository.MusicRepository
import javax.inject.Inject

class MusicListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    suspend fun execute() = musicRepository.getMusicList()
}