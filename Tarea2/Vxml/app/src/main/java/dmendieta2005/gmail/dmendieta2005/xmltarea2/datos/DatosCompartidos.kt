package dmendieta2005.gmail.dmendieta2005.xmltarea2.datos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object DatosCompartidos {

    private val _elementosAgregados = MutableLiveData<MutableList<String>>(mutableListOf())
    val elementosAgregados: LiveData<MutableList<String>> = _elementosAgregados

    private val _nombreUsuario = MutableLiveData<String>("")
    val nombreUsuario: LiveData<String> = _nombreUsuario

    private val _progresoGlobal = MutableLiveData<Int>(30)
    val progresoGlobal: LiveData<Int> = _progresoGlobal

    private val _notificacionesActivas = MutableLiveData<Boolean>(true)
    val notificacionesActivas: LiveData<Boolean> = _notificacionesActivas

    private val _tamanoContenedor = MutableLiveData<Int>(80)
    val tamanoContenedor: LiveData<Int> = _tamanoContenedor

    private val _colorContenedor = MutableLiveData<Int>(0xFF6750A4.toInt())
    val colorContenedor: LiveData<Int> = _colorContenedor

    private val _elementoSeleccionado = MutableLiveData<String>("")
    val elementoSeleccionado: LiveData<String> = _elementoSeleccionado

    fun agregarElemento(texto: String): Boolean {
        val limpio = texto.trim()
        if (limpio.isEmpty()) return false
        val lista = _elementosAgregados.value ?: mutableListOf()
        if (lista.contains(limpio)) return false
        val nueva = mutableListOf(limpio)
        nueva.addAll(lista)
        _elementosAgregados.value = nueva
        return true
    }

    fun eliminarElemento(texto: String) {
        val lista = _elementosAgregados.value ?: mutableListOf()
        lista.remove(texto)
        _elementosAgregados.value = lista.toMutableList()
    }

    fun limpiarLista() {
        _elementosAgregados.value = mutableListOf()
    }

    fun establecerNombre(texto: String) {
        _nombreUsuario.value = texto
    }

    fun avanzarProgreso() {
        val actual = _progresoGlobal.value ?: 30
        val nuevo = (actual + 10) % 110
        _progresoGlobal.value = if (nuevo > 100) 0 else nuevo
    }

    fun establecerNotificaciones(valor: Boolean) {
        _notificacionesActivas.value = valor
    }

    fun establecerTamano(valor: Int) {
        _tamanoContenedor.value = valor
    }

    fun establecerColor(valor: Int) {
        _colorContenedor.value = valor
    }

    fun establecerElementoSeleccionado(texto: String) {
        _elementoSeleccionado.value = texto
    }
}