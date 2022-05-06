package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.okhttp.*
import java.io.IOException
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /**
     * Declaração de variáveis em Escopo Global:
     */

    private val client = OkHttpClient()
    private lateinit var textViewNomeConta: TextView
    private lateinit var textViewNumeroConta: TextView
    private lateinit var texViewEmail: TextView
    private lateinit var botaoAcessarConta: Button
    private lateinit var botaoCriarconta: Button
    private lateinit var imageButtonContasCadastradas: ImageButton
    private lateinit var checkBoxLembrar: CheckBox
    private lateinit var textViewAContasCadastradas: TextView
    private lateinit var listViewEmail: ListView
    private var listViewEmailArrayList: ArrayList<String>? = null
    private var adapterListEmail: ArrayAdapter<String>? = null

    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Bloquear Orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Inicializações de Funções:
         */
        inicializarVariaveis()
        inicializarFuncoes()
        sharedPreferencesReceberDados()

    }

    private fun imageButtonEntrarContasCadastradas() {
        val intent = Intent1(this, ActivityContasCadastradas::class.java).apply {
        }
        startActivity(intent)
    }

    private fun textViewEntrarContasCadastradas() {
        imageButtonEntrarContasCadastradas()
    }

    private fun criarConta() {
        val intent = Intent1(this, ActivityCriarConta::class.java).apply {
        }
        finish()
        startActivity(intent)
    }

    private fun acessarConta() {
        val intent = Intent1(this, ActivityLogin::class.java).apply {
        }
        finish()
        startActivity(intent)
    }

    private fun inicializarVariaveis() {
        botaoAcessarConta = findViewById(R.id.botao_acessarConta_mainActvt_Id)
        textViewNomeConta = findViewById(R.id.txtVw_nomeCliente_componentDados_id)
        textViewNumeroConta = findViewById(R.id.txtVw_contaCliente_componentDados_id)
        texViewEmail = findViewById(R.id.txtVw_emailCliente_componentDados_id)
        botaoCriarconta = findViewById(R.id.botao_criarConta_mainActvt_Id)
        checkBoxLembrar = findViewById(R.id.chckBx_lembrarUsuario_actvtMain_id)
        textViewAContasCadastradas =
            findViewById(R.id.txtVw_acessarContasCadastradas_mainActvt_id)
        imageButtonContasCadastradas = findViewById(R.id.imageButtonContasCadastradasMainActivityId)
        listViewEmail = findViewById(R.id.lstVw_listaEmails_actvt_Main_id)
    }

    private fun inicializarFuncoes() {
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        textViewAContasCadastradas.setOnClickListener { textViewEntrarContasCadastradas() }
        imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarContasCadastradas() }
        checkBoxLembrar.setOnClickListener { lembrarUsuario() }
    }

    private fun lembrarUsuario() {
        if (checkBoxLembrar.isClickable && textViewNomeConta.text != "Nome:" &&
            texViewEmail.text != "E-Mail:" && textViewNumeroConta.text != "Nº Conta:"
        ) {

            listViewEmailArrayList = ArrayList()
            adapterListEmail = ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                listViewEmailArrayList!!
            )
            listViewEmail.adapter = adapterListEmail
            listViewEmailArrayList!!.add(texViewEmail.text.toString())
            adapterListEmail!!.notifyDataSetChanged()
        }
    }

    private fun sharedPreferencesReceberDados() {
        val sharedPreference = getSharedPreferences("chaveSP_ActvtConta", Context.MODE_PRIVATE)
        val nomeSP = sharedPreference.getString("chaveNomeActvtConta", "Nome:")
        textViewNomeConta.setText(nomeSP)
        val emailSP = sharedPreference.getString("chaveEmailActvtConta", "E-Mail: ")
        texViewEmail.setText(emailSP)
        val contaSP = sharedPreference.getString("chaveContaActvtConta", "Nº Conta: ")
        textViewNumeroConta.setText(contaSP)
    }
}

