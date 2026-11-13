package com.victorbustos.exploracastellar.data.model

import androidx.annotation.StringRes

sealed interface TipoReto {
    data class Pregunta(@StringRes val respuesta: Int) : TipoReto
    data object Foto : TipoReto
    data class Busca(@StringRes val pista: Int? = null) : TipoReto
}