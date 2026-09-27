import 'package:flutter/foundation.dart';

class DatosCompartidos {
  static final nombreUsuario = ValueNotifier<String>('');
  static final progresoGlobal = ValueNotifier<int>(30);
  static final notificacionesActivas = ValueNotifier<bool>(true);
  static final tamanoContenedor = ValueNotifier<double>(80);
  static final colorContenedor = ValueNotifier<int>(0xFF6750A4);
  static final elementoSeleccionado = ValueNotifier<String>('');
  static final elementosAgregados = ValueNotifier<List<String>>([]);

  static bool agregarElemento(String texto) {
    final limpio = texto.trim();
    if (limpio.isEmpty) return false;
    if (elementosAgregados.value.contains(limpio)) return false;
    elementosAgregados.value = [limpio, ...elementosAgregados.value];
    return true;
  }

  static void eliminarElemento(String texto) {
    elementosAgregados.value =
        elementosAgregados.value.where((e) => e != texto).toList();
  }

  static void limpiarLista() {
    elementosAgregados.value = [];
  }

  static void avanzarProgreso() {
    progresoGlobal.value = (progresoGlobal.value + 10) % 110;
    if (progresoGlobal.value > 100) progresoGlobal.value = 0;
  }

  static void reiniciarProgreso() {
    progresoGlobal.value = 30;
  }
}