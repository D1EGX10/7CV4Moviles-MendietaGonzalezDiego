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
├── apk/                (por el peso de algunos binarios (más de 100 MB), no fue posible incluirlos todos dentro del repositorio. Los APK grandes están disponibles en la sección **Releases** de este repositorio.)
├── docs/              Capturas de pantalla organizadas por tecnología y sección
├── Vcompose/   Versión con Jetpack Compose
├── Vflutter/           Versión con Flutter
├── Vxml/           Versión con Xml
└── README.md          Documento principal
```

## Binarios APK

Los APK de cada versión están disponibles en la sección de Releases de este repositorio:

- [APK de Views y XML](https://github.com/D1EGX10/7CV4Moviles-MendietaGonzalezDiego/releases/tag/Tarea2Apk)
- [APK de Flutter](https://github.com/D1EGX10/7CV4Moviles-MendietaGonzalezDiego/releases/tag/Tarea2Apk)
- [APK de Jetpack Compose](https://github.com/D1EGX10/7CV4Moviles-MendietaGonzalezDiego/releases/tag/Tarea2Apk)

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

Abrir la carpeta `Vxml/` desde Android Studio y ejecutar la configuración del módulo `app` sobre un dispositivo o emulador con Android 7.0 (API 24) o superior.

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

#### Sección 1: Entrada de texto

Así se ve la sección 1:

![Sección 1 - imagen 1](docs/Xml/Seccion1/imagen_1.jpeg)
![Sección 1 - imagen 2](docs/Xml/Seccion1/imagen_2.jpeg)

#### Sección 2: Botones y acciones

Así se ve la sección 2:

![Sección 2 - imagen 1](docs/Xml/Seccion2/imagen_1.jpeg)
![Sección 2 - imagen 2](docs/Xml/Seccion2/imagen_2.jpeg)

#### Sección 3: Elementos de selección

Así se ve la sección 3:

![Sección 3 - imagen 1](docs/Xml/Seccion3/imagen_1.jpeg)
![Sección 3 - imagen 2](docs/Xml/Seccion3/imagen_2.jpeg)

#### Sección 4: Listas y colecciones

Así se ve la sección 4:

![Sección 4 - imagen 1](docs/Xml/Seccion4/imagen_1.jpeg)
![Sección 4 - imagen 2](docs/Xml/Seccion4/imagen_2.jpeg)

#### Sección 5: Información y retroalimentación

Así se ve la sección 5:

![Sección 5 - imagen 1](docs/Xml/Seccion5/imagen_1.jpeg)
![Sección 5 - imagen 2](docs/Xml/Seccion5/imagen_2.jpeg)
![Sección 5 - imagen 3](docs/Xml/Seccion5/imagen_3.jpeg)
![Sección 5 - imagen 4](docs/Xml/Seccion5/imagen_4.jpeg)

#### Sección 6: Contenedores y estructura

Así se ve la sección 6:

![Sección 6 - imagen 1](docs/Xml/Seccion6/imagen_1.jpeg)
![Sección 6 - imagen 2](docs/Xml/Seccion6/imagen_2.jpeg)
![Sección 6 - imagen 3](docs/Xml/Seccion6/imagen_3.jpeg)

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

![Inicio 1](docs/Flutter/imagen_1.jpeg)
![Inicio 2](docs/Flutter/imagen_2.jpeg)

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

### Sobre Views y XML

Construir la interfaz con Views y XML fue el enfoque más laborioso de los tres. Cada pantalla requirió su propio archivo de layout en XML, más una clase Fragment o Activity en Kotlin, y en varios casos adaptadores separados para las listas. El código quedó repartido entre muchos archivos pequeños, lo que dificultó tener una visión completa de una pantalla a simple vista.

La parte más complicada fue la sincronización entre las vistas y los datos. Cada vez que cambiaba un valor compartido había que notificar manualmente al adaptador con `notifyDataSetChanged`, o al `TextView` con `setText`. A diferencia de Compose y Flutter, donde el estado se propaga solo, aquí hay que ser explícito en cada actualización.

El manejo de la lista compartida requirió usar `LiveData` y observar los cambios desde el Fragment con `viewLifecycleOwner`. Una vez montado el patrón, funcionó bien, pero tomó más código que en las otras tecnologías. También hubo que tener cuidado con el ciclo de vida: si el Fragment se destruye mientras un observer está activo, puede causar fugas de memoria.

Otra dificultad fue la navegación. Hubo un problema con los ids del `nav_graph` que no coincidían con los del menú inferior, y la app se quedaba atorada en la primera sección. Se resolvió estandarizando todos los ids y forzando una reinstalación limpia con `Clean Project` y `Rebuild Project`.

El tema claro y oscuro se manejó de forma nativa con `values/themes.xml` y `values-night/themes.xml`. Fue el sistema más sencillo de los tres porque Android lo maneja automáticamente si se usan colores de tema en lugar de colores fijos.

En cuanto a legibilidad, el código XML es muy claro para describir la estructura de una pantalla. Sin embargo, la lógica en Kotlin queda dispersa entre `findViewById`, listeners y adaptadores. Es un enfoque más verboso que Compose y Flutter.

Preferiría trabajar con Views solo si heredara un proyecto existente o si tuviera que mantener una aplicación antigua. Para proyectos nuevos elegiría Compose o Flutter.

### Sobre Jetpack Compose

Construir la interfaz con Jetpack Compose resultó muy distinto a trabajar con layouts XML. El código quedó concentrado en archivos pequeños, cada pantalla en una sola función composable que describe su estructura de arriba hacia abajo. No hubo que crear adaptadores ni archivos de layout separados.

La parte más cómoda fue el manejo de estado. Con `mutableStateOf` y `mutableStateListOf`, cualquier cambio en los datos compartidos se reflejó automáticamente en las pantallas que los leían, sin necesidad de notificar adaptadores ni recargar vistas. Esto simplificó enormemente las conexiones entre secciones: las seis quedaron enlazadas sin escribir una sola línea de comunicación manual.

La dificultad principal fue la gestión de dependencias. Hubo varios conflictos entre versiones del BOM de Compose, la librería de navegación, los íconos y Coil. Cada incompatibilidad generaba errores en cascada que no señalaban la causa real. Se resolvió fijando versiones estables y usando el BOM para que este controlara las versiones internas.

Otra dificultad fue el manejo de permisos. El permiso `INTERNET` en el manifiesto es indispensable para cargar imágenes desde URL, y sin él la aplicación se cierra en lugar de mostrar un error visible, lo que dificulta el diagnóstico.

En cuanto a legibilidad, Compose genera código muy claro. Una función por pantalla, componentes reutilizables como `BloqueDocumentado` y `EncabezadoSeccion`, y cero código repetido en los layouts. Es, sin duda, el código más limpio de las tres versiones.

Sobre la velocidad de construcción, una vez superada la configuración inicial de dependencias, el desarrollo fue muy rápido. Cada elemento nuevo se agregaba con una sola función y sin crear archivos adicionales. En ese sentido resultó más ágil que Views.

Preferiría trabajar con Jetpack Compose en proyectos futuros, porque el modelo declarativo encaja mejor con la forma en que pienso las interfaces y porque el código es más fácil de mantener y de leer.

### Sobre Flutter

Flutter fue la tecnología más rápida de configurar. El comando `flutter create` generó todo el proyecto listo para correr, sin tener que pelear con versiones de Gradle ni SDKs. La curva de aprendizaje fue suave porque el modelo de widgets es muy similar al de Compose: todo es un widget, todo se compone en árboles, y el estado se maneja con `setState` o con `ValueNotifier`.

La parte más cómoda fue que el mismo código funciona en Android e iOS sin cambios. También fue muy útil el hot reload: al guardar un archivo, la app en el celular se actualizaba en menos de un segundo sin reiniciar. Eso aceleró mucho la iteración, sobre todo en las secciones más visuales como la de listas y la de contenedores.

La dificultad principal fue el manejo de estado compartido entre secciones. Compose tiene `mutableStateListOf` que recompone solo, pero en Flutter tuve que usar `ValueNotifier` y `ValueListenableBuilder` para lograr el mismo efecto. Una vez entendido el patrón, funcionó bien, pero requirió más código repetido que en Compose.

Otra dificultad fue la curva inicial del lenguaje Dart. Aunque es similar a Kotlin, tiene detalles propios como los constructores `const`, el uso de `late`, las listas por comprensión y la forma en que se manejan los null safety. Una vez que uno se acostumbra, se vuelve natural.

En cuanto a legibilidad, el código de Flutter es muy claro pero más verboso que Compose. Cada widget requiere un `child:` o `children:` explícito, y el anidamiento puede volverse profundo si no se separan bien los componentes. En este proyecto se manejó bien gracias a los reutilizables `BloqueDocumentado` y `EncabezadoSeccion`.

Sobre la velocidad de construcción, una vez montado el proyecto fue muy ágil gracias al hot reload. El único punto lento fue la primera compilación para Android, que tardó varios minutos porque tuvo que descargar Gradle y todas las dependencias.

Preferiría trabajar con Flutter si necesito una sola base de código para Android e iOS a la vez, porque el ahorro de esfuerzo es enorme. Para proyectos exclusivamente Android preferiría Compose, pero Flutter es una alternativa muy sólida.

### Comparación general

Las tres tecnologías permiten construir el mismo catálogo, pero cada una tiene un enfoque distinto:

**Velocidad de configuración inicial.** Flutter fue la más rápida: un solo comando y el proyecto está listo. Compose requiere configurar Gradle, dependencias y versiones del BOM. Views fue la más lenta porque exige crear layouts XML, adaptadores y clases de datos por separado.

**Velocidad de desarrollo.** Compose y Flutter fueron las más ágiles una vez configuradas. Compose gana ligeramente porque cada pantalla vive en una sola función y el estado se propaga solo. Flutter compensa con el hot reload, que hace la iteración casi instantánea. Views es el más lento de los tres porque cada cambio visual implica tocar dos archivos.

**Legibilidad del código.** Compose genera el código más limpio. Flutter es claro pero más verboso. Views es el más disperso porque obliga a saltar entre XML y Kotlin.

**Manejo de estado compartido.** Compose lo resuelve de forma nativa con `mutableStateOf` y `mutableStateListOf`. Flutter requiere `ValueNotifier` y `ValueListenableBuilder`. Views necesita `LiveData`, `ViewModel` u `Observer` con más código repetido.

**Tema claro y oscuro.** Views lo maneja automáticamente con `values-night`. Compose también con `isSystemInDarkTheme()`. Flutter lo hace con `ThemeMode.system`. Las tres cumplen sin mayor problema.

**Preferencia personal.** Para proyectos exclusivamente Android, Compose es la mejor opción por su modelo declarativo y su integración nativa. Para proyectos que necesiten cubrir Android e iOS, Flutter es la elección más eficiente. Views quedaría como última opción, salvo que se herede un proyecto antiguo que ya lo use.

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