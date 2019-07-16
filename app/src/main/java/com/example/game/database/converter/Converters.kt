package com.example.game.database.converter

import androidx.room.TypeConverter
import com.example.game.database.entities.EventToDraw
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun measurementsToString(drawString: List<EventToDraw>): String {
       val gson = Gson()
        val type = object : TypeToken<List<EventToDraw>>(){}.type
        return gson.toJson(drawString, type)
    }

    @TypeConverter
    fun stringToMeasurements(json: String): List<EventToDraw>{
        val gson = Gson()
        val type = object : TypeToken<List<EventToDraw>>(){}.type
        return gson.fromJson(json, type)
    }
}