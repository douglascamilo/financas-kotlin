package br.com.financask.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import br.com.financask.ui.ResumoView
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_lista_transacoes)

        val listaTransacoes = listOf(
            Transacao(BigDecimal("20.5"), "Comida", TipoTransacao.DESPESA),
            Transacao(BigDecimal("100"), "Economia", TipoTransacao.RECEITA),
            Transacao(BigDecimal("51.79"), "Almoco de final de semana", TipoTransacao.RECEITA)
        )

        this.configuraResumo(listaTransacoes)
        this.configuraLista(listaTransacoes)

        val view = window.decorView
        val viewCriada = LayoutInflater.from(this)
            .inflate(R.layout.form_transacao, view as ViewGroup, false)

        lista_transacoes_adiciona_receita.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCriada)
                .show()
        }
    }

    private fun configuraLista(listaTransacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }

    private fun configuraResumo(listaTransacoes: List<Transacao>) {
        ResumoView(this, window.decorView, listaTransacoes)
            .atualiza()
    }
}