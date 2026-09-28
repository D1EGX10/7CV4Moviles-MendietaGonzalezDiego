# Catálogo de elementos de interfaz de usuario

Aplicación móvil que muestra los componentes básicos de una interfaz de usuario organizados en seis secciones. El mismo catálogo se implementa en tres tecnologías distintas para comparar sus enfoques de construcción, identificar las equivalencias entre plataformas y reconocer las diferencias entre cada modelo de desarrollo.

## Datos de identificación

- **Nombre completo:** Diego Mendieta González
- **Número de boleta:** 2024630077
- **Grupo:** 7CV4
- **Materia:** Desarrollo de aplicaciones móviles
- **Profesor:** Gabriel Hurtado Avilés
- **Fecha de entrega:** 25 de septiembre de 2026

## Tecnologías utilizadas

1. **Android nativo con Views y XML** — Kotlin con layouts XML y Fragments.
2. **Android nativo con Jetpack Compose** — Kotlin con funciones composable.
3. **Flutter** — Dart, ejecutándose al menos en Android.

## Estructura del repositorio

```
├── .idea/     
├── apk/               Binarios APK de cada versión
├── docs/              Capturas de pantalla organizadas por tecnología y sección
├── Vcompose/   Versión con Jetpack Compose
├── Vflutter/           Versión con Flutter
├── Vxml/           Versión con Xml
└── README.md          Documento principal
```

## Secciones del catálogo

Cada versión incluye las siguientes seis secciones, con navegación entre ellas y documentación en pantalla de cada elemento:

1. **Entrada de texto** — campos simples con etiqueta, validación con mensaje de error, contraseña con mostrar u ocultar, teclados específicos, campo multilínea, lista desplegable y barra de búsqueda.
2. **Botones y acciones** — botones rellenos, de contorno y de texto, botones con ícono, botón flotante normal y extendido, selector segmentado, botón deshabilitado y botón en estado de carga.
3. **Elementos de selección** — casillas de verificación con estado indeterminado, botones de opción mutuamente excluyentes, interruptor, deslizador de valor único y de rango, lista desplegable, selectores de fecha y hora, y chips de filtro.
4. **Listas y colecciones** — lista vertical con más de quince elementos, cuadrícula, encabezados de sección, detalle de un elemento, deslizar para eliminar, actualización de la lista, estado vacío y pestañas con contenido deslizable.
5. **Información y retroalimentación** — textos con distintos estilos, imágenes locales y desde URL con diferentes modos de escalado, indicadores de progreso lineal y circular determinados e indeterminados, Toast, Snackbar con acción, diálogo de confirmación, hoja inferior, tarjeta, separador y badge.
6. **Contenedores y estructura** — distribución en fila, columna y superpuesta, contenedor con desplazamiento vertical, barra superior con acciones, menú lateral de navegación y distribución con pesos proporcionales.

## Conexiones entre secciones

Cada versión implementa varias conexiones que vinculan las secciones entre sí, demostrando que la aplicación es un sistema integrado y no pantallas aisladas

## Instrucciones de compilación y ejecución

### Versión Views y XML

(Pendiente)

### Versión Jetpack Compose

Abrir la carpeta `Vcompose/` desde Android Studio y ejecutar la configuración del módulo `app` sobre un dispositivo o emulador con Android 7.0 (API 24) o superior.


### Versión Flutter

Abrir la carpeta `Vflutter/` desde Android Studio o Visual Studio Code. Requiere tener instalado Flutter SDK 3.x y Dart 3.x.

Para ejecutar en un dispositivo o emulador Android:

```bash
cd Vflutter
flutter pub get
flutter run
```

## Tabla de equivalencias

| Elemento | Views / XML | Jetpack Compose | Flutter |
|---|---|---|---|
| Campo de texto simple | `TextInputLayout` + `TextInputEditText` | `OutlinedTextField` | `TextField` |
| Campo con validación | `TextInputLayout` con `app:errorEnabled` | `OutlinedTextField` con `isError` | `TextField` con `errorText` |
| Campo de contraseña | `TextInputEditText` con `inputType=textPassword` | `OutlinedTextField` con `PasswordVisualTransformation` | `TextField` con `obscureText` |
| Teclado específico | `android:inputType` | `KeyboardOptions(keyboardType = ...)` | `keyboardType: TextInputType` |
| Campo multilínea | `TextInputEditText` con `inputType=textMultiLine` | `OutlinedTextField` con `minLines` | `TextField` con `maxLines` |
| Desplegable de opciones | `AutoCompleteTextView` / `Spinner` | `ExposedDropdownMenuBox` | `DropdownButtonFormField` |
| Barra de búsqueda | `SearchView` / `TextInputLayout` | `OutlinedTextField` con `leadingIcon` | `TextField` con `prefixIcon` |
| Botón relleno | `MaterialButton` | `Button` | `ElevatedButton` |
| Botón contorno | `MaterialButton` con estilo Outlined | `OutlinedButton` | `OutlinedButton` |
| Botón de texto | `MaterialButton` con estilo Text | `TextButton` | `TextButton` |
| Botón con ícono | `MaterialButton` con `app:icon` | `Button` con `Icon` + `Text` | `ElevatedButton.icon` |
| Botón flotante | `FloatingActionButton` | `FloatingActionButton` | `FloatingActionButton` |
| Botón flotante extendido | `ExtendedFloatingActionButton` | `ExtendedFloatingActionButton` | `FloatingActionButton.extended` |
| Alternancia / segmentado | `MaterialButtonToggleGroup` | `SingleChoiceSegmentedButtonRow` | `SegmentedButton` |
| Casilla de verificación | `MaterialCheckBox` | `Checkbox` / `TriStateCheckbox` | `Checkbox` / `CheckboxListTile` con `tristate` |
| Botón de opción | `MaterialRadioButton` | `RadioButton` | `RadioListTile` |
| Interruptor | `MaterialSwitch` | `Switch` | `Switch` / `SwitchListTile` |
| Deslizador | `Slider` | `Slider` / `RangeSlider` | `Slider` / `RangeSlider` |
| Lista desplegable | `Spinner` | `ExposedDropdownMenuBox` | `DropdownButtonFormField` |
| Selector de fecha | `MaterialDatePicker` | `DatePickerDialog` | `showDatePicker` |
| Selector de hora | `MaterialTimePicker` | `TimePicker` | `showTimePicker` |
| Chips de filtro | `Chip` / `FilterChip` | `FilterChip` | `FilterChip` |
| Lista vertical | `RecyclerView` | `LazyColumn` | `ListView.builder` |
| Cuadrícula | `RecyclerView` con `GridLayoutManager` | `LazyVerticalGrid` | `GridView.builder` |
| Encabezados en lista | `RecyclerView` con múltiples tipos de vista | `item { }` dentro de `LazyColumn` | `ListView` con hijos mixtos |
| Deslizar para eliminar | `ItemTouchHelper` | `SwipeToDismissBox` | `Dismissible` |
| Actualizar la lista | `SwipeRefreshLayout` | Botón de actualizar | `RefreshIndicator` |
| Estado vacío | `LinearLayout` centrado | `Box` centrado con `Column` | `Center` con `Column` |
| Pestañas | `TabLayout` + `ViewPager2` | `TabRow` + `HorizontalPager` | `TabBar` + `TabBarView` |
| Progreso lineal | `LinearProgressIndicator` | `LinearProgressIndicator` | `LinearProgressIndicator` |
| Progreso circular | `CircularProgressIndicator` | `CircularProgressIndicator` | `CircularProgressIndicator` |
| Toast | `Toast` | `Toast` | `SnackBar` |
| Snackbar | `Snackbar` | `SnackbarHostState` | `SnackBar` con `SnackBarAction` |
| Diálogo de confirmación | `MaterialAlertDialogBuilder` | `AlertDialog` | `showDialog` + `AlertDialog` |
| Hoja inferior | `BottomSheetDialog` | `ModalBottomSheet` | `showModalBottomSheet` |
| Tarjeta | `MaterialCardView` | `Card` | `Card` |
| Separador | `View` con altura 1dp | `HorizontalDivider` | `Divider` |
| Badge | `BadgeView` | `BadgedBox` | `Badge` |
| Distribución en fila | `LinearLayout` horizontal | `Row` | `Row` |
| Distribución en columna | `LinearLayout` vertical | `Column` | `Column` |
| Distribución superpuesta | `FrameLayout` | `Box` con `align` | `Stack` |
| Desplazamiento vertical | `ScrollView` / `NestedScrollView` | `Modifier.verticalScroll` | `ListView` / `SingleChildScrollView` |
| Barra superior | `MaterialToolbar` | `TopAppBar` | `AppBar` |
| Menú lateral | `DrawerLayout` + `NavigationView` | `ModalNavigationDrawer` | `Drawer` |
| Pesos proporcionales | `layout_weight` | `Modifier.weight` | `Expanded` con `flex` |

## Capturas de pantalla

### Versión Views y XML

(Pendiente)

### Versión Jetpack Compose

#### Pantalla de inicio

Así se ve la pantalla de inicio:

![Inicio 1](docs/Compose/inicio_1.jpeg)
![Inicio 2](docs/Compose/inicio_2.jpeg)

#### Sección 1: Entrada de texto

Así se ve la sección 1:

![Sección 1 - imagen 1](docs/Compose/Seccion1/imagen_1.jpeg)
![Sección 1 - imagen 2](docs/Compose/Seccion1/imagen_2.jpeg)
![Sección 1 - imagen 3](docs/Compose/Seccion1/imagen_3.jpeg)

#### Sección 2: Botones y acciones

Así se ve la sección 2:

![Sección 2 - imagen 1](docs/Compose/Seccion2/imagen_1.jpeg)
![Sección 2 - imagen 2](docs/Compose/Seccion2/imagen_2.jpeg)
![Sección 2 - imagen 3](docs/Compose/Seccion2/imagen_3.jpeg)

#### Sección 3: Elementos de selección

Así se ve la sección 3:

![Sección 3 - imagen 1](docs/Compose/Seccion3/imagen_1.jpeg)
![Sección 3 - imagen 2](docs/Compose/Seccion3/imagen_2.jpeg)
![Sección 3 - imagen 3](docs/Compose/Seccion3/imagen_3.jpeg)
![Sección 3 - imagen 4](docs/Compose/Seccion3/imagen_4.jpeg)

#### Sección 4: Listas y colecciones

Así se ve la sección 4:

![Sección 4 - imagen 1](docs/Compose/Seccion4/imagen_1.jpeg)
![Sección 4 - imagen 2](docs/Compose/Seccion4/imagen_2.jpeg)
![Sección 4 - imagen 3](docs/Compose/Seccion4/imagen_3.jpeg)
![Sección 4 - imagen 4](docs/Compose/Seccion4/imagen_4.jpeg)
![Sección 4 - imagen 5](docs/Compose/Seccion4/imagen_5.jpeg)

#### Sección 5: Información y retroalimentación

Así se ve la sección 5:

![Sección 5 - imagen 1](docs/Compose/Seccion5/imagen_1.jpeg)
![Sección 5 - imagen 2](docs/Compose/Seccion5/imagen_2.jpeg)
![Sección 5 - imagen 3](docs/Compose/Seccion5/imagen_3.jpeg)
![Sección 5 - imagen 4](docs/Compose/Seccion5/imagen_4.jpeg)
![Sección 5 - imagen 5](docs/Compose/Seccion5/imagen_5.jpeg)
![Sección 5 - imagen 6](docs/Compose/Seccion5/imagen_6.jpeg)
![Sección 5 - imagen 7](docs/Compose/Seccion5/imagen_7.jpeg)

#### Sección 6: Contenedores y estructura

Así se ve la sección 6:

![Sección 6 - imagen 1](docs/Compose/Seccion6/imagen_1.jpeg)
![Sección 6 - imagen 2](docs/Compose/Seccion6/imagen_2.jpeg)
![Sección 6 - imagen 3](docs/Compose/Seccion6/imagen_3.jpeg)


### Versión Flutter

#### Pantalla de inicio

Así se ve la pantalla de inicio:

![Inicio 1](docs/Flutter/inicio_1.jpeg)
![Inicio 2](docs/Flutter/inicio_2.jpeg)

#### Sección 1: Entrada de texto

Así se ve la sección 1:

![Sección 1 - imagen 1](docs/Flutter/Seccion1/imagen_1.jpeg)
![Sección 1 - imagen 2](docs/Flutter/Seccion1/imagen_2.jpeg)
![Sección 1 - imagen 3](docs/Flutter/Seccion1/imagen_3.jpeg)

#### Sección 2: Botones y acciones

Así se ve la sección 2:

![Sección 2 - imagen 1](docs/Flutter/Seccion2/imagen_1.jpeg)
![Sección 2 - imagen 2](docs/Flutter/Seccion2/imagen_2.jpeg)
![Sección 2 - imagen 3](docs/Flutter/Seccion2/imagen_3.jpeg)

#### Sección 3: Elementos de selección

Así se ve la sección 3:

![Sección 3 - imagen 1](docs/Flutter/Seccion3/imagen_1.jpeg)
![Sección 3 - imagen 2](docs/Flutter/Seccion3/imagen_2.jpeg)
![Sección 3 - imagen 3](docs/Flutter/Seccion3/imagen_3.jpeg)
![Sección 3 - imagen 4](docs/Flutter/Seccion3/imagen_4.jpeg)

#### Sección 4: Listas y colecciones

Así se ve la sección 4:

![Sección 4 - imagen 1](docs/Flutter/Seccion4/imagen_1.jpeg)
![Sección 4 - imagen 2](docs/Flutter/Seccion4/imagen_2.jpeg)
![Sección 4 - imagen 3](docs/Flutter/Seccion4/imagen_3.jpeg)

#### Sección 5: Información y retroalimentación

Así se ve la sección 5:

![Sección 5 - imagen 1](docs/Flutter/Seccion5/imagen_1.jpeg)
![Sección 5 - imagen 2](docs/Flutter/Seccion5/imagen_2.jpeg)
![Sección 5 - imagen 3](docs/Flutter/Seccion5/imagen_3.jpeg)
![Sección 5 - imagen 4](docs/Flutter/Seccion5/imagen_4.jpeg)

#### Sección 6: Contenedores y estructura

Así se ve la sección 6:

![Sección 6 - imagen 1](docs/Flutter/Seccion6/imagen_1.jpeg)
![Sección 6 - imagen 2](docs/Flutter/Seccion6/imagen_2.jpeg)
![Sección 6 - imagen 3](docs/Flutter/Seccion6/imagen_3.jpeg)

## Reflexión final

## Referencias consultadas

Android Developers. (2024). *Jetpack Compose*. https://developer.android.com/jetpack/compose

Android Developers. (2024). *Navigation Compose*. https://developer.android.com/jetpack/compose/navigation

Android Developers. (2024). *Permisos en Android*. https://developer.android.com/guide/topics/permissions/overview

Coil. (2024). *Coil Compose*. https://coil-kt.github.io/coil/compose/

Flutter. (2024). *Flutter documentation*. https://docs.flutter.dev/

Flutter. (2024). *Layouts in Flutter*. https://docs.flutter.dev/ui/layout

Flutter. (2024). *State management*. https://docs.flutter.dev/data-and-backend/state-mgmt/intro

Google. (2024). *Material Design 3*. https://m3.material.io/

Kotlin. (2024). *Kotlin documentation*. https://kotlinlang.org/docs/home.html

pub.dev. (2024). *cached_network_image*. https://pub.dev/packages/cached_network_image