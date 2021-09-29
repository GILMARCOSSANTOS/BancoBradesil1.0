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
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /*
        Criar Função imageViewVoltarActivityContasCadastradas():
         */
        val imageButtonVoltar02 =
            findViewById<ImageView>(R.id.imageButtonVoltarContasCadastradasId)
        imageButtonVoltar02.setOnClickListener { imageButtonVoltarContasCadastradasId() }

        /*
       Criar Função textViewVoltarActivityContasCadastradas():
         */
        val textViewVoltar02 = findViewById<TextView>(R.id.textViewVoltarContasCadastradasId)
        textViewVoltar02.setOnClickListener { textViewVoltarActivityContasCadastradas() }
    }

    /* override fun onSaveInstanceState(outState: Bundle) {
         super.onSaveInstanceState(outState)
         outState.putStringArrayList("nomes", nomes)
     }*/

    /*
   Função textViewVoltarActivityContasCadastradas():
  */
    private fun textViewVoltarActivityContasCadastradas() {
        // val intent = Intent(this, MainActivity::class.java).apply {
        // }
        // startActivity(intent)
        finish()

    }

    /*
        Função imageViewVoltarActivityContasCadastradas():
     */

    private fun imageButtonVoltarContasCadastradasId() {
        // val intent = Intent(this, MainActivity::class.java).apply {
        //  }
        //  startActivity(intent)
        finish()

    }
}