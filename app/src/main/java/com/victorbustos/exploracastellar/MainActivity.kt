package com.victorbustos.exploracastellar

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.victorbustos.exploracastellar.data.model.Lugar
import com.victorbustos.exploracastellar.data.DatosEjemplo
import com.victorbustos.exploracastellar.ui.theme.ExploraCastellarTheme

/**
 * Actividad principal de la app. Muestra ExploraCastellar y registra en Logcat
 * cada paso del ciclo de vida con la etiqueta [TAG].
 *
 * @author Victor Bustos Vallejo
 * @since 1.0
 */
class MainActivity : ComponentActivity() {
    private val TAG = "CicloVida"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        enableEdgeToEdge()
        setContent {
            ExploraCastellarTheme {
                AppExplora()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

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
    var lugares by rememberSaveable() { mutableStateOf(DatosEjemplo.lugares) }
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
                lugares = lugares.map {
                    if (it.id == id) it.copy(visitado = marcado) else it
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Campo de texto para buscar lugares por nombre o zona.
 *
 * @param busqueda Texto que se muestra en el buscador.
 * @param onTextoChange Se llama con el texto nuevo cada vez que el usuario escribe.
 */
@Composable
fun Buscador(busqueda: String, onTextoChange: (String) -> Unit) {
    OutlinedTextField(
        value = busqueda,
        onValueChange = onTextoChange,
        label = { Text(stringResource(R.string.titulo_buscador), color = MaterialTheme.colorScheme.tertiary) },
        placeholder = { Text(stringResource(R.string.placeholder_buscador)) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.tertiary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

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

/**
 * Tarjeta de un lugar con su foto, nombre, zona, insignia y casilla de visitado.
 *
 * No guarda estado propio: muestra el [lugar] que recibe y avisa con
 * [onVisitadoChange] cuando se pulsa la casilla.
 *
 * @param lugar Lugar que se muestra.
 * @param onVisitadoChange Se llama con el nuevo valor al marcar o desmarcar la casilla.
 */
@Composable
fun TarjetaLugar(lugar: Lugar, onVisitadoChange: (Boolean) -> Unit) {
    val altoFoto = 140.dp

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(lugar.imagen),
                    contentDescription = stringResource(lugar.nombre),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(altoFoto)
                )
                InsigniaVisitado(visitado = lugar.visitado, modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp))
            }

            Row(
                Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        stringResource(lugar.nombre),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            stringResource(lugar.zona),
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Checkbox(
                    checked = lugar.visitado,
                    onCheckedChange = onVisitadoChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.secondary,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}

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