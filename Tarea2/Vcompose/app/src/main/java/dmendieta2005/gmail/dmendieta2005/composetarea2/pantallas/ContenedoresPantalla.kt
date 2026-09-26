package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContenedoresPantalla() {
    Text(
        text = "Sección 6 en construcción",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}