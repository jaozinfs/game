package com.example.game.usecase

import androidx.lifecycle.LiveData
import com.example.game.database.entities.Draw
import com.example.game.repositore.Repository
import com.example.game.snake.utils.logT

class DrawUseCase(private val repository: Repository){
    suspend fun insertDraw(draw: Draw){
        if(draw.listPoints.isEmpty()){
            repository.delet()
            return
        }
        repository.insert(draw)
    }
    suspend fun getDraw(): Draw? {
        return repository.get()
    }
}