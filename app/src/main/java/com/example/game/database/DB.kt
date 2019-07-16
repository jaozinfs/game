package com.example.game.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.game.database.entities.Draw
import com.example.game.database.dao.DrawDao


@Database(entities = arrayOf(Draw::class), version = 2)
abstract class DB : RoomDatabase(){
    abstract fun drawDao(): DrawDao

}