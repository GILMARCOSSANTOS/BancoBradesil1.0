package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /**
     * Declaração de variáveis em Escopo Global:
     */
    private lateinit var textViewNomeConta: TextView
    private lateinit var textViewNumeroConta: TextView
    private lateinit var texViewEmail: TextView
    private lateinit var botaoAcessarConta: MaterialButton
    private lateinit var botaoCriarconta: MaterialButton
    private lateinit var imageButtonContasCadastradas: ImageButton
    private lateinit var texViewAcessarConta: TextView
    private lateinit var checkBoxLembrar: CheckBox

    // Firebase 02 = Recuperar instância do Firebase Database para recuperar dados:
    private val bancoDadosFirebase = FirebaseFirestore.getInstance()
    private var usuarioFirebase: String = ""
    private var emailFirebase: String = ""
    private var numeroContaFirebase: String = ""

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
        textViewNomeConta = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
        textViewNumeroConta = findViewById<TextView>(R.id.textViewNumeroContaMainActivityId)
        texViewEmail = findViewById<TextView>(R.id.textViewEmailMainActivityId)
        botaoAcessarConta = findViewById<MaterialButton>(R.id.acessarContaBotaoMainActivityId)
        botaoCriarconta = findViewById<MaterialButton>(R.id.botaoCriarContaMainActivityId)
        imageButtonContasCadastradas =
            findViewById<ImageButton>(R.id.imageButtonContasCadastradasMainActivityId)
        texViewAcessarConta = findViewById<TextView>(R.id.textViewAcessarContasMainActivityId)
        checkBoxLembrar = findViewById<CheckBox>(R.id.checkboxLembrarUsuarioId)

        /*
        Criar Funções:
         */
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarEmContasCadastradas() }
        texViewAcessarConta.setOnClickListener() { textViewEntrarEmContasCadastradas() }
        checkBoxLembrar.setOnClickListener { lembrarUsuario() }

        // lembrarMeuUsuario.setOnClickListener { lembrarUsuario() }
    }

    /*
    Função CheckBoxLembrarUsuario():
     */
    private fun lembrarUsuario() {
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

        if (textViewNomeConta.text.toString().isEmpty() || textViewNumeroConta.text.toString()
                .isEmpty() || texViewEmail.text.toString().isEmpty()
        ) {
            val snackBar =
                Snackbar.make(botaoAcessarConta, "Cadastre a sua conta.", Snackbar.LENGTH_LONG)
            snackBar.show()

        } else {
            val intent = Intent1(this, ActivityLogin::class.java).apply {
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        usuarioFirebase = FirebaseAuth.getInstance().currentUser!!.uid
        emailFirebase = FirebaseAuth.getInstance().currentUser.toString()
        numeroContaFirebase = FirebaseAuth.getInstance().currentUser.toString()

        val documentReference =
            bancoDadosFirebase.collection("UsuariosFirebase").document(usuarioFirebase)
        documentReference.addSnapshotListener { value, error ->
            if (value != null) {
                textViewNomeConta.text = value.getString("NomeUsuario")
                texViewEmail.text = value.getString("EmailUsuario")
                textViewNumeroConta.text = value.getString("NumeroConta")
            }
        }
    }
}
