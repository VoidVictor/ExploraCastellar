package com.victorbustos.exploracastellar.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorbustos.exploracastellar.R

/**
 * Cuadro con el número de lugares visitados y una barra de progreso animada.
 *
 * @param visitados Número de lugares visitados.
 * @param total Número total de lugares.
 */
@Composable
fun BarraContador(visitados: Int, total: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            pluralStringResource(R.plurals.contador_visitados, visitados, visitados, total),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.padding(5.dp))
        val progreso = if (total == 0) 0f else visitados.toFloat() / total
        val progresoAnimado by animateFloatAsState(
            targetValue = progreso,
            animationSpec = tween(durationMillis = 300)
        )
        LinearProgressIndicator(
            progress = { progresoAnimado },
            color = MaterialTheme.colorScheme.tertiary,
            trackColor = MaterialTheme.colorScheme.tertiaryContainer,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)

        )
    }
}