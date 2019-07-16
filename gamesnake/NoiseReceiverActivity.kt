package com.example.game.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.game.R
import com.example.game.snake.utils.logT

class NoiseReceiverActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plug_test)



    }
    private class BecomingNoisyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
              logT(intent.identifier.toString())
              logT(intent.dataString.toString())
              logT(intent.`package`.toString())
              logT(intent.scheme.toString())
              logT(intent.type.toString())
              logT(intent.categories.toString())
              logT(intent.clipData.toString())
              logT(intent.component.toString())
              logT(intent.extras.toString())
              logT(intent.flags.toString())
              logT(intent.selector.toString())
              logT(intent.sourceBounds.toString())
              logT(intent.extras.toString())
            }
        }
    }
}

