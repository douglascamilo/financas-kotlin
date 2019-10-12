package br.com.financask.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.model.TipoTransacao
import br.com.financask.model.Transacao
import br.com.financask.ui.ResumoView
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import br.com.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {
    private val listaTransacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

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

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
        lista_transacoes_listview.setOnItemClickListener { _, _, posicao, _ ->
            chamaAlteraTransacaoDialog(posicao)
        }
    }

    private fun chamaAdicionaTransacaoDialog(tipoTransacao: TipoTransacao) {
        AdicionaTransacaoDialog(this, viewGroupDaActivity)
            .mostrar(tipoTransacao, delegate = { transacaoAdicionada ->
                adiciona(transacaoAdicionada)
                lista_transacoes_adiciona_menu.close(true)
            })
    }

    private fun chamaAlteraTransacaoDialog(posicao: Int) {
        val transacaoSelecionada = listaTransacoes[posicao]

        AlteraTransacaoDialog(this, viewGroupDaActivity)
            .mostrar(transacaoSelecionada) { transacaoAlterada ->
                altera(transacaoAlterada, posicao)
            }
    }

    private fun adiciona(transacao: Transacao) {
        listaTransacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        listaTransacoes[posicao] = transacao
        atualizaTransacoes()
    }

    private fun configuraResumo() {
        ResumoView(this, viewDaActivity, listaTransacoes).atualiza()
    }
}