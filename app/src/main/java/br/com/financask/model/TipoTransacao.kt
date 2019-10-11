package br.com.financask.model

import br.com.financask.R

enum class TipoTransacao(
    var cor: Int,
    var icone: Int,
    var categorias: Int,
    var titulo: Int
    ) {

    RECEITA(
        R.color.receita,
        R.drawable.icone_transacao_item_receita,
        R.array.categorias_de_receita,
        R.string.adiciona_receita),

    DESPESA(
        R.color.despesa,
        R.drawable.icone_transacao_item_despesa,
        R.array.categorias_de_despesa,
        R.string.adiciona_despesa),

    ;
}