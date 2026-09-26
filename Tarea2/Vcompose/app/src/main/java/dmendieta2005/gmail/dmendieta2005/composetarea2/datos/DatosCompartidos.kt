package dmendieta2005.gmail.dmendieta2005.composetarea2.datos

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object DatosCompartidos {

    val elementosAgregados = mutableStateListOf<String>()
    val nombreUsuario = mutableStateOf("")

    val progresoGlobal = mutableStateOf(30)

    val notificacionesActivas = mutableStateOf(true)

    val tamanoContenedor = mutableStateOf(80)

    val colorContenedor = mutableStateOf(0xFF6750A4)

    val elementoSeleccionado = mutableStateOf("")

    fun agregarElemento(texto: String): Boolean {
        val limpio = texto.trim()
        if (limpio.isEmpty()) return false
        if (elementosAgregados.contains(limpio)) return false
        elementosAgregados.add(0, limpio)
        return true
    }

    fun eliminarElemento(texto: String) {
        elementosAgregados.remove(texto)
    }

    fun limpiarLista() {
        elementosAgregados.clear()
    }

    fun avanzarProgreso() {
        progresoGlobal.value = (progresoGlobal.value + 10) % 110
        if (progresoGlobal.value > 100) progresoGlobal.value = 0
    }

    fun reiniciarProgreso() {
        progresoGlobal.value = 30
    }
}