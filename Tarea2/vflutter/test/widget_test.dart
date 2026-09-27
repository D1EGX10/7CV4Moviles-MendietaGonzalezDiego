import 'package:flutter_test/flutter_test.dart';
import 'package:vflutter/main.dart';

void main() {
  testWidgets('La aplicación arranca correctamente', (WidgetTester tester) async {
    await tester.pumpWidget(const AplicacionCatalogo());
    expect(find.text('Catálogo de elementos de interfaz'), findsOneWidget);
  });
}