package com.example.game.treinamento

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import com.example.game.snake.utils.logT
import com.example.game.database.entities.EventToDraw
import com.example.game.database.entities.Draw


class CustomView @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    interface IReplay{
        fun onReplayChange(enable: Boolean)
    }

    private val list: ArrayList<EventToDraw> = ArrayList()
    private var COLOR: Int = Color.RED
    private var SIZE: Int = 15
    private lateinit var verticalSeekBar: VerticalSeekBar
    private var onDraw: IOnDraw? = null
    private var newSize = 0
    private var replayEnable = false
    private var iReplay: IReplay? = null
    private var drawsArray: ArrayList<Int> = ArrayList()


    var paint: Paint = Paint().apply {
        isAntiAlias = true
        color = COLOR
        style = Paint.Style.FILL_AND_STROKE

    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        list.forEach {
            paint.color = it.color
            canvas?.drawCircle(it.x, it.y, it.size.toFloat(), paint)
        }
    }

    override fun setOnTouchListener(l: OnTouchListener?) {
        super.setOnTouchListener(l)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        logT(event?.action.toString())

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                list.add(EventToDraw(event.x, event.y, COLOR, SIZE))
                newSize = list.size
                drawsArray.add(newSize)
                invalidate()
            }
            MotionEvent.ACTION_MOVE->{
                list.add(EventToDraw(event.x, event.y, COLOR, SIZE))
                invalidate()
            }
            MotionEvent.ACTION_UP->{
                changeReply(true)
            }
        }
        onDraw?.onUpdate()
        return true
    }

    /**
     * Redraw
     */
//    fun redraw(){
//        val removeList: ArrayList<EventToDraw> = ArrayList()
//        for(i in newSize..list.size){
//            removeList.add(list[i - 1 ])
//        }
//        removeList.forEach {
//            list.remove(it)
//        }
//        changeReply(false)
//        invalidate()
//    }
    fun redraw(){
        val removeList: ArrayList<EventToDraw> = ArrayList()
        val size = drawsArray[drawsArray.size - 1]
        for(i in size.. list.size){
            removeList.add(list[i - 1 ])
        }
        removeList.forEach {
            list.remove(it)
        }
        drawsArray.remove(drawsArray.size - 1)
        if(drawsArray.isEmpty()){
            changeReply(false)
        }
        invalidate()
    }

    /**
     * Clear points to clean screen
     */
    fun clear() {
        list.clear()
        invalidate()
    }

    /**
     * Get list of points
     *
     */
    fun getDrawList(): MutableList<EventToDraw>{
        return list.toMutableList()
    }
    /**
     * Get draw
     */

    fun getDraw(): Draw{
        return Draw(0, list, COLOR)
    }

    /**
     * Update list of points to draw
     */
    fun setDraw(newList: Draw?){
        list.clear()
        list.addAll(newList!!.listPoints)
        invalidate()
    }

    /**
     * Change and set color of PAINT
     */
    fun updateColor(color: Int) = run { COLOR = color }
    fun getColor() = COLOR

    /**
     * To enable seekbar to change size of paint
     */
    fun setSeekBar(seekBar: VerticalSeekBar){
        verticalSeekBar = seekBar
        verticalSeekBar.visibility = VISIBLE
        verticalSeekBar.max = 30
        verticalSeekBar.progress = SIZE
        verticalSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                p0?.let{
                    SIZE = it.progress
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) { }
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }

    /**
     * Update listener to raceive updates of view
     */
    fun setOnDrawListener(onDraw: IOnDraw){
        this.onDraw = onDraw
    }

    interface IOnDraw{
        fun onUpdate()
    }

    fun setReplayChangedListener(iReplay: IReplay){
        this.iReplay = iReplay
    }

    fun changeReply(enable: Boolean){
        replayEnable = enable
        iReplay?.onReplayChange(replayEnable)
    }

}