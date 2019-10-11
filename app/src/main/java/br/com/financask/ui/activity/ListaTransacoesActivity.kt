package br.com.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.model.Transacao
import br.com.financask.ui.ResumoView
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {
    private val listaTransacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_lista_transacoes)

        this.configuraResumo()
        this.configuraLista()

        lista_transacoes_adiciona_receita.setOnClickListener {
            configuraDialog()
        }
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        listaTransacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }

    private fun configuraResumo() {
        ResumoView(this, window.decorView, listaTransacoes)
            .atualiza()
    }
}