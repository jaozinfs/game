package com.example.game.snake.arena

import android.view.View
import com.example.game.snake.utils.logT
import java.util.*
import kotlin.concurrent.fixedRateTimer
import android.widget.LinearLayout
import android.R
import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.game.snake.utils.fadeIn
import com.example.game.snake.utils.fadeOutWithFadeIn
import kotlinx.android.synthetic.main.activity_main.*


class Arena(private val iArenaDrawListener: IArenaDrawListener,
            private val view: Activity
){
    private var initGame = false
    private var endScale = false
    private lateinit var s: SorteSize
    private var gameOver = false


    init {

        measureView()
    }

    private fun measureView(){
        createGameSizes(newSorteSize = true, initGame = initGame)
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun createGameSizes(newSorteSize: Boolean = false, initGame: Boolean = false) {
        val v = view.arenaSonOne
        val v1 = view.arenaSonTwo
        val v2 = view.arenaSonThree

        if (newSorteSize) {
            s = genereteRandom()
            verifySomeBlockIsNegative(s)
            view.randomOne.text = s.sorteSize[0].toString()
            view.randomTwo.text = s.sorteSize[1].toString()
            view.randomThree.text = s.sorteSize[2].toString()
        }


        if (!endScale) {
            val metric = DisplayMetrics()
            view.windowManager.defaultDisplay.getMetrics(metric)
            endScale = true
            ObjectAnimator.ofFloat(v, "translationY", view.window.decorView.height * 75 / 100F).apply {
                duration = 2000
//                    repeatMode = 0
                start()
            }.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {}
                override fun onAnimationEnd(p0: Animator?) {
                    animeViewsToRestart(mutableListOf(v, v1, v2), initGame)
                }

                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationStart(p0: Animator?) {}

            })
            ObjectAnimator.ofFloat(v1, "translationY", view.window.decorView.height * 75 / 100F).apply {
                duration = 2000
                start()
            }
            ObjectAnimator.ofFloat(v2, "translationY", view.window.decorView.height * 75 / 100F).apply {
                duration = 2000
                start()
            }
        }
    }

    private fun verifySomeBlockIsNegative(s: SorteSize) {
        var ifHaveNegative = false
        var negativePosition = 0
        for (i in s.sorteSize) {
            if(s.sorteSize[i] < 0){
                ifHaveNegative = true
                negativePosition = i+1
                break
            }
        }
        if(!ifHaveNegative){
            return
        }
        when(negativePosition){
            1->{
                view.arenaSonOne.setBackgroundColor(Color.RED)

//                view.arenaSonThree.setBackgroundColor(Compat)
                view.arenaSonTwo.setBackgroundColor(Color.RED)
            }

            2->view.arenaSonTwo.setBackgroundColor(Color.RED)
            3->view.arenaSonThree.setBackgroundColor(Color.RED)
        }
    }

    fun animeViewsToRestart(views: MutableList<View>, initGame: Boolean = false){
        iArenaDrawListener.onArenaBottom(s, initGame)
        views[0].fadeOutWithFadeIn{
            views[0].y = 0F
            views[0].fadeIn {
                if(gameOver){
                    endScale = false

                    gameOver()

                    return@fadeIn
                }
                endScale = false
                createGameSizes(true)
            }
        }
        views[1].fadeOutWithFadeIn{
            views[1].y = 0F
            views[1].fadeIn()
        }
        views[2].fadeOutWithFadeIn{
            views[2].y = 0F
            views[2].fadeIn()
        }
    }

    var difficult = 0

    fun genereteRandom(): SorteSize{
        val random = Random()
        var value1 = random.nextInt(100)
        var value2 = random.nextInt(100)
        var value3 = random.nextInt(100)
        if(difficult > 3){
            val negativePosition = random.nextInt(3)
            when (negativePosition) {
                1 -> {
                    value1 = -Math.abs(value1)
                }
                2 -> {
                    value2 = -Math.abs(value1)
                }
                else -> {
                    value3 = -Math.abs(value1)
                }
            }
        }

        //incres difficult
        difficult++
        return SorteSize(listOf(value1,value2, value3))
    }


    /**
     *
     * Game over animation
     */
    fun gameOver(){
        gameOver = true
        val viewOwner = view
        GameOverBottomDialog().apply {
            show((viewOwner as AppCompatActivity).supportFragmentManager, "gameOver")
        }

    }

}