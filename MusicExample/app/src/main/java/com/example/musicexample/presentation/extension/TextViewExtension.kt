package com.example.musicexample.presentation.extension

import android.widget.TextView
import java.util.concurrent.TimeUnit

fun TextView.setTime(time: Long) {
    text = String.format(
        "%02d:%02d",
        TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS),
        (time / 1000) % 60
    )
}