package com.example.bancobradesil10

import android.app.ProgressDialog.show
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.Gravity.*
import android.widget.*
import androidx.core.graphics.green
import android.widget.PopupWindow
import com.google.android.material.transition.MaterialSharedAxis
import android.graphics.drawable.Drawable


import android.widget.FrameLayout


import android.view.LayoutInflater

import android.R.layout
import android.content.Context
import androidx.core.widget.PopupWindowCompat.showAsDropDown
import kotlin.math.absoluteValue


class ActivityConta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta)

        /**
         * Bloquear função de orientação de tela:
         */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        /**
         * Declaração de Variáveis:
         */
        val imageViewVisivel = findViewById<ImageView>(R.id.imageViewVisivelActivityContaId)
        val imageViewNaoVisivel = findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
        val botaoSair = findViewById<ImageView>(R.id.imageViewSairActivityContaId)
        val nomeCliente = findViewById<TextView>(R.id.textViewNomeClienteActivityContaId)
        val mensagemNomeActivityConta = intent.getStringExtra("chaveNomeConta")
        val faleBia = findViewById<AutoCompleteTextView>(R.id.autoCompleteFaleBiaContaActivityId)

        /**
         * Recebimento dos dados do nome da Conta:
         */
        nomeCliente.apply {
            text = mensagemNomeActivityConta
        }

        /**
         *OlhoNãoVisivel inicia de forma oculta:
         */
        imageViewNaoVisivel.setColorFilter(
            R.color.corSecundariaVariante,
            PorterDuff.Mode.CLEAR
        )

        /**
         * Criar Funções:
         */
        botaoSair.setOnClickListener { botaoSair() }
        imageViewVisivel.setOnClickListener { olhoVisivel() }
        imageViewNaoVisivel.setOnClickListener { olhoNaoVisivel() }
        faleBia.setOnClickListener { faleComBia() }
    }


    /**
     * Função Menu de Opções FaleComBia():
     */
    private fun faleComBia() {

        val faleBia = findViewById<AutoCompleteTextView>(R.id.autoCompleteFaleBiaContaActivityId)
        val opcoesMenuBia = arrayOf<String>(
            "Quero meu informe de rendimentos",
            "Quero pagar uma conta",
            "Preciso transferir dinheiro"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcoesMenuBia)
        faleBia.setAdapter(adapter)

        when (faleBia.text.toString()) {
            "Quero meu informe de rendimentos" -> Toast.makeText(
                this,
                "Rendimentos",
                Toast.LENGTH_SHORT
            ).show()
            "Quero pagar uma conta" -> Toast.makeText(this, "Pagar", Toast.LENGTH_SHORT).show()
            "Preciso transferir dinheiro" -> Toast.makeText(this, "Teansferir", Toast.LENGTH_SHORT)
                .show()
        }
        faleBia.showDropDown()
    }

    /**
     * Função olhoNaoVisivel():
     */
    private fun olhoNaoVisivel() {
        val textViewSaldo = findViewById<TextView>(R.id.textViewSaldoReaisContaActivityId)
        val imageViewNaoVisivel =
            findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
        val imageViewVisivel = findViewById<ImageView>(R.id.imageViewVisivelActivityContaId)

        if (imageViewNaoVisivel.isClickable) {
            textViewSaldo.background = getDrawable(R.drawable.botao_redondo03)
            imageViewNaoVisivel.setColorFilter(
                R.color.ocultarOlhoVisivel,
                PorterDuff.Mode.CLEAR
            )
            imageViewVisivel.setColorFilter(R.color.white01, PorterDuff.Mode.DST)
        }
    }

    /**
     * Função olhoVisivel():
     */
    private fun olhoVisivel() {
        val textViewSaldo = findViewById<TextView>(R.id.textViewSaldoReaisContaActivityId)
        val imageViewNaoVisivel =
            findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
        val imageViewVisivel = findViewById<ImageView>(R.id.imageViewVisivelActivityContaId)

        if (imageViewVisivel.isClickable) {
            textViewSaldo.background = getDrawable(R.color.ocultarOlhoVisivel)
            imageViewVisivel.setColorFilter(R.color.ocultarOlhoVisivel, PorterDuff.Mode.CLEAR)
            imageViewNaoVisivel.setColorFilter(R.color.white01, PorterDuff.Mode.DST)
        }
    }

    /**
     * Função botaoSair():
     */
    private fun botaoSair() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}





