package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        BLOQUEAR ORIENTAÇÃO DE TELA:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /*
        Teste:
         */

        val textViewNomeConta = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
        val textViewNumeroConta = findViewById<TextView>(R.id.textViewNumeroContaMainActivityId)

        val mensagemNome = intent.getStringExtra("chaveNomeConta")
       val mensagemConta = intent.getStringExtra("chaveNumeroConta")


       textViewNomeConta.apply {

           text = mensagemNome
       }

        textViewNumeroConta.apply {
            text = mensagemConta
        }








        /*
        CRIAR FUNÇÃO FUNCAOACESSARCONTA():
         */
        val botaoAcessarConta = findViewById<MaterialButton>(R.id.acessarContaBotaoMainActivityId)
        botaoAcessarConta.setOnClickListener { acessarConta() }

        /*
        CRIAR FUNÇÃO CRIARCONTA():
         */
        val botaoCriarconta = findViewById<MaterialButton>(R.id.botaoCriarContaMainActivityId)
        botaoCriarconta.setOnClickListener { criarConta() }


        /*
        CRIAR FUNÇÃO CADASTRARCONTAS:
         */
        val imageButtonContasCadastradas =
            findViewById<ImageButton>(R.id.imageButtonContasCadastradasMainActivityId)
        imageButtonContasCadastradas.setOnClickListener { cadastrarContas() }
    }

    /*
    FUNÇÃO CADASTRARCONTAS ():
     */
    private fun cadastrarContas() {
        val intent = Intent1(this, ActivityContasCadastradas::class.java).apply {
        }
        startActivity(intent)
    }

    /*
    FUNÇÃO CRIARCONTA():
     */
    private fun criarConta() {
        val intent = Intent1(this, ActivityCriarConta::class.java).apply {
        }
        startActivity(intent)
    }

    /*
    FUNÇÃO ACESSAR CONTA():
     */
    private fun acessarConta() {


    }

}





