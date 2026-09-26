package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dmendieta2005.gmail.dmendieta2005.composetarea2.navegacion.Rutas

@Composable
fun InicioPantalla(controladorNav: NavHostController) {

    val secciones = listOf(
        Triple("Sección 1", "Entrada de texto", Rutas.ENTRADA_TEXTO),
        Triple("Sección 2", "Botones y acciones", Rutas.BOTONES),
        Triple("Sección 3", "Elementos de selección", Rutas.SELECCION),
        Triple("Sección 4", "Listas y colecciones", Rutas.LISTAS),
        Triple("Sección 5", "Información y retroalimentación", Rutas.INFORMACION),
        Triple("Sección 6", "Contenedores y estructura", Rutas.CONTENEDORES)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Catálogo de elementos de interfaz",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Esta aplicación muestra los componentes básicos de una interfaz móvil. " +
                    "Explora cada sección desde el menú lateral o toca una tarjeta para entrar directamente.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))

        secciones.forEach { (numero, titulo, ruta) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                onClick = { controladorNav.navigate(ruta) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = numero,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}