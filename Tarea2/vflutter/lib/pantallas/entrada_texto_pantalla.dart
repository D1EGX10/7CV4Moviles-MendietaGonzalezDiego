import 'package:flutter/material.dart';
import '../datos/datos_compartidos.dart';
import 'componentes_comunes.dart';

class EntradaTextoPantalla extends StatefulWidget {
  const EntradaTextoPantalla({super.key});

  @override
  State<EntradaTextoPantalla> createState() => _EntradaTextoPantallaState();
}

class _EntradaTextoPantallaState extends State<EntradaTextoPantalla> {
  final _nombreCtrl = TextEditingController();
  final _codigoCtrl = TextEditingController();
  final _contrasenaCtrl = TextEditingController();
  final _edadCtrl = TextEditingController();
  final _correoCtrl = TextEditingController();
  final _telefonoCtrl = TextEditingController();
  final _comentariosCtrl = TextEditingController();
  final _busquedaCtrl = TextEditingController();

  bool _contrasenaVisible = false;
  String? _opcionSeleccionada;

  final List<String> _opciones = [
    'Opción A',
    'Opción B',
    'Opción C',
    'Opción D',
  ];

  @override
  void initState() {
    super.initState();
    _nombreCtrl.addListener(() {
      DatosCompartidos.nombreUsuario.value = _nombreCtrl.text;
    });
  }

  @override
  void dispose() {
    _nombreCtrl.dispose();
    _codigoCtrl.dispose();
    _contrasenaCtrl.dispose();
    _edadCtrl.dispose();
    _correoCtrl.dispose();
    _telefonoCtrl.dispose();
    _comentariosCtrl.dispose();
    _busquedaCtrl.dispose();
    super.dispose();
  }

  bool get _codigoValido => _codigoCtrl.text.length >= 5;
  bool get _mostrarErrorCodigo =>
      _codigoCtrl.text.isNotEmpty && !_codigoValido;

  void _agregarALista() {
    final agregado = DatosCompartidos.agregarElemento(_nombreCtrl.text);
    final mensaje = agregado
        ? 'Elemento agregado a la lista'
        : _nombreCtrl.text.trim().isEmpty
        ? 'Escribe un nombre primero'
        : 'Ese elemento ya existe en la lista';

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(mensaje)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        const EncabezadoSeccion(
          titulo: 'Sección 1: Entrada de texto',
          descripcion:
          'Los campos de texto permiten al usuario capturar información. '
              'Existen variantes según el tipo de dato esperado, el teclado '
              'que deben mostrar y las validaciones que aplican.',
        ),

        BloqueDocumentado(
          titulo: '1. Campo de texto simple',
          descripcion:
          'Permite capturar una línea corta de texto libre. Se usa para '
              'nombres, títulos o cualquier dato breve. La etiqueta flota al '
              'enfocar el campo.',
          child: TextField(
            controller: _nombreCtrl,
            decoration: const InputDecoration(
              labelText: 'Nombre completo',
              border: OutlineInputBorder(),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '2. Campo con validación',
          descripcion:
          'Muestra un mensaje de error debajo del campo cuando el valor '
              'no cumple las reglas. El borde se pinta en rojo para que el '
              'usuario identifique el problema.',
          child: TextField(
            controller: _codigoCtrl,
            onChanged: (_) => setState(() {}),
            decoration: InputDecoration(
              labelText: 'Código (mínimo 5 caracteres)',
              border: const OutlineInputBorder(),
              errorText: _mostrarErrorCodigo
                  ? 'Debe tener al menos 5 caracteres'
                  : null,
              helperText: _codigoValido ? 'Código válido' : null,
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '3. Campo de contraseña',
          descripcion:
          'Oculta los caracteres que el usuario escribe. El botón permite '
              'alternar entre mostrar el texto y mantenerlo oculto.',
          child: TextField(
            controller: _contrasenaCtrl,
            obscureText: !_contrasenaVisible,
            decoration: InputDecoration(
              labelText: 'Contraseña',
              border: const OutlineInputBorder(),
              suffixIcon: TextButton(
                onPressed: () {
                  setState(() => _contrasenaVisible = !_contrasenaVisible);
                },
                child: Text(_contrasenaVisible ? 'Ocultar' : 'Mostrar'),
              ),
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '4. Tipos de teclado',
          descripcion:
          'El teclado cambia según el dato esperado: solo números para '
              'edad, arroba para correo y dígitos con formato telefónico para '
              'números de teléfono.',
          child: Column(
            children: [
              TextField(
                controller: _edadCtrl,
                keyboardType: TextInputType.number,
                inputFormatters: const [],
                decoration: const InputDecoration(
                  labelText: 'Edad (solo números)',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 12),
              TextField(
                controller: _correoCtrl,
                keyboardType: TextInputType.emailAddress,
                decoration: const InputDecoration(
                  labelText: 'Correo electrónico',
                  border: OutlineInputBorder(),
                ),
              ),
              const SizedBox(height: 12),
              TextField(
                controller: _telefonoCtrl,
                keyboardType: TextInputType.phone,
                decoration: const InputDecoration(
                  labelText: 'Número de teléfono',
                  border: OutlineInputBorder(),
                ),
              ),
            ],
          ),
        ),

        BloqueDocumentado(
          titulo: '5. Campo multilínea',
          descripcion:
          'Permite escribir párrafos largos que ocupan varias líneas. El '
              'campo crece en altura automáticamente según el contenido.',
          child: TextField(
            controller: _comentariosCtrl,
            maxLines: 5,
            minLines: 3,
            decoration: const InputDecoration(
              labelText: 'Comentarios',
              border: OutlineInputBorder(),
              alignLabelWithHint: true,
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '6. Campo con opciones desplegables',
          descripcion:
          'Muestra una lista de opciones al pulsarlo. Evita errores de '
              'escritura y restringe la captura a valores predefinidos.',
          child: DropdownButtonFormField<String>(
            value: _opcionSeleccionada,
            decoration: const InputDecoration(
              labelText: 'Selecciona una opción',
              border: OutlineInputBorder(),
            ),
            items: _opciones.map((opcion) {
              return DropdownMenuItem<String>(
                value: opcion,
                child: Text(opcion),
              );
            }).toList(),
            onChanged: (valor) {
              setState(() => _opcionSeleccionada = valor);
            },
          ),
        ),

        BloqueDocumentado(
          titulo: '7. Barra de búsqueda',
          descripcion:
          'Campo especializado con ícono de lupa y botón para limpiar. '
              'Se usa para filtrar listas o encontrar contenido rápidamente.',
          child: TextField(
            controller: _busquedaCtrl,
            onChanged: (_) => setState(() {}),
            decoration: InputDecoration(
              labelText: 'Buscar...',
              border: const OutlineInputBorder(),
              prefixIcon: const Icon(Icons.search),
              suffixIcon: _busquedaCtrl.text.isNotEmpty
                  ? IconButton(
                icon: const Icon(Icons.clear),
                onPressed: () {
                  _busquedaCtrl.clear();
                  setState(() {});
                },
              )
                  : null,
            ),
          ),
        ),

        BloqueDocumentado(
          titulo: '8. Conexión con la Sección 4',
          descripcion:
          'El nombre escrito en el campo 1 se puede agregar a la lista '
              'de la Sección 4. Es un ejemplo de comunicación entre secciones.',
          child: SizedBox(
            width: double.infinity,
            child: ElevatedButton.icon(
              onPressed: _agregarALista,
              icon: const Icon(Icons.add),
              label: Text('Agregar "${_nombreCtrl.text}" a la lista'),
            ),
          ),
        ),
      ],
    );
  }
}