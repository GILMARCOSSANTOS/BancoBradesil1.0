package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /**
     * Shared Preferences:
     */
    lateinit var sharedPreferences: SharedPreferences

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
        val textViewNomeConta = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
        val textViewNumeroConta = findViewById<TextView>(R.id.textViewNumeroContaMainActivityId)
        val texViewEmail = findViewById<TextView>(R.id.textViewEmailMainActivityId)
        val botaoAcessarConta = findViewById<MaterialButton>(R.id.acessarContaBotaoMainActivityId)
        val botaoCriarconta = findViewById<MaterialButton>(R.id.botaoCriarContaMainActivityId)
        val imageButtonContasCadastradas =
            findViewById<ImageButton>(R.id.imageButtonContasCadastradasMainActivityId)
        val texViewAcessarConta = findViewById<TextView>(R.id.textViewAcessarContasMainActivityId)
        val lembrarMeuUsuario = findViewById<CheckBox>(R.id.checkboxLembrarUsuarioId)

        /*
       Recepção dos dados da Activity CriarConta:
         */
        val mensagemNome = intent.getStringExtra("chaveNomeConta")
        val mensagemConta = intent.getStringExtra("chaveNumeroConta")
        val mensagemEmail = intent.getStringExtra("chaveEmail")

        // Valores das Views da MainActivity = valores da ActivityCriarConta:
        textViewNomeConta.apply {
            text = mensagemNome
        }
        textViewNumeroConta.apply {
            text = mensagemConta
        }
        texViewEmail.apply {
            text = mensagemEmail
        }

        /*
        Criar Funções:
         */
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarEmContasCadastradas() }
        texViewAcessarConta.setOnClickListener() { textViewEntrarEmContasCadastradas() }
        lembrarMeuUsuario.setOnClickListener { lembrarUsuario() }
    }

    /*
    Função CheckBoxLembrarUsuario():
     */
    private fun lembrarUsuario() {
        // Declaração de variáveis:
        val textViewNomeConta = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
        val textViewNumeroConta = findViewById<TextView>(R.id.textViewNumeroContaMainActivityId)
        val texViewEmail = findViewById<TextView>(R.id.textViewEmailMainActivityId)
        val nomeConta = textViewNomeConta.text.toString()
        val numeroConta = textViewNumeroConta.text.toString()
        val eMail = texViewEmail.text.toString()

        if (textViewNomeConta.length() != 0 || textViewNumeroConta.length() != 0 || texViewEmail.length() != 0) {

        }

    }

    /*
    Função EntrarEmContasCadastradas():
     */
    private fun imageButtonEntrarEmContasCadastradas() {
        val intent = Intent1(this, ActivityContasCadastradas::class.java).apply {
        }
        startActivity(intent)
    }

    private fun textViewEntrarEmContasCadastradas() {
        imageButtonEntrarEmContasCadastradas()
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
        //Variáveis:
        val nomeMainActivity = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
        val numeroContaMainActivity = findViewById<TextView>(R.id.textViewNumeroContaMainActivityId)
        val nome = nomeMainActivity.text.toString()
        val conta = numeroContaMainActivity.text.toString()
        val recebeSenha = intent.getStringExtra("chaveSenha")
        val botaoAcessarConta = findViewById<MaterialButton>(R.id.acessarContaBotaoMainActivityId)


        // Enviar dados para a ActivityLogin:
        if (nome.isEmpty() && conta.isEmpty()) {
            val snackBar =
                Snackbar.make(botaoAcessarConta, "Cadastre a sua conta.", Snackbar.LENGTH_LONG)
            snackBar.show()

        } else {
            val intent = Intent1(this, ActivityLogin::class.java).apply {
                putExtra("chaveNomeConta", nomeMainActivity.text.toString())
                putExtra("chaveNumeroConta", numeroContaMainActivity.text.toString())
                putExtra("chaveSenha", recebeSenha)
            }
            startActivity(intent)
        }
    }
}


