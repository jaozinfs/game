package com.example.game.treinamento

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import com.example.game.R
import com.example.game.randomColor
import com.example.game.snake.utils.logT


class CircleColor @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr), View.OnClickListener {

    private lateinit var circleClickListener: (Int)->Unit
    private var COLOR: Int = 0

    init {
        setOnClickListener(this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircleColor, 0, 0)
            val color = R.styleable.CircleColor_circleColor
            if(typedArray.hasValue(color)){
                COLOR = typedArray.getInt(color, randomColor())
            }else{
                COLOR = randomColor()
            }
            typedArray.recycle()
        }
    }
    var paint: Paint = Paint().apply {
        isAntiAlias = true
        color = COLOR
        style = Paint.Style.FILL_AND_STROKE
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            Math.min(width/ 2,height / 2).toFloat(),
            paint)
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        super.setOnTouchListener(l)
    }
    fun setCircleColorClickListener(circleClickListener: (Int)->Unit){
        this.circleClickListener = circleClickListener
    }
    override fun onClick(p0: View?) {
        circleClickListener(COLOR)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }


}