package br.com.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val listaTransacoes = listOf(
            Transacao(BigDecimal(20.5), "Comida", TipoTransacao.DESPESA),
            Transacao(BigDecimal(100), "Economia", TipoTransacao.RECEITA),
            Transacao(BigDecimal(51.79), tipo = TipoTransacao.RECEITA)
        )

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }
}