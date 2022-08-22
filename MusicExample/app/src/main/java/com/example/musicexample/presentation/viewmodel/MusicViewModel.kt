package com.example.musicexample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicexample.domain.entity.MusicEntity
import com.example.musicexample.domain.usecase.MusicListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicListUseCase: MusicListUseCase
): ViewModel() {
    private val _musicList = MutableLiveData<List<MusicEntity.MusicItem>>()
    val musicList: LiveData<List<MusicEntity.MusicItem>> get() = _musicList

    private val _currentMusic = MutableLiveData<MusicEntity.MusicItem>()
    val currentMusic: LiveData<MusicEntity.MusicItem> get() = _currentMusic

    private val _currentPosition = MutableLiveData<Int>()
    val currentPosition: LiveData<Int> get() = _currentPosition

    fun getMusicList() {
        viewModelScope.launch {
            musicListUseCase.execute().let { response ->
                if(response != null) {
                    _musicList.value = response.musics
                }
            }
        }
    }

    fun setMusic(music: MusicEntity.MusicItem) {
        _currentMusic.value = music
        _currentPosition.value = _musicList.value!!.indexOf(_currentMusic.value!!)
    }

    fun nextMusic() {
        if(_currentPosition.value!! + 1 >= _musicList.value!!.size) return
        _currentPosition.value = _currentPosition.value!! + 1
        _currentMusic.value = _musicList.value!![_currentPosition.value!!]
    }

    fun prevMusic() {
        if(_currentPosition.value!! - 1 < 0) return
        _currentPosition.value = _currentPosition.value!! - 1
        _currentMusic.value = _musicList.value!![_currentPosition.value!!]
    }
}