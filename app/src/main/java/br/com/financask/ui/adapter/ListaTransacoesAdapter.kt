package br.com.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.financask.R

class ListaTransacoesAdapter(
    private val transacoes: List<String>,
    private val context: Context
    ): BaseAdapter() {

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)

        return view
    }

    override fun getItem(posicao: Int): String {
        return transacoes[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}