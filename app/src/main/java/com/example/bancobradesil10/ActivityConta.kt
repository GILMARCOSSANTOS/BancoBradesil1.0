package com.example.bancobradesil10

import android.R.id
import android.content.ClipData
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.view.*
import android.widget.*
import android.graphics.drawable.Drawable as Drawable1
import android.R.id.button1

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
        val faleBia = findViewById<EditText>(R.id.editTextFaleBiaContaActivityId)

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
         * Invocar para usar a função Menu:
         */
        registerForContextMenu(faleBia)

        /**
         * Criar Funções:
         */
        botaoSair.setOnClickListener { botaoSair() }
        imageViewVisivel.setOnClickListener { olhoVisivel() }
        imageViewNaoVisivel.setOnClickListener { olhoNaoVisivel() }
        faleBia.setOnClickListener{ faleComBia()}

    }

    /**
     * Função Menu de Opções FaleComBia():
     */
    private fun faleComBia() {
        val faleBia = findViewById<EditText>(R.id.editTextFaleBiaContaActivityId)
        showPopup(faleBia)
    }

    private fun showPopup(view: View){
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.meu_menu_um)
        popup.show()
    }





    /**
     * Função olhoNaoVisivel():
     */
    private fun olhoNaoVisivel() {
        val textViewSaldo = findViewById<TextView>(R.id.textViewSaldoReaisContaActivityId)
        val imageViewNaoVisivel = findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
        val imageViewVisivel = findViewById<ImageView>(R.id.imageViewVisivelActivityContaId)

        if (imageViewNaoVisivel.isClickable) {
            textViewSaldo.background = getDrawable(R.drawable.botao_redondo03)
            imageViewNaoVisivel.setColorFilter(R.color.ocultarOlhoVisivel, PorterDuff.Mode.CLEAR)
            imageViewVisivel.setColorFilter(R.color.white01, PorterDuff.Mode.DST)
        }
    }

    /**
     * Função olhoVisivel():
     */
    private fun olhoVisivel() {
        val textViewSaldo = findViewById<TextView>(R.id.textViewSaldoReaisContaActivityId)
        val imageViewNaoVisivel = findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
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