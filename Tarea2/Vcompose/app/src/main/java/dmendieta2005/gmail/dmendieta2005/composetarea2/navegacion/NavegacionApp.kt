package dmendieta2005.gmail.dmendieta2005.composetarea2.navegacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.BotonesPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.ContenedoresPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.EntradaTextoPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.InformacionPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.InicioPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.ListasPantalla
import dmendieta2005.gmail.dmendieta2005.composetarea2.pantallas.SeleccionPantalla
import kotlinx.coroutines.launch

private data class OpcionMenu(
    val ruta: String,
    val titulo: String,
    val icono: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavegacionApp() {

    val controladorNav = rememberNavController()
    val estadoDrawer = rememberDrawerState(DrawerValue.Closed)
    val alcance = rememberCoroutineScope()

    val opciones = listOf(
        OpcionMenu(Rutas.INICIO, "Inicio") {
            Icon(Icons.Filled.Home, contentDescription = "Inicio")
        },
        OpcionMenu(Rutas.ENTRADA_TEXTO, "1. Entrada de texto") {
            Icon(Icons.Filled.Edit, contentDescription = "Entrada de texto")
        },
        OpcionMenu(Rutas.BOTONES, "2. Botones y acciones") {
            Icon(Icons.Filled.Build, contentDescription = "Botones")
        },
        OpcionMenu(Rutas.SELECCION, "3. Elementos de selección") {
            Icon(Icons.Filled.Settings, contentDescription = "Selección")
        },
        OpcionMenu(Rutas.LISTAS, "4. Listas y colecciones") {
            Icon(Icons.Filled.List, contentDescription = "Listas")
        },
        OpcionMenu(Rutas.INFORMACION, "5. Información y retroalimentación") {
            Icon(Icons.Filled.Info, contentDescription = "Información")
        },
        OpcionMenu(Rutas.CONTENEDORES, "6. Contenedores y estructura") {
            Icon(Icons.Filled.Build, contentDescription = "Contenedores")
        }
    )

    val entradaActual by controladorNav.currentBackStackEntryAsState()
    val rutaActual = entradaActual?.destination?.route

    val tituloActual = opciones.firstOrNull { it.ruta == rutaActual }?.titulo ?: "Catálogo UI"
    val esInicio = rutaActual == Rutas.INICIO

    ModalNavigationDrawer(
        drawerState = estadoDrawer,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Catálogo UI",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                Spacer(Modifier.height(8.dp))
                opciones.forEach { opcion ->
                    NavigationDrawerItem(
                        label = { Text(opcion.titulo) },
                        icon = opcion.icono,
                        selected = rutaActual == opcion.ruta,
                        onClick = {
                            alcance.launch { estadoDrawer.close() }
                            if (rutaActual != opcion.ruta) {
                                controladorNav.navigate(opcion.ruta) {
                                    popUpTo(Rutas.INICIO)
                                    launchSingleTop = true
                                }
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(tituloActual) },
                    navigationIcon = {
                        if (esInicio) {
                            IconButton(onClick = { alcance.launch { estadoDrawer.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Abrir menú")
                            }
                        } else {
                            IconButton(onClick = {
                                controladorNav.navigate(Rutas.INICIO) {
                                    popUpTo(Rutas.INICIO) { inclusive = true }
                                }
                            }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver al inicio"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            }
        ) { relleno ->
            Column(Modifier.padding(relleno)) {
                NavHost(
                    navController = controladorNav,
                    startDestination = Rutas.INICIO
                ) {
                    composable(Rutas.INICIO) { InicioPantalla(controladorNav) }
                    composable(Rutas.ENTRADA_TEXTO) { EntradaTextoPantalla() }
                    composable(Rutas.BOTONES) { BotonesPantalla() }
                    composable(Rutas.SELECCION) { SeleccionPantalla() }
                    composable(Rutas.LISTAS) { ListasPantalla() }
                    composable(Rutas.INFORMACION) { InformacionPantalla() }
                    composable(Rutas.CONTENEDORES) { ContenedoresPantalla() }
                }
            }
        }
    }
}