package com.example.game.snake

import android.widget.Toast
import com.example.game.snake.utils.logT

class Snake(private val snakeDrawListener: SnakeDrawListener){
    private var size = 1
    private var snakeControl : SnakePosition = SnakePosition.SnakeMiddle


    fun updatePosition(snakePosition: SnakePosition) {

        // caso a nova posição for igual a que ja está retorna
        if(snakeControl == SnakePosition.SnakeLeft
            && snakePosition == SnakePosition.SnakeLeft ||
            snakeControl == SnakePosition.SnakeRight &&
            snakePosition == SnakePosition.SnakeRight){
            return
        }

        // caso estiver no middle e a nova for para esqurda
        if(snakeControl == SnakePosition.SnakeMiddle
            && snakePosition == SnakePosition.SnakeLeft){
            snakeControl = SnakePosition.SnakeLeft
            snakeDrawListener.onUpdateSnake(snakeControl)
            return
        }
        // caso estiver no middle e a nova for para direita
        if(snakeControl == SnakePosition.SnakeMiddle
            && snakePosition == SnakePosition.SnakeRight){
            snakeControl = SnakePosition.SnakeRight
            snakeDrawListener.onUpdateSnake(snakeControl)
            return
        }

        // caso estiver na esqurda e a nova for para direita va para o middle
        if(snakeControl == SnakePosition.SnakeLeft
            && snakePosition == SnakePosition.SnakeRight){
            snakeControl = SnakePosition.SnakeMiddle
            snakeDrawListener.onUpdateSnake(snakeControl)
            return
        }

        // caso estiver na direita e a nova for para esquerda va para o middle
        if(snakeControl == SnakePosition.SnakeRight
            && snakePosition == SnakePosition.SnakeLeft){
            snakeControl = SnakePosition.SnakeMiddle
            snakeDrawListener.onUpdateSnake(snakeControl)
            return
        }
    }

    fun updateSnake(newSize: Int){
        if(newSize == size) return
        size = newSize
        snakeDrawListener.onUpdateSnakeSize(newSize)
    }

    fun getPosition() = snakeControl
}