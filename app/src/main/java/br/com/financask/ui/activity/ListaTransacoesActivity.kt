package br.com.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.financask.R
import br.com.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val listaTransacoes = listOf(
            "Comida - R$ 20,50",
            "Economia - R$ 100,00")

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(listaTransacoes, this)
    }
}