package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancobradesil10.criar_conta.CriarConta
import android.text.TextUtils
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*
import java.text.NumberFormat
import android.preference.PreferenceManager

import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences


class ActivityCriarConta : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        /*
        Bloquear função de orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Declaração de variáveis:
         */
        val botaoCriarConta = findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)
        val imageViewVoltarParaMainActivity =
            findViewById<ImageView>(R.id.imageViewVoltarActivityCriarContaId)
        val texViewVoltarParaTelaPrincipal =
            findViewById<TextView>(R.id.texTeViewVoltarActivityCriarContaId)

        /*
       Criar Funções
         */
        botaoCriarConta.setOnClickListener { criarConta() }
        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }

        /*
       Lista para contas criadas:
       */
        val listaContasAbertas: MutableList<CriarConta> = mutableListOf()
        val clienteGilmarcos = CriarConta(
            "Gilmarcos Santos",
            1555
        )
        listaContasAbertas.add(clienteGilmarcos)
    }

    /*
    Função imageViewVoltarParaMainActivity():
     */
    private fun imageViewVoltarParaMainActivity() {
        //VARIÁVEIS USADAS NA FUNÇÃO:
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val informeNome = informeSeuNome.text.toString()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        val suaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)

        // Fechar ActivityCriarConta e voltar para MainActivity caso a connta não tenha sido criada.
        finish()

        // Intent para envio de todos os dados para a MainActivity:
        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("chaveNomeConta", informeNome)
                putExtra("chaveNumeroConta", numeroConta.text.toString())
                putExtra("chaveSenha", suaSenha.text.toString())
            }
            startActivity(intent)
        }
    }

    /*
   Função textViewVoltarParaMainActivity():
    */
    private fun textViewVoltarParaMainActivity() {
        //VARIÁVEIS USADAS NA FUNÇÃO:
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val informeNome = informeSeuNome.text.toString()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        val suaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)

        // Fechar ActivityCriarConta e voltar para MainActivity caso a connta não tenha sido criada.
        finish()

        // Intent para envio de todos os dados para a MainActivity:
        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("chaveNomeConta", informeNome)
                putExtra("chaveNumeroConta", numeroConta.text.toString())
                putExtra("chaveSenha", suaSenha.text.toString())
            }
            startActivity(intent)
        }
    }

    /*
    Função criarConta():
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun criarConta() {
        //Variáveis usadas na função.
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        val digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        val repitaSuaSenha =
            findViewById<EditText>(R.id.editTextRepitaSenhaActivityCriarContaId)
        val suaSenha = digiteSuaSenha.text.toString()
        val informeNome = informeSeuNome.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val contaAbertasRandom = (10000..99999).random()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val ptBr = Locale("pt", "BR")
        val formatarValorConta =
            "Conta: " + NumberFormat.getNumberInstance(ptBr)
                .format(contaAbertasRandom.toDouble())
        val botaoNaoClicavel =
            findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)

        //Condicional
        when {
            TextUtils.isEmpty(informeNome) -> {
                Toast.makeText(this, "INFORME SEU NOME.",  Toast.LENGTH_LONG).show()

                situacaoConta.text = getString(R.string.situacaoErroPreenchaNome)
            }
            suaSenha.isEmpty() -> {
                Toast.makeText(this, "INFORME A SUA SENHA.", Toast.LENGTH_LONG).show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaSenha)
            }
            repitaSenha.isEmpty() -> {
                Toast.makeText(this, "REPITA A SENHA", Toast.LENGTH_SHORT).show()
                situacaoConta.text = getString(R.string.situacaoErroRepitaSenhas)
            }
            repitaSenha != suaSenha -> {
                Toast.makeText(this, "AS SENHAS SÃO DIFERENTES.", Toast.LENGTH_LONG).show()
                situacaoConta.text = getString(R.string.situacaoErroSenhasDiferentes)
            }
            suaSenha.length > 5 -> {
                Toast.makeText(this, "A SENHA DEVE TER NO MÁXIMO 5 DÍGITOS.", Toast.LENGTH_LONG)
                    .show()
                situacaoConta.text = getString(R.string.SituacaoErroSenhaMaiorCinco)
            }
            suaSenha.length < 5 -> {
                Toast.makeText(this, "A SENHA DEVE TER 5 DÍGITOS.", Toast.LENGTH_LONG).show()
                situacaoConta.text = getString(R.string.situacaoErroSenhaMenos)
            }
            situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso) -> {
                Toast.makeText(this, "A SUA CONTA JÁ FOI CRIADA.", Toast.LENGTH_LONG).show()
                botaoNaoClicavel.isEnabled = false
            }
            informeSeuNome.length() != 0 &&
                    suaSenha == repitaSenha &&
                    repitaSenha == suaSenha &&
                    suaSenha.length == 5 -> {
                situacaoConta.text = getString(R.string.situacaoContaCriadaComSucesso)
                numeroConta.text = formatarValorConta

            }
        }
    }
}





















































