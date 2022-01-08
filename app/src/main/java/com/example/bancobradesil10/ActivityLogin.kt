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
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {

    /*
      Variáveis em Escopo Global:
       */
    private lateinit var textViewNumeroContaActivityLogin: TextView
    private lateinit var imageViewVoltar01: ImageView
    private lateinit var textViewVoltar01: TextView
    private lateinit var botaoContinuarActivityLogin: Button
    private lateinit var editTextInformeSenha: EditText
    private lateinit var textViewNomeActivityLogin: TextView
    private lateinit var textViewEmailActivityLogin: TextView
    private lateinit var editTexTEmail: EditText

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
        textViewNomeActivityLogin =
            findViewById(R.id.textViewNomeCliente02ActivityLoginId)
        textViewNumeroContaActivityLogin =
            findViewById(R.id.textViewConta02ActivityLoginId)
        imageViewVoltar01 = findViewById(R.id.imageViewVoltarLoginId)
        textViewVoltar01 = findViewById(R.id.textViewVoltarLoginId)
        botaoContinuarActivityLogin = findViewById(R.id.botaoAContinuarActivityLoginId)
        textViewEmailActivityLogin = findViewById(R.id.textViewEmail2ActivityLoginId)
        editTextInformeSenha = findViewById(R.id.editTextQualSenhaLoginId)
        editTexTEmail = findViewById(R.id.editTextQualEmailLoginId)

        /*
        Criar Funções:
         */
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }

        /**
         * Shared Preferences: Recebimentos dos dados da ActivityCriarConta:
         */
        val nomeUsuarioShared = intent.getStringExtra("chaveNome")
        textViewNomeActivityLogin.apply {
            text = nomeUsuarioShared
        }
        val emailUsuarioShared = intent.getStringExtra("chaveEmail")
        textViewEmailActivityLogin.apply {
            text = emailUsuarioShared
        }
        val contaUsuarioshared = intent.getStringExtra("chaveConta")
        textViewNumeroContaActivityLogin.apply {
            text = contaUsuarioshared
        }

        /*
    Função botaoContinuar():
     */
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
            val email = editTexTEmail.text.toString()

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, ActivityConta::class.java)
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

        /*
        Função imageViewVoltarActivityLogin():
         */
        private fun imageViewVoltarActivityLogin() {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
            finish()
        }

        /*
        Função textViewVoltarActivityLogin():
        */
        private fun textViewVoltarActivityLogin() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }