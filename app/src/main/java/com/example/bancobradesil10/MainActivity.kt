package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /**
     * Declaração de variáveis em Escopo Global:
     */
    private lateinit var textViewNomeConta: TextView
    private lateinit var textViewNumeroConta: TextView
    private lateinit var texViewEmail: TextView
    private lateinit var botaoAcessarConta: Button
    private lateinit var botaoCriarconta: Button
    private lateinit var imageButtonContasCadastradas: ImageButton
    private lateinit var checkBoxLembrar: CheckBox
    private lateinit var textViewAContasCadastradas: TextView


    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Bloquear Orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Declaração de variáveis:
         */

        botaoAcessarConta = findViewById(R.id.botaoa_acessarConta_mainActivity_Id)
        textViewNomeConta = findViewById(R.id.txtVw_nomeCliente_componentDados_id)
        textViewNumeroConta = findViewById(R.id.txtVw_contaCliente_componentDados_id)
        texViewEmail = findViewById(R.id.txtVw_emailCliente_componentDados_id)
        botaoCriarconta = findViewById(R.id.botaoCriarContaMainActivityId)
        checkBoxLembrar = findViewById(R.id.checkboxLembrarUsuarioId)
        textViewAContasCadastradas =
            findViewById(R.id.textViewAcessarContasCadastradasMainActivityId)
        imageButtonContasCadastradas = findViewById(R.id.imageButtonContasCadastradasMainActivityId)

        /*
        Criar Funções:
         */
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        // imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarEmContasCadastradas() }
        textViewAContasCadastradas.setOnClickListener { textViewEntrarContasCadastradas() }
        imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarContasCadastradas() }
        checkBoxLembrar.setOnClickListener { lembrarUsuario() }
        // lembrarMeuUsuario.setOnClickListener { lembrarUsuario() }

        /**
         * Shared Preferences: Recebimentos dos dados da ActivityCriarConta:
         */
        val nomeUsuarioShared = intent.getStringExtra("chaveNome")
        textViewNomeConta.apply {
            text = nomeUsuarioShared
        }
        val emailUsuarioShared = intent.getStringExtra("chaveEmail")
        texViewEmail.apply {
            text = emailUsuarioShared
        }
        val contaUsuarioshared = intent.getStringExtra("chaveConta")
        textViewNumeroConta.apply {
            text = contaUsuarioshared
        }

    }

    /*
Função CheckBoxLembrarUsuario():
 */
    private fun lembrarUsuario() {
    }

    /*
    Função EntrarEmContasCadastradas():
     */
    private fun imageButtonEntrarContasCadastradas() {
        val intent = Intent1(this, ActivityContasCadastradas::class.java).apply {
        }
        startActivity(intent)
    }

    private fun textViewEntrarContasCadastradas() {
        imageButtonEntrarContasCadastradas()
    }

    /*
   Função CriarConta():
     */
    private fun criarConta() {
        val intent = Intent1(this, ActivityCriarConta::class.java).apply {
        }
        startActivity(intent)
    }

    /*
    Função AcessarConta():
     */
    private fun acessarConta() {

        val intent = Intent1(this, ActivityLogin::class.java).apply {
        }
        startActivity(intent)
    }

}

