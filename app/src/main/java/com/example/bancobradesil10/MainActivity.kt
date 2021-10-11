package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.google.android.material.button.MaterialButton
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {
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
        val botaoAcessarConta = findViewById<MaterialButton>(R.id.acessarContaBotaoMainActivityId)
        val botaoCriarconta = findViewById<MaterialButton>(R.id.botaoCriarContaMainActivityId)
        val imageButtonContasCadastradas =
            findViewById<ImageButton>(R.id.imageButtonContasCadastradasMainActivityId)

        /*
       Recepção dos dados da Activity CriarConta:
         */
        val mensagemNome = intent.getStringExtra("chaveNomeConta")
        val mensagemConta = intent.getStringExtra("chaveNumeroConta")

        // Valores das Views da MainActivity = valores da ActivityCriarConta:
        textViewNomeConta.apply {
            text = mensagemNome
        }
        textViewNumeroConta.apply {
            text = mensagemConta
        }

        /*
        Criar Funções:
         */
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        imageButtonContasCadastradas.setOnClickListener { entrarEmContasCadastradas() }
    }

    /*
    Função EntrarEmContasCadastradas():
     */
    private fun entrarEmContasCadastradas() {
        val intent = Intent1(this, ActivityContasCadastradas::class.java).apply {
        }
        startActivity(intent)
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

        // Enviar dados para a ActivityLogin:
        if (nome.isEmpty() && conta.isEmpty()) {
            Toast.makeText(
                this,
                "Cadastre a sua conta.",
                Toast.LENGTH_LONG
            ).show()
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