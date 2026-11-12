package com.victorbustos.exploracastellar.data.model

import androidx.annotation.StringRes

data class Reto(
    val id: Int,
    @StringRes val enunciado:Int,
    val dificultad: Dificultad,
    val tipo: TipoReto
)
