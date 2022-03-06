package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val ivTrophy:ImageView=findViewById(R.id.ivTrophy)
        val tvName:TextView=findViewById(R.id.tvName)
        val tvScore:TextView=findViewById(R.id.tvScore)
        val btnFinish: Button =findViewById(R.id.btnFinish)

        ivTrophy.setImageResource(R.drawable.trophy)

        tvName.text=intent.getStringExtra(Constants.USER_NAME)
        tvScore.text="You got "+intent.getIntExtra(Constants.CORRECT_ANSWERS,0).toString()+" out of "+intent.getIntExtra(Constants.TOTAL_QUESTIONS,0).toString()+"! Great job!"

        btnFinish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}