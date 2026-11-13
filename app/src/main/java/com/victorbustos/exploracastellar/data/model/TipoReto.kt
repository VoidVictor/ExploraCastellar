package com.victorbustos.exploracastellar.data.model

import androidx.annotation.StringRes

/**
 * Tipos de reto posibles.
 */
sealed interface TipoReto {
    /**
     * Reto en el que hay que responder una pregunta.
     *
     * @property respuesta Recurso de texto con la respuesta correcta.
     */
    data class Pregunta(@StringRes val respuesta: Int) : TipoReto
    /** Reto en el que hay que hacer una foto. */
    data object Foto : TipoReto
    /**
     * Reto en el que hay que encontrar algo en el lugar.
     *
     * @property pista Recurso de texto con una pista opcional, o `null` si no hay pista.
     */
    data class Busca(@StringRes val pista: Int? = null) : TipoReto
}