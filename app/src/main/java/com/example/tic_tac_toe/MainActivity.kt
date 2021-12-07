package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var X:String=""
    var O:String=""
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         X = intent.getStringExtra("X").toString()
         O = intent.getStringExtra("O").toString()
        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board)
        {
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            TURN_COUNT = 0
            initializeBoardStatus()
            PLAYER = true
            updateDisplay("Player X Turn")
            //updateDisplay("Player $X Turn")
        }




    }



    override fun onClick(v: View) {
        when(v.id){
            R.id.button1->{
                updateValue(0,0,PLAYER)
            }
            R.id.button2->{
                updateValue(0,1,PLAYER)
            }
            R.id.button3->{
                updateValue(0,2,PLAYER)
            }
            R.id.button4->{
                updateValue(1,0,PLAYER)
            }
            R.id.button5->{
                updateValue(1,1,PLAYER)
            }
            R.id.button6->{
                updateValue(1,2,PLAYER)
            }
            R.id.button7->{
                updateValue(2,0,PLAYER)
            }
            R.id.button8->{
                updateValue(2,1,PLAYER)
            }
            R.id.button9->{
                updateValue(2,2,PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER)
        {
            //updateDisplay("Player X Turn")
            updateDisplay("Player $X Turn")
        }
        else
        {
            //updateDisplay("Player O Turn")
            updateDisplay("Player $O Turn")
        }
        if(TURN_COUNT == 9)
        {
            updateDisplay("GAME DRAW")
            disableButton()
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal Rows
        for(i in 0..2)
        {
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2])
            {
                if(boardStatus[i][0] == 1)
                {
                    updateDisplay("Player X Winner")
                    //updateDisplay("Player $X Winner")
                    disableButton()
                    break
                }
                else if(boardStatus[i][0] == 0)
                {
                    updateDisplay("Player O Winner")
                    //updateDisplay("Player $O Winner")
                    disableButton()
                    break
                }
            }
        }
        // Vertical Columns
        for(i in 0..2)
        {
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i])
            {
                if(boardStatus[0][i] == 1)
                {
                    updateDisplay("Player X Winner")
                    ////updateDisplay("Player $X Winner")
                    disableButton()
                    break
                }
                else if(boardStatus[0][i] == 0)
                {
                    updateDisplay("Player O Winner")
                    //updateDisplay("Player $O Winner")
                    disableButton()
                    break
                }
            }
        }
        // Diagonals
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2])
        {
            if(boardStatus[0][0] == 1)
            {
                updateDisplay("Player X Winner")
                //updateDisplay("Player $X Winner")
                disableButton()
            }
            else if(boardStatus[0][0] == 0)
            {
                updateDisplay("Player O Winner")
                //updateDisplay("Player $O Winner")
                disableButton()
            }
        }
        else{
            if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0])
            {
                if(boardStatus[1][1] == 1)
                {
                    updateDisplay("Player X Winner")
                    //updateDisplay("Player $X Winner")
                    disableButton()
                }
                else if(boardStatus[1][1] == 0)
                {
                    updateDisplay("Player O Winner")
                    //updateDisplay("Player $O Winner")
                    disableButton()
                }
            }
        }


    }

    private fun disableButton() {
        for(i in board)
        {
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        DisplayTv.text = s
    }

    private fun initializeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardStatus[row][col] = value


    }
}