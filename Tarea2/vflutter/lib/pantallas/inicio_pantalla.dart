import 'package:flutter/material.dart';

class InicioPantalla extends StatelessWidget {
  final void Function(int)? onNavegar;

  const InicioPantalla({super.key, this.onNavegar});

  @override
  Widget build(BuildContext context) {
    final secciones = [
      ('Sección 1', 'Entrada de texto', 1),
      ('Sección 2', 'Botones y acciones', 2),
      ('Sección 3', 'Elementos de selección', 3),
      ('Sección 4', 'Listas y colecciones', 4),
      ('Sección 5', 'Información y retroalimentación', 5),
      ('Sección 6', 'Contenedores y estructura', 6),
    ];

    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Text(
          'Catálogo de elementos de interfaz',
          style: Theme.of(context).textTheme.headlineMedium?.copyWith(
            fontWeight: FontWeight.bold,
          ),
        ),
        const SizedBox(height: 8),
        Text(
          'Esta aplicación muestra los componentes básicos de una interfaz móvil. '
              'Explora cada sección desde el menú lateral o toca una tarjeta para entrar directamente.',
          style: Theme.of(context).textTheme.bodyMedium,
        ),
        const SizedBox(height: 24),
        ...secciones.map((s) {
          return Card(
            margin: const EdgeInsets.only(bottom: 12),
            color: Theme.of(context).colorScheme.surfaceContainerHighest,
            child: InkWell(
              onTap: onNavegar != null ? () => onNavegar!(s.$3) : null,
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      s.$1,
                      style: Theme.of(context).textTheme.labelSmall?.copyWith(
                        color: Theme.of(context).colorScheme.primary,
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      s.$2,
                      style: Theme.of(context).textTheme.titleMedium,
                    ),
                  ],
                ),
              ),
            ),
          );
        }),
      ],
    );
  }
}