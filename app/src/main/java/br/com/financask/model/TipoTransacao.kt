package br.com.financask.model

import br.com.financask.R

enum class TipoTransacao(
    var cor: Int,
    var icone: Int
    ) {

    RECEITA(R.color.receita, R.drawable.icone_transacao_item_receita),
    DESPESA(R.color.despesa, R.drawable.icone_transacao_item_despesa),
    ;
}