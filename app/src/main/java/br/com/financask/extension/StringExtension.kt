package br.com.financask.extension

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(quantidadeCaracteres: Int): CharSequence {
    if (this.length > quantidadeCaracteres) {
        return "${this.substring(0, quantidadeCaracteres)}..."
    }

    return this
}

fun String.converteParaCalendar(): Calendar {
    val dataTransacao = Calendar.getInstance()
    dataTransacao.time = SimpleDateFormat("dd/MM/yyyy").parse(this)

    return dataTransacao
}

fun String.converteParaBigDecimal(): BigDecimal {
    return BigDecimal(this)
}