package com.example.game.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.database.ContentObserver
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.widget.Toast
import com.example.game.snake.utils.logT
import java.util.*
import android.os.CountDownTimer
import com.example.game.R


class TestPlugActivity: AppCompatActivity(){
    private lateinit var observer: ContentObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plug_test)


    }
    var KEY_EVENT: Int = ACTION_UP
    var keyFound = true
    lateinit var timerSchedule: Timer

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event != null) {
            when(event.action) {
                ACTION_DOWN-> {
                    setSchedule()
                    KEY_EVENT = ACTION_DOWN
                }

                ACTION_UP->{
                    logT("up")
                    KEY_EVENT = ACTION_UP
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }
    var flagStarte = false
    private fun setSchedule() {
        if(flagStarte){
            return
        }
        flagStarte = true
        var timer = true



        val mCountDownTimer = object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                timer=false
            }
        }

        mCountDownTimer.start()

        Thread{
            var notBasc = false

            while (timer){
                if(KEY_EVENT != ACTION_DOWN){
                    keyFound = true
                    mCountDownTimer.cancel()
                    flagStarte = false
                    notBasc = true
                    break
                }
            }
            if(!notBasc) {
                runOnUiThread {
                    basculando()
                    flagStarte = false
                }
            }

        }.start()
    }

    fun basculando() = Toast.makeText(this, "Basculando", Toast.LENGTH_LONG).show()
}