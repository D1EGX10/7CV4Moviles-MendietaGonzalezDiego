package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonesPantalla() {

    var opcionSegmentada by rememberSaveable { mutableIntStateOf(0) }

    var cargando by rememberSaveable { mutableStateOf(false) }

    var contadorRapido by rememberSaveable { mutableIntStateOf(0) }

    val hostSnackbar = remember { SnackbarHostState() }
    val alcance = rememberCoroutineScope()

    fun avisar(mensaje: String) {
        alcance.launch { hostSnackbar.showSnackbar(mensaje) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 2: Botones y acciones",
            descripcion = "Los botones comunican acciones al usuario. Su nivel de énfasis " +
                    "indica la importancia de la acción: relleno para la principal, " +
                    "contorno para secundarias y texto para acciones terciarias."
        )

        BloqueDocumentado(
            titulo = "1. Botones básicos",
            descripcion = "El botón relleno se usa para la acción principal de la pantalla. " +
                    "El de contorno para acciones secundarias. El de texto para acciones " +
                    "de menor importancia o enlaces dentro de un párrafo."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { avisar("Pulsaste el botón relleno") }) {
                    Text("Relleno")
                }
                OutlinedButton(onClick = { avisar("Pulsaste el botón de contorno") }) {
                    Text("Contorno")
                }
                TextButton(onClick = { avisar("Pulsaste el botón de texto") }) {
                    Text("Texto")
                }
            }
        }

        BloqueDocumentado(
            titulo = "2. Botones con ícono",
            descripcion = "Los íconos refuerzan visualmente la acción. Pueden usarse solos " +
                    "cuando la acción es reconocible al instante, o acompañados de texto " +
                    "para mayor claridad."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { avisar("Pulsaste el botón con solo ícono") }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir"
                    )
                }
                Button(onClick = { avisar("Pulsaste el botón con ícono y texto") }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.size(8.dp))
                    Text("Enviar")
                }
            }
        }

        BloqueDocumentado(
            titulo = "3. Botones de acción flotante",
            descripcion = "El botón flotante se usa para la acción principal de la pantalla " +
                    "y flota sobre el contenido. El extendido incluye texto para dejar " +
                    "clara la acción que realiza."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = { avisar("Pulsaste el botón flotante") }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar")
                }
                ExtendedFloatingActionButton(
                    onClick = { avisar("Pulsaste el botón flotante extendido") },
                    icon = {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    },
                    text = { Text("Crear nuevo") }
                )
            }
        }

        BloqueDocumentado(
            titulo = "4. Selector segmentado",
            descripcion = "Permite elegir una sola opción entre varias contiguas. Es útil " +
                    "para alternar vistas o modos donde las opciones son mutuamente " +
                    "excluyentes y se conocen de antemano."
        ) {
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                val opciones = listOf("Opción A", "Opción B", "Opción C")
                opciones.forEachIndexed { indice, texto ->
                    SegmentedButton(
                        selected = opcionSegmentada == indice,
                        onClick = {
                            opcionSegmentada = indice
                            avisar("Seleccionaste: $texto")
                        },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = indice,
                            count = opciones.size
                        )
                    ) {
                        Text(texto)
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "5. Estados de botón",
            descripcion = "Un botón deshabilitado indica que la acción no está disponible. " +
                    "El estado de carga muestra que una acción está en proceso y evita " +
                    "pulsaciones repetidas."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { avisar("Este botón está deshabilitado") },
                    enabled = false
                ) {
                    Text("Deshabilitado")
                }
                Button(
                    onClick = {
                        cargando = true
                        alcance.launch {
                            delay(2000)
                            cargando = false
                            hostSnackbar.showSnackbar("Guardado completado")
                        }
                    },
                    enabled = !cargando
                ) {
                    if (cargando) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.size(8.dp))
                        Text("Guardando…")
                    } else {
                        Text("Guardar")
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "6. Conexión con la Sección 4",
            descripcion = "Este botón agrega un elemento nuevo a la lista compartida que " +
                    "verás en la Sección 4. Demuestra cómo una acción en una pantalla " +
                    "modifica el contenido de otra."
        ) {
            Button(
                onClick = {
                    contadorRapido++
                    val nuevo = "Elemento rápido $contadorRapido"
                    DatosCompartidos.agregarElemento(nuevo)
                    avisar("Agregado: $nuevo")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Agregar elemento rápido a la lista")
            }
        }

        BloqueDocumentado(
            titulo = "7. Conexión con la Sección 5",
            descripcion = "Este botón avanza el progreso global que se dibuja en la " +
                    "Sección 5. Cada pulsación suma un 10 por ciento y se reinicia " +
                    "al superar el 100 por ciento."
        ) {
            Button(
                onClick = {
                    DatosCompartidos.avanzarProgreso()
                    avisar("Progreso actualizado: ${DatosCompartidos.progresoGlobal.value}%")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Avanzar progreso global")
            }
        }

        BloqueDocumentado(
            titulo = "8. Limpiar lista compartida",
            descripcion = "Elimina todos los elementos agregados desde la Sección 1 y la " +
                    "Sección 2. Se usa para probar el estado vacío de la Sección 4."
        ) {
            OutlinedButton(
                onClick = {
                    DatosCompartidos.limpiarLista()
                    avisar("Lista vaciada")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Limpiar lista compartida")
            }
        }

        Spacer(Modifier.height(24.dp))
        SnackbarHost(hostState = hostSnackbar)
    }
}