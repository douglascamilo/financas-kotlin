package br.com.financask.extension

fun String.limitaEmAte(quantidadeCaracteres: Int): CharSequence {
    if (this.length > quantidadeCaracteres) {
        return "${this.substring(0, quantidadeCaracteres)}..."
    }

    return this
}