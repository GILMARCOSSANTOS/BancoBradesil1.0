/**
 * Tela Firebase 01; Tela posterior -> MainActivity:
 */

package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ActivityCriarConta : AppCompatActivity() {

    /*
    Variáveis em Escopo global:
     */
    private lateinit var botaoCriarConta: Button
    lateinit var imageViewVoltarParaMainActivity: ImageView
    lateinit var texViewVoltarParaTelaPrincipal: TextView
    lateinit var informeSeuNome: EditText
    lateinit var situacaoConta: TextView
    lateinit var numeroConta: TextView
    lateinit var digiteEmail: EditText
    lateinit var repitaSuaSenha: EditText
    private lateinit var digiteSuaSenha: EditText

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
        digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        repitaSuaSenha =
            findViewById<EditText>(R.id.editTextRepitaSenhaActivityCriarContaId)
        informeSeuNome = findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        situacaoConta = findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)
        botaoCriarConta = findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)
        imageViewVoltarParaMainActivity =
            findViewById<ImageView>(R.id.imageViewVoltarActivityCriarContaId)
        texViewVoltarParaTelaPrincipal =
            findViewById<TextView>(R.id.texTeViewVoltarActivityCriarContaId)

        /*
       Criar Funções
         */
        botaoCriarConta.setOnClickListener { criarConta() }
        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }
    }

    /*
    Função imageViewVoltarParaMainActivity():
     */
    private fun imageViewVoltarParaMainActivity() {
        finish()
    }

    /*
  Função textViewVoltarParaMainActivity():
   */
    private fun textViewVoltarParaMainActivity() {
        finish()
    }

    /*
    Função criarConta():
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun criarConta() {

        //Variáveis usadas na função.
        val email = digiteEmail.text.toString()
        val informeNome = informeSeuNome.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val suaSenha = digiteSuaSenha.text.toString()

        //Condicional
        when {
            (informeNome.isEmpty()) -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! Preencha o seu nome.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaNome)
            }
            email.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! Preencha o seu E - mail.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaEmail)
            }
            suaSenha.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! Preencha a senha.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaSenha)
            }
            repitaSenha.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! Repita a senha.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroRepitaSenhas)
            }
            repitaSenha != suaSenha -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! Senhas incompatíveis.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroSenhasDiferentes)
            }
            suaSenha.length < 6 -> {
                val snackBar = Snackbar.make(
                    botaoCriarConta,
                    "Situação: Erro! A senha tem menos de 6 dígitos.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroSenhaMenos)
            }
            situacaoConta.text == "" -> {
                Toast.makeText(this, "Preencha todos os dados.", Toast.LENGTH_SHORT).show()
            }
            else -> {

                cadastrarUsuarioFirebase()
            }
        }
    }

    /**
     * Função emailLogadoComSucesso()
     *OBJETIVO: ENVIAR DADOS para o Firebase:
     */
    private fun cadastrarUsuarioFirebase() {
        //Declaração de variáveis:

//        val repitaSenha = repitaSuaSenha.text.toString()
//        val informeNome = informeSeuNome.text.toString()
//        val email = digiteEmail.text.toString()
//        val suaSenha = digiteSuaSenha.text.toString()
//        val contaAbertasRandom = (10000..99999).random()
//        val ptBr = Locale("pt", "BR")
//        val formatarValorConta =
//            "Conta: " + NumberFormat.getNumberInstance(ptBr)
//                .format(contaAbertasRandom.toDouble())


    }
}
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()

}