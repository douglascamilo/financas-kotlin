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
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
        view.resumo_card_receita.setTextColor(this.obterCorPor(R.color.receita))
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
        view.resumo_card_despesa.setTextColor(this.obterCorPor(R.color.despesa))
    }

    fun total() {
        val total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
        view.resumo_card_total.setTextColor(this.obterCorTotal(total))
    }

    private fun obterCorTotal(total: BigDecimal): Int {
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            return this.obterCorPor(R.color.despesa)
        }

        return this.obterCorPor(R.color.receita)
    }

    private fun obterCorPor(idResourceBundle: Int) = ContextCompat.getColor(context, idResourceBundle)
}