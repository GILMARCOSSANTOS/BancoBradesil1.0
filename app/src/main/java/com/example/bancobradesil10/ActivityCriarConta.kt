package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.NumberFormat
import java.util.*

class ActivityCriarConta : AppCompatActivity() {

    lateinit var authFireBase: FirebaseAuth
    // lateinit var userFirebase: FirebaseUser

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
        authFireBase = FirebaseAuth.getInstance()


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
        //VARIÁVEIS USADAS NA FUNÇÃO:
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val informeNome = informeSeuNome.text.toString()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        val suaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        val digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)

        // Fechar ActivityCriarConta e voltar para MainActivity caso a connta não tenha sido criada.
        finish()

        // Intent para envio de todos os dados para a MainActivity:
        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("chaveNomeConta", informeNome)
                putExtra("chaveNumeroConta", numeroConta.text.toString())
                putExtra("chaveSenha", suaSenha.text.toString())
                putExtra("chaveEmail", digiteEmail.text.toString())
            }
            startActivity(intent)
        }
    }

    /*
   Função textViewVoltarParaMainActivity():
    */
    private fun textViewVoltarParaMainActivity() {
        // Invocar função():
        imageViewVoltarParaMainActivity()
    }

    /*
    Função criarConta():
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun criarConta() {
        //Variáveis usadas na função.
        val digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)
        val email = digiteEmail.text.toString()
        val botaoCriarConta = findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)
        val informeSeuNome =
            findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        val repitaSuaSenha =
            findViewById<EditText>(R.id.editTextRepitaSenhaActivityCriarContaId)
        val digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        val suaSenha = digiteSuaSenha.text.toString()
        val informeNome = informeSeuNome.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val contaAbertasRandom = (10000..99999).random()
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        //  userFirebase = authFireBase.getCurrentUser()!!
        val ptBr = Locale("pt", "BR")
        val formatarValorConta =
            "Conta: " + NumberFormat.getNumberInstance(ptBr)
                .format(contaAbertasRandom.toDouble())

        //Condicional
        when {
            TextUtils.isEmpty(informeNome) -> {
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
            informeSeuNome.length() != 0 &&
                    email.isNotEmpty() &&
                    suaSenha == repitaSenha &&
                    repitaSenha == suaSenha &&
                    suaSenha.length >= 6 -> {
                situacaoConta.text = getString(R.string.situacaoContaCriadaComSucesso)
                botaoCriarConta.isEnabled = false
                numeroConta.text = formatarValorConta
            }
        }
        emailLogadoComSucesso()
    }

    /**
     * Função emailLogadoComSucesso()
     *OBJETIVO: ENVIAR DADOS para o Firebase:
     */
    private fun emailLogadoComSucesso() {
        //Declaração de variáveis:
        val situacaoConta =
            findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarContaId)
        val digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)
        val email = digiteEmail.text.toString()
        val digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        val suaSenha = digiteSuaSenha.text.toString()
        val numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarContaId)
        //  userFirebase = authFireBase.getCurrentUser()!!
        val contaAbertasRandom = (10000..99999).random()
        val ptBr = Locale("pt", "BR")
        val formatarValorConta =
            "Conta: " + NumberFormat.getNumberInstance(ptBr)
                .format(contaAbertasRandom.toDouble())

        //Condicional If():
        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
            numeroConta.text = formatarValorConta
            authFireBase.createUserWithEmailAndPassword(email, suaSenha)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->

                    //Condicional If():
                    if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
                        numeroConta.text = formatarValorConta
                        authFireBase.createUserWithEmailAndPassword(email, suaSenha)
                            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this,
                                        "Cadastro realizado com sucesso!",
                                        Toast.LENGTH_LONG
                                    ).show()

                                } else {
                                    Toast.makeText(
                                        this,
                                        "E-mail incorreto ou já cadastrado.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            )

                        // .addOnCompleteListener { }
                    } else {
                        Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}












































