package com.example.musicexample.presentation.extension

import android.widget.SeekBar

fun SeekBar.setTime(duration: Long, position: Long) {
    max = (duration / 1000).toInt()
    progress = (position / 1000).toInt()
}

fun SeekBar.onStopTrackingTouch(action: (p0: SeekBar) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) = Unit

        override fun onStartTrackingTouch(p0: SeekBar?) = Unit

        override fun onStopTrackingTouch(p0: SeekBar?) {
            action(p0!!)
        }
    })
}