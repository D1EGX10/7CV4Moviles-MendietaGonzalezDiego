package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dmendieta2005.gmail.dmendieta2005.composetarea2.R
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformacionPantalla() {

    val contexto = LocalContext.current
    val hostSnackbar = remember { SnackbarHostState() }
    val alcance = rememberCoroutineScope()

    var dialogoVisible by rememberSaveable { mutableStateOf(false) }
    var hojaVisible by rememberSaveable { mutableStateOf(false) }
    val estadoHoja = rememberModalBottomSheetState()

    val progreso = DatosCompartidos.progresoGlobal.value
    val nombre = DatosCompartidos.nombreUsuario.value
    val notificaciones = DatosCompartidos.notificacionesActivas.value
    val seleccionado = DatosCompartidos.elementoSeleccionado.value

    LaunchedEffect(notificaciones) {
        if (notificaciones) {
            hostSnackbar.showSnackbar("Notificaciones activas: recibirás avisos")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 5: Información y retroalimentación",
            descripcion = "Estos elementos comunican al usuario el estado de la aplicación, " +
                    "el resultado de sus acciones y mensajes importantes. Incluyen textos " +
                    "con jerarquía visual, imágenes, barras de progreso y avisos emergentes."
        )

        BloqueDocumentado(
            titulo = "1. Textos con jerarquía",
            descripcion = "El tamaño, peso y color del texto establecen la importancia de " +
                    "cada mensaje. Un título grande llama la atención, mientras que una " +
                    "etiqueta pequeña acompaña sin competir."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Display: título principal",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Headline: encabezado de sección",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Title: título de tarjeta",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Body: texto normal de párrafo para contenido general.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Label: etiqueta pequeña",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Texto en negrita",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Texto en cursiva",
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Texto con color de énfasis",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        BloqueDocumentado(
            titulo = "2. Imágenes y modos de escalado",
            descripcion = "Las imágenes refuerzan el contenido. El modo de escalado decide " +
                    "cómo se ajustan al espacio: fitCenter las contiene sin recortar, " +
                    "centerCrop las recorta para llenar el contenedor."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Imagen local (fitCenter)", style = MaterialTheme.typography.bodyMedium)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Imagen local de ejemplo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                )
                Text("Imagen desde URL (centerCrop)", style = MaterialTheme.typography.bodyMedium)
                AsyncImage(
                    model = "https://picsum.photos/800/400",
                    contentDescription = "Imagen cargada desde una URL",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                )
            }
        }

        BloqueDocumentado(
            titulo = "3. Indicadores de progreso (conexión con Sección 2)",
            descripcion = "El progreso determinado muestra un porcentaje específico y " +
                    "avanza con cada acción de la Sección 2. El indeterminado indica " +
                    "que hay una operación en curso sin conocer el tiempo restante."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Progreso global: $progreso%")
                LinearProgressIndicator(
                    progress = { progreso / 100f },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Progreso lineal indeterminado")
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Circular determinado", style = MaterialTheme.typography.labelSmall)
                        CircularProgressIndicator(progress = { progreso / 100f })
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Circular indeterminado", style = MaterialTheme.typography.labelSmall)
                        CircularProgressIndicator()
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "4. Tarjeta personalizada (conexión con Sección 1 y 4)",
            descripcion = "Esta tarjeta muestra el nombre escrito en la Sección 1 y el " +
                    "elemento seleccionado en la Sección 4. El badge numérico indica " +
                    "cuántos elementos hay en la lista compartida."
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Datos compartidos",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(DatosCompartidos.elementosAgregados.size.toString())
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Elementos en la lista"
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Nombre escrito: ${if (nombre.isBlank()) "sin definir" else nombre}")
                    Text("Elemento seleccionado: ${if (seleccionado.isBlank()) "ninguno" else seleccionado}")
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Separador con texto debajo",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        BloqueDocumentado(
            titulo = "5. Toast y Snackbar",
            descripcion = "El Toast es un aviso breve que desaparece solo. El Snackbar " +
                    "aparece al fondo, permite incluir un botón de acción y se usa cuando " +
                    "el usuario puede deshacer o responder."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        Toast.makeText(contexto, "Mensaje Toast breve", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Toast")
                }
                Button(
                    onClick = {
                        alcance.launch {
                            val resultado = hostSnackbar.showSnackbar(
                                message = "Elemento eliminado",
                                actionLabel = "DESHACER",
                                withDismissAction = true
                            )
                            if (resultado == SnackbarResult.ActionPerformed) {
                                Toast.makeText(contexto, "Acción deshecha", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Snackbar")
                }
            }
        }

        BloqueDocumentado(
            titulo = "6. Diálogo de confirmación",
            descripcion = "Ventana modal que interrumpe al usuario para pedir una decisión " +
                    "importante. Debe reservarse para acciones delicadas como eliminar o " +
                    "cerrar sesión."
        ) {
            Button(
                onClick = { dialogoVisible = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Clear, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Mostrar diálogo")
            }
        }

        BloqueDocumentado(
            titulo = "7. Hoja inferior (bottom sheet)",
            descripcion = "Panel que sube desde la parte inferior y ofrece opciones " +
                    "relacionadas con la pantalla. Es menos intrusivo que un diálogo y " +
                    "más rápido que navegar a otra pantalla."
        ) {
            OutlinedButton(
                onClick = { hojaVisible = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Info, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Mostrar hoja inferior")
            }
        }

        Spacer(Modifier.height(24.dp))
        SnackbarHost(hostState = hostSnackbar)
    }

    if (dialogoVisible) {
        AlertDialog(
            onDismissRequest = { dialogoVisible = false },
            title = { Text("¿Confirmar acción?") },
            text = { Text("Esta acción no se puede revertir. ¿Deseas continuar?") },
            confirmButton = {
                TextButton(onClick = {
                    dialogoVisible = false
                    Toast.makeText(contexto, "Acción confirmada", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { dialogoVisible = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (hojaVisible) {
        ModalBottomSheet(
            onDismissRequest = { hojaVisible = false },
            sheetState = estadoHoja
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Opciones disponibles",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                TextButton(
                    onClick = {
                        hojaVisible = false
                        Toast.makeText(contexto, "Compartir", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Compartir")
                }
                TextButton(
                    onClick = {
                        hojaVisible = false
                        Toast.makeText(contexto, "Guardado en favoritos", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar en favoritos")
                }
                TextButton(
                    onClick = {
                        hojaVisible = false
                        Toast.makeText(contexto, "Reportado", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reportar")
                }
            }
        }
    }
}