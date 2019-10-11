package br.com.financask.model

import br.com.financask.R

enum class TipoTransacao(
    val cor: Int,
    val icone: Int,
    val categorias: Int,
    val tituloInclusao: Int,
    val tituloAlteracao: Int
    ) {

    RECEITA(
        R.color.receita,
        R.drawable.icone_transacao_item_receita,
        R.array.categorias_de_receita,
        R.string.adiciona_receita,
        R.string.altera_receita),

    DESPESA(
        R.color.despesa,
        R.drawable.icone_transacao_item_despesa,
        R.array.categorias_de_despesa,
        R.string.adiciona_despesa,
        R.string.altera_despesa),

    ;
}