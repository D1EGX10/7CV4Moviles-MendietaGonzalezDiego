import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class SeleccionPantalla extends StatefulWidget {
  const SeleccionPantalla({super.key});

  @override
  State<SeleccionPantalla> createState() => _SeleccionPantallaState();
}

class _SeleccionPantallaState extends State<SeleccionPantalla> {
  bool _checkNormal = false;
  bool? _checkIndeterminado;

  String _opcionRadio = 'A';

  double _valorUnico = 50;
  RangeValues _rango = const RangeValues(20, 80);

  String? _opcionDesplegable;
  final List<String> _opcionesDesplegable = ['Alta', 'Media', 'Baja'];

  DateTime? _fecha;
  TimeOfDay? _hora;

  final List<Map<String, dynamic>> _chips = [
    {'nombre': 'Filtro rojo', 'color': 0xFFE53935},
    {'nombre': 'Filtro azul', 'color': 0xFF1E88E5},
    {'nombre': 'Filtro verde', 'color': 0xFF43A047},
    {'nombre': 'Filtro morado', 'color': 0xFF8E24AA},
  ];
  String _chipSeleccionado = 'Filtro rojo';

  void _avisar(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensaje),
        duration: const Duration(seconds: 2),
      ),
    );
  }

  Future<void> _elegirFecha() async {
    final seleccion = await showDatePicker(
      context: context,
      initialDate: _fecha ?? DateTime.now(),
      firstDate: DateTime(2020),
      lastDate: DateTime(2030),
      locale: const Locale('es', 'MX'),
    );
    if (seleccion != null) {
      setState(() => _fecha = seleccion);
      _avisar('Fecha elegida: ${seleccion.day}/${seleccion.month}/${seleccion.year}');
    }
  }

  Future<void> _elegirHora() async {
    final seleccion = await showTimePicker(
      context: context,
      initialTime: _hora ?? TimeOfDay.now(),
    );
    if (seleccion != null) {
      setState(() => _hora = seleccion);
      _avisar('Hora elegida: ${seleccion.hour}:${seleccion.minute.toString().padLeft(2, '0')}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const EncabezadoSeccion(
          titulo: 'Sección 3: Elementos de selección',
          descripcion:
          'Los elementos de selección permiten al usuario elegir entre '
              'opciones. Pueden aceptar una sola respuesta, varias a la vez, '
              'o valores continuos como los deslizadores.',
        ),

        BloqueDocumentado(
          titulo: '1. Casillas de verificación',
          descripcion:
          'Permiten seleccionar varias opciones a la vez. La casilla '
              'indeterminada representa un estado parcial, útil cuando solo '
              'algunos elementos de un grupo están seleccionados.',
          child: Column(
            children: [
              CheckboxListTile(
                title: const Text('Opción normal'),
                value: _checkNormal,
                onChanged: (valor) {
                  setState(() => _checkNormal = valor ?? false);
                },
                contentPadding: EdgeInsets.zero,
              ),
              CheckboxListTile(
                title: const Text('Estado indeterminado'),
                tristate: true,
                value: _checkIndeterminado,
                onChanged: (valor) {
                  setState(() => _checkIndeterminado = valor);
                },
                contentPadding: EdgeInsets.zero,
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '2. Botones de opción',
          descripcion:
          'Permiten seleccionar una sola opción dentro de un grupo. '
              'Al elegir una, las demás se desmarcan automáticamente.',
          child: Column(
            children: ['A', 'B', 'C'].map((opcion) {
              return RadioListTile<String>(
                title: Text('Opción $opcion'),
                value: opcion,
                groupValue: _opcionRadio,
                onChanged: (valor) {
                  setState(() => _opcionRadio = valor ?? 'A');
                },
                contentPadding: EdgeInsets.zero,
              );
            }).toList(),
          ),
        ),

        BloqueDocumentado(
          titulo: '3. Interruptor (conexión con Sección 5)',
          descripcion:
          'Activa o desactiva una configuración al instante. Este '
              'interruptor controla si la Sección 5 muestra un mensaje de '
              'bienvenida al entrar.',
          child: ValueListenableBuilder<bool>(
            valueListenable: DatosCompartidos.notificacionesActivas,
            builder: (context, activo, _) {
              return SwitchListTile(
                title: const Text('Activar notificaciones'),
                value: activo,
                onChanged: (valor) {
                  DatosCompartidos.notificacionesActivas.value = valor;
                  _avisar(valor
                      ? 'Notificaciones activadas'
                      : 'Notificaciones desactivadas');
                },
                contentPadding: EdgeInsets.zero,
              );
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '4. Deslizador de valor único',
          descripcion:
          'Permite elegir un valor dentro de un rango arrastrando el '
              'control. Es útil para ajustes como volumen, brillo o tamaño.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('Valor: ${_valorUnico.round()}'),
              Slider(
                value: _valorUnico,
                min: 0,
                max: 100,
                divisions: 100,
                label: _valorUnico.round().toString(),
                onChanged: (valor) {
                  setState(() => _valorUnico = valor);
                },
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '5. Deslizador de rango (conexión con Sección 6)',
          descripcion:
          'Permite elegir dos valores a la vez para definir un rango. '
              'El tamaño elegido modifica las cajas de la Sección 6.',
          child: ValueListenableBuilder<double>(
            valueListenable: DatosCompartidos.tamanoContenedor,
            builder: (context, tamano, _) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Tamaño de cajas: ${tamano.round()} dp'),
                  Slider(
                    value: tamano,
                    min: 40,
                    max: 140,
                    divisions: 100,
                    label: '${tamano.round()}',
                    onChanged: (valor) {
                      DatosCompartidos.tamanoContenedor.value = valor;
                    },
                  ),
                  const SizedBox(height: 12),
                  Text(
                    'Rango de ejemplo: ${_rango.start.round()} a ${_rango.end.round()}',
                  ),
                  RangeSlider(
                    values: _rango,
                    min: 0,
                    max: 100,
                    divisions: 100,
                    labels: RangeLabels(
                      _rango.start.round().toString(),
                      _rango.end.round().toString(),
                    ),
                    onChanged: (valores) {
                      setState(() => _rango = valores);
                    },
                  ),
                ],
              );
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '6. Lista desplegable',
          descripcion:
          'Muestra una lista de opciones al pulsarla. Es un componente '
              'compacto para elegir un solo valor entre varios.',
          child: DropdownButtonFormField<String>(
            value: _opcionDesplegable,
            decoration: const InputDecoration(
              labelText: 'Prioridad',
              border: OutlineInputBorder(),
            ),
            items: _opcionesDesplegable.map((opcion) {
              return DropdownMenuItem<String>(
                value: opcion,
                child: Text(opcion),
              );
            }).toList(),
            onChanged: (valor) {
              setState(() => _opcionDesplegable = valor);
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '7. Selector de fecha',
          descripcion:
          'Abre un diálogo del sistema para elegir un día del calendario. '
              'El resultado se muestra como texto en formato local.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Fecha elegida: ${_fecha == null ? "sin seleccionar" : "${_fecha!.day}/${_fecha!.month}/${_fecha!.year}"}',
              ),
              const SizedBox(height: 8),
              SizedBox(
                width: double.infinity,
                child: OutlinedButton.icon(
                  onPressed: _elegirFecha,
                  icon: const Icon(Icons.calendar_today),
                  label: const Text('Elegir fecha'),
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '8. Selector de hora',
          descripcion:
          'Abre un diálogo para elegir una hora del día. Se usa para '
              'programar eventos, alarmas o recordatorios.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Hora elegida: ${_hora == null ? "sin seleccionar" : "${_hora!.hour}:${_hora!.minute.toString().padLeft(2, "0")}"}',
              ),
              const SizedBox(height: 8),
              SizedBox(
                width: double.infinity,
                child: OutlinedButton.icon(
                  onPressed: _elegirHora,
                  icon: const Icon(Icons.access_time),
                  label: const Text('Elegir hora'),
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '9. Chips de filtro (conexión con Sección 6)',
          descripcion:
          'Elementos compactos que actúan como etiquetas seleccionables. '
              'El color elegido se aplica al contenedor superpuesto de la '
              'Sección 6.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Wrap(
                spacing: 8,
                runSpacing: 8,
                children: _chips.map((chip) {
                  final nombre = chip['nombre'] as String;
                  return FilterChip(
                    label: Text(nombre),
                    selected: _chipSeleccionado == nombre,
                    onSelected: (seleccionado) {
                      setState(() => _chipSeleccionado = nombre);
                      DatosCompartidos.colorContenedor.value =
                      chip['color'] as int;
                    },
                  );
                }).toList(),
              ),
              const SizedBox(height: 16),
              const Text('Vista previa del color:'),
              const SizedBox(height: 8),
              ValueListenableBuilder<int>(
                valueListenable: DatosCompartidos.colorContenedor,
                builder: (context, color, _) {
                  return Container(
                    height: 60,
                    decoration: BoxDecoration(
                      color: Color(color),
                      borderRadius: BorderRadius.circular(8),
                    ),
                  );
                },
              ),
            ],
          ),
        ),
      ],
    );
  }
}