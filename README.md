# Práctica 3: Aplicaciones Nativas - Ejercicio 1

## 1.1 Identificación y Comparativa de Equipos

Para cumplir con los requisitos de la práctica, se analizaron las especificaciones de las computadoras de los integrantes del equipo para determinar cuál cuenta con el mejor rendimiento (procesador, memoria RAM y capacidad de virtualización) para albergar el entorno de desarrollo macOS mediante Docker.

### Tabla Comparativa de Especificaciones

| Integrante | Modelo de PC / Procesador | Memoria RAM | Almacenamiento | Gráficos (GPU) | Justificación |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Sofía Ortega García** | Lenovo ThinkPad T480 | 8.00 GB (7.84 GB utilizable) | 198 GB de 477 GB usado | Intel UHD Graphics| **NO Seleccionada** por No conta con suficiente memoria RAM ni la mejor memoria. |
| *Integrante 2* | [Modelo] | [RAM] | [Almacenamiento] | [GPU] | - |


### Datos del Responsable del Entorno
* **Nombre completo:** Sofía Ortega García
* **Número de boleta:** 2024630517
* **Grupo:** 7CV4

### Evidencia de Hardware Seleccionado
![Especificaciones de Sofía](./evidencias/evidencia1.png)


---

## 1.2 Bitácora de Sesiones de Trabajo

A pesar de que el entorno se instala en una sola computadora, todo el equipo participó de forma colaborativa en el análisis, configuración y pruebas

* **Fecha de sesión:** 25/09/26
* **Horario:**  16:00 - 19:00 
* **Modalidad:** Presencial 
* **Integrantes presentes:**Mendieta Gonzalez Diego y Ortega Garcia Sofia 
* **Actividades realizadas:** Comparativa de equipos, clonación del repositorio de Docker y pruebas iniciales de asignación de recursos (CPU/RAM).

---

## 1.3 Instalación del Entorno macOS con Docker

1. Se clonó el repositorio oficial de la práctica: `github.com/gabrielhuav/MacOS-Docker`.
2. Se validaron los requisitos del sistema 
3. Se configuraron los recursos óptimos en el contenedor (núcleos de CPU asignados y memoria RAM dedicada).
4. Se verificó el arranque exitoso de macOS y la conectividad a internet dentro del contenedor.

---

## 1.4 Configuración del Entorno de Desarrollo iOS

1. Instalación de **Xcode** desde la Mac App Store virtualizada.
2. Configuración de los simuladores de iPhone y iPad.
3. Instalación de herramientas complementarias (Homebrew, CocoaPods, Swift Package Manager).
4. Creación y ejecución exitosa de un proyecto de prueba en Swift/SwiftUI en el simulador de iOS.