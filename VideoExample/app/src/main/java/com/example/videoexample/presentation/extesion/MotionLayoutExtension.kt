package com.example.videoexample.presentation.extesion

import androidx.constraintlayout.motion.widget.MotionLayout

fun MotionLayout.onTransitionChange(action: (motionLayout: MotionLayout?,startId: Int,endId: Int,progress: Float) -> Unit) {
    setTransitionListener(object : MotionLayout.TransitionListener {
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) =
            Unit

        override fun onTransitionChange(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float
        ) {
            action(motionLayout, startId, endId, progress)
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) = Unit

        override fun onTransitionTrigger(
            motionLayout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) = Unit

    })
}