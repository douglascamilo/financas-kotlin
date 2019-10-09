package br.com.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro(): String {
    return DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(this)
        .replace("R$", "R$ ")
        .replace("-R$ ", "R$ -")
}