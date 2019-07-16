package com.example.game.snake.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.game.R
import com.example.game.snake.Snake
import com.example.game.snake.SnakePosition
import com.example.game.snake.arena.ArenaState
import com.example.game.snake.arena.IArenaDrawListener
import com.example.game.snake.arena.SorteSize

fun updateSnake(snakePosition: SnakePosition, buttons: List<View>){
    when(snakePosition){
        SnakePosition.SnakeLeft->{
            buttons[0].visibility = View.VISIBLE
            buttons[1].visibility = View.GONE
            buttons[2].visibility = View.GONE
        }
        SnakePosition.SnakeMiddle->{
            buttons[0].visibility = View.GONE
            buttons[1].visibility = View.VISIBLE
            buttons[2].visibility = View.GONE
        }
        SnakePosition.SnakeRight->{
            buttons[0].visibility = View.GONE
            buttons[1].visibility = View.GONE
            buttons[2].visibility = View.VISIBLE
        }
    }
}
fun updateSnakeSize(newSize: SorteSize, snake: Snake, snakeSize: List<View>, initGame: Boolean = false, callbackGameOver: () -> Unit){
    when(snake.getPosition()){
        SnakePosition.SnakeLeft-> updateSnakeTexts(newSize.sorteSize[0].toString(), snakeSize, initGame, callbackGameOver)
        SnakePosition.SnakeMiddle-> updateSnakeTexts(newSize.sorteSize[1].toString(), snakeSize, initGame, callbackGameOver)
        SnakePosition.SnakeRight-> updateSnakeTexts(newSize.sorteSize[2].toString(), snakeSize, initGame, callbackGameOver)
    }
}
fun updateSnakeTexts(newSize: String, snakeSize: List<View>, initialValue : Boolean = false, callbackGameOver: () -> Unit){
    var nSize = newSize

    if(!initialValue){
        nSize = snakeSize[0].findViewById<TextView>(R.id.snakeSizeLeft).text.toString().updateSize(newSize)
    }
    if(nSize.toInt() <= 0){
        callbackGameOver()
    }
    (snakeSize[0].findViewById<TextView>(R.id.snakeSizeLeft)).text = nSize
    (snakeSize[1].findViewById<TextView>(R.id.snakeSizeMiddle)).text = nSize
    (snakeSize[2].findViewById<TextView>(R.id.snakeSizeRight)).text = nSize
}

fun updateArena(arenaState: ArenaState, arena: List<View>,
                iArenaDrawListener: IArenaDrawListener,
                sorteSize: SorteSize? = null){
    when(arenaState){
        ArenaState.Top->{
            arena[0].visibility = View.VISIBLE
            arena[1].visibility = View.INVISIBLE
            arena[2].visibility = View.INVISIBLE
        }
        ArenaState.Mid->{
            arena[0].visibility = View.INVISIBLE
            arena[1].visibility = View.VISIBLE
            arena[2].visibility = View.INVISIBLE
        }
        ArenaState.Bottom->{
            arena[0].visibility = View.INVISIBLE
            arena[1].visibility = View.INVISIBLE
            arena[2].visibility = View.VISIBLE
//            iArenaDrawListener.onArenaBottom(s)
        }
    }
}

fun getNewSizeValueFromPositionSnake(relativeLayout: ConstraintLayout,
                        snakePosition: SnakePosition) : Int{
    return when(snakePosition){
        SnakePosition.SnakeLeft-> relativeLayout.findViewById<TextView>(R.id.sete).text.toString().toInt()
        SnakePosition.SnakeMiddle-> relativeLayout.findViewById<TextView>(R.id.oito).text.toString().toInt()
        SnakePosition.SnakeRight-> relativeLayout.findViewById<TextView>(R.id.nove).text.toString().toInt()
    }
}

fun logT(text: String){
    Log.d("SnakeGame", text)
}