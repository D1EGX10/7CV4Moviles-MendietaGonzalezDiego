package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos

@Composable
fun ContenedoresPantalla() {

    val tamano = DatosCompartidos.tamanoContenedor.value.dp
    val colorActual = Color(DatosCompartidos.colorContenedor.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 6: Contenedores y estructura",
            descripcion = "Los contenedores organizan el espacio de la pantalla. Definen " +
                    "si los elementos se acomodan en fila, en columna, superpuestos o " +
                    "con pesos que reparten el espacio disponible."
        )

        BloqueDocumentado(
            titulo = "1. Distribución en fila",
            descripcion = "Los elementos se acomodan uno al lado del otro de forma " +
                    "horizontal. Se usa cuando los componentes deben compararse o " +
                    "alinearse en el mismo eje."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CajaColor(
                    texto = "Uno",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    textoColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(1f)
                )
                CajaColor(
                    texto = "Dos",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textoColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.weight(1f)
                )
                CajaColor(
                    texto = "Tres",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    textoColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        BloqueDocumentado(
            titulo = "2. Distribución en columna",
            descripcion = "Los elementos se acomodan uno debajo del otro de forma " +
                    "vertical. Es la organización más común para agrupar contenido."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CajaColor(
                    texto = "Primer elemento",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    textoColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.fillMaxWidth()
                )
                CajaColor(
                    texto = "Segundo elemento",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textoColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.fillMaxWidth()
                )
                CajaColor(
                    texto = "Tercer elemento",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    textoColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        BloqueDocumentado(
            titulo = "3. Distribución superpuesta (conexión con Sección 3)",
            descripcion = "Los elementos se colocan uno encima del otro dentro del mismo " +
                    "espacio. El color y el tamaño vienen de los controles de la " +
                    "Sección 3, así que cambian en tiempo real."
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(tamano)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(tamano)
                        .background(colorActual)
                )
                Card(
                    modifier = Modifier.align(Alignment.Center),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = "Capa al frente",
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Tamaño actual: ${DatosCompartidos.tamanoContenedor.value} dp",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BloqueDocumentado(
            titulo = "4. Distribución con pesos proporcionales",
            descripcion = "Cada elemento ocupa una fracción del espacio disponible según " +
                    "su peso. Un peso mayor significa más espacio en la pantalla."
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CajaColor(
                    texto = "Peso 1",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    textoColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                )
                CajaColor(
                    texto = "Peso 2",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    textoColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                )
                CajaColor(
                    texto = "Peso 3",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    textoColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxSize()
                )
            }
        }

        BloqueDocumentado(
            titulo = "5. Contenedor con desplazamiento vertical",
            descripcion = "Permite recorrer contenido más largo que la pantalla con un " +
                    "desplazamiento vertical. Este bloque contiene 25 elementos " +
                    "numerados que puedes recorrer."
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    for (numero in 1..25) {
                        Text(
                            text = "Elemento desplazable número $numero",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "6. Barra superior y menú lateral",
            descripcion = "La barra superior muestra el título de la sección actual y " +
                    "acciones como abrir el menú o volver al inicio. El menú lateral " +
                    "permite moverse entre las seis secciones del catálogo."
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Contenedores y estructura",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "☰",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Esta barra es un ejemplo estático. La barra real ya funciona " +
                        "en toda la aplicación y se abrió desde el menú lateral.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun CajaColor(
    texto: String,
    color: Color,
    textoColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = textoColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}