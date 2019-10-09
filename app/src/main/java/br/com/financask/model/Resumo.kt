package br.com.financask.model

import java.math.BigDecimal

class Resumo(
    private val transacoes: List<Transacao>
    ) {

    fun receita(): BigDecimal {
        return BigDecimal.ZERO
    }

    fun despesa(): BigDecimal {
        return BigDecimal.ZERO
    }

    fun total(): BigDecimal {
        return this.receita().subtract(this.despesa())
    }
}