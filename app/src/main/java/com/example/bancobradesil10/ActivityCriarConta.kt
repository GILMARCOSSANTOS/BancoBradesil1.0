/**
 * Tela Firebase 01; Tela posterior -> MainActivity:
 */

package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.*


class ActivityCriarConta : AppCompatActivity() {

    /*
    Variáveis em Escopo global:
     */
    private lateinit var botaoCadastrarUsuario: Button
    private lateinit var imageViewVoltarParaMainActivity: ImageView
    private lateinit var texViewVoltarParaTelaPrincipal: TextView
    private lateinit var digiteSeuNome: EditText
    private lateinit var situacaoConta: TextView
    private lateinit var numeroConta: TextView
    private lateinit var digiteEmail: EditText
    private lateinit var repitaSuaSenha: EditText
    private lateinit var digiteSuaSenha: EditText
    private lateinit var indicadorDeProgresso: ProgressBar
    private lateinit var imageViewLogarActivityCriarConta: ImageView
    private lateinit var textViewLogarActivityCriarConta: TextView

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        /**
         *  Bloquear função de orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Função de declaração de variáveis:
         */
        declararVariaveis()

        /**
         * Função para declaração de funções:
         */
        criarFuncoes()
    }

    private fun imageViewLogar() {

        val nome = digiteSeuNome.text.toString()
        val email = digiteEmail.text.toString()
        val conta = numeroConta.text.toString()

        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso) || situacaoConta.text == "" || situacaoConta.text != "") {
            val intent = Intent(this, ActivityLogin::class.java).apply {
                putExtra("chaveNome", nome)
                putExtra("chaveConta", conta)
                putExtra("chaveEmail", email)
            }
            startActivity(intent)
        }
    }

    private fun textViewLogar() {
        imageViewLogar()
    }

    private fun imageViewVoltarParaMainActivity() {

        val nome = digiteSeuNome.text.toString()
        val email = digiteEmail.text.toString()
        val conta = numeroConta.text.toString()

        if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso) || situacaoConta.text == "" || situacaoConta.text != "") {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("chaveNome", nome)
                putExtra("chaveConta", conta)
                putExtra("chaveEmail", email)
            }
            startActivity(intent)
        }
    }

    private fun textViewVoltarParaMainActivity() {
        imageViewVoltarParaMainActivity()
    }

    //    @RequiresApi(Build.VERSION_CODES.S)
    private fun cadastrarUsuario() {

        //Variáveis usadas na função.
        val emailDigitado = digiteEmail.text.toString()
        val informeNome = digiteSeuNome.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val suaSenha = digiteSuaSenha.text.toString()
        var contador = 0

        //Condicional
        when {
            (informeNome.isEmpty()) -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! Preencha o seu nome.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaNome)
            }

            emailDigitado.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! Preencha o seu E - mail.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaEmail)
            }
            suaSenha.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! Preencha a senha.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroPreenchaSenha)
            }
            repitaSenha.isEmpty() -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! Repita a senha.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroRepitaSenhas)
            }
            repitaSenha != suaSenha -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! Senhas incompatíveis.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroSenhasDiferentes)
            }
            suaSenha.length < 6 -> {
                val snackBar = Snackbar.make(
                    botaoCadastrarUsuario,
                    "Situação: Erro! A senha tem menos de 6 dígitos.",
                    Snackbar.LENGTH_LONG
                )
                snackBar.show()
                situacaoConta.text = getString(R.string.situacaoErroSenhaMenos)
            }

            else -> {

                cadastrarUsuarioFirebase()

            }
        }
    }


    //Firebase Tela 01.01 = Implementar Função de cadastrar usuário no Firebase Authentication:
    private fun cadastrarUsuarioFirebase() {

        //Para Cadastrar, só precisa do e - mail e senha.
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            digiteEmail.text.toString(),
            repitaSuaSenha.text.toString()
        )
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {

                    indicadorDeProgresso.visibility

                    val contaAbertasRandom = (10000..99999).random()
                    val ptBr = Locale("pt", "BR")
                    val formatarValorConta =
                        NumberFormat.getNumberInstance(ptBr)
                            .format(contaAbertasRandom.toDouble())
                    numeroConta.text = formatarValorConta

                    situacaoConta.text = getString(R.string.situacaoContaCriadaComSucesso)

                    indicadorDeProgresso.progress = 100
                    indicadorDeProgresso.isIndeterminate = false

                    salvarDadoscloudFirestore()

                    //Firebase Tela 01.03 = Invocar Função de cadastrar usuário no Firebase Authentication:
                    Toast.makeText(this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG)
                        .show()
                } else {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, "A senha tem menos de 6 dígitos!", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "O e - mail já está cadastrado.", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "O e - mail usado é inválido!", Toast.LENGTH_LONG)
                            .show()
                    } catch (e: Exception) {
                        Toast.makeText(this, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun salvarDadoscloudFirestore() {
        val bancoDadosFirebase = FirebaseFirestore.getInstance()
        val usuarioFirebaseId = FirebaseAuth.getInstance().currentUser!!.uid
        val nomeFirebase: String = digiteSeuNome.text.toString()
        val emailFirebase: String = digiteEmail.text.toString()
        val contaFirebase: String = numeroConta.text.toString()

        val usuariosFirebaseHashMap: MutableMap<String, Any> = HashMap()
        usuariosFirebaseHashMap["nomeFirebase"] = nomeFirebase
        usuariosFirebaseHashMap["emailFirebase"] = emailFirebase
        usuariosFirebaseHashMap["contaFirebase"] = contaFirebase

        val documentReference =
            bancoDadosFirebase.collection("Usuarios Bradesil").document(usuarioFirebaseId)
        documentReference.set(usuariosFirebaseHashMap)
            .addOnSuccessListener(object : OnSuccessListener<Void?> {
                override fun onSuccess(p0: Void?) {
                    Log.d("db", "Sucesso ao salvar os dados.")
                }
            })
    }

    private fun declararVariaveis() {
        digiteSeuNome = findViewById<EditText>(R.id.editTextInformeNomeActivityCriarContaId)
        digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        repitaSuaSenha =
            findViewById<EditText>(R.id.editTextRepitaSenhaActivityCriarContaId)
        numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarConta02Id)
        digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)
        situacaoConta = findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarConta02Id)
//        botaoCriarConta = findViewById<Button>(R.id.botaoCriarContaActivityCriarContaId)
        botaoCadastrarUsuario = findViewById(R.id.btn_linkConfirmacao_actvt_criarConta)
        imageViewVoltarParaMainActivity =
            findViewById<ImageView>(R.id.imageViewVoltarActivityCriarContaId)
        texViewVoltarParaTelaPrincipal =
            findViewById<TextView>(R.id.texTeViewVoltarActivityCriarContaId)
        indicadorDeProgresso = findViewById(R.id.progressBarMainActivityId)
        imageViewLogarActivityCriarConta = findViewById(R.id.imageViewLogintarActivityCriarContaId)
        textViewLogarActivityCriarConta = findViewById(R.id.texTeViewLoginctivityCriarContaId)
    }

    private fun criarFuncoes() {

        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }
        imageViewLogarActivityCriarConta.setOnClickListener { imageViewLogar() }
        textViewLogarActivityCriarConta.setOnClickListener { textViewLogar() }
        botaoCadastrarUsuario.setOnClickListener { cadastrarUsuario() }
    }

}

