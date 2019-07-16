package com.example.game.treinamento

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game.R
import com.example.game.database.entities.Draw
import com.example.game.randomColor
import com.example.game.snake.utils.logT
import com.example.game.view.ColorAdapter
import com.example.game.view.TrainingViewModel
import kotlinx.android.synthetic.main.activty_training.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainingActivy : AppCompatActivity(), CustomView.IOnDraw, CustomView.IReplay {

    private val viewModel by viewModel<TrainingViewModel>()
    private lateinit var menuReplyItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_training)
        customView.setSeekBar(seekBar)
        customView.setOnDrawListener(this)
        customView.setReplayChangedListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        viewModel.createAdapter()
    }

    override fun onResume() {
        super.onResume()
        observeValues()
    }

    override fun onUpdate() {
        viewModel.updateDraw(customView.getDraw())
    }

    private fun observeValues() {
        viewModel.draw().observe(this, Observer {d->
            d?.let {
                logT("size: ${it.listPoints.size}")

                customView.setDraw(it)
            }
        })
        viewModel.staticDraw().observe(this, Observer {
            logT("${lifecycle.currentState}")
            if(lifecycle.currentState == Lifecycle.State.STARTED){
                customView.setDraw(it)
            }
        })
        viewModel.adapter().observe(this, Observer {adapter->
            adapter.setOnClickListener {color->
                customView.updateColor(color)
            }
            recyclerView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu, menu)
        menuReplyItem = menu!!.findItem(R.id.reply)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clea -> {
                customView.clear()
            }
            R.id.save -> {
                viewModel.save((Draw(0, listPoints = customView.getDrawList(), color = customView.getColor())))
            }
            R.id.color ->{
                customView.updateColor(randomColor())
            }
            R.id.reply->{
                customView.redraw()
            }
        }
        return true
    }
    override fun onReplayChange(enable: Boolean) {
        menuReplyItem.isVisible = enable
    }

}