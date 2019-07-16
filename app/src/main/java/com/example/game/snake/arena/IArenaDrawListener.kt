package com.example.game.snake.arena

interface IArenaDrawListener{
    fun onUpdateArena(arenaState: ArenaState)
    fun onArenaBottom(s: SorteSize, initGame: Boolean = false)
}
