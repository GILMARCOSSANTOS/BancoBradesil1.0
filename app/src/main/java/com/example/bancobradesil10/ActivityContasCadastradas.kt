package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


class ActivityContasCadastradas : AppCompatActivity() {

    /**
     * Declaração de Variáveis em Escopo Global:
     */
//    private lateinit var imageButtonVoltar: ImageView
//    private lateinit var textViewVoltar: TextView

    /**
     * Declaração de variáveis em Escopo Global:
     */
    private lateinit var imageViewVoltar: ImageView
    private lateinit var textViewVoltar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas_cadastradas)

        /**
         *  FUNÇÃO BLOQUEAR ORIENTAÇÃO DE TELA:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /**
         * Inicializar Variáveis:
         */
       inicializarVarias()

        /**
         * Criar Funçõoes:
         */
       inicializarFuncoes()
    }

    private fun textViewVoltarContasCadastradas() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        finish()
        startActivity(intent)
    }

    private fun imageViewVoltarContasCadastradas() {
       textViewVoltarContasCadastradas()
    }

    private fun inicializarVarias(){
        textViewVoltar = findViewById(R.id.txtVw_voltar_contasCadastradas_id)
        imageViewVoltar = findViewById(R.id.imgVw_voltar_contasCadastradas_id)
    }

    private fun inicializarFuncoes(){
        textViewVoltar.setOnClickListener { textViewVoltarContasCadastradas() }
        imageViewVoltar.setOnClickListener { imageViewVoltarContasCadastradas() }
    }
}