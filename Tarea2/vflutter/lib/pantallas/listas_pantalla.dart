import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class ListasPantalla extends StatefulWidget {
  const ListasPantalla({super.key});

  @override
  State<ListasPantalla> createState() => _ListasPantallaState();
}

class _ListasPantallaState extends State<ListasPantalla>
    with SingleTickerProviderStateMixin {
  late TabController _tabCtrl;

  final Map<String, List<String>> _categorias = {
    'Electrónica': ['Audífonos', 'Teclado', 'Mouse', 'Monitor', 'Cámara'],
    'Ropa': ['Camisa', 'Pantalón', 'Chamarra', 'Zapatos', 'Gorra'],
    'Libros': ['Novela', 'Ciencia ficción', 'Historia', 'Poesía', 'Ensayo'],
    'Deportes': ['Balón', 'Raqueta', 'Bicicleta', 'Tenis', 'Guantes'],
  };

  final List<String> _cuadricula = List.generate(20, (i) => 'Tarjeta ${i + 1}');

  bool _actualizando = false;

  @override
  void initState() {
    super.initState();
    _tabCtrl = TabController(length: 3, vsync: this);
  }

  @override
  void dispose() {
    _tabCtrl.dispose();
    super.dispose();
  }

  void _avisar(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(mensaje),
        duration: const Duration(seconds: 2),
      ),
    );
  }

  void _mostrarDetalle(String texto) {
    DatosCompartidos.elementoSeleccionado.value = texto;
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Detalle del elemento'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              texto,
              style: const TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 16,
              ),
            ),
            const SizedBox(height: 8),
            const Text(
              'Este elemento fue seleccionado desde la lista. Su nombre '
                  'también se refleja en la Sección 5.',
            ),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cerrar'),
          ),
        ],
      ),
    );
  }

  Future<void> _actualizar() async {
    setState(() => _actualizando = true);
    await Future.delayed(const Duration(milliseconds: 1500));
    if (!mounted) return;
    setState(() => _actualizando = false);
    _avisar('Lista actualizada');
  }

  Widget _listaVertical() {
    return ValueListenableBuilder<List<String>>(
      valueListenable: DatosCompartidos.elementosAgregados,
      builder: (context, agregados, _) {
        if (agregados.isEmpty && _categorias.values.every((e) => e.isEmpty)) {
          return _estadoVacio();
        }

        return Column(
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
              child: Row(
                children: [
                  Expanded(
                    child: Text(
                      _actualizando
                          ? 'Actualizando...'
                          : 'Desliza para actualizar la lista',
                      style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                        color: Theme.of(context).colorScheme.onSurfaceVariant,
                      ),
                    ),
                  ),
                  IconButton(
                    onPressed: _actualizando ? null : _actualizar,
                    icon: const Icon(Icons.refresh),
                    tooltip: 'Actualizar lista',
                  ),
                ],
              ),
            ),
            Expanded(
              child: RefreshIndicator(
                onRefresh: _actualizar,
                child: ListView(
                  padding: const EdgeInsets.symmetric(vertical: 8),
                  children: [
                    if (agregados.isNotEmpty) ...[
                      Padding(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 16,
                          vertical: 8,
                        ),
                        child: Text(
                          'Agregados desde otras secciones',
                          style: Theme.of(context).textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                            color: Theme.of(context).colorScheme.primary,
                          ),
                        ),
                      ),
                      ...agregados.map((elemento) {
                        return _itemDeslizable(
                          elemento,
                          onEliminar: () {
                            DatosCompartidos.eliminarElemento(elemento);
                            _avisar('Elemento eliminado');
                          },
                        );
                      }),
                    ],
                    ..._categorias.entries.expand((entrada) {
                      return [
                        Padding(
                          padding: const EdgeInsets.fromLTRB(16, 16, 16, 8),
                          child: Text(
                            entrada.key,
                            style: Theme.of(context).textTheme.titleMedium?.copyWith(
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        ...entrada.value.map((elemento) {
                          return _itemDeslizable(
                            elemento,
                            onEliminar: () {
                              _avisar('$elemento eliminado');
                            },
                          );
                        }),
                      ];
                    }),
                    const SizedBox(height: 24),
                  ],
                ),
              ),
            ),
          ],
        );
      },
    );
  }

  Widget _itemDeslizable(String texto, {required VoidCallback onEliminar}) {
    return Dismissible(
      key: ValueKey('dismissible_$texto'),
      direction: DismissDirection.endToStart,
      background: Container(
        color: Theme.of(context).colorScheme.errorContainer,
        alignment: Alignment.centerRight,
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: Icon(
          Icons.delete,
          color: Theme.of(context).colorScheme.onErrorContainer,
        ),
      ),
      onDismissed: (_) => onEliminar(),
      child: Card(
        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 4),
        color: Theme.of(context).colorScheme.surfaceContainerHighest,
        child: ListTile(
          leading: Icon(
            Icons.info,
            color: Theme.of(context).colorScheme.primary,
          ),
          title: Text(texto),
          onTap: () => _mostrarDetalle(texto),
        ),
      ),
    );
  }

  Widget _estadoVacio() {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.list_alt,
              size: 72,
              color: Theme.of(context).colorScheme.primary,
            ),
            const SizedBox(height: 16),
            Text(
              'Lista vacía',
              style: Theme.of(context).textTheme.titleMedium?.copyWith(
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 8),
            const Text(
              'Agrega elementos desde las secciones 1 y 2.',
              textAlign: TextAlign.center,
            ),
          ],
        ),
      ),
    );
  }

  Widget _cuadriculaWidget() {
    return GridView.builder(
      padding: const EdgeInsets.all(8),
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        crossAxisSpacing: 8,
        mainAxisSpacing: 8,
      ),
      itemCount: _cuadricula.length,
      itemBuilder: (context, indice) {
        return Card(
          color: Theme.of(context).colorScheme.secondaryContainer,
          child: Center(
            child: Text(
              _cuadricula[indice],
              style: TextStyle(
                fontWeight: FontWeight.bold,
                color: Theme.of(context).colorScheme.onSecondaryContainer,
              ),
            ),
          ),
        );
      },
    );
  }

  Widget _pestanasInternas() {
    return DefaultTabController(
      length: 3,
      child: Column(
        children: [
          const TabBar(
            tabs: [
              Tab(text: 'Uno'),
              Tab(text: 'Dos'),
              Tab(text: 'Tres'),
            ],
          ),
          Expanded(
            child: TabBarView(
              children: [
                _contenidoPestana(
                  'Contenido de la pestaña 1',
                  Theme.of(context).colorScheme.primaryContainer,
                ),
                _contenidoPestana(
                  'Contenido de la pestaña 2',
                  Theme.of(context).colorScheme.secondaryContainer,
                ),
                _contenidoPestana(
                  'Contenido de la pestaña 3',
                  Theme.of(context).colorScheme.tertiaryContainer,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _contenidoPestana(String texto, Color color) {
    return Container(
      color: color,
      alignment: Alignment.center,
      child: Text(
        texto,
        style: Theme.of(context).textTheme.headlineSmall?.copyWith(
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Padding(
          padding: EdgeInsets.all(16),
          child: EncabezadoSeccion(
            titulo: 'Sección 4: Listas y colecciones',
            descripcion:
            'Las listas organizan grandes cantidades de información en un '
                'formato recorrible. Pueden ser verticales, en cuadrícula o '
                'con pestañas, y admiten gestos como deslizar para eliminar.',
          ),
        ),
        TabBar(
          controller: _tabCtrl,
          tabs: const [
            Tab(text: 'Lista'),
            Tab(text: 'Cuadrícula'),
            Tab(text: 'Pestañas'),
          ],
        ),
        Expanded(
          child: TabBarView(
            controller: _tabCtrl,
            children: [
              _listaVertical(),
              _cuadriculaWidget(),
              _pestanasInternas(),
            ],
          ),
        ),
      ],
    );
  }
}