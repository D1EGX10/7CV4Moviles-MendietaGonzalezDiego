import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class BotonesPantalla extends StatefulWidget {
  const BotonesPantalla({super.key});

  @override
  State<BotonesPantalla> createState() => _BotonesPantallaState();
}

class _BotonesPantallaState extends State<BotonesPantalla> {
  int _opcionSegmentada = 0;
  bool _cargando = false;
  int _contadorRapido = 0;

  void _avisar(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensaje),
        duration: const Duration(seconds: 2),
      ),
    );
  }

  Future<void> _simularCarga() async {
    setState(() => _cargando = true);
    await Future.delayed(const Duration(seconds: 2));
    if (!mounted) return;
    setState(() => _cargando = false);
    _avisar('Guardado completado');
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const EncabezadoSeccion(
          titulo: 'Sección 2: Botones y acciones',
          descripcion:
          'Los botones comunican acciones al usuario. Su nivel de énfasis '
              'indica la importancia de la acción: relleno para la principal, '
              'contorno para secundarias y texto para acciones terciarias.',
        ),

        BloqueDocumentado(
          titulo: '1. Botones básicos',
          descripcion:
          'El botón relleno se usa para la acción principal. El de contorno '
              'para acciones secundarias. El de texto para acciones de menor '
              'importancia o enlaces dentro de un párrafo.',
          child: Wrap(
            spacing: 8,
            runSpacing: 8,
            children: [
              ElevatedButton(
                onPressed: () => _avisar('Pulsaste el botón relleno'),
                child: const Text('Relleno'),
              ),
              OutlinedButton(
                onPressed: () => _avisar('Pulsaste el botón de contorno'),
                child: const Text('Contorno'),
              ),
              TextButton(
                onPressed: () => _avisar('Pulsaste el botón de texto'),
                child: const Text('Texto'),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '2. Botones con ícono',
          descripcion:
          'Los íconos refuerzan visualmente la acción. Pueden usarse solos '
              'cuando la acción es reconocible al instante, o acompañados de '
              'texto para mayor claridad.',
          child: Row(
            children: [
              IconButton(
                onPressed: () => _avisar('Pulsaste el botón con solo ícono'),
                icon: const Icon(Icons.share),
                tooltip: 'Compartir',
              ),
              const SizedBox(width: 8),
              ElevatedButton.icon(
                onPressed: () => _avisar('Pulsaste el botón con ícono y texto'),
                icon: const Icon(Icons.check, size: 18),
                label: const Text('Enviar'),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '3. Botones de acción flotante',
          descripcion:
          'El botón flotante se usa para la acción principal de la pantalla. '
              'El extendido incluye texto para dejar clara la acción que realiza.',
          child: Row(
            children: [
              FloatingActionButton(
                onPressed: () => _avisar('Pulsaste el botón flotante'),
                child: const Icon(Icons.add),
              ),
              const SizedBox(width: 16),
              FloatingActionButton.extended(
                onPressed: () => _avisar('Pulsaste el botón flotante extendido'),
                icon: const Icon(Icons.add),
                label: const Text('Crear nuevo'),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '4. Selector segmentado',
          descripcion:
          'Permite elegir una sola opción entre varias contiguas. Es útil '
              'para alternar vistas o modos donde las opciones son mutuamente '
              'excluyentes y se conocen de antemano.',
          child: SegmentedButton<int>(
            segments: const [
              ButtonSegment(value: 0, label: Text('Opción A')),
              ButtonSegment(value: 1, label: Text('Opción B')),
              ButtonSegment(value: 2, label: Text('Opción C')),
            ],
            selected: {_opcionSegmentada},
            onSelectionChanged: (seleccion) {
              setState(() => _opcionSegmentada = seleccion.first);
              _avisar('Seleccionaste: Opción ${String.fromCharCode(65 + _opcionSegmentada)}');
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '5. Estados de botón',
          descripcion:
          'Un botón deshabilitado indica que la acción no está disponible. '
              'El estado de carga muestra que una acción está en proceso y evita '
              'pulsaciones repetidas.',
          child: Row(
            children: [
              ElevatedButton(
                onPressed: null,
                child: const Text('Deshabilitado'),
              ),
              const SizedBox(width: 8),
              ElevatedButton(
                onPressed: _cargando ? null : _simularCarga,
                child: _cargando
                    ? const Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    SizedBox(
                      width: 16,
                      height: 16,
                      child: CircularProgressIndicator(
                        strokeWidth: 2,
                        color: Colors.white,
                      ),
                    ),
                    SizedBox(width: 8),
                    Text('Guardando...'),
                  ],
                )
                    : const Text('Guardar'),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '6. Conexión con la Sección 4',
          descripcion:
          'Este botón agrega un elemento nuevo a la lista compartida que '
              'verás en la Sección 4. Demuestra cómo una acción en una pantalla '
              'modifica el contenido de otra.',
          child: SizedBox(
            width: double.infinity,
            child: ElevatedButton.icon(
              onPressed: () {
                _contadorRapido++;
                final nuevo = 'Elemento rápido $_contadorRapido';
                DatosCompartidos.agregarElemento(nuevo);
                _avisar('Agregado: $nuevo');
              },
              icon: const Icon(Icons.add),
              label: const Text('Agregar elemento rápido a la lista'),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '7. Conexión con la Sección 5',
          descripcion:
          'Este botón avanza el progreso global que se dibuja en la '
              'Sección 5. Cada pulsación suma un 10 por ciento y se reinicia '
              'al superar el 100 por ciento.',
          child: SizedBox(
            width: double.infinity,
            child: ElevatedButton.icon(
              onPressed: () {
                DatosCompartidos.avanzarProgreso();
                _avisar('Progreso actualizado: ${DatosCompartidos.progresoGlobal.value}%');
              },
              icon: const Icon(Icons.refresh),
              label: const Text('Avanzar progreso global'),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '8. Limpiar lista compartida',
          descripcion:
          'Elimina todos los elementos agregados desde la Sección 1 y la '
              'Sección 2. Se usa para probar el estado vacío de la Sección 4.',
          child: SizedBox(
            width: double.infinity,
            child: OutlinedButton.icon(
              onPressed: () {
                DatosCompartidos.limpiarLista();
                _avisar('Lista vaciada');
              },
              icon: const Icon(Icons.delete),
              label: const Text('Limpiar lista compartida'),
            ),
          ),
        ),
      ],
    );
  }
}