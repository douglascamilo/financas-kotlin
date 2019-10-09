package br.com.financask.model

enum class TipoTransacao {
    RECEITA,
    DESPESA,
    ;

    fun isDespesa(): Boolean {
        return this == DESPESA
    }
}