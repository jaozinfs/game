package com.example.game.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.game.R
import com.example.game.snake.Snake
import com.example.game.snake.SnakeDrawListener
import com.example.game.snake.SnakePosition
import com.example.game.snake.arena.Arena
import com.example.game.snake.arena.ArenaState
import com.example.game.snake.arena.IArenaDrawListener
import com.example.game.snake.arena.SorteSize
import com.example.game.snake.utils.updateArena
import com.example.game.snake.utils.updateSnake
import com.example.game.snake.utils.updateSnakeSize
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    SnakeDrawListener,
    View.OnClickListener,
    IArenaDrawListener{


    private val snake = Snake(this)
    private lateinit var arena : Arena

    private lateinit var snakePosition: List<RelativeLayout>
    private lateinit var arenaState: List<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        left.setOnClickListener(this)
        right.setOnClickListener(this)

        initGame()
    }

    private fun initGame() {
        snakePosition = listOf(snakleft, snakmiddle, snakright)
        arenaState = listOf(topArena, midArena, bottomArena)
        arena = Arena(this, this)
    }


    /**
     * Snake Drawer listener
     */
    override fun onUpdateSnake(newPosition: SnakePosition) {
        updateSnake(newPosition, snakePosition)
    }
    override fun onUpdateSnakeSize(snakeSize: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * End region
     */


    /**
     * Arena Drawer listener
     */
    override fun onUpdateArena(newState: ArenaState) {
        runOnUiThread {
            updateArena(newState, arenaState, this)
        }
    }


    override fun onArenaBottom(s: SorteSize, initGame: Boolean) {
        updateSnakeSize(s, snake, snakePosition, initGame){
            arena.gameOver()
        }
    }

    /**
     * End region
     */


    ///////////////////////////
    override fun onClick(p0: View?) {
      when(p0?.id){
          R.id.left ->{
              snake.updatePosition(SnakePosition.SnakeLeft)
          }
          R.id.right ->{
              snake.updatePosition(SnakePosition.SnakeRight)
          }
      }
    }
}
