package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ActivityContasCadastradas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas_cadastradas)

        /*
        FUNÇÃO BLOQUEAR ORIENTAÇÃO DE TELA:
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /*
        IMAGEBUTTON VOLTAR À TELA INICIAL:
         */
        val imageButtonVoltar02 = findViewById<ImageView>(R.id.imageViewVoltarId02)
        imageButtonVoltar02.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
        /*
        TEXTVIEW VOLTAR À TELA INICIAL:
         */
        val textViewVoltar02 = findViewById<TextView>(R.id.textViewVoltarId02)
        textViewVoltar02.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }
}