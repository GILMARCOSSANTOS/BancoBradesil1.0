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

        /**
         * Bloquear a função de orientação de tela:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /**
         *  Função para declaração de variáveis:
         */
        declararVariaveis()

        /**
         * Criar Funções:
         */
        criarFuncoes()

        /**
         * Função Shared Preferences: Recebimentos dos dados da ActivityCriarConta:
         */
        sharedPreferencesActvtLogin()

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

    private fun declararVariaveis() {
        textViewNomeActivityLogin =
            findViewById(R.id.txtVw_nomeCliente_componentDados_id)
        textViewNumeroContaActivityLogin =
            findViewById(R.id.txtVw_contaCliente_componentDados_id)
        imageViewVoltar01 = findViewById(R.id.imageViewVoltarLoginId)
        textViewVoltar01 = findViewById(R.id.textViewVoltarLoginId)
        botaoContinuarActivityLogin = findViewById(R.id.botaoAContinuarActivityLoginId)
        textViewEmailActivityLogin = findViewById(R.id.txtVw_emailCliente_componentDados_id)
        editTextInformeSenha = findViewById(R.id.editTextQualSenhaLoginId)
        editTexTEmail = findViewById(R.id.editTextQualEmailLoginId)
    }

    private fun criarFuncoes() {
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }
    }

    private fun sharedPreferencesActvtLogin() {
        val sharedPreferences = getSharedPreferences("chaveSP_ActvtConta", MODE_PRIVATE)
        val nomeSharedPreferencs = sharedPreferences.getString("chaveNomeActvtConta", "Nome:")
        textViewNomeActivityLogin.setText(nomeSharedPreferencs)
        val emailSharedPreferences = sharedPreferences.getString("chaveEmailActvtConta", "E-Mail:" )
        textViewEmailActivityLogin.setText(emailSharedPreferences)
        val contaSharedPreferences = sharedPreferences.getString("chaveContaActvtConta", "Nº Conta:")
       textViewNumeroContaActivityLogin.setText(contaSharedPreferences)
    }
}