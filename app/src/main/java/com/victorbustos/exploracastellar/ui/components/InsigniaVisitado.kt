package com.victorbustos.exploracastellar.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorbustos.exploracastellar.R

/**
 * Insignia que indica si un lugar se ha visitado. Cambia de color con una animación.
 *
 * @param visitado `true` para mostrar "Visitado" con un tick, `false` para "Sin visitar".
 * @param modifier Modificador que se aplica a la insignia, por ejemplo su posición.
 */
@Composable
fun InsigniaVisitado(visitado: Boolean, modifier: Modifier = Modifier) {
    val colorFondo by animateColorAsState(
        targetValue = if (visitado) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondaryContainer
    )
    val colorTextoInsignia by animateColorAsState(
        targetValue = if (visitado) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSecondaryContainer
    )
    val textoInsignia = if (visitado) stringResource(R.string.visitado) else stringResource(R.string.sin_visitar)

    Row(
        modifier
            .background(colorFondo, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (visitado) {
            Icon(
                Icons.Filled.Check,
                contentDescription = null,
                tint = colorTextoInsignia,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(4.dp))
        }
        Text(
            textoInsignia,
            color = colorTextoInsignia,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}