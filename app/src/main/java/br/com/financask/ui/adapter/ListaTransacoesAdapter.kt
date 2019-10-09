package br.com.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.financask.R
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
    ): BaseAdapter() {

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent, false)

        val transacao = this.getItem(posicao)

        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
        viewCriada.transacao_categoria.text = transacao.categoria
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
        viewCriada.transacao_valor.setTextColor(this.obterTransacaoValorTextColor(transacao))
        viewCriada.transacao_icone.setBackgroundResource(this.obterIconeTransacao(transacao))

        return viewCriada
    }

    private fun obterIconeTransacao(transacao: Transacao): Int {
        return if (transacao.isDespesa()) {
            R.drawable.icone_transacao_item_despesa
        } else {
            R.drawable.icone_transacao_item_receita
        }
    }

    private fun obterTransacaoValorTextColor(transacao: Transacao): Int {
        return if (transacao.isDespesa()) {
            ContextCompat.getColor(context, R.color.despesa)
        } else {
            ContextCompat.getColor(context, R.color.receita)
        }
    }

    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}