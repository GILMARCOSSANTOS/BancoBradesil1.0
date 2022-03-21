package com.example.bancobradesil10

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {

    /**
     * Variáveis em Escopo Global:
     */
    private lateinit var imageViewVoltar01: ImageView
    private lateinit var textViewVoltar01: TextView
    private lateinit var botaoContinuarActivityLogin: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextInformeSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /**
         * Bloquear a função de orientação de tela:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /**
         *  Função para declaração de variáveis:
         */
        inicializarVariaveis()

        /**
         * Criar Funções:
         */
        inicializarFuncoes()

    }

    private fun botaoContinuar() {
        //Reconhecer usuário atual e fazer LOGIN no Firebase:

        if (editTextInformeSenha.text.toString().isEmpty()) {
            val snackBar = Snackbar.make(
                botaoContinuarActivityLogin,
                "Digite a sua senha.",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        } else {
            autenticarLoginUsuario()

        }
    }

    private fun autenticarLoginUsuario() {
        val senha = editTextInformeSenha.text.toString()
        val email = editTextEmail.text.toString()

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, ActivityConta::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    val snackbar = Snackbar.make(
                        botaoContinuarActivityLogin,
                        "Senha ou e e-mail incorretos.",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                }
            }
    }

    private fun imageViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        finish()
        startActivity(intent)
    }

    private fun textViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun inicializarVariaveis() {
        imageViewVoltar01 = findViewById(R.id.imageViewVoltarLoginId)
        textViewVoltar01 = findViewById(R.id.textViewVoltarLoginId)
        botaoContinuarActivityLogin = findViewById(R.id.botaoAContinuarActivityLoginId)
        editTextInformeSenha = findViewById(R.id.editTextQualSenhaLoginId)
        editTextEmail = findViewById(R.id.editTextQualEmailLoginId)
    }

    private fun inicializarFuncoes() {
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }
    }
}