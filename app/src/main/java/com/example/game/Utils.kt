package com.example.game

import android.graphics.Color
import java.util.*


fun randomColor() : Int{
    val rnd = Random()
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}