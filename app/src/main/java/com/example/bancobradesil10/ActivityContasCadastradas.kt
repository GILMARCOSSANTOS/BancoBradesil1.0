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

        /**
         *  FUNÇÃO BLOQUEAR ORIENTAÇÃO DE TELA:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /**
         * Declaração de funcões:
         */
        criarFuncoes()
    }

    private fun textViewVoltarActivityContasCadastradas() {
        // val intent = Intent(this, MainActivity::class.java).apply {
        // }
        // startActivity(intent)
        finish()

    }

    private fun imageButtonVoltarContasCadastradasId() {
        // val intent = Intent(this, MainActivity::class.java).apply {
        //  }
        //  startActivity(intent)
        finish()
    }

    private fun criarFuncoes() {
        val imageButtonVoltar02 = findViewById<ImageButton>(R.id.imgBtton_voltar_contasCadastradas_id)
        val textViewVoltar02 = findViewById<TextView>(R.id.txtVw_voltar_contasCadastradas_id)

        imageButtonVoltar02.setOnClickListener { imageButtonVoltarContasCadastradasId() }
        textViewVoltar02.setOnClickListener { textViewVoltarActivityContasCadastradas() }
    }

}