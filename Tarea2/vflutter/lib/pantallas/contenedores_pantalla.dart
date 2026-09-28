import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class ContenedoresPantalla extends StatelessWidget {
  const ContenedoresPantalla({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const EncabezadoSeccion(
          titulo: 'Sección 6: Contenedores y estructura',
          descripcion:
          'Los contenedores organizan el espacio de la pantalla. Definen '
              'si los elementos se acomodan en fila, en columna, superpuestos '
              'o con pesos que reparten el espacio disponible.',
        ),

        BloqueDocumentado(
          titulo: '1. Distribución en fila',
          descripcion:
          'Los elementos se acomodan uno al lado del otro de forma '
              'horizontal. Se usa cuando los componentes deben compararse o '
              'alinearse en el mismo eje.',
          child: Row(
            children: [
              Expanded(
                child: _cajaColor(
                  context,
                  'Uno',
                  Theme.of(context).colorScheme.primaryContainer,
                  Theme.of(context).colorScheme.onPrimaryContainer,
                ),
              ),
              const SizedBox(width: 8),
              Expanded(
                child: _cajaColor(
                  context,
                  'Dos',
                  Theme.of(context).colorScheme.secondaryContainer,
                  Theme.of(context).colorScheme.onSecondaryContainer,
                ),
              ),
              const SizedBox(width: 8),
              Expanded(
                child: _cajaColor(
                  context,
                  'Tres',
                  Theme.of(context).colorScheme.tertiaryContainer,
                  Theme.of(context).colorScheme.onTertiaryContainer,
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '2. Distribución en columna',
          descripcion:
          'Los elementos se acomodan uno debajo del otro de forma '
              'vertical. Es la organización más común para agrupar contenido.',
          child: Column(
            children: [
              _cajaColor(
                context,
                'Primer elemento',
                Theme.of(context).colorScheme.primaryContainer,
                Theme.of(context).colorScheme.onPrimaryContainer,
              ),
              const SizedBox(height: 8),
              _cajaColor(
                context,
                'Segundo elemento',
                Theme.of(context).colorScheme.secondaryContainer,
                Theme.of(context).colorScheme.onSecondaryContainer,
              ),
              const SizedBox(height: 8),
              _cajaColor(
                context,
                'Tercer elemento',
                Theme.of(context).colorScheme.tertiaryContainer,
                Theme.of(context).colorScheme.onTertiaryContainer,
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '3. Distribución superpuesta (conexión con Sección 3)',
          descripcion:
          'Los elementos se colocan uno encima del otro dentro del mismo '
              'espacio. El color y el tamaño vienen de los controles de la '
              'Sección 3, así que cambian en tiempo real.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                height: 200,
                decoration: BoxDecoration(
                  color: Theme.of(context).colorScheme.surfaceContainerHighest,
                  borderRadius: BorderRadius.circular(8),
                ),
                child: Stack(
                  children: [
                    Positioned(
                      top: 0,
                      left: 0,
                      child: ValueListenableBuilder<double>(
                        valueListenable: DatosCompartidos.tamanoContenedor,
                        builder: (context, tamano, _) {
                          return Container(
                            width: tamano,
                            height: tamano,
                            color: Theme.of(context).colorScheme.primaryContainer,
                          );
                        },
                      ),
                    ),
                    Positioned(
                      bottom: 0,
                      right: 0,
                      child: ValueListenableBuilder<double>(
                        valueListenable: DatosCompartidos.tamanoContenedor,
                        builder: (context, tamano, _) {
                          return ValueListenableBuilder<int>(
                            valueListenable: DatosCompartidos.colorContenedor,
                            builder: (context, color, _) {
                              return Container(
                                width: tamano,
                                height: tamano,
                                color: Color(color),
                              );
                            },
                          );
                        },
                      ),
                    ),
                    Center(
                      child: Card(
                        color: Theme.of(context).colorScheme.surface,
                        child: const Padding(
                          padding: EdgeInsets.all(8),
                          child: Text(
                            'Capa al frente',
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 8),
              ValueListenableBuilder<double>(
                valueListenable: DatosCompartidos.tamanoContenedor,
                builder: (context, tamano, _) {
                  return Text(
                    'Tamaño actual: ${tamano.round()} dp',
                    style: Theme.of(context).textTheme.bodySmall?.copyWith(
                      color: Theme.of(context).colorScheme.onSurfaceVariant,
                    ),
                  );
                },
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '4. Distribución con pesos proporcionales',
          descripcion:
          'Cada elemento ocupa una fracción del espacio disponible según '
              'su peso. Un peso mayor significa más espacio en la pantalla.',
          child: SizedBox(
            height: 80,
            child: Row(
              children: [
                Expanded(
                  flex: 1,
                  child: _cajaColor(
                    context,
                    'Peso 1',
                    Theme.of(context).colorScheme.primaryContainer,
                    Theme.of(context).colorScheme.onPrimaryContainer,
                    alturaCompleta: true,
                  ),
                ),
                const SizedBox(width: 8),
                Expanded(
                  flex: 2,
                  child: _cajaColor(
                    context,
                    'Peso 2',
                    Theme.of(context).colorScheme.secondaryContainer,
                    Theme.of(context).colorScheme.onSecondaryContainer,
                    alturaCompleta: true,
                  ),
                ),
                const SizedBox(width: 8),
                Expanded(
                  flex: 3,
                  child: _cajaColor(
                    context,
                    'Peso 3',
                    Theme.of(context).colorScheme.tertiaryContainer,
                    Theme.of(context).colorScheme.onTertiaryContainer,
                    alturaCompleta: true,
                  ),
                ),
              ],
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '5. Contenedor con desplazamiento vertical',
          descripcion:
          'Permite recorrer contenido más largo que la pantalla con un '
              'desplazamiento vertical. Este bloque contiene 25 elementos '
              'numerados que puedes recorrer.',
          child: Container(
            height: 220,
            decoration: BoxDecoration(
              color: Theme.of(context).colorScheme.surfaceContainerHighest,
              borderRadius: BorderRadius.circular(8),
            ),
            child: ListView.builder(
              padding: const EdgeInsets.all(8),
              itemCount: 25,
              itemBuilder: (context, indice) {
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
                  child: Text(
                    'Elemento desplazable número ${indice + 1}',
                    style: Theme.of(context).textTheme.bodyMedium,
                  ),
                );
              },
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '6. Barra superior y menú lateral',
          descripcion:
          'La barra superior muestra el título de la sección actual y '
              'acciones como abrir el menú o volver al inicio. El menú lateral '
              'permite moverse entre las seis secciones del catálogo.',
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Card(
                color: Theme.of(context).colorScheme.primary,
                child: Padding(
                  padding: const EdgeInsets.all(12),
                  child: Row(
                    children: [
                      Expanded(
                        child: Text(
                          'Contenedores y estructura',
                          style: TextStyle(
                            color: Theme.of(context).colorScheme.onPrimary,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                      Icon(
                        Icons.menu,
                        color: Theme.of(context).colorScheme.onPrimary,
                      ),
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 8),
              Text(
                'Esta barra es un ejemplo estático. La barra real ya funciona '
                    'en toda la aplicación y se abrió desde el menú lateral.',
                style: Theme.of(context).textTheme.bodySmall?.copyWith(
                  color: Theme.of(context).colorScheme.onSurfaceVariant,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget _cajaColor(
      BuildContext context,
      String texto,
      Color color,
      Color textoColor, {
        bool alturaCompleta = false,
      }) {
    return Container(
      height: alturaCompleta ? null : 60,
      decoration: BoxDecoration(
        color: color,
        borderRadius: BorderRadius.circular(4),
      ),
      alignment: Alignment.center,
      child: Text(
        texto,
        style: TextStyle(
          color: textoColor,
          fontWeight: FontWeight.w600,
        ),
      ),
    );
  }
}