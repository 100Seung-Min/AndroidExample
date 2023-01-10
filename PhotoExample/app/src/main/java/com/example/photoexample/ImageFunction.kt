package com.example.photoexample

import android.os.Environment
import java.io.File
import java.time.LocalDateTime

object FilePaths {
    val ROOT_DIR = Environment.getExternalStorageDirectory().path
    val CAMERA = "$ROOT_DIR/DCIM/Camera"
}

fun getImageDirectory(): List<String> {
    var imgList = listOf<String>()
    imgList = imgList.plus(FilePaths.CAMERA.getFilePaths())
    imgList = imgList.filter { !it.contains(".mp4") }
    return imgList.reversed()
}

fun photoName(): File {
    val sdCard = Environment.getExternalStorageDirectory()
    val fileName = LocalDateTime.now().toString()
    return File(sdCard, "${fileName}.jpg")
}

fun String.getDirectoryPaths(): List<String> {
    var directoryList = listOf<String>()
    File(this).listFiles().forEach {
        if (it.isDirectory) {
            directoryList = directoryList.plus(it.absolutePath)
        }
    }
    return directoryList
}

fun String.getFilePaths(): List<String> {
    var directoryList = listOf<String>()
    File(this).listFiles().forEach {
        if (it.isFile) {
            directoryList = directoryList.plus(it.absolutePath)
        }
    }
    return directoryList
}