package com.example.bancobradesil10


import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



class ActivityConta : AppCompatActivity() {

    /**
     * Variáveis em escopo global:
     */
    private lateinit var imageViewVisivel: ImageView
    lateinit var imageViewNaoVisivel: ImageView
    lateinit var nomeCliente: TextView
    private lateinit var faleBia: AutoCompleteTextView
    lateinit var textViewSaldo: TextView
    lateinit var textViewSairDaConta: TextView
    lateinit var imageViewSairDaConta: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta)

        /**
         * Declaração de variáveis:
         */
       declararVariaveis()

        /**
         * Instância de funções:
         */
       criarFuncoes()

        /**
         * Função para Saldo não visível:
         */
        saldoNaoVisivel()

    }

    /**
     * Funções:
     */
    private fun textViewSair() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        imageViewVisivel
    }

    private fun imageViewSair() {
        textViewSair()
    }

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

    private fun olhoVisivel() {
        if (imageViewVisivel.isClickable) {
            textViewSaldo.background = getDrawable(R.color.ocultarOlhoVisivel)
            imageViewVisivel.setColorFilter(R.color.ocultarOlhoVisivel, PorterDuff.Mode.CLEAR)
            imageViewNaoVisivel.setColorFilter(R.color.white01, PorterDuff.Mode.DST)
        }
    }

    override fun onStart() {
        super.onStart()

        val usuarioId: String = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val documentReference = db.collection("Usuarios Bradesil").document(usuarioId)
        documentReference.addSnapshotListener { documentSnapshot, error ->
            if (documentSnapshot != null) {
                nomeCliente.text = documentSnapshot.getString("nomeFirebase")
//                textViewEmailActivityLogin.text = documentSnapshot.getString("emailUsuario")
//                textViewNumeroContaActivityLogin.text = documentSnapshot.getString("contaUsuario")
            }
        }
    }

    private fun declararVariaveis() {
        imageViewVisivel = findViewById(R.id.imageViewVisivelActivityContaId)
        textViewSaldo = findViewById(R.id.textViewValorSaldoContaActivityId)
        imageViewNaoVisivel = findViewById(R.id.imageViewNaoVisivelActivityContaId)
        nomeCliente = findViewById(R.id.textViewNomeClienteActivityContaId)
        faleBia = findViewById(R.id.autoCompleteFaleBiaContaActivityId)
        textViewSairDaConta = findViewById(R.id.textViewSairActivityContaId)
        imageViewSairDaConta = findViewById(R.id.imageViewSairActivityContaId)
    }

    private fun criarFuncoes () {
        imageViewSairDaConta.setOnClickListener { imageViewSair() }
        textViewSairDaConta.setOnClickListener { textViewSair() }
        imageViewVisivel.setOnClickListener { olhoVisivel() }
        imageViewNaoVisivel.setOnClickListener { olhoNaoVisivel() }
        faleBia.setOnClickListener { faleComBia() }
    }

    private fun saldoNaoVisivel() {

        imageViewNaoVisivel.setColorFilter(
            R.color.corSecundariaVariante,
            PorterDuff.Mode.CLEAR
        )

    }
}