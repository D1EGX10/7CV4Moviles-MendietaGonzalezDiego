package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextButton
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntradaTextoPantalla() {

    var nombre by rememberSaveable { mutableStateOf("") }
    var codigo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var contrasenaVisible by rememberSaveable { mutableStateOf(false) }
    var edad by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var comentarios by rememberSaveable { mutableStateOf("") }
    var opcionSeleccionada by rememberSaveable { mutableStateOf("") }
    var opcionExpandida by remember { mutableStateOf(false) }
    var busqueda by rememberSaveable { mutableStateOf("") }

    val opciones = listOf("Opción A", "Opción B", "Opción C", "Opción D")

    val codigoValido = codigo.length >= 5
    val mostrarErrorCodigo = codigo.isNotEmpty() && !codigoValido

    val hostSnackbar = remember { SnackbarHostState() }
    val alcance = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 1: Entrada de texto",
            descripcion = "Los campos de texto permiten al usuario capturar información. " +
                    "Existen variantes según el tipo de dato esperado, el teclado que " +
                    "deben mostrar y las validaciones que aplican."
        )

        BloqueDocumentado(
            titulo = "1. Campo de texto simple",
            descripcion = "Permite capturar una línea corta de texto libre. Se usa para " +
                    "nombres, títulos o cualquier dato breve. La etiqueta flota al enfocar."
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it

                    DatosCompartidos.nombreUsuario.value = it
                },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        BloqueDocumentado(
            titulo = "2. Campo con validación",
            descripcion = "Muestra un mensaje de error debajo del campo cuando el valor " +
                    "no cumple las reglas. El borde y el texto se pintan en rojo para " +
                    "que el usuario identifique el problema."
        ) {
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Código (mínimo 5 caracteres)") },
                modifier = Modifier.fillMaxWidth(),
                isError = mostrarErrorCodigo,
                supportingText = {
                    if (mostrarErrorCodigo) {
                        Text(
                            text = "Debe tener al menos 5 caracteres",
                            color = MaterialTheme.colorScheme.error
                        )
                    } else if (codigo.isNotEmpty()) {
                        Text(text = "Código válido")
                    }
                },
                singleLine = true
            )
        }

        BloqueDocumentado(
            titulo = "3. Campo de contraseña",
            descripcion = "Oculta los caracteres que el usuario escribe. El ícono del ojo " +
                    "permite alternar entre mostrar el texto y mantenerlo oculto."
        ) {
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (contrasenaVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    TextButton(onClick = { contrasenaVisible = !contrasenaVisible }) {
                        Text(text = if (contrasenaVisible) "Ocultar" else "Mostrar")
                    }
                }
            )
        }

        BloqueDocumentado(
            titulo = "4. Tipos de teclado",
            descripcion = "El teclado cambia según el dato esperado: solo números para " +
                    "edad, arroba para correo y dígitos con formato telefónico para " +
                    "números de teléfono."
        ) {
            Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = edad,
                    onValueChange = { nuevo ->

                        if (nuevo.all { it.isDigit() }) edad = nuevo
                    },
                    label = { Text("Edad (solo números)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Número de teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )
            }
        }

        BloqueDocumentado(
            titulo = "5. Campo multilínea",
            descripcion = "Permite escribir párrafos largos que ocupan varias líneas. " +
                    "El campo crece en altura automáticamente según el contenido."
        ) {
            OutlinedTextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text("Comentarios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                minLines = 3,
                maxLines = 6,
                singleLine = false
            )
        }

        BloqueDocumentado(
            titulo = "6. Campo con opciones desplegables",
            descripcion = "Muestra una lista de opciones al pulsarlo. Evita errores de " +
                    "escritura y restringe la captura a valores predefinidos."
        ) {
            ExposedDropdownMenuBox(
                expanded = opcionExpandida,
                onExpandedChange = { opcionExpandida = !opcionExpandida }
            ) {
                OutlinedTextField(
                    value = opcionSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Selecciona una opción") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = opcionExpandida)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = opcionExpandida,
                    onDismissRequest = { opcionExpandida = false }
                ) {
                    opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                opcionSeleccionada = opcion
                                opcionExpandida = false
                            }
                        )
                    }
                }
            }
        }

        BloqueDocumentado(
            titulo = "7. Barra de búsqueda",
            descripcion = "Campo especializado con ícono de lupa y botón para limpiar. " +
                    "Se usa para filtrar listas o encontrar contenido rápidamente."
        ) {
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                label = { Text("Buscar…") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar")
                },
                trailingIcon = {
                    if (busqueda.isNotEmpty()) {
                        IconButton(onClick = { busqueda = "" }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Limpiar búsqueda")
                        }
                    }
                }
            )
        }

        BloqueDocumentado(
            titulo = "8. Conexión con la Sección 4",
            descripcion = "El nombre escrito en el campo 1 se puede agregar a la lista " +
                    "de la Sección 4. Es un ejemplo de comunicación entre secciones."
        ) {
            Button(
                onClick = {
                    val agregado = DatosCompartidos.agregarElemento(nombre)
                    val mensaje = if (agregado) {
                        "Elemento agregado a la lista"
                    } else if (nombre.isBlank()) {
                        "Escribe un nombre primero"
                    } else {
                        "Ese elemento ya existe en la lista"
                    }
                    alcance.launch {
                        hostSnackbar.showSnackbar(mensaje)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Spacer(Modifier.height(0.dp))
                Text(
                    text = "  Agregar \"$nombre\" a la lista",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        SnackbarHost(hostState = hostSnackbar)
    }
}