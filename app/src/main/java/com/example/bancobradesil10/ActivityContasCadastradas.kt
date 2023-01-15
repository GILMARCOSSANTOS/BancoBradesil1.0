package com.example.bancobradesil10

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ActivityContasCadastradas : AppCompatActivity() {

    /* Declaração de variáveis em Escopo Global: */
    private lateinit var imageViewVoltar: ImageView;
    private lateinit var textViewVoltar: TextView;
    private lateinit var listViewEmailsClientes: ListView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contas_cadastradas)

        /* Criar Funçõoes: */
        inicializarFuncoes()
        bloquearOrientacaoTela()
        inicializarVariaveis()
        listarEmail()
        listarEmail()
    }

    private fun textViewVoltarContasCadastradas() {
        val intent = Intent(this, MainActivity::class.java).apply {
        };
        finish();
        startActivity(intent);
    }

    private fun imageViewVoltarContasCadastradas() {
        textViewVoltarContasCadastradas();
    }

    private fun inicializarVariaveis() {
        textViewVoltar = findViewById(R.id.txtVw_voltar_contasCadastradas_id);
        imageViewVoltar = findViewById(R.id.imgVw_voltar_contasCadastradas_id);
        listViewEmailsClientes = findViewById(R.id.lstVw_emails_actct_contasCadastradas_id);
    }

    private fun inicializarFuncoes() {
        textViewVoltar.setOnClickListener { textViewVoltarContasCadastradas() };
        imageViewVoltar.setOnClickListener { imageViewVoltarContasCadastradas() };
    }

    private fun listarEmail() {
        val listViewEmail: ArrayList<String> = java.util.ArrayList();
        listViewEmail.add("");
        val listaView: ListView = findViewById(R.id.lstVw_emails_actct_contasCadastradas_id);
        val meuAdapterView: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewEmail)
        listaView.setAdapter(meuAdapterView);

    }

    private fun bloquearOrientacaoTela() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }
}