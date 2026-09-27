package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionPantalla() {

    var checkNormal by rememberSaveable { mutableStateOf(false) }
    var checkIndeterminado by rememberSaveable { mutableStateOf(ToggleableState.Indeterminate) }

    var opcionRadio by rememberSaveable { mutableStateOf("A") }

    var valorUnico by rememberSaveable { mutableFloatStateOf(50f) }
    var rangoInicio by rememberSaveable { mutableFloatStateOf(20f) }
    var rangoFin by rememberSaveable { mutableFloatStateOf(80f) }

    var opcionDesplegable by rememberSaveable { mutableStateOf("") }
    var desplegableAbierto by remember { mutableStateOf(false) }
    val opcionesDesplegable = listOf("Alta", "Media", "Baja")

    var mostrarDialogoFecha by remember { mutableStateOf(false) }
    var textoFecha by rememberSaveable { mutableStateOf("Sin seleccionar") }
    var mostrarDialogoHora by remember { mutableStateOf(false) }
    var textoHora by rememberSaveable { mutableStateOf("Sin seleccionar") }

    val chips = listOf(
        "Filtro rojo" to 0xFFE53935L,
        "Filtro azul" to 0xFF1E88E5L,
        "Filtro verde" to 0xFF43A047L,
        "Filtro morado" to 0xFF8E24AAL
    )
    var chipSeleccionado by rememberSaveable { mutableStateOf(chips.first().first) }

    val hostSnackbar = remember { SnackbarHostState() }
    val alcance = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 3: Elementos de selección",
            descripcion = "Los elementos de selección permiten al usuario elegir entre " +
                    "opciones. Pueden aceptar una sola respuesta, varias a la vez, o " +
                    "valores continuos como los deslizadores."
        )

        BloqueDocumentado(
            titulo = "1. Casillas de verificación",
            descripcion = "Permiten seleccionar varias opciones a la vez. La casilla " +
                    "indeterminada representa un estado parcial, útil cuando solo " +
                    "algunos elementos de un grupo están seleccionados."
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkNormal,
                        onCheckedChange = { checkNormal = it }
                    )
                    Text("Opción normal")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TriStateCheckbox(
                        state = checkIndeterminado,
                        onClick = {
                            checkIndeterminado = when (checkIndeterminado) {
                                ToggleableState.On -> ToggleableState.Off
                                ToggleableState.Off -> ToggleableState.Indeterminate
                                ToggleableState.Indeterminate -> ToggleableState.On
                            }
                        }
                    )
                    Text("Estado indeterminado (púlsalo para alternar)")
                }
            }
        }

        BloqueDocumentado(
            titulo = "2. Botones de opción",
            descripcion = "Permiten seleccionar una sola opción dentro de un grupo. " +
                    "Al elegir una, las demás se desmarcan automáticamente."
        ) {
            Column {
                listOf("A", "B", "C").forEach { opcion ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = opcionRadio == opcion,
                            onClick = { opcionRadio = opcion }
                        )
                        Text("Opción $opcion")
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "3. Interruptor (conexión con Sección 5)",
            descripcion = "Activa o desactiva una configuración al instante. Este " +
                    "interruptor controla si la Sección 5 muestra un mensaje de " +
                    "bienvenida al entrar."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Activar notificaciones", modifier = Modifier.fillMaxWidth(0.7f))
                Switch(
                    checked = DatosCompartidos.notificacionesActivas.value,
                    onCheckedChange = {
                        DatosCompartidos.notificacionesActivas.value = it
                        alcance.launch {
                            hostSnackbar.showSnackbar(
                                if (it) "Notificaciones activadas" else "Notificaciones desactivadas"
                            )
                        }
                    }
                )
            }
        }

        BloqueDocumentado(
            titulo = "4. Deslizador de valor único",
            descripcion = "Permite elegir un valor dentro de un rango arrastrando el " +
                    "control. Es útil para ajustes como volumen, brillo o tamaño."
        ) {
            Column {
                Text("Valor: ${valorUnico.toInt()}")
                Slider(
                    value = valorUnico,
                    onValueChange = { valorUnico = it },
                    valueRange = 0f..100f
                )
            }
        }

        BloqueDocumentado(
            titulo = "5. Deslizador de rango (conexión con Sección 6)",
            descripcion = "Permite elegir dos valores a la vez para definir un rango. " +
                    "El tamaño elegido modifica las cajas de la Sección 6."
        ) {
            Column {
                Text("Tamaño de cajas: ${DatosCompartidos.tamanoContenedor.value} dp")
                Slider(
                    value = DatosCompartidos.tamanoContenedor.value.toFloat(),
                    onValueChange = {
                        DatosCompartidos.tamanoContenedor.value = it.toInt()
                    },
                    valueRange = 40f..140f
                )
                Spacer(Modifier.height(12.dp))
                Text("Rango de ejemplo: ${rangoInicio.toInt()} a ${rangoFin.toInt()}")
                RangeSlider(
                    value = rangoInicio..rangoFin,
                    onValueChange = {
                        rangoInicio = it.start
                        rangoFin = it.endInclusive
                    },
                    valueRange = 0f..100f
                )
            }
        }

        BloqueDocumentado(
            titulo = "6. Lista desplegable",
            descripcion = "Muestra una lista de opciones al pulsarla. Es un componente " +
                    "compacto para elegir un solo valor entre varios."
        ) {
            ExposedDropdownMenuBox(
                expanded = desplegableAbierto,
                onExpandedChange = { desplegableAbierto = !desplegableAbierto }
            ) {
                OutlinedTextField(
                    value = opcionDesplegable,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Prioridad") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = desplegableAbierto)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = desplegableAbierto,
                    onDismissRequest = { desplegableAbierto = false }
                ) {
                    opcionesDesplegable.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                opcionDesplegable = opcion
                                desplegableAbierto = false
                            }
                        )
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "7. Selector de fecha",
            descripcion = "Abre un diálogo del sistema para elegir un día del calendario. " +
                    "El resultado se muestra como texto en formato local."
        ) {
            Column {
                Text("Fecha elegida: $textoFecha")
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { mostrarDialogoFecha = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Elegir fecha")
                }
            }
            if (mostrarDialogoFecha) {
                val estadoFecha = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { mostrarDialogoFecha = false },
                    confirmButton = {
                        TextButton(onClick = {
                            estadoFecha.selectedDateMillis?.let { millis ->
                                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                textoFecha = formato.format(millis)
                            }
                            mostrarDialogoFecha = false
                        }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarDialogoFecha = false }) {
                            Text("Cancelar")
                        }
                    }
                ) {
                    DatePicker(state = estadoFecha)
                }
            }
        }

        BloqueDocumentado(
            titulo = "8. Selector de hora",
            descripcion = "Abre un diálogo para elegir una hora del día. Se usa para " +
                    "programar eventos, alarmas o recordatorios."
        ) {
            Column {
                Text("Hora elegida: $textoHora")
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { mostrarDialogoHora = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Elegir hora")
                }
            }
            if (mostrarDialogoHora) {
                val estadoHora = rememberTimePickerState(
                    initialHour = 12,
                    initialMinute = 0,
                    is24Hour = false
                )
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { mostrarDialogoHora = false },
                    confirmButton = {
                        TextButton(onClick = {
                            textoHora = String.format(
                                Locale.getDefault(),
                                "%02d:%02d",
                                estadoHora.hour,
                                estadoHora.minute
                            )
                            mostrarDialogoHora = false
                        }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarDialogoHora = false }) {
                            Text("Cancelar")
                        }
                    },
                    text = { TimePicker(state = estadoHora) }
                )
            }
        }

        BloqueDocumentado(
            titulo = "9. Chips de filtro (conexión con Sección 6)",
            descripcion = "Elementos compactos que actúan como etiquetas seleccionables. " +
                    "El color elegido se aplica al contenedor superpuesto de la Sección 6."
        ) {
            Column {
                chips.chunked(2).forEach { fila ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        fila.forEach { (nombre, color) ->
                            FilterChip(
                                selected = chipSeleccionado == nombre,
                                onClick = {
                                    chipSeleccionado = nombre
                                    DatosCompartidos.colorContenedor.value = color
                                },
                                label = { Text(nombre) }
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text("Color aplicado:")
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text("Vista previa")
                    }
                    Spacer(Modifier.height(0.dp))
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        androidx.compose.foundation.Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            drawRect(color = Color(DatosCompartidos.colorContenedor.value))
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        SnackbarHost(hostState = hostSnackbar)
    }
}