package com.example.game

import android.app.Application
import com.example.game.di.roomModule
import com.example.game.di.viewModuleModules
import org.koin.android.ext.android.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin(this@App,
            listOf(
            roomModule,
            viewModuleModules)
        )
    }


}