package com.example.bancobradesil10

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityContasCadastradas : AppCompatActivity() {

    /**
     * Variáveis em Escopo global:
     */
    private lateinit var imageButtonVoltar02: ImageView
    private lateinit var textViewVoltar02: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas_cadastradas)

        /*
        FUNÇÃO BLOQUEAR ORIENTAÇÃO DE TELA:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /**
         * Declaração de variáveis:
         */
        textViewVoltar02 = findViewById(R.id.textViewVoltarContasCadastradasId)
        imageButtonVoltar02 = findViewById(R.id.imageButtonVoltarContasCadastradasId)


        /*
        Criar Funções:
         */

        textViewVoltar02.setOnClickListener { textViewVoltarActivityContasCadastradas() }
        imageButtonVoltar02.setOnClickListener { imageButtonVoltarContasCadastradasId() }
    }

    /*
   Função textViewVoltarActivityContasCadastradas():
  */
    private fun textViewVoltarActivityContasCadastradas() {
        finish()
    }

    /**
     * Função imageViewVoltarActivityContasCadastradas():
     */

    private fun imageButtonVoltarContasCadastradasId() {
        finish()
    }
}