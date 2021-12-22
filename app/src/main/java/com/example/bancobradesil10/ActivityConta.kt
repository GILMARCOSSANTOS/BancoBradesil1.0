package com.example.bancobradesil10


import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ActivityConta : AppCompatActivity() {

    /**
     * Variáveis em escopo global:
     */
    lateinit var imageViewVisivel: ImageView
    lateinit var imageViewNaoVisivel: ImageView
    lateinit var botaoSair: ImageView
    lateinit var nomeCliente: TextView
    lateinit var faleBia: AutoCompleteTextView
    lateinit var textViewSaldo: TextView

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
        textViewSaldo = findViewById<TextView>(R.id.textViewSaldoReaisContaActivityId)
        imageViewVisivel = findViewById<ImageView>(R.id.imageViewVisivelActivityContaId)
        imageViewNaoVisivel = findViewById<ImageView>(R.id.imageViewNaoVisivelActivityContaId)
        botaoSair = findViewById<ImageView>(R.id.imageViewSairActivityContaId)
        nomeCliente = findViewById<TextView>(R.id.textViewNomeClienteActivityContaId)
        faleBia = findViewById<AutoCompleteTextView>(R.id.autoCompleteFaleBiaContaActivityId)

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