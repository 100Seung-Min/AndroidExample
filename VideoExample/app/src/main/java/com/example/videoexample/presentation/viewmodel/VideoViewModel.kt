package com.example.videoexample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoexample.domain.usecase.VideoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.videoexample.domain.entity.VideoEntity.VideoItem

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoListUseCase: VideoListUseCase
): ViewModel() {
    private val _videoList = MutableLiveData<List<VideoItem>>()
    val videoList: LiveData<List<VideoItem>> get() = _videoList
    fun getVideoList() {
        viewModelScope.launch {
            videoListUseCase.execute().let { response ->
                if(response != null) {
                    _videoList.value = response.videos
                }
            }
        }
    }
}