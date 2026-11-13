package com.victorbustos.exploracastellar.data.model

import androidx.annotation.StringRes

/**
 * Reto que el usuario puede superar en un [Lugar].
 *
 * @property id Identificador único del reto.
 * @property enunciado Recurso de texto con lo que se pide hacer.
 * @property dificultad Nivel de dificultad del reto.
 * @property tipo Tipo de reto: pregunta, foto o búsqueda.
 */
data class Reto(
    val id: Int,
    @StringRes val enunciado:Int,
    val dificultad: Dificultad,
    val tipo: TipoReto
)
