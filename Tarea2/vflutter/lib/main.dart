import 'package:flutter/material.dart';
import 'navegacion/navegacion_app.dart';

void main() {
  runApp(const AplicacionCatalogo());
}

class AplicacionCatalogo extends StatelessWidget {
  const AplicacionCatalogo({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Catálogo UI',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF6750A4),
          brightness: Brightness.light,
        ),
      ),
      darkTheme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF6750A4),
          brightness: Brightness.dark,
        ),
      ),
      themeMode: ThemeMode.system,
      home: const NavegacionApp(),
    );
  }
}