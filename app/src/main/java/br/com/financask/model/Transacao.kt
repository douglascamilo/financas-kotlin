package br.com.financask.model

import java.math.BigDecimal
import java.util.*

class Transacao(
    val valor: BigDecimal,
    val categoria: String = "Indefinida",
    val tipo: TipoTransacao,
    val data: Calendar = Calendar.getInstance())