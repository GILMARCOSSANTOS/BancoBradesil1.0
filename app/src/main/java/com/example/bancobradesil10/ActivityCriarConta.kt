/**
 * Tela Firebase 01; Tela posterior -> MainActivity:
 */

package com.example.bancobradesil10

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.time.Duration
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
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var analiseDados: TextView
    private lateinit var botaoCriarConta: Button

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
         * Funções:
         */
        inicializarVariavies()
        inicializarFuncoes()


    }

    private fun imageViewLogar() {
        if (analiseDados.text == getString(R.string.situacaoContaCriadaComSucesso) || analiseDados.text == "" || situacaoConta.text != "") {
            val intent = Intent(this, ActivityLogin::class.java).apply {
            }
            finish()
            sharedePreferences()
            startActivity(intent)
        }
    }

    private fun textViewLogar() {
        imageViewLogar()
    }

    private fun imageViewVoltarParaMainActivity() {
        if (analiseDados.text == getString(R.string.situacaoContaCriadaComSucesso) || analiseDados.text == "" || analiseDados.text != "") {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            finish()
            sharedePreferences()
            startActivity(intent)
        }
    }

    private fun textViewVoltarParaMainActivity() {
        imageViewVoltarParaMainActivity()
    }

    //    @RequiresApi(Build.VERSION_CODES.S)
    private fun cadastrarUsuario() {

        if(botaoCriarConta.isClickable){
            esconderTeclado()
        }

        //Variáveis usadas na função.
        val emailDigitado = digiteEmail.text.toString()
        val informeNome = digiteSeuNome.text.toString()
        val repitaSenha = repitaSuaSenha.text.toString()
        val suaSenha = digiteSuaSenha.text.toString()
        var contador = 0

        //Condicional
        when {
            (informeNome.isEmpty()) -> {

                analiseDados.setText(R.string.AnalisandoDados)

                indicadorProgressoErro()

                Handler().postDelayed({
                    analiseDados.setText(R.string.situacaoErroPreenchaNome)
                }, 750)
            }

            (emailDigitado.isEmpty()) -> {

                analiseDados.setText(R.string.AnalisandoDados)

                indicadorProgressoErro()

                Handler().postDelayed({
                    analiseDados.setText(R.string.situacaoErroPreenchaEmail)
                }, 750)
            }
            (suaSenha.isEmpty()) -> {

                analiseDados.setText(R.string.AnalisandoDados)

                indicadorProgressoErro()

                Handler().postDelayed({
                    analiseDados.setText(R.string.situacaoErroPreenchaSenha)
                }, 750)
            }

            (repitaSenha.isEmpty()) -> {

                analiseDados.setText(R.string.AnalisandoDados)

                indicadorProgressoErro()

                Handler().postDelayed({
                    analiseDados.setText(R.string.situacaoErroRepitaSenha)
                }, 750)
            }

            (repitaSenha != suaSenha) -> {

                analiseDados.setText(R.string.AnalisandoDados)

                indicadorProgressoErro()

                Handler().postDelayed({
                    analiseDados.setText(R.string.situacaoErroSenhasDiferentes)
                }, 750)
            }

            else -> {
                cadastrarUsuarioFirebase()
            }
        }
    }

    //Firebase Tela 01.01 = Implementar Função de cadastrar usuário no Firebase Authentication:
    private fun cadastrarUsuarioFirebase() {

        analiseDados.setText(R.string.AnalisandoDados)
        indicadorDeProgresso.progress = 0
        var nivelProgresso = 50
        ObjectAnimator.ofInt(indicadorDeProgresso, "progress", nivelProgresso)
            .setDuration(1000)
            .start()

        //Para Cadastrar, só precisa do e - mail e senha.
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            digiteEmail.text.toString(),
            repitaSuaSenha.text.toString()
        )
            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {

                    Handler().postDelayed({

                        indicadorDeProgresso.progress = 0
                        var nivelProgresso = 50
                        ObjectAnimator.ofInt(indicadorDeProgresso, "progress", nivelProgresso)
                            .setDuration(1000)
                            .start()

                        val contaAbertasRandom = (10000..99999).random()
                        val ptBr = Locale("pt", "BR")
                        val formatarValorConta =
                            NumberFormat.getNumberInstance(ptBr)
                                .format(contaAbertasRandom.toDouble())
                        numeroConta.text = formatarValorConta

                        analiseDados.text = getString(R.string.situacaoContaCriadaComSucesso)

                        salvarDadoscloudFirestore()

                    }, 1000)

                    //Firebase Tela 01.03 = Invocar Função de cadastrar usuário no Firebase Authentication:
                } else {

                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {

                        Handler().postDelayed({
                            analiseDados.setText(R.string.situacaoErroSenhaMenos)
                        }, 1000)
                        indicadorProgressoErro()


                    } catch (e: FirebaseAuthUserCollisionException) {

                        Handler().postDelayed({
                            analiseDados.setText(R.string.situacaoErroEmailJaCadastrado)
                        }, 1000)
                        indicadorProgressoErro()

                    } catch (e: FirebaseAuthInvalidCredentialsException) {

                        Handler().postDelayed({
                            analiseDados.setText(R.string.situacaoErroEmailInvalido)
                        }, 1000)
                        indicadorProgressoErro()

                    } catch (e: Exception) {

                        Handler().postDelayed({
                            analiseDados.setText(R.string.situacaoErroCadastrarUsuario)
                        }, 1000)
                        indicadorProgressoErro()


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

    private fun sharedePreferences() {
        val sharedPrefActvtCriarConta = sharedPreferences.edit()
        sharedPrefActvtCriarConta.putString(
            "chaveNomeActvtCriarConta",
            digiteSeuNome.text.toString()
        )
        sharedPrefActvtCriarConta.putString(
            "chaveEmailActvtCriarConta",
            digiteEmail.text.toString()
        )
        sharedPrefActvtCriarConta.putString("contaActvitiCriarConta", numeroConta.text.toString())
        sharedPrefActvtCriarConta.apply()
    }

    private fun inicializarVariavies() {
        digiteSeuNome = findViewById(R.id.editTextInformeNomeActivityCriarContaId)
        digiteSuaSenha = findViewById<EditText>(R.id.editTextCriarSenhaActivityCriarContaId)
        repitaSuaSenha =
            findViewById<EditText>(R.id.editTextRepitaSenhaActivityCriarContaId)
        numeroConta = findViewById<TextView>(R.id.textViewNumeroContaActivityCriarConta02Id)
        digiteEmail = findViewById<EditText>(R.id.editTextInformeEmailActivityCriarContaId)
        //  situacaoConta = findViewById<TextView>(R.id.textViewSituacaoContaActivityCriarConta02Id)
        botaoCriarConta = findViewById<Button>(R.id.bt_CriarConta_ActvtCriarConta_id)
        imageViewVoltarParaMainActivity =
            findViewById<ImageView>(R.id.imageViewVoltarActivityCriarContaId)
        texViewVoltarParaTelaPrincipal =
            findViewById<TextView>(R.id.texTeViewVoltarActivityCriarContaId)
        indicadorDeProgresso = findViewById(R.id.progressBarMainActivityId)
        imageViewLogarActivityCriarConta = findViewById(R.id.imgVw_logar_actvt_criarConta)
        textViewLogarActivityCriarConta = findViewById(R.id.texTeViewLoginctivityCriarContaId)
        analiseDados = findViewById(R.id.txtVw_analiseDados_ActvtCriarConta_id)
        sharedPreferences = getSharedPreferences("chaveGeralActvtCriarConta", MODE_PRIVATE)
    }

    private fun inicializarFuncoes() {
        imageViewVoltarParaMainActivity.setOnClickListener { imageViewVoltarParaMainActivity() }
        texViewVoltarParaTelaPrincipal.setOnClickListener { textViewVoltarParaMainActivity() }
        imageViewLogarActivityCriarConta.setOnClickListener { imageViewLogar() }
        textViewLogarActivityCriarConta.setOnClickListener { textViewLogar() }
        botaoCriarConta.setOnClickListener { cadastrarUsuario() }
    }

    private fun indicadorProgressoErro() {
        indicadorDeProgresso.progress = 0
        val nivelProgresso = 50
        ObjectAnimator.ofInt(indicadorDeProgresso, "progress", nivelProgresso)
            .setDuration(750)
            .start()
    }

    private fun esconderTeclado(){
        val View: View? = currentFocus
        View?.let {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}

