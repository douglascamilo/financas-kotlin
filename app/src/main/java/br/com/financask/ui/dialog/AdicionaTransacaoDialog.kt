package br.com.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.financask.model.TipoTransacao

class AdicionaTransacaoDialog(
    context: Context,
    viewGroup: ViewGroup): FormularioTransacaoDialog(context, viewGroup) {

    override val textoBotaoConfirmacao: String get() = "Adicionar"

    override fun getIdTitulo(tipoTransacao: TipoTransacao): Int {
        return tipoTransacao.tituloInclusao
    }
}