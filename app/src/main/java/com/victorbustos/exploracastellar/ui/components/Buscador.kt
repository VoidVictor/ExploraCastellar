package com.victorbustos.exploracastellar.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victorbustos.exploracastellar.R

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