package com.example.game.snake

interface SnakeDrawListener{
    fun onUpdateSnake(snakePosition: SnakePosition)
    fun onUpdateSnakeSize(snakeSize: Int)
}