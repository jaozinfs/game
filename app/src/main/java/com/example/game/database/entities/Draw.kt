package com.example.game.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.game.database.converter.Converters

@Entity
@TypeConverters(Converters::class)
data class Draw(@PrimaryKey(autoGenerate = false)val id: Int,
                val listPoints:List<EventToDraw>,
                val color: Int)