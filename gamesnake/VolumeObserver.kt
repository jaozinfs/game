package com.example.game.game

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.provider.Settings.SettingNotFoundException
import androidx.core.content.ContextCompat.getSystemService
import android.media.AudioManager
import com.example.game.snake.utils.logT


open class VolumeObserver(val context: Context, handler: Handler) : ContentObserver(handler) {
    private val audioManager  = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    override fun deliverSelfNotifications(): Boolean =  false


    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val currentVolume2 = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)
        val currentVolume3 = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM)

        logT("New volume $currentVolume")
        logT("New volume $currentVolume2")
        logT("New volume $currentVolume3")
    }


}