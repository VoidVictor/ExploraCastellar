package com.victorbustos.exploracastellar.data

import androidx.compose.ui.res.stringResource
import com.victorbustos.exploracastellar.R
import com.victorbustos.exploracastellar.data.model.Dificultad
import com.victorbustos.exploracastellar.data.model.Lugar
import com.victorbustos.exploracastellar.data.model.Reto
import com.victorbustos.exploracastellar.data.model.TipoReto

object DatosEjemplo {
    val lugares: List<Lugar> = listOf(

        Lugar(
            id = 1,
            nombre = R.string.lugar_1_nombre,
            zona = R.string.zona_castellar,
            descripcion = R.string.lugar_1_desc,
            imagen = R.drawable.lugar_castellar,
            listaRetos = listOf(
                Reto(1, R.string.reto_1, Dificultad.FACIL, TipoReto.Pregunta(R.string.respuesta_reto1)),
                Reto(2, R.string.reto_2, Dificultad.FACIL, TipoReto.Foto)
            )
        ),

        Lugar(
            id = 2,
            nombre = R.string.lugar_2_nombre,
            zona = R.string.zona_castellar,
            descripcion = R.string.lugar_2_desc,
            imagen = R.drawable.lugar_iglesia,
            listaRetos = listOf(
                Reto(3, R.string.reto_3, Dificultad.FACIL, TipoReto.Foto),
                Reto(4, R.string.reto_4, Dificultad.MEDIA, TipoReto.Pregunta(R.string.respuesta_reto4))
            )
        ),

        Lugar(
            id = 3,
            nombre = R.string.lugar_3_nombre,
            zona = R.string.zona_oliveral,
            descripcion = R.string.lugar_3_desc,
            imagen = R.drawable.lugar_brosquil,
            listaRetos = listOf(
                Reto(5, R.string.reto_5, Dificultad.MEDIA, TipoReto.Pregunta(R.string.respuesta_reto5)),
                Reto(6, R.string.reto_6, Dificultad.FACIL, TipoReto.Busca())
            )
        ),

        Lugar(
            id = 4,
            nombre = R.string.lugar_4_nombre,
            zona = R.string.zona_oliveral_pinedo,
            descripcion = R.string.lugar_4_desc,
            imagen = R.drawable.lugar_cami_tremolar,
            listaRetos = listOf(
                Reto(7, R.string.reto_7, Dificultad.MEDIA, TipoReto.Foto),
                Reto(8, R.string.reto_8, Dificultad.FACIL, TipoReto.Pregunta(R.string.respuesta_reto8))
            )
        ),

        Lugar(
            id = 5,
            nombre = R.string.lugar_5_nombre,
            zona = R.string.zona_tremolar,
            descripcion = R.string.lugar_5_desc,
            imagen = R.drawable.lugar_puerto_tremolar,
            listaRetos = listOf(
                Reto(9, R.string.reto_9, Dificultad.DIFICIL, TipoReto.Pregunta(R.string.respuesta_reto9)),
                Reto(10, R.string.reto_10, Dificultad.DIFICIL, TipoReto.Pregunta(R.string.respuesta_reto10)),
                Reto(11, R.string.reto_11, Dificultad.MEDIA, TipoReto.Foto)
            )
        ),

        Lugar(
            id = 6,
            nombre = R.string.lugar_6_nombre,
            zona = R.string.zona_saler,
            descripcion = R.string.lugar_6_desc,
            imagen = R.drawable.lugar_estany_pujol,
            listaRetos = listOf(
                Reto(12, R.string.reto_12, Dificultad.MEDIA, TipoReto.Foto),
                Reto(13, R.string.reto_13, Dificultad.MEDIA, TipoReto.Pregunta(R.string.respuesta_reto13))
            )
        )
    )
}