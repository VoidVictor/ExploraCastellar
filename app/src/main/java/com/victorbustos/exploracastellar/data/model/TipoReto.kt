package com.victorbustos.exploracastellar.data.model

sealed interface TipoReto {
    data class Pregunta(val respuesta: String) : TipoReto
    data object Foto : TipoReto
    data class Busca(val pista: String? = null) : TipoReto
}