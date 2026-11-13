package com.victorbustos.exploracastellar.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Lugar de interés que se puede visitar en la app.
 *
 * Los textos y la imagen se guardan como identificadores de recursos
 * para que la app se pueda traducir a otros idiomas.
 *
 * @property id Identificador único del lugar.
 * @property nombre Recurso de texto con el nombre del lugar.
 * @property zona Recurso de texto con la zona o barrio donde está.
 * @property descripcion Recurso de texto con una breve descripción.
 * @property imagen Recurso drawable con la foto del lugar.
 * @property listaRetos Retos que se pueden superar en este lugar.
 * @property visitado `true` si el usuario ha marcado el lugar como visitado.
 */
data class Lugar(
    val id: Int,
    @StringRes val nombre: Int,
    @StringRes val zona: Int,
    @StringRes val descripcion: Int,
    @DrawableRes val imagen: Int,
    val listaRetos:List<Reto>,
    val visitado: Boolean = false
)
