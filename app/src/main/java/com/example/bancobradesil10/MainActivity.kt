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
import com.google.firebase.database.DatabaseReference
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
    private lateinit var lembrarMeuUsuario: CheckBox
    private lateinit var checkBoxLembrar: CheckBox
    private lateinit var nomeMainActivity: TextView
    private lateinit var numeroContaMainActivity: TextView

    // 01 Recuperar dados do Cloud Firestore:
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    lateinit var auth: FirebaseAuth
    lateinit var firebaseDatabase: DatabaseReference

    // 02 Recuperar dados do Cloud Firestore:
    lateinit var usuarioId: String

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
        //Variáveis:
        val nome = nomeMainActivity.text.toString()
        val conta = numeroContaMainActivity.text.toString()
        val recebeSenha = intent.getStringExtra("chaveSenha")

        // Enviar dados para a ActivityLogin:
        if (nome.isEmpty() && conta.isEmpty()) {
            val snackBar =
                Snackbar.make(botaoAcessarConta, "Cadastre a sua conta.", Snackbar.LENGTH_LONG)
            snackBar.show()

        } else {
            val intent = Intent1(this, ActivityLogin::class.java).apply {
                putExtra("chaveNomeConta", nomeMainActivity.text.toString())
                putExtra("chaveNumeroConta", numeroContaMainActivity.text.toString())
                putExtra("chaveSenha", recebeSenha)
            }
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()

//        // usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid()
//        usuarioId = FirebaseAuth.getInstance().currentUser!!.uid
//        val nomeMainActivity = findViewById<TextView>(R.id.textViewNomeClienteMainActivityId)
//        var nome = nomeMainActivity.text.toString()
//
//        val documentReference: DocumentReference = db!!.collection("Usuários Banco Bradesil 1.0").document(usuarioId)
//        documentReference.addSnapshotListener { value, error ->
//            if (value != null) {
//               //nomeMainActivity.setText(value.getString("Nome do usuário"))
//                nomeMainActivity.text = value.getString("Nome do usuário")
//            }
//        }

    }
}
