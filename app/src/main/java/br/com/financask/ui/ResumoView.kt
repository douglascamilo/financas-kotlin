package br.com.financask.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.financask.R
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.Resumo
import br.com.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        with(view.resumo_card_receita) {
            text = totalReceita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        with(view.resumo_card_despesa) {
            text = totalDespesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    fun total() {
        val total = resumo.total()
        with(view.resumo_card_total) {
            text = total.formataParaBrasileiro()
            setTextColor(obterCorTotal(total))
        }
    }

    private fun obterCorTotal(total: BigDecimal): Int {
        if (total < BigDecimal.ZERO) {
            return corDespesa
        }

        return corReceita
    }
}