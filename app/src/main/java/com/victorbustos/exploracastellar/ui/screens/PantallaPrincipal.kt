package com.victorbustos.exploracastellar.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.victorbustos.exploracastellar.R
import com.victorbustos.exploracastellar.data.DatosEjemplo
import com.victorbustos.exploracastellar.ui.components.BarraContador
import com.victorbustos.exploracastellar.ui.components.Buscador
import com.victorbustos.exploracastellar.ui.components.Tarjetas
import com.victorbustos.exploracastellar.ui.theme.ExploraCastellarTheme

/**
 * Estructura general de la app: barra superior con el título y la pantalla principal.
 *
 * El título se encoge solo si no cabe en una línea, por ejemplo con la fuente grande.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppExplora() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    BasicText(
                        text = stringResource(R.string.app_name),
                        maxLines = 1,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 18.sp,
                            maxFontSize = 40.sp
                        ),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            letterSpacing = 2.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding -> PantallaPrincipal(Modifier.padding(innerPadding)) }
}

/**
 * Pantalla principal con el buscador, el contador de visitados y la lista de lugares.
 *
 * Guarda el estado de la pantalla (texto buscado y lugares) y se lo pasa
 * a los componentes hijos, que avisan de los cambios con eventos.
 *
 * @param modifier Modificador que se aplica a la pantalla.
 */
@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    var busqueda by rememberSaveable() { mutableStateOf("") }
    var idsVisitados by rememberSaveable { mutableStateOf(setOf<Int>()) }
    val lugares = DatosEjemplo.lugares.map { it.copy(visitado = it.id in idsVisitados) }
    val visitados = lugares.count { it.visitado }
    val total = lugares.size
    val lugaresFiltrados = lugares.filter {
        val nombre = stringResource(it.nombre)
        val zona = stringResource(it.zona)
        nombre.contains(busqueda, ignoreCase = true) ||
                zona.contains(busqueda, ignoreCase = true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Buscador(busqueda, onTextoChange = { busqueda = it })
        BarraContador(visitados, total)
        Tarjetas(
            lugares = lugaresFiltrados,
            onVisitadoChange = { id, marcado ->
                idsVisitados = if (marcado) idsVisitados + id else idsVisitados - id
            },
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Previews de la app en modo claro, modo oscuro e inglés.
 */
@Preview(name = "Claro", locale = "es", showBackground = true)
@Preview(name = "Oscuro", locale = "es", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Inglés", locale = "en", showBackground = true)
@Composable
private fun AppExploraPreview() {
    ExploraCastellarTheme {
        AppExplora()
    }
}