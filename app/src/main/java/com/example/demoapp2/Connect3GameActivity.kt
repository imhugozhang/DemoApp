package com.example.demoapp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.widget.GridLayout
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_connect3_game.*

class Connect3GameActivity : AppCompatActivity() {
    //blank is 0, yellow is 1, red is 2
    var activePlayer: Int = 1
    val winningPositions = listOf(
        listOf(0,1,2), listOf(3,4,5), listOf(6,7,8),
        listOf(0,3,6), listOf(1,4,7), listOf(2,5,8),
        listOf(0,4,8), listOf(2,4,6)
    )
    var gameIsActive:Boolean = true

    fun createInitGameState (numberOfSpace:Int, gameState: MutableList<Int> = mutableListOf()): MutableList<Int>{
        for (i in 0 until numberOfSpace){
            gameState.add(0)
        }
        return gameState
    }
    var gameState: MutableList<Int> = createInitGameState(9)

    fun dropIn(view:View) {
        val counter: ImageView = view as ImageView
        var tappedCounter: Int = counter.tag.toString().toInt()
        if (gameState[tappedCounter] == 0 && gameIsActive) {
            gameState[tappedCounter] = activePlayer
            counter.translationY = -1000f
            activePlayer = if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow)
//                counter.setBackgroundResource(R.drawable.yellow)
                2
            } else {
                counter.setImageResource(R.drawable.red)
//                counter.setBackgroundResource(R.drawable.red)
                1
            }
            counter.animate().translationYBy(1000f).duration = 300
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 0
                ) {
                    var winnerNumber: Int = gameState[winningPosition[0]]
                    // Someone has won, or draw
                    gameIsActive = false
                    var winner: String = if (winnerNumber == 1) "Yellow" else "Red"
                    val endGameTextView: TextView = findViewById(R.id.endGameTextView)
                    endGameTextView.text = "$winner has won!"
                    val layout: LinearLayout = findViewById(R.id.playAgainLayout)
                    if (layout.visibility == View.INVISIBLE) {
                        layout.translationY = -1000f
                        layout.visibility = View.VISIBLE
                        layout.animate().translationYBy(1000f).duration = 300
                    }
                } else {
                    var gameIsOver: Boolean = true
                    for (counterState in gameState) {
                        if (counterState == 0) gameIsOver = false
                    }
                    if (gameIsOver) {
                        endGameTextView.text = "It is a draw"
                        val layout: LinearLayout = findViewById(R.id.playAgainLayout)
                        if (layout.visibility == View.INVISIBLE) {
                            layout.translationY = -1000f
                            layout.visibility = View.VISIBLE
                            layout.animate().translationYBy(1000f).duration = 300
                        }
                    }
                }
            }
        }
    }

    fun startGame(view:View){
        gameIsActive = true
        val layout: LinearLayout = findViewById(R.id.playAgainLayout)
        if(layout.visibility == View.VISIBLE)
            layout.visibility = View.INVISIBLE
        activePlayer = 1
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in 0 until gridLayout.childCount){
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0)
        }
        gameState = createInitGameState(9)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect3_game)
    }
}
