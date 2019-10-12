package br.com.financask.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import br.com.financask.R
import br.com.financask.extension.converteParaBigDecimal
import br.com.financask.extension.converteParaCalendar
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

abstract class FormularioTransacaoDialog(
    private val context: Context,
    private val viewGroup: ViewGroup) {

    private val viewCriada = this.criaLayout()
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoData = viewCriada.form_transacao_data
    protected val campoCategoria = viewCriada.form_transacao_categoria
    protected abstract val textoBotaoConfirmacao: String

    fun mostrar(
        tipoTransacao: TipoTransacao,
        delegate: (transacao: Transacao) -> Unit) {

        this.configuraCampoData()
        this.configuraCampoCategoria(tipoTransacao)
        this.configuraFormulario(tipoTransacao, delegate)
    }

    private fun configuraFormulario(
        tipoTransacao: TipoTransacao,
        delegate: (transacao: Transacao) -> Unit) {

        AlertDialog.Builder(context)
            .setTitle(this.getIdTitulo(tipoTransacao))
            .setView(viewCriada)
            .setPositiveButton(textoBotaoConfirmacao) { _, _ ->
                val valor = campoValor.text.toString().converteParaBigDecimal()
                val data = campoData.text.toString().converteParaCalendar()
                val categoria = campoCategoria.selectedItem.toString()

                val transacao = Transacao(valor, categoria, tipoTransacao, data)
                delegate(transacao)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun configuraCampoCategoria(tipoTransacao: TipoTransacao) {
        val categoriasAdapter = ArrayAdapter.createFromResource(
            context,
            tipoTransacao.categorias,
            android.R.layout.simple_spinner_dropdown_item)

        campoCategoria.adapter = categoriasAdapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formataParaBrasileiro())
        campoData.setOnClickListener {
            DatePickerDialog(
                context,
                { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)

                    campoData.setText(dataSelecionada.formataParaBrasileiro())
                },
                ano, mes, dia
            ).show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }

    protected abstract fun getIdTitulo(tipoTransacao: TipoTransacao): Int
}