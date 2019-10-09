package br.com.financask.model

import java.math.BigDecimal

class Resumo(
    private val transacoes: List<Transacao>
    ) {

    fun receita(): BigDecimal {
        val totalReceitas = transacoes
            .filter { item -> item.tipo.isReceita() }
            .sumByDouble { item -> item.valor.toDouble() }

        return BigDecimal(totalReceitas)
    }

    fun despesa(): BigDecimal {
        val totalDespesas = transacoes
            .filter { item -> item.tipo.isDespesa() }
            .sumByDouble { item -> item.valor.toDouble() }

        return BigDecimal(totalDespesas)
    }

    fun total(): BigDecimal {
        return this.receita().subtract(this.despesa())
    }
}