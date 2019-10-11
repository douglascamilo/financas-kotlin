package br.com.financask.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.delegate.TransacaoDelegate
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import br.com.financask.ui.ResumoView
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import br.com.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {
    private val listaTransacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFAB()
    }

    private fun configuraFAB() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaAdicionaTransacaoDialog(TipoTransacao.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaAdicionaTransacaoDialog(TipoTransacao.DESPESA)
        }
    }

    private fun chamaAdicionaTransacaoDialog(tipoTransacao: TipoTransacao) {
        AdicionaTransacaoDialog(this, window.decorView as ViewGroup)
            .mostrar(tipoTransacao, object : TransacaoDelegate {

                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        listaTransacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(listaTransacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { parent, view, posicao, id ->
            }
        }
    }

    private fun configuraResumo() {
        ResumoView(this, window.decorView, listaTransacoes)
            .atualiza()
    }
}