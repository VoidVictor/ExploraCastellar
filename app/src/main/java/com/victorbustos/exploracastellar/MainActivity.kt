package com.victorbustos.exploracastellar

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.victorbustos.exploracastellar.ui.screens.AppExplora
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

