package com.victorbustos.exploracastellar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorbustos.exploracastellar.data.DatosEjemplo
import com.victorbustos.exploracastellar.ui.theme.ExploraCastellarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploraCastellarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaPrincipal(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    var busqueda by rememberSaveable() { mutableStateOf("") }
    var lugares by rememberSaveable() { mutableStateOf(DatosEjemplo.lugares) }
    val visitados = lugares.count { it.visitado }
    val total = lugares.size
    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Spacer(Modifier.padding(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.green_title)),
            Arrangement.Center
        ) {
            Text(
                "Explora Castellar",
                Modifier.padding(20.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                letterSpacing = 2.sp
            )
        }
        Spacer(Modifier.padding(5.dp))
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
    }

}