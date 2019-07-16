package com.example.game.snake.utils

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


fun View.fadeOutWithFadeIn(callbackOnEnd: ()->Unit){
    val fadeOut = AlphaAnimation(1f, 0f)
    fadeOut.interpolator = AccelerateInterpolator() //and this
    fadeOut.startOffset = 200
    fadeOut.duration = 200
    this.animation = fadeOut
    fadeOut.setAnimationListener(object : Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {}

        override fun onAnimationEnd(p0: Animation?) {
            callbackOnEnd()
        }

        override fun onAnimationStart(p0: Animation?) {}

    })
    fadeOut.start()
}
fun View.fadeIn(callbackOnEnd: (() -> Unit)? = null){
    val fadeIn = AlphaAnimation(0f, 1f)
    fadeIn.interpolator = AccelerateInterpolator() //and this
    fadeIn.startOffset = 200
    fadeIn.duration = 200
    this.animation = fadeIn
    fadeIn.setAnimationListener(object : Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) { }
        override fun onAnimationEnd(p0: Animation?) {callbackOnEnd?.let { callbackOnEnd() }}
        override fun onAnimationStart(p0: Animation?) {}
    })
    fadeIn.start()
}
fun String.updateSize(size: Int): String{
    return (this.toInt() + size).toString()
}
fun String.updateSize(size: String): String{
    return (this.toInt() + size.toInt()).toString()
}