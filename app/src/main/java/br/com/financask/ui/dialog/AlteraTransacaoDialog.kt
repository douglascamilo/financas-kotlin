package br.com.financask.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import br.com.financask.R
import br.com.financask.delegate.TransacaoDelegate
import br.com.financask.extension.converteParaBigDecimal
import br.com.financask.extension.converteParaCalendar
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

class AlteraTransacaoDialog(
    private val context: Context,
    viewGroup: ViewGroup) {

    private val viewCriada = this.criaLayout(context, viewGroup)
    private val campoValor = viewCriada.form_transacao_valor
    private val campoData = viewCriada.form_transacao_data
    private val campoCategoria = viewCriada.form_transacao_categoria

    fun mostrar(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val tipoTransacao = transacao.tipo

        this.configuraCampoData(transacao.data)
        this.configuraCampoCategoria(tipoTransacao)
        this.configuraFormulario(tipoTransacao, transacaoDelegate)

        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formataParaBrasileiro())

        val categoriasCarregadas = context.resources.getStringArray(tipoTransacao.categorias)
        val indiceCategoriaSelecionada = categoriasCarregadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(indiceCategoriaSelecionada, true)
    }

    private fun configuraFormulario(
            tipoTransacao: TipoTransacao,
            transacaoDelegate: TransacaoDelegate) {

        AlertDialog.Builder(context)
            .setTitle(tipoTransacao.tituloAlteracao)
            .setView(viewCriada)
            .setPositiveButton("Alterar") { _, _ ->
                val valor = campoValor.text.toString().converteParaBigDecimal()
                val data = campoData.text.toString().converteParaCalendar()
                val categoria = campoCategoria.selectedItem.toString()

                val transacao = Transacao(valor, categoria, tipoTransacao, data)
                transacaoDelegate.delegate(transacao)
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

    private fun configuraCampoData(data: Calendar) {
        val ano = data.get(Calendar.YEAR)
        val mes = data.get(Calendar.MONTH)
        val dia = data.get(Calendar.DAY_OF_MONTH)

        campoData.setText(data.formataParaBrasileiro())
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

    private fun criaLayout(context: Context, viewGroup: ViewGroup): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }
}