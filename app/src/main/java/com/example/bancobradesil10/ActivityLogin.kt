package com.example.bancobradesil10

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlin.time.toDuration

class ActivityLogin : AppCompatActivity() {

    /**
     * Variáveis em Escopo Global:
     */
    private lateinit var imageViewVoltar01: ImageView
    private lateinit var textViewVoltar01: TextView
    private lateinit var botaoContinuarActivityLogin: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextInformeSenha: EditText
    private lateinit var progressBar: LinearProgressIndicator
    private lateinit var notificacao: TextView

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

        if (editTextEmail.text.isEmpty()) {

            notificacao.setText(R.string.AnalisandoDados)

            progressBar.progress = 0
            var currentProgress = 50
            ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
                .setDuration(1000)
                .start()

            Handler().postDelayed({

                notificacao.setText(R.string.ErroDigiteEmail)

            }, 1000)

        } else if (editTextInformeSenha.text.isEmpty()) {
            notificacao.setText(R.string.AnalisandoDados)

            progressBar.progress = 0
            var currentProgress = 50
            ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
                .setDuration(1000)
                .start()

            Handler().postDelayed({
                notificacao.setText(R.string.ErroDigiteSenha)

            }, 1000)

        } else {
            autenticarLoginUsuario()
        }

    }

    private fun autenticarLoginUsuario() {
        val senha = editTextInformeSenha.text.toString()
        val email = editTextEmail.text.toString()

        notificacao.setText(R.string.AnalisandoDados)

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    progressBar.progress = 100
                    val currentProgress = 0
                    ObjectAnimator.ofInt(progressBar, "progress", currentProgress)
                        .setDuration(5000)
                        .start()

                    val intent = Intent(this, ActivityConta::class.java)
                    finish()
                    startActivity(intent)
                } else {
                   notificacao.setText(R.string.ErroSenhaConta)
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
        progressBar = findViewById(R.id.prgrssBar_actvtLogin_id)
        notificacao = findViewById(R.id.txtVw_notificacao_LoginActvt_id)
    }

    private fun inicializarFuncoes() {
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }
    }
}