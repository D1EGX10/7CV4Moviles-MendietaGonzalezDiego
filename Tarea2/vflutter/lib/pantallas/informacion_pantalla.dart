import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class InformacionPantalla extends StatefulWidget {
  const InformacionPantalla({super.key});

  @override
  State<InformacionPantalla> createState() => _InformacionPantallaState();
}

class _InformacionPantallaState extends State<InformacionPantalla> {
  bool _mostrarMensajeInicial = true;

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      if (DatosCompartidos.notificacionesActivas.value) {
        _avisar('Notificaciones activas: recibirás avisos');
      }
    });
  }

  void _avisar(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensaje),
        duration: const Duration(seconds: 2),
      ),
    );
  }

  void _mostrarDialogo() {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('¿Confirmar acción?'),
        content: const Text(
          'Esta acción no se puede revertir. ¿Deseas continuar?',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cancelar'),
          ),
          TextButton(
            onPressed: () {
              Navigator.pop(context);
              _avisar('Acción confirmada');
            },
            child: const Text('Confirmar'),
          ),
        ],
      ),
    );
  }

  void _mostrarHojaInferior() {
    showModalBottomSheet(
      context: context,
      builder: (context) => Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          const SizedBox(height: 16),
          Text(
            'Opciones disponibles',
            style: Theme.of(context).textTheme.titleMedium?.copyWith(
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 8),
          ListTile(
            leading: const Icon(Icons.share),
            title: const Text('Compartir'),
            onTap: () {
              Navigator.pop(context);
              _avisar('Compartir');
            },
          ),
          ListTile(
            leading: const Icon(Icons.favorite),
            title: const Text('Guardar en favoritos'),
            onTap: () {
              Navigator.pop(context);
              _avisar('Guardado en favoritos');
            },
          ),
          ListTile(
            leading: const Icon(Icons.report),
            title: const Text('Reportar'),
            onTap: () {
              Navigator.pop(context);
              _avisar('Reportado');
            },
          ),
          const SizedBox(height: 16),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const EncabezadoSeccion(
          titulo: 'Sección 5: Información y retroalimentación',
          descripcion:
          'Estos elementos comunican al usuario el estado de la aplicación, '
              'el resultado de sus acciones y mensajes importantes. Incluyen '
              'textos con jerarquía visual, imágenes, barras de progreso y '
              'avisos emergentes.',
        ),

        BloqueDocumentado(
          titulo: '1. Textos con jerarquía',
          descripcion:
          'El tamaño, peso y color del texto establecen la importancia de '
              'cada mensaje. Un título grande llama la atención, mientras que '
              'una etiqueta pequeña acompaña sin competir.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Display: título principal',
                style: Theme.of(context).textTheme.displaySmall?.copyWith(
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 6),
              Text(
                'Headline: encabezado de sección',
                style: Theme.of(context).textTheme.headlineSmall,
              ),
              const SizedBox(height: 6),
              Text(
                'Title: título de tarjeta',
                style: Theme.of(context).textTheme.titleMedium,
              ),
              const SizedBox(height: 6),
              const Text('Body: texto normal de párrafo para contenido general.'),
              const SizedBox(height: 6),
              Text(
                'Label: etiqueta pequeña',
                style: Theme.of(context).textTheme.labelSmall?.copyWith(
                  color: Theme.of(context).colorScheme.onSurfaceVariant,
                ),
              ),
              const SizedBox(height: 6),
              const Text(
                'Texto en negrita',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 6),
              const Text(
                'Texto en cursiva',
                style: TextStyle(fontStyle: FontStyle.italic),
              ),
              const SizedBox(height: 6),
              Text(
                'Texto con color de énfasis',
                style: TextStyle(
                  color: Theme.of(context).colorScheme.primary,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '2. Imágenes y modos de escalado',
          descripcion:
          'Las imágenes refuerzan el contenido. El modo de escalado decide '
              'cómo se ajustan al espacio: contain las contiene sin recortar, '
              'cover las recorta para llenar el contenedor.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text('Imagen local (BoxFit.contain)'),
              const SizedBox(height: 8),
              SizedBox(
                height: 120,
                width: double.infinity,
                child: Image.asset(
                  'assets/imagen_local.png',
                  fit: BoxFit.contain,
                  errorBuilder: (context, error, stack) => Container(
                    color: Theme.of(context).colorScheme.surfaceContainerHighest,
                    child: const Center(
                      child: Icon(Icons.image, size: 48),
                    ),
                  ),
                ),
              ),
              const SizedBox(height: 16),
              const Text('Imagen desde URL (BoxFit.cover)'),
              const SizedBox(height: 8),
              ClipRRect(
                borderRadius: BorderRadius.circular(8),
                child: CachedNetworkImage(
                  imageUrl: 'https://picsum.photos/800/400',
                  height: 120,
                  width: double.infinity,
                  fit: BoxFit.cover,
                  placeholder: (context, url) => Container(
                    height: 120,
                    color: Theme.of(context).colorScheme.surfaceContainerHighest,
                    child: const Center(child: CircularProgressIndicator()),
                  ),
                  errorWidget: (context, url, error) => Container(
                    height: 120,
                    color: Theme.of(context).colorScheme.errorContainer,
                    child: const Center(
                      child: Icon(Icons.broken_image, size: 48),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '3. Indicadores de progreso',
          descripcion:
          'El progreso determinado muestra un porcentaje específico y '
              'avanza con cada acción de la Sección 2. El indeterminado indica '
              'que hay una operación en curso sin conocer el tiempo restante.',
          child: ValueListenableBuilder<int>(
            valueListenable: DatosCompartidos.progresoGlobal,
            builder: (context, progreso, _) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Progreso global: $progreso%'),
                  const SizedBox(height: 8),
                  LinearProgressIndicator(value: progreso / 100),
                  const SizedBox(height: 12),
                  const Text('Progreso lineal indeterminado'),
                  const SizedBox(height: 8),
                  const LinearProgressIndicator(),
                  const SizedBox(height: 16),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Column(
                        children: [
                          const Text('Circular determinado'),
                          const SizedBox(height: 8),
                          CircularProgressIndicator(value: progreso / 100),
                        ],
                      ),
                      Column(
                        children: const [
                          Text('Circular indeterminado'),
                          SizedBox(height: 8),
                          CircularProgressIndicator(),
                        ],
                      ),
                    ],
                  ),
                ],
              );
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '4. Tarjeta con datos compartidos',
          descripcion:
          'Esta tarjeta muestra el nombre escrito en la Sección 1 y el '
              'elemento seleccionado en la Sección 4. El badge numérico indica '
              'cuántos elementos hay en la lista compartida.',
          child: Card(
            color: Theme.of(context).colorScheme.surfaceContainerHighest,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Expanded(
                        child: Text(
                          'Datos compartidos',
                          style: Theme.of(context).textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                      ValueListenableBuilder<List<String>>(
                        valueListenable: DatosCompartidos.elementosAgregados,
                        builder: (context, lista, _) {
                          return Badge(
                            label: Text(lista.length.toString()),
                            child: const Icon(Icons.notifications),
                          );
                        },
                      ),
                    ],
                  ),
                  const SizedBox(height: 12),
                  ValueListenableBuilder<String>(
                    valueListenable: DatosCompartidos.nombreUsuario,
                    builder: (context, nombre, _) {
                      return Text(
                        'Nombre escrito: ${nombre.isEmpty ? "sin definir" : nombre}',
                      );
                    },
                  ),
                  const SizedBox(height: 4),
                  ValueListenableBuilder<String>(
                    valueListenable: DatosCompartidos.elementoSeleccionado,
                    builder: (context, seleccionado, _) {
                      return Text(
                        'Elemento seleccionado: ${seleccionado.isEmpty ? "ninguno" : seleccionado}',
                      );
                    },
                  ),
                  const SizedBox(height: 8),
                  const Divider(),
                  const SizedBox(height: 8),
                  const Text(
                    'Separador con texto debajo',
                    style: TextStyle(fontStyle: FontStyle.italic),
                  ),
                ],
              ),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '5. Snackbar y Snackbar con acción',
          descripcion:
          'El Snackbar es un aviso que aparece al fondo. Puede incluir un '
              'botón de acción y se usa cuando el usuario puede deshacer o '
              'responder algo.',
          child: Row(
            children: [
              Expanded(
                child: ElevatedButton(
                  onPressed: () => _avisar('Mensaje emergente breve'),
                  child: const Text('Snackbar'),
                ),
              ),
              const SizedBox(width: 8),
              Expanded(
                child: ElevatedButton(
                  onPressed: () {
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: const Text('Elemento eliminado'),
                        action: SnackBarAction(
                          label: 'DESHACER',
                          onPressed: () => _avisar('Acción deshecha'),
                        ),
                      ),
                    );
                  },
                  child: const Text('Con acción'),
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '6. Diálogo de confirmación',
          descripcion:
          'Ventana modal que interrumpe al usuario para pedir una decisión '
              'importante. Debe reservarse para acciones delicadas como '
              'eliminar o cerrar sesión.',
          child: SizedBox(
            width: double.infinity,
            child: ElevatedButton.icon(
              onPressed: _mostrarDialogo,
              icon: const Icon(Icons.clear),
              label: const Text('Mostrar diálogo'),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '7. Hoja inferior',
          descripcion:
          'Panel que sube desde la parte inferior y ofrece opciones '
              'relacionadas con la pantalla. Es menos intrusivo que un diálogo '
              'y más rápido que navegar a otra pantalla.',
          child: SizedBox(
            width: double.infinity,
            child: OutlinedButton.icon(
              onPressed: _mostrarHojaInferior,
              icon: const Icon(Icons.info),
              label: const Text('Mostrar hoja inferior'),
            ),
          ),
        ),
      ],
    );
  }
}