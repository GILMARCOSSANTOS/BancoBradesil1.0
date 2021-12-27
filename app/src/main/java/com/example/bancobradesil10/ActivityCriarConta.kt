/**
 * Tela Firebase 01; Tela posterior -> MainActivity:
 */

package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap

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
    private val nome = "Nome: "
    private val email = "E-mail: "
    private val conta = "Conta: "
    lateinit var indicadorDeProgresso: ProgressBar

    //Aula 01.05: Usuário do CurrentUserId do Firebase Database:
    private lateinit var usuarioId: String
    var bancoDadosFirestore = FirebaseFirestore.getInstance()

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
       indicadorDeProgresso = findViewById(R.id.progressBarMainActivityId)

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

        var contador = 0

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
              //  indicadorDeProgresso.setVisibility(View.VISIBLE)
               // indicadorDeProgresso.setVisibility(View.VISIBLE)

             //   indicadorDeProgresso.setVisibility(if (isVisible === 3) View.Visible else View.Invisible)

                java.lang.Thread(
                    object: Runnable {
                        override fun run() {
                            while (contador < 100) {
                               contador += 1
                                try {
                                    Thread.sleep(0)
                                    Thread.interrupted()
                                } catch (e: InterruptedException) {
                                    e.printStackTrace()
                                }
                            }
                            indicadorDeProgresso.post(Runnable { indicadorDeProgresso.setVisibility(View.VISIBLE) })
                        }
                    }).start()

               // indicadorDeProgresso.setVisibility(View.GONE)

                cadastrarUsuarioFirebase()

            }
        }
    }



    /**
     * Função cadastrarUsuarioFirebase():
     */
    // Firebase 01.02 = Implementar função para o cadastro do usuário no Firebase:
    private fun cadastrarUsuarioFirebase() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            digiteEmail.text.toString(),
            repitaSuaSenha.text.toString()
        )
            .addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful) {
                   // indicadorDeProgresso.setIndeterminate(false)
                    indicadorDeProgresso.visibility



                    // Firebase 01.03.01 = Criar função para salvar os dados do usuário no Firestore Database:

                    situacaoConta.text = getString(R.string.situacaoContaCriadaComSucesso)

                    val contaAbertasRandom = (10000..99999).random()
                    val ptBr = Locale("pt", "BR")
                    val formatarValorConta =
                        "Conta: " + NumberFormat.getNumberInstance(ptBr)
                            .format(contaAbertasRandom.toDouble())
                    numeroConta.text = formatarValorConta

                    salvarDadosFirestoreDatabase()

                    // Firebase 01.03.02 = Condicionais de exceção de erro no cadastro de usuário no Firerestore Database:
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

    /**
     * Função salvarDadosFirestoreDatabase():
     */
    // Firebase: 01.04 = Implementar função para salvar os dados no Firestore Database:
    private fun salvarDadosFirestoreDatabase() {

        // Firebase 01.05: Iniciar o banco de dados Firestore Database:
        bancoDadosFirestore = FirebaseFirestore.getInstance()
        val usuarioDatabase: MutableMap<String, Any> = HashMap()
        usuarioDatabase["NomeUsuario"] = nome + informeSeuNome.text.toString()
        usuarioDatabase["EmailUsuario"] = email + digiteEmail.text.toString()
        usuarioDatabase["NumeroConta"] = conta + numeroConta.text.toString()

        usuarioId = FirebaseAuth.getInstance().currentUser!!.uid

        // Firebase 01.06: Salvar dados no Firebase Database; -> Segue para a MainActivity :
        val documentReference =
            bancoDadosFirestore.collection("UsuariosFirebase").document(usuarioId)
        documentReference.set(usuarioDatabase)
            .addOnSuccessListener(object : OnSuccessListener<Void?> {

                override fun onSuccess(p0: Void?) {
                    Log.d("db", "Sucesso ao salvar os dados.")
                }
            })
            .addOnFailureListener {
                Log.d("dbError", "Erro ao salvar os dados")
            }
    }
}

