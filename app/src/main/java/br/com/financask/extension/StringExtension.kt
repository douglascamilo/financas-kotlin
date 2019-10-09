package br.com.financask.extension

fun String.limitaEmAte(quantidadeCaracteres: Int): CharSequence? {
    return if (this.length > quantidadeCaracteres) {
        "${this.substring(0, quantidadeCaracteres)}..."
    } else {
        this
    }
}