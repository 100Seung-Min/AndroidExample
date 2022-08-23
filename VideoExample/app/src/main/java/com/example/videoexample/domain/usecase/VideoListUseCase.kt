package com.example.videoexample.domain.usecase

import com.example.videoexample.domain.repository.VideoRepository
import javax.inject.Inject

class VideoListUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) {
    suspend fun execute() = videoRepository.getVideoList()
}