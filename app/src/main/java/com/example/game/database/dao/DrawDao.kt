package com.example.game.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.game.database.entities.Draw

@Dao
abstract interface DrawDao{

    @Query("SELECT * FROM Draw")
    abstract suspend fun getDraws(): List<Draw>?

    @Query("SELECT * FROM Draw WHERE id = :id")
    abstract suspend fun getDraw(id: Int): Draw

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDraw(draw: Draw)

    @Delete
    abstract suspend fun deleteDraw(draw: Draw)
}