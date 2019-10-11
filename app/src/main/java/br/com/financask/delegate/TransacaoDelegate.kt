package br.com.financask.delegate

import br.com.financask.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}