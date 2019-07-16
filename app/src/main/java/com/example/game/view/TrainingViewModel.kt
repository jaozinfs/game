package com.example.game.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game.database.entities.Draw
import com.example.game.snake.utils.logT

import com.example.game.usecase.DrawUseCase
import kotlinx.coroutines.launch


class TrainingViewModel(private val drawUseCase: DrawUseCase) : ViewModel(){
    private var _draw: MutableLiveData<Draw> = MutableLiveData()
    fun draw() : LiveData<Draw> = _draw

    private var _adapter: MutableLiveData<ColorAdapter> = MutableLiveData()
    fun adapter(): LiveData<ColorAdapter> = _adapter


    private var _staticDraw: MutableLiveData<Draw> = MutableLiveData()
    fun staticDraw() : LiveData<Draw> = _staticDraw

    init {
        load()
    }


    fun save(draw: Draw) = viewModelScope.launch{
        drawUseCase.insertDraw(draw)
    }

    fun load() = viewModelScope.launch{
        logT("LOAD")
        _draw.value = drawUseCase.getDraw()
    }

    fun updateDraw(draw: Draw){
        _staticDraw.value = draw
    }

    fun createAdapter() {
        logT("here")
        if(_adapter.value != null){
            return
        }
        _adapter.value = ColorAdapter()
    }

}