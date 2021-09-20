package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val imageViewVoltar01 = findViewById<ImageView>(R.id.imageViewVoltarId01)
        imageViewVoltar01.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java).apply {

            }
            startActivity(intent)

        }

        val textViewVoltar01 = findViewById<TextView>(R.id.textViewVoltarId01)
        textViewVoltar01.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java).apply {

            }
            startActivity(intent)

        }



    }
}