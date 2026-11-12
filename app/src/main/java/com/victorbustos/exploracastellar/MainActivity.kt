package com.victorbustos.exploracastellar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.victorbustos.exploracastellar.data.model.Lugar
import com.victorbustos.exploracastellar.data.DatosEjemplo
import com.victorbustos.exploracastellar.ui.theme.ExploraCastellarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploraCastellarTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(title = { Text(stringResource(R.string.app_name),
                            Modifier.padding(20.dp),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            letterSpacing = 2.sp) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = colorResource(R.color.green_title),
                                titleContentColor = Color.Black
                            ))

                    }
                ) { innerPadding -> PantallaPrincipal(Modifier.padding(innerPadding)) }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    var busqueda by rememberSaveable() { mutableStateOf("") }
    var lugares by remember() { mutableStateOf(DatosEjemplo.lugares) }
    val visitados = lugares.count { it.visitado }
    val total = lugares.size
    val lugaresFiltrados = lugares.filter {
        stringResource(it.nombre).contains(busqueda, ignoreCase = true) ||
                stringResource(it.zona).contains(busqueda, ignoreCase = true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar lugar") },
            placeholder = { Text("Alquería, Tremolar, Saler,...") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color(0xFFDDEDE8), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                "$visitados de $total lugares visitados",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(Modifier.padding(5.dp))
            LinearProgressIndicator(
                progress = { if (total == 0) 0f else visitados.toFloat() / total },
                color = Color(0xFF0A5450),
                trackColor = Color(0xFFB7DCD6),
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)

            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lugaresFiltrados, key = { it.id }) { lugar ->
                TarjetaLugar(
                    lugar = lugar,
                    onVisitadoChange = { marcado ->
                        lugares = lugares.map {
                            if (it.id == lugar.id) it.copy(visitado = marcado) else it
                        }
                    }
                )
            }
        }
    }
}
@Composable
fun TarjetaLugar(lugar: Lugar, onVisitadoChange: (Boolean) -> Unit) {
    val altoFoto = 140.dp
    val altoInsignia = 24.dp

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E6E8))
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(lugar.imagen),
                    contentDescription = stringResource(lugar.nombre),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(altoFoto)
                )

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
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(stringResource(lugar.zona), fontSize = 13.sp, color = Color.Gray)
                        }
                    }
                    Checkbox(
                        checked = lugar.visitado,
                        onCheckedChange = onVisitadoChange,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF0A5450),
                            uncheckedColor = Color.Gray
                        )
                    )
                }
            }

            if (lugar.visitado) {
                Row(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = altoFoto - altoInsignia / 2, end = 10.dp)
                        .height(altoInsignia)
                        .background(Color(0xFF0A5450), RoundedCornerShape(50))
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "Visitado",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}