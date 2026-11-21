package com.victorbustos.exploracastellar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.victorbustos.exploracastellar.data.model.Lugar

/**
 * Lista con scroll de las tarjetas de los lugares.
 *
 * @param lugares Lugares que se muestran (ya filtrados por la búsqueda).
 * @param onVisitadoChange Se llama con el id del lugar y su nuevo estado al marcar o desmarcar la casilla.
 * @param modifier Modificador que se aplica a la lista.
 */
@Composable
fun Tarjetas(lugares: List<Lugar>, onVisitadoChange: (Int, Boolean) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(lugares, key = { it.id }) { lugar ->
            TarjetaLugar(
                lugar = lugar,
                onVisitadoChange = { marcado -> onVisitadoChange(lugar.id, marcado) }
            )
        }
    }
}