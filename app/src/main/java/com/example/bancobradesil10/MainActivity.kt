package com.example.bancobradesil10

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.*
import java.util.*
import android.content.Intent as Intent1
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    /* Declaração de variáveis em Escopo Global:  */

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
    private var emailArrayList: ArrayList<String>? = null
    private var adapterListEmail: ArrayAdapter<String>? = null
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Bloquear Orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        /**
         * Inicialização de Funções:
         */
        inicializarVariaveis()
        inicializarFuncoes()
        sharedPreferencesReceberDadosActvtConta()
        deletarUsuario()
        // selecionarItemListView()
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
        emailArrayList = java.util.ArrayList()
        adapterListEmail = ArrayAdapter(this, android.R.layout.simple_list_item_1, emailArrayList!!)
        listViewEmail.adapter = adapterListEmail
        sharedPreferences = getSharedPreferences("chaveSpListView", MODE_PRIVATE)
    }

    private fun inicializarFuncoes() {
        botaoAcessarConta.setOnClickListener { acessarConta() }
        botaoCriarconta.setOnClickListener { criarConta() }
        textViewAContasCadastradas.setOnClickListener { textViewEntrarContasCadastradas() }
        imageButtonContasCadastradas.setOnClickListener { imageButtonEntrarContasCadastradas() }
        checkBoxLembrar.setOnClickListener { lembrarUsuario() }
        lerSharedPreferences()
    }

    private fun lembrarUsuario() {
        if (textViewNomeConta.text != "Nome: " &&
            texViewEmail.text != "E-Mail: " && textViewNumeroConta.text != "Nº Conta: "
        ) {
            emailArrayList!!.add(texViewEmail.text.toString())
            adapterListEmail!!.notifyDataSetChanged()
            val addSharedPreferences = sharedPreferences.edit()
            addSharedPreferences.putString(texViewEmail.text.toString(), "")
            addSharedPreferences.apply()
        }
    }

    private fun deletarUsuario() {

        listViewEmail.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.remove("datostelefonos")
                editor.commit()

                emailArrayList?.removeAt(position)
                adapterListEmail?.notifyDataSetChanged()

            }
    }

    private fun selecionarItemListView() {

        listViewEmail.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent1(this, ActivityLogin::class.java).apply {
            }
            startActivity(intent)
        }
    }

    private fun sharedPreferencesReceberDadosActvtConta() {
        val sharedPreference = getSharedPreferences("chaveSP_ActvtConta", Context.MODE_PRIVATE)

        val nomeSP = sharedPreference.getString("chaveNomeActvtConta", "Nome: ")
        textViewNomeConta.text = nomeSP

        val emailSP = sharedPreference.getString("chaveEmailActvtConta", "E-Mail: ")
        texViewEmail.setText(emailSP)

        val contaSP = sharedPreference.getString("chaveContaActvtConta", "Nº Conta: ")
        textViewNumeroConta.setText(contaSP)
    }

    private fun lerSharedPreferences() {
        // sharedPreferences = getSharedPreferences("datostelefonos", MODE_PRIVATE)
        val claves = sharedPreferences.all
        for ((key, value) in claves) {
            emailArrayList!!.add(key + value.toString())
        }
    }
}







