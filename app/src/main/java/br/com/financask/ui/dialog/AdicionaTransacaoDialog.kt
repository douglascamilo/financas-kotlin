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

class AdicionaTransacaoDialog(
    private val context: Context,
    viewGroup: ViewGroup) {

    val viewCriada = this.criaLayout(context, viewGroup)

    fun configuraDialog(tipoTransacao: TipoTransacao, transacaoDelegate: TransacaoDelegate) {
        this.configuraCampoData()
        this.configuraCampoCategoria(tipoTransacao)
        this.configuraFormulario(tipoTransacao, transacaoDelegate)
    }

    private fun configuraFormulario(
            tipoTransacao: TipoTransacao,
            transacaoDelegate: TransacaoDelegate) {

        AlertDialog.Builder(context)
            .setTitle(tipoTransacao.titulo)
            .setView(viewCriada)
            .setPositiveButton("Adicionar") { _, _ ->
                val valor = viewCriada.form_transacao_valor.text.toString().converteParaBigDecimal()
                val data = viewCriada.form_transacao_data.text.toString().converteParaCalendar()
                val categoria = viewCriada.form_transacao_categoria.selectedItem.toString()

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

        viewCriada.form_transacao_categoria.adapter = categoriasAdapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                context,
                { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)

                    viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
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