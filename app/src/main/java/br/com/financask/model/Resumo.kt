package br.com.financask.model

import java.math.BigDecimal

class Resumo(
    private val transacoes: List<Transacao>
    ) {

    val receita get() = this.somaPor(TipoTransacao.RECEITA)

    val despesa get() = this.somaPor(TipoTransacao.DESPESA)

    val total get() = this.receita.subtract(this.despesa)

    private fun somaPor(tipo: TipoTransacao): BigDecimal {
        val valorRetorno = transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() }

        return BigDecimal(valorRetorno)
    }
}