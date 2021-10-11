package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*
        Bloquear a função de orientação de tela:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /*
        Recepção dos dados (NOME E CONTA) enviados por MainActivity:
         */
        val nomeActivityActivityLogin =
            findViewById<TextView>(R.id.textViewNomeClienteActivityLoginId)
        val numeroContaActivityLogin =
            findViewById<TextView>(R.id.textViewNumeroContaLoginActivityId)

        val mensagemNomeActivityLogin = intent.getStringExtra("chaveNomeConta")
        val mensagemContaActivityLogin = intent.getStringExtra("chaveNumeroConta")

        nomeActivityActivityLogin.apply {
            text = mensagemNomeActivityLogin
        }
        numeroContaActivityLogin.apply {
            text = mensagemContaActivityLogin
        }

        /*
        Criar Função imageViewVoltarActivityLogin():
         */
        val imageViewVoltar01 = findViewById<ImageView>(R.id.imageViewVoltarLoginId)
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }

        /*
        Criar função textViewVoltarActivityLogin():
         */
        val textViewVoltar01 = findViewById<TextView>(R.id.textViewVoltarLoginId)
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }

        val botaoContinuarActivityLogin = findViewById<Button>(R.id.botaoAContinuarActivityLoginId)
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
    }

    /*
    Função botaoContinuar():
     */
    private fun botaoContinuar() {
        //Declaração das variáveis:
        val qualSenha = findViewById<EditText>(R.id.editTextQualSenhaLoginId)
        val mensagemConta = intent.getStringExtra("chaveSenha")
        val nomeActivityLogin = findViewById<TextView>(R.id.textViewNomeClienteActivityLoginId)

       qualSenha.apply {
        }
        if (qualSenha.text.toString() == mensagemConta.toString()){
            val intent = Intent(this, ActivityConta::class.java).apply {
                putExtra("chaveNomeConta", nomeActivityLogin.text.toString())
            }
            startActivity(intent)

        } else{
            Toast.makeText(this, "Senha Errada!", Toast.LENGTH_LONG).show()
        }
    }

    /*
    Função imageViewVoltarActivityLogin():
     */
    private fun imageViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
       // startActivity(intent)
        finish()
    }

    /*
    Função textViewVoltarActivityLogin():
    */
    private fun textViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
       // startActivity(intent)
        finish()
    }
}