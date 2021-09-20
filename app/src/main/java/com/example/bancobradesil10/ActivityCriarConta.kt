package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancobradesil10.criar_conta.CriarConta
import android.text.TextUtils
import android.widget.*
import java.util.*
import java.text.NumberFormat


class ActivityCriarConta : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        /*
        BLOQUEAR FUNÇÃO DE ORIENTAÇÃO DA TELA:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /*
       CRIAR FUNÇÃO CRIAR CONTA():
         */
        val botaoCriarConta = findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)
        botaoCriarConta.setOnClickListener { criarConta() }


        /*
       CRIAR FUNÇÃO IMAGEVIEWVOLTARAPARAMAINCTIVITY():
         */
        val imageViewVoltarParaMainActivity =
            findViewById<ImageView>(R.id.imageViewVoltarActivityCriarContaId)
        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }

        /*
   CRIAR FUNÇÃO IMAGEVIEWVOLTARPARAMAINACTIVITY():
     */
        val texViewVoltarParaTelaPrincipal =
            findViewById<TextView>(R.id.texTeViewVoltarActivityCriarContaId)
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }

        /*
LISTA PARA CONTAS CRIADAS NO SISTEMA:
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
    fun imageViewVoltarParaMainActivity() {
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val informeNome = informeSeuNome.text.toString()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)


        val intentVoltarParaMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentVoltarParaMainActivity)

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("chaveNomeConta", informeNome)
            putExtra("chaveNumeroConta", numeroConta.text.toString())
        }
        startActivity(intent)


    }

    /*
    Função textViewVoltarParaMainActivity():
     */
    private fun textViewVoltarParaMainActivity() {
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val informeNome = informeSeuNome.text.toString()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)

        val intentVoltarParaMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentVoltarParaMainActivity)

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("chaveNomeConta", informeNome)
            putExtra("chaveNumeroConta", numeroConta.text)
        }
        if (situacaoConta.text == getString(R.string.SituacaoContacriadacomsucesso)) {
            startActivity(intent)
        }
    }

    /*
    Função criarConta():
     */
    private fun criarConta() {

        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val suaConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
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

        when {
            TextUtils.isEmpty(informeNome) -> {
                Toast.makeText(this, "INFORME SEU NOME.", Toast.LENGTH_LONG).show()
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
            informeSeuNome.length() != 0 &&
                    suaSenha == repitaSenha &&
                    repitaSenha == suaSenha &&
                    suaSenha.length == 5 -> {
                situacaoConta.text = getString(R.string.SituacaoContacriadacomsucesso)
                suaConta.text = formatarValorConta

            }

        }


    }


}


















































