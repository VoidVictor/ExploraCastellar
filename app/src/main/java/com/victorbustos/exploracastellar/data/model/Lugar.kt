package com.victorbustos.exploracastellar.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Lugar(
    val id: Int,
    @StringRes val nombre: Int,
    @StringRes val zona: Int,
    @StringRes val descripcion: Int,
    @DrawableRes val imagen: Int,
    val listaRetos:List<Reto>,
    val visitado: Boolean = false
)
