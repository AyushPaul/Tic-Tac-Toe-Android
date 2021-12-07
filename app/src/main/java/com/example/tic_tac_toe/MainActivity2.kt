package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            //i.putExtra("X", textView1.getText().toString())
            //i.putExtra("O", textView2.getText().toString())
            var X:String = editTextTextPersonName.text.toString()
            var O:String = editTextTextPersonName2.text.toString()
            i.putExtra("X","$X")
            i.putExtra("O","$O")
            startActivity(i)
        }
    }
}