package br.com.financask.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.extension.formataParaBrasileiro
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import br.com.financask.ui.ResumoView
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.activity_lista_transacoes.view.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_lista_transacoes)

        val listaTransacoes = listOf(
            Transacao(BigDecimal("20.5"), "Comida", TipoTransacao.DESPESA),
            Transacao(BigDecimal("100"), "Economia", TipoTransacao.RECEITA),
            Transacao(BigDecimal("51.79"), "Almoco de final de semana", TipoTransacao.RECEITA)
        )

        this.configuraResumo(listaTransacoes)
        this.configuraLista(listaTransacoes)

        val view = window.decorView
        val viewCriada = LayoutInflater.from(this)
            .inflate(R.layout.form_transacao, view as ViewGroup, false)

        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                this,
                { view, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                },
                ano, mes, dia)
                .show()
        }

        val categoriasAdapter = ArrayAdapter.createFromResource(
            this, R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)
        viewCriada.form_transacao_categoria.adapter = categoriasAdapter

        lista_transacoes_adiciona_receita.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCriada)
                .setPositiveButton("Adicionar") { _, _ ->
                    val valor = viewCriada.form_transacao_valor.text.toString()
                    val data = viewCriada.form_transacao_data.text.toString()
                    val categoria = viewCriada.form_transacao_categoria.selectedItem.toString()

                    val dataTransacao = Calendar.getInstance()
                    dataTransacao.time = SimpleDateFormat("dd/MM/yyyy").parse(data)

                    Transacao(BigDecimal(valor), categoria, TipoTransacao.RECEITA, dataTransacao)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun configuraLista(listaTransacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }

    private fun configuraResumo(listaTransacoes: List<Transacao>) {
        ResumoView(this, window.decorView, listaTransacoes)
            .atualiza()
    }
}