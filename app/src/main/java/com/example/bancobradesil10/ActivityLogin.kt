package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class ActivityLogin : AppCompatActivity() {

    /*
      Variáveis em Escopo Global:
       */
    private lateinit var nomeActivityActivityLogin: TextView
    private lateinit var numeroContaActivityLogin: TextView
    private lateinit var imageViewVoltar01: ImageView
    private lateinit var textViewVoltar01: TextView
    private lateinit var botaoContinuarActivityLogin: Button
    private lateinit var qualSenha: EditText
    private lateinit var nomeActivityLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*
        Bloquear a função de orientação de tela:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /*
        Declaração de variáveis:
         */
        nomeActivityActivityLogin =
            findViewById<TextView>(R.id.textViewNomeClienteActivityLoginId)
        numeroContaActivityLogin =
            findViewById<TextView>(R.id.textViewNumeroContaLoginActivityId)
        imageViewVoltar01 = findViewById<ImageView>(R.id.imageViewVoltarLoginId)
        textViewVoltar01 = findViewById<TextView>(R.id.textViewVoltarLoginId)
        botaoContinuarActivityLogin = findViewById<Button>(R.id.botaoAContinuarActivityLoginId)

        /*
        Criar Funções:
         */
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }
    }

    /*
    Função botaoContinuar():
     */
    private fun botaoContinuar() {
        //Declaração das variáveis:
        val mensagemConta = intent.getStringExtra("chaveSenha")

        if (qualSenha.text.toString().isEmpty()) {
            val snackBar = Snackbar.make(
                botaoContinuarActivityLogin,
                "Digite a sua senha.",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
        if (qualSenha.text.toString() != mensagemConta.toString()) {
            val snackBar =
                Snackbar.make(botaoContinuarActivityLogin, "Senha Errada!", Snackbar.LENGTH_LONG)
            snackBar.show()
        }
        if (qualSenha.text.toString() == mensagemConta.toString()) {
            val intent = Intent(this, ActivityConta::class.java).apply {

            }
            startActivity(intent)
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