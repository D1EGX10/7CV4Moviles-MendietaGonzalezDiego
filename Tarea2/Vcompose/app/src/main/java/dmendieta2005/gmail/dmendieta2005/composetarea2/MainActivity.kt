package dmendieta2005.gmail.dmendieta2005.composetarea2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dmendieta2005.gmail.dmendieta2005.composetarea2.navegacion.NavegacionApp
import dmendieta2005.gmail.dmendieta2005.composetarea2.ui.theme.ComposeTarea2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTarea2Theme {
                NavegacionApp()
            }
        }
    }
}