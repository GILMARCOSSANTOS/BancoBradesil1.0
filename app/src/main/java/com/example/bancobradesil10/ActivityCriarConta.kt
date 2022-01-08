/**
 * Tela Firebase 01; Tela posterior -> MainActivity:
 */

package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.*

class ActivityCriarConta : AppCompatActivity() {

    /*
    Variáveis em Escopo global:
     */
    private lateinit var botaoCriarConta: Button
    lateinit var imageViewVoltarParaMainActivity: ImageView
    private lateinit var texViewVoltarParaTelaPrincipal: TextView
    lateinit var digiteSeuNome: EditText
    lateinit var situacaoConta: TextView
    lateinit var numeroConta: TextView
    lateinit var digiteEmail: EditText
    lateinit var repitaSuaSenha: EditText
    private lateinit var digiteSuaSenha: EditText
    lateinit var indicadorDeProgresso: ProgressBar
    private lateinit var imageViewLogarActivityCriarConta: ImageView
    private lateinit var textViewLogarActivityCriarConta: TextView

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
        digiteSuaSenha = findViewById(R.id.editTextCriarSenhaActivityCriarContaId)
        repitaSuaSenha =
            findViewById(R.id.editTextRepitaSenhaActivityCriarContaId)
        digiteSeuNome = findViewById(R.id.editTextInformeNomeActivityCriarContaId)
        situacaoConta = findViewById(R.id.textViewSituacaoContaActivityCriarContaId)
        numeroConta = findViewById(R.id.textViewNumeroContaActivityCriarConta02Id)
        digiteEmail = findViewById(R.id.editTextInformeEmailActivityCriarContaId)
        botaoCriarConta = findViewById(R.id.botaoCriarContaActivityCriarContaId)
        imageViewVoltarParaMainActivity =
            findViewById(R.id.imageViewVoltarActivityCriarContaId)
        texViewVoltarParaTelaPrincipal =
            findViewById(R.id.texTeViewVoltarActivityCriarContaId)
        indicadorDeProgresso = findViewById(R.id.progressBarMainActivityId)
        imageViewLogarActivityCriarConta = findViewById(R.id.imageViewLogintarActivityCriarContaId)
        textViewLogarActivityCriarConta = findViewById(R.id.texTeViewLoginctivityCriarContaId)

        /*
       Criar Funções
         */
        botaoCriarConta.setOnClickListener { criarConta() }
        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }
        imageViewLogarActivityCriarConta.setOnClickListener { imageViewLogar() }
        textViewLogarActivityCriarConta.setOnClickListener { textViewLogar() }

    }

    private fun imageViewLogar() {
        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
    }

    private fun textViewLogar() {
        imageViewLogar()
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
        val nome = digiteSeuNome.text.toString()
        val email = digiteEmail.text.toString()
        val conta = numeroConta.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val suaSenha = digiteSuaSenha.text.toString()
        var contador = 0

        //Condicional
        when {
            (nome.isEmpty()) -> {
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
                //  indicadorDeProgresso.setVisibility(View.VISIBLE)
                // indicadorDeProgresso.setVisibility(View.VISIBLE)

                //   indicadorDeProgresso.setVisibility(if (isVisible === 3) View.Visible else View.Invisible)

//                java.lang.Thread(
//                    object : Runnable {
//                        override fun run() {
//                            while (contador < 100) {
//                                contador += 1
//                                try {
//                                    Thread.sleep(0)
//                                    Thread.interrupted()
//                                } catch (e: InterruptedException) {
//                                    e.printStackTrace()
//                                }
//                            }
//                            indicadorDeProgresso.post(Runnable {
//                                indicadorDeProgresso.setVisibility(
//                                    View.VISIBLE
//                                )
//                            })
//                        }
//                    }).start()

                //Firebase Tela 01.02 = Invocar Função de cadastrar usuário no Firebase Authentication:
                cadastrarUsuarioFirebase()

            }
        }
    }

    /**
     * Função cadastrarUsuarioFirebase():
     */
    //Firebase Tela 01.01 = Implementar Função de cadastrar usuário no Firebase Authentication:
    private fun cadastrarUsuarioFirebase() {
        val nome = digiteSeuNome.text.toString()
        val email = digiteEmail.text.toString()
        val conta = numeroConta.text.toString()
        val senha = repitaSuaSenha.text.toString()

        //Para Cadastrar, só precisa do e - mail e senha.
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
          email, senha)
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {

                    salvarDadosFirestoreDatabase()
                    //Firebase Tela 01.03 = Invocar Função de cadastrar usuário no Firebase Authentication:

                    situacaoConta.text = getString(R.string.situacaoContaCriadaComSucesso)

//                    if (situacaoConta.text == getString(R.string.situacaoContaCriadaComSucesso)) {
//
//
//
//
//
//                        val intent = Intent(this, ActivityLogin::class.java).apply {
//                            putExtra("chaveNome", nome)
//                            putExtra("chaveEmail", email)
//                            putExtra("chaveConta", conta)
//                        }
                    // startActivity(intent)

                    indicadorDeProgresso.visibility

                    val contaAbertasRandom = (10000..99999).random()
                    val ptBr = Locale("pt", "BR")
                    val formatarValorConta =
                        NumberFormat.getNumberInstance(ptBr)
                            .format(contaAbertasRandom.toDouble())
                    numeroConta.text = formatarValorConta

                    Toast.makeText(this, "xxxxxxCadastro realizado com sucesso.", Toast.LENGTH_LONG)
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

    /**
     * Função salvarDadosFirestoreDatabase():
     */
    //Firebase Tela 01.04 = Implementar Função de cadastrar usuário no Firestore Database:
    private fun salvarDadosFirestoreDatabase() {

        val usuarioFirebaseId = FirebaseAuth.getInstance().currentUser!!.uid
        val emailUsuarioFirebase: String?
        emailUsuarioFirebase = digiteEmail.text.toString()
        val contaUsuarioFirebase: String?
        contaUsuarioFirebase = numeroConta.text.toString()
        val bancoDadosFirebase = FirebaseFirestore.getInstance()
        val nomeUsuarioFirebased: String?
        nomeUsuarioFirebased = digiteSeuNome.text.toString()

        val usuariosHashMapFirebase: MutableMap<String, Any> = HashMap()
        usuariosHashMapFirebase["nomeUsuario"] = nomeUsuarioFirebased
        usuariosHashMapFirebase["emailUsuario"] = emailUsuarioFirebase
        usuariosHashMapFirebase["contaUsuario"] = contaUsuarioFirebase


        val documentReference =
            bancoDadosFirebase.collection("Usuarios Bradesil").document(usuarioFirebaseId)
        documentReference.set(usuariosHashMapFirebase).addOnSuccessListener(object :
            OnSuccessListener<Void?> {
            override fun onSuccess(p0: Void) {
                TODO("Not yet implemented")
            }

        })
    }
}