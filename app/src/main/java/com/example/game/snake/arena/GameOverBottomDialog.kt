package com.example.game.snake.arena

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.game.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment




open class GameOverBottomDialog : BottomSheetDialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.game_over_dialog, container,
            false
        )
    }
}