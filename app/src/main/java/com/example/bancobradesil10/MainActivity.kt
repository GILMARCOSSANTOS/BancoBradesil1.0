package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /**
     * Declaração de variáveis em Escopo Global:
     */
    private lateinit var textViewNomeConta: TextView
    private lateinit var textViewNumeroConta: TextView
    private lateinit var texViewEmail: TextView
    private lateinit var botaoAcessarConta: MaterialButton
    private lateinit var botaoCriarconta: MaterialButton
    private lateinit var imageButtonContasCadastradas: ImageButton
    private lateinit var checkBoxLembrar: CheckBox
    private lateinit var textViewAContasCadastradas: TextView

    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        Bloquear Orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Declaração de variáveis:
         */
        textViewNomeConta = findViewById(R.id.textViewNomeCliente02ActivityMainId)
        textViewNumeroConta = findViewById(R.id.textViewConta02ActivityMainId)
        texViewEmail = findViewById(R.id.textViewEmail2ActivityLoginId)
        botaoAcessarConta = findViewById(R.id.acessarContaBotaoMainActivityId)
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




