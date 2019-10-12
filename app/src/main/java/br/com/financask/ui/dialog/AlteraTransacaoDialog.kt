package br.com.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.financask.delegate.TransacaoDelegate
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao

class AlteraTransacaoDialog(
    private val context: Context,
    viewGroup: ViewGroup): FormularioTransacaoDialog(context, viewGroup) {

    override val textoBotaoConfirmacao: String get() = "Alterar"

    fun mostrar(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        super.mostrar(transacao.tipo, transacaoDelegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(transacao)
    }

    private fun inicializaCampoCategoria(transacao: Transacao) {
        val tipoTransacao = transacao.tipo

        val categoriasCarregadas = context.resources.getStringArray(tipoTransacao.categorias)
        val indiceCategoriaSelecionada = categoriasCarregadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(indiceCategoriaSelecionada, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

    override fun getIdTitulo(tipoTransacao: TipoTransacao): Int {
        return tipoTransacao.tituloAlteracao
    }
}