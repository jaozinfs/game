package com.example.game.repositore

import androidx.lifecycle.LiveData
import com.example.game.database.DB
import com.example.game.database.entities.Draw
import com.example.game.snake.utils.logT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository( val database: DB ){
    suspend fun insert( draw: Draw ) = withContext(Dispatchers.IO){
        database.drawDao().insertDraw(draw)
    }
    suspend fun get() : Draw? = withContext(Dispatchers.IO){
        getDraws()
        database.drawDao().getDraw(0)
    }
    suspend fun delet() = withContext(Dispatchers.IO){
        database.drawDao().deleteDraw(database.drawDao().getDraw(0))
    }
    suspend fun getDraws() = withContext(Dispatchers.IO){
        logT("ids")

        database.drawDao().getDraws()?.forEach {
            logT("id: ${it.id}")
        }
    }
}
