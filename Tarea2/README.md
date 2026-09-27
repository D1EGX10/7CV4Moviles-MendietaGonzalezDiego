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

El APK...


### Versión Flutter

(Pendiente)

## Tabla de equivalencias

| Elemento | Views / XML | Jetpack Compose | Flutter |
|---|---|---|---|
| Campo de texto simple | `TextInputLayout` + `TextInputEditText` | `OutlinedTextField` | _(Pendiente)_ |
| Campo con validación | `TextInputLayout` con `app:errorEnabled` | `OutlinedTextField` con `isError` | _(Pendiente)_ |
| Campo de contraseña | `TextInputEditText` con `inputType=textPassword` | `OutlinedTextField` con `PasswordVisualTransformation` | _(Pendiente)_ |
| Teclado específico | `android:inputType` | `KeyboardOptions(keyboardType = ...)` | _(Pendiente)_ |
| Campo multilínea | `TextInputEditText` con `inputType=textMultiLine` | `OutlinedTextField` con `minLines` | _(Pendiente)_ |
| Desplegable de opciones | `AutoCompleteTextView` / `Spinner` | `ExposedDropdownMenuBox` | _(Pendiente)_ |
| Barra de búsqueda | `SearchView` / `TextInputLayout` | `OutlinedTextField` con `leadingIcon` | _(Pendiente)_ |
| Botón relleno | `MaterialButton` | `Button` | _(Pendiente)_ |
| Botón contorno | `MaterialButton` con estilo Outlined | `OutlinedButton` | _(Pendiente)_ |
| Botón de texto | `MaterialButton` con estilo Text | `TextButton` | _(Pendiente)_ |
| Botón con ícono | `MaterialButton` con `app:icon` | `Button` con `Icon` + `Text` | _(Pendiente)_ |
| Botón flotante | `FloatingActionButton` | `FloatingActionButton` | _(Pendiente)_ |
| Botón flotante extendido | `ExtendedFloatingActionButton` | `ExtendedFloatingActionButton` | _(Pendiente)_ |
| Alternancia / segmentado | `MaterialButtonToggleGroup` | `SingleChoiceSegmentedButtonRow` | _(Pendiente)_ |
| Casilla de verificación | `MaterialCheckBox` | `Checkbox` / `TriStateCheckbox` | _(Pendiente)_ |
| Botón de opción | `MaterialRadioButton` | `RadioButton` | _(Pendiente)_ |
| Interruptor | `MaterialSwitch` | `Switch` | _(Pendiente)_ |
| Deslizador | `Slider` | `Slider` / `RangeSlider` | _(Pendiente)_ |
| Lista desplegable | `Spinner` | `ExposedDropdownMenuBox` | _(Pendiente)_ |
| Selector de fecha | `MaterialDatePicker` | `DatePickerDialog` | _(Pendiente)_ |
| Selector de hora | `MaterialTimePicker` | `TimePicker` | _(Pendiente)_ |
| Chips de filtro | `Chip` / `FilterChip` | `FilterChip` | _(Pendiente)_ |
| Lista vertical | `RecyclerView` | `LazyColumn` | _(Pendiente)_ |
| Cuadrícula | `RecyclerView` con `GridLayoutManager` | `LazyVerticalGrid` | _(Pendiente)_ |
| Encabezados en lista | `RecyclerView` con múltiples tipos de vista | `item { }` dentro de `LazyColumn` | _(Pendiente)_ |
| Deslizar para eliminar | `ItemTouchHelper` | `SwipeToDismissBox` | _(Pendiente)_ |
| Actualizar la lista | `SwipeRefreshLayout` | Botón de actualizar | _(Pendiente)_ |
| Estado vacío | `LinearLayout` centrado | `Box` centrado con `Column` | _(Pendiente)_ |
| Pestañas | `TabLayout` + `ViewPager2` | `TabRow` + `HorizontalPager` | _(Pendiente)_ |
| Progreso lineal | `LinearProgressIndicator` | `LinearProgressIndicator` | _(Pendiente)_ |
| Progreso circular | `CircularProgressIndicator` | `CircularProgressIndicator` | _(Pendiente)_ |
| Toast | `Toast` | `Toast` | _(Pendiente)_ |
| Snackbar | `Snackbar` | `SnackbarHostState` | _(Pendiente)_ |
| Diálogo de confirmación | `MaterialAlertDialogBuilder` | `AlertDialog` | _(Pendiente)_ |
| Hoja inferior | `BottomSheetDialog` | `ModalBottomSheet` | _(Pendiente)_ |
| Tarjeta | `MaterialCardView` | `Card` | _(Pendiente)_ |
| Separador | `View` con altura 1dp | `HorizontalDivider` | _(Pendiente)_ |
| Badge | `BadgeView` | `BadgedBox` | _(Pendiente)_ |
| Distribución en fila | `LinearLayout` horizontal | `Row` | _(Pendiente)_ |
| Distribución en columna | `LinearLayout` vertical | `Column` | _(Pendiente)_ |
| Distribución superpuesta | `FrameLayout` | `Box` con `align` | _(Pendiente)_ |
| Desplazamiento vertical | `ScrollView` / `NestedScrollView` | `Modifier.verticalScroll` | _(Pendiente)_ |
| Barra superior | `MaterialToolbar` | `TopAppBar` | _(Pendiente)_ |
| Menú lateral | `DrawerLayout` + `NavigationView` | `ModalNavigationDrawer` | _(Pendiente)_ |
| Pesos proporcionales | `layout_weight` | `Modifier.weight` | _(Pendiente)_ |


## Capturas de pantalla

### Versión Views y XML

(Pendiente)

### Versión Jetpack Compose

#### Pantalla de inicio

Así se ve la pantalla de inicio:

![Inicio 1](docs/Compose/WhatsApp%20Image%202026-09-27%20at%2014.44.19.jpeg)
![Inicio 2](docs/Compose/WhatsApp%20Image%202026-09-27%20at%2014.44.19(1).jpeg)

#### Sección 1: Entrada de texto

Así se ve la sección 1:

![Sección 1 - imagen 1](docs/Compose/Seccion1/WhatsApp%20Image%202026-09-27%20at%2013.47.44.jpeg)
![Sección 1 - imagen 2](docs/Compose/Seccion1/WhatsApp%20Image%202026-09-27%20at%2013.47.44%281%29.jpeg)
![Sección 1 - imagen 3](docs/Compose/Seccion1/WhatsApp%20Image%202026-09-27%20at%2013.47.44%282%29.jpeg)

#### Sección 2: Botones y acciones

Así se ve la sección 2:

![Sección 2 - imagen 1](docs/Compose/Seccion2/WhatsApp%20Image%202026-09-27%20at%2013.47.45.jpeg)
![Sección 2 - imagen 2](docs/Compose/Seccion2/WhatsApp%20Image%202026-09-27%20at%2013.47.45%281%29.jpeg)
![Sección 2 - imagen 3](docs/Compose/Seccion2/WhatsApp%20Image%202026-09-27%20at%2013.47.45%282%29.jpeg)

#### Sección 3: Elementos de selección

Así se ve la sección 3:

![Sección 3 - imagen 1](docs/Compose/Seccion3/WhatsApp%20Image%202026-09-27%20at%2014.30.30.jpeg)
![Sección 3 - imagen 2](docs/Compose/Seccion3/WhatsApp%20Image%202026-09-27%20at%2014.30.30%281%29.jpeg)
![Sección 3 - imagen 3](docs/Compose/Seccion3/WhatsApp%20Image%202026-09-27%20at%2014.30.30%282%29.jpeg)
![Sección 3 - imagen 4](docs/Compose/Seccion3/WhatsApp%20Image%202026-09-27%20at%2014.30.30%283%29.jpeg)

#### Sección 4: Listas y colecciones

Así se ve la sección 4:

![Sección 4 - imagen 1](docs/Compose/Seccion4/WhatsApp%20Image%202026-09-27%20at%2014.30.46.jpeg)
![Sección 4 - imagen 2](docs/Compose/Seccion4/WhatsApp%20Image%202026-09-27%20at%2014.30.47.jpeg)
![Sección 4 - imagen 3](docs/Compose/Seccion4/WhatsApp%20Image%202026-09-27%20at%2014.30.47%281%29.jpeg)
![Sección 4 - imagen 4](docs/Compose/Seccion4/WhatsApp%20Image%202026-09-27%20at%2014.30.47%282%29.jpeg)
![Sección 4 - imagen 5](docs/Compose/Seccion4/WhatsApp%20Image%202026-09-27%20at%2014.30.47%283%29.jpeg)

#### Sección 5: Información y retroalimentación

Así se ve la sección 5:

![Sección 5 - imagen 1](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.56.jpeg)
![Sección 5 - imagen 2](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57.jpeg)
![Sección 5 - imagen 3](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57%281%29.jpeg)
![Sección 5 - imagen 4](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57%282%29.jpeg)
![Sección 5 - imagen 5](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57%283%29.jpeg)
![Sección 5 - imagen 6](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57%284%29.jpeg)
![Sección 5 - imagen 7](docs/Compose/Seccion5/WhatsApp%20Image%202026-09-27%20at%2014.30.57%285%29.jpeg)

#### Sección 6: Contenedores y estructura

Así se ve la sección 6:

![Sección 6 - imagen 1](docs/Compose/Seccion6/WhatsApp%20Image%202026-09-27%20at%2014.31.06.jpeg)
![Sección 6 - imagen 2](docs/Compose/Seccion6/WhatsApp%20Image%202026-09-27%20at%2014.31.06%281%29.jpeg)
![Sección 6 - imagen 3](docs/Compose/Seccion6/WhatsApp%20Image%202026-09-27%20at%2014.31.06%282%29.jpeg)


### Versión Flutter

(Pendiente)

## Reflexión final

## Referencias consultadas

Android Developers. (2024). *Jetpack Compose*. https://developer.android.com/jetpack/compose

Android Developers. (2024). *Navigation Compose*. https://developer.android.com/jetpack/compose/navigation

Android Developers. (2024). *Permisos en Android*. https://developer.android.com/guide/topics/permissions/overview

Coil. (2024). *Coil Compose*. https://coil-kt.github.io/coil/compose/

Flutter. (2024). *Flutter documentation*. https://docs.flutter.dev/

Google. (2024). *Material Design 3*. https://m3.material.io/

Kotlin. (2024). *Kotlin documentation*. https://kotlinlang.org/docs/home.html