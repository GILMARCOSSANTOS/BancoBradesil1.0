package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ActivityLogin : AppCompatActivity() {

    /*
      Variáveis em Escopo Global:
       */
    private lateinit var textViewNumeroContaActivityLogin: TextView
    private lateinit var imageViewVoltar01: ImageView
    private lateinit var textViewVoltar01: TextView
    private lateinit var botaoContinuarActivityLogin: Button
    private lateinit var qualSenha: EditText
    private lateinit var textViewNomeActivityLogin: TextView
    private lateinit var textViewEmailActivityLogin: TextView
    private var usuarioFirebaseActivityLogin: String = ""
    private var emailFirebaseActivityLogin: String = ""
    private var contaFirebaseActivityLogin: String = ""
    val bancoDeDadosFirebaseActivityLogin = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*
        Bloquear a função de orientação de tela:
         */
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /*
        Declaração de variáveis:
         */
        textViewNomeActivityLogin =
            findViewById<TextView>(R.id.textViewNomeClienteActivityLoginId)
       textViewNumeroContaActivityLogin =
            findViewById<TextView>(R.id.textViewNumeroContaLoginActivityId)
        imageViewVoltar01 = findViewById<ImageView>(R.id.imageViewVoltarLoginId)
        textViewVoltar01 = findViewById<TextView>(R.id.textViewVoltarLoginId)
        botaoContinuarActivityLogin = findViewById<Button>(R.id.botaoAContinuarActivityLoginId)
        textViewEmailActivityLogin = findViewById(R.id.textViewEmailLoginActivityId)

        /*
        Criar Funções:
         */
        textViewVoltar01.setOnClickListener { textViewVoltarActivityLogin() }
        botaoContinuarActivityLogin.setOnClickListener { botaoContinuar() }
        imageViewVoltar01.setOnClickListener { imageViewVoltarActivityLogin() }


    }

    /*
    Função botaoContinuar():
     */
    private fun botaoContinuar() {
        //Declaração das variáveis:

        //Reconhecer usuário atual e fazer LOGIN no Firebase:

        if (qualSenha.text.toString().isEmpty()) {
            val snackBar = Snackbar.make(
                botaoContinuarActivityLogin,
                "Digite a sua senha.",
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
    }

    /*
    Função imageViewVoltarActivityLogin():
     */
    private fun imageViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        // startActivity(intent)
        finish()
    }

    /*
    Função textViewVoltarActivityLogin():
    */
    private fun textViewVoltarActivityLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        // startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        usuarioFirebaseActivityLogin = FirebaseAuth.getInstance().currentUser!!.uid
        emailFirebaseActivityLogin = FirebaseAuth.getInstance().currentUser.toString()
        contaFirebaseActivityLogin = FirebaseAuth.getInstance().currentUser.toString()

        val documentReference =
            bancoDeDadosFirebaseActivityLogin.collection("UsuariosFirebase")
                .document(usuarioFirebaseActivityLogin)
        documentReference.addSnapshotListener { value, error ->
            if (value != null) {
               textViewNomeActivityLogin.text = value.getString("NomeUsuario")
               textViewEmailActivityLogin.text = value.getString("EmailUsuario")
               textViewNumeroContaActivityLogin.text = value.getString("NumeroConta")
            }
        }
    }

}