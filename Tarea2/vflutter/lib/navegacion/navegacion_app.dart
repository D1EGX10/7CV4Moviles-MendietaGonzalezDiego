import 'package:flutter/material.dart';
import '../pantallas/inicio_pantalla.dart';
import '../pantallas/entrada_texto_pantalla.dart';
import '../pantallas/botones_pantalla.dart';
import '../pantallas/seleccion_pantalla.dart';
import '../pantallas/listas_pantalla.dart';
import '../pantallas/informacion_pantalla.dart';
import '../pantallas/contenedores_pantalla.dart';

class NavegacionApp extends StatefulWidget {
  const NavegacionApp({super.key});

  @override
  State<NavegacionApp> createState() => _NavegacionAppState();
}

class _NavegacionAppState extends State<NavegacionApp> {
  int _indice = 0;

  static const List<Map<String, dynamic>> _secciones = [
    {'titulo': 'Inicio', 'icono': Icons.home},
    {'titulo': '1. Entrada de texto', 'icono': Icons.edit},
    {'titulo': '2. Botones y acciones', 'icono': Icons.smart_button},
    {'titulo': '3. Elementos de selección', 'icono': Icons.tune},
    {'titulo': '4. Listas y colecciones', 'icono': Icons.list},
    {'titulo': '5. Información y retroalimentación', 'icono': Icons.info},
    {'titulo': '6. Contenedores y estructura', 'icono': Icons.dashboard},
  ];

  void _cambiarSeccion(int indice) {
    setState(() => _indice = indice);
  }

  Widget _pantallaActual() {
    switch (_indice) {
      case 0:
        return InicioPantalla(onNavegar: _cambiarSeccion);
      case 1:
        return const EntradaTextoPantalla();
      case 2:
        return const BotonesPantalla();
      case 3:
        return const SeleccionPantalla();
      case 4:
        return const ListasPantalla();
      case 5:
        return const InformacionPantalla();
      case 6:
        return const ContenedoresPantalla();
      default:
        return InicioPantalla(onNavegar: _cambiarSeccion);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_secciones[_indice]['titulo'] as String),
        leading: _indice == 0
            ? Builder(
          builder: (context) => IconButton(
            icon: const Icon(Icons.menu),
            onPressed: () => Scaffold.of(context).openDrawer(),
          ),
        )
            : IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () => setState(() => _indice = 0),
        ),
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            DrawerHeader(
              decoration: BoxDecoration(
                color: Theme.of(context).colorScheme.primary,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Icon(
                    Icons.widgets,
                    size: 40,
                    color: Theme.of(context).colorScheme.onPrimary,
                  ),
                  const SizedBox(height: 8),
                  Text(
                    'Catálogo UI',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.onPrimary,
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ],
              ),
            ),
            ..._secciones.asMap().entries.map((e) {
              return ListTile(
                leading: Icon(e.value['icono'] as IconData),
                title: Text(e.value['titulo'] as String),
                selected: _indice == e.key,
                selectedTileColor:
                Theme.of(context).colorScheme.primaryContainer,
                onTap: () {
                  setState(() => _indice = e.key);
                  Navigator.pop(context);
                },
              );
            }),
          ],
        ),
      ),
      body: _pantallaActual(),
    );
  }
}