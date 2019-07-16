package com.example.game.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.R
import kotlinx.android.synthetic.main.color_adapter_holder.view.*

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>(){

    private var clickedColor:Int = 0
    private var clickListener: ((Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.color_adapter_holder, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView()
    }

    fun setOnClickListener(clickListener: (Int)->Unit){
        this.clickListener = clickListener
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bindView(){
            view.colorView.setCircleColorClickListener {
                clickListener?.invoke(it)
            }
        }
    }
}