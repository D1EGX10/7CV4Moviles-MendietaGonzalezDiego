package dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dmendieta2005.gmail.dmendieta2005.composetarea2.datos.DatosCompartidos
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListasPantalla() {

    val categorias = listOf(
        "Electrónica" to listOf("Audífonos", "Teclado", "Mouse", "Monitor", "Cámara"),
        "Ropa" to listOf("Camisa", "Pantalón", "Chamarra", "Zapatos", "Gorra"),
        "Libros" to listOf("Novela", "Ciencia ficción", "Historia", "Poesía", "Ensayo"),
        "Deportes" to listOf("Balón", "Raqueta", "Bicicleta", "Tenis", "Guantes")
    )

    val elementosCuadricula = (1..20).map { "Tarjeta $it" }

    var elementoDetalle by remember { mutableStateOf<String?>(null) }
    var actualizando by remember { mutableStateOf(false) }
    var indicePestana by rememberSaveable { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(pageCount = { 3 })

    val hostSnackbar = remember { SnackbarHostState() }
    val alcance = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        EncabezadoSeccion(
            titulo = "Sección 4: Listas y colecciones",
            descripcion = "Las listas organizan grandes cantidades de información en un " +
                    "formato recorrible. Pueden ser verticales, en cuadrícula o con " +
                    "pestañas, y admiten gestos como deslizar para eliminar."
        )

        TabRow(selectedTabIndex = indicePestana) {
            listOf("Lista", "Cuadrícula", "Pestañas").forEachIndexed { indice, titulo ->
                Tab(
                    selected = indicePestana == indice,
                    onClick = { indicePestana = indice },
                    text = { Text(titulo) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pagina ->
            when (pagina) {
                0 -> ListaVertical(
                    categorias = categorias,
                    onSeleccion = { elemento ->
                        elementoDetalle = elemento
                        DatosCompartidos.elementoSeleccionado.value = elemento
                    },
                    onActualizar = {
                        alcance.launch {
                            actualizando = true
                            delay(1500)
                            actualizando = false
                            hostSnackbar.showSnackbar("Lista actualizada")
                        }
                    },
                    actualizando = actualizando,
                    hostSnackbar = hostSnackbar
                )
                1 -> Cuadricula(elementosCuadricula)
                2 -> PestanasInternas()
            }
        }
    }

    if (elementoDetalle != null) {
        AlertDialog(
            onDismissRequest = { elementoDetalle = null },
            confirmButton = {
                TextButton(onClick = { elementoDetalle = null }) {
                    Text("Cerrar")
                }
            },
            title = { Text("Detalle del elemento") },
            text = {
                Column {
                    Text(
                        text = elementoDetalle!!,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Este elemento fue seleccionado desde la lista. Su nombre " +
                                "también se reflejará en la Sección 5.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListaVertical(
    categorias: List<Pair<String, List<String>>>,
    onSeleccion: (String) -> Unit,
    onActualizar: () -> Unit,
    actualizando: Boolean,
    hostSnackbar: SnackbarHostState
) {

    val alcance = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (actualizando) "Actualizando…" else "Desliza o pulsa actualizar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onActualizar,
                enabled = !actualizando
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Actualizar lista",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (DatosCompartidos.elementosAgregados.isEmpty()) {
            EstadoVacio()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    Text(
                        text = "Agregados desde otras secciones",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(
                    items = DatosCompartidos.elementosAgregados.toList(),
                    key = { it }
                ) { elemento ->
                    ElementoDeslizable(
                        texto = elemento,
                        onClick = { onSeleccion(elemento) },
                        onEliminar = {
                            DatosCompartidos.eliminarElemento(elemento)
                            alcance.launch {
                                hostSnackbar.showSnackbar("Elemento eliminado")
                            }
                        }
                    )
                }

                categorias.forEach { (categoria, elementos) ->
                    item {
                        Text(
                            text = categoria,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )
                    }
                    items(
                        items = elementos,
                        key = { it }
                    ) { elemento ->
                        ElementoDeslizable(
                            texto = elemento,
                            onClick = { onSeleccion(elemento) },
                            onEliminar = {
                                alcance.launch {
                                    hostSnackbar.showSnackbar("$elemento eliminado")
                                }
                            }
                        )
                    }
                }

                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ElementoDeslizable(
    texto: String,
    onClick: () -> Unit,
    onEliminar: () -> Unit
) {

    val estado = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onEliminar()
                true
            } else false
        }
    )

    SwipeToDismissBox(
        state = estado,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.size(12.dp))
                Text(text = texto, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun EstadoVacio() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Lista vacía",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Arrastra hacia abajo para recargar o agrega elementos desde " +
                        "las secciones 1 y 2.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun Cuadricula(elementos: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(elementos) { texto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = texto,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun PestanasInternas() {

    val pagerInterno = rememberPagerState(pageCount = { 3 })

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pagerInterno.currentPage) {
            listOf("Uno", "Dos", "Tres").forEachIndexed { indice, titulo ->
                Tab(
                    selected = pagerInterno.currentPage == indice,
                    onClick = {},
                    text = { Text(titulo) },
                    modifier = Modifier.clickable {
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerInterno,
            modifier = Modifier.fillMaxSize()
        ) { pagina ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        when (pagina) {
                            0 -> MaterialTheme.colorScheme.primaryContainer
                            1 -> MaterialTheme.colorScheme.secondaryContainer
                            else -> MaterialTheme.colorScheme.tertiaryContainer
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Contenido de la pestaña ${pagina + 1}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}