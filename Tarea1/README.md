# TAREA 1.- MCP Y SISTEMA DE ARCHIVOS: INVESTIGACIÓN E IMPLEMENTACIÓN
* **Nombre:** Diego Mendieta González
* **Boleta:** 2024630077
* **Materia:** Desarrollo de Aplicaciones Móviles Nativas
* **Grupo:** 7CV4
##
## Implementación del servidor MCP de sistema de archivos

### Entorno utilizado

- **Sistema operativo:** Windows.
- **Cliente MCP:** Visual Studio Code con GitHub Copilot Chat en modo Agent.
- **Servidor:** `@modelcontextprotocol/server-filesystem`.
- **Transporte:** stdio.
- **Runtime:** Node.js y npm. Las versiones exactas se documentan a partir de las salidas de `node --version` y `npm --version`.

### Directorio de trabajo

Para realizar las pruebas se utilizó el directorio `archivos-prueba`, ubicado dentro de la carpeta autorizada del proyecto `Tarea1`. Este directorio contiene los archivos de prueba y permite mantener las operaciones separadas de los documentos de investigación.

### Operaciones verificadas

1. Listado de directorios.
2. Lectura de un archivo existente.
3. Creación de un archivo nuevo.
4. Modificación de un archivo existente.
5. Búsqueda de un archivo por nombre.

Las operaciones se realizaron mediante herramientas MCP invocadas desde GitHub Copilot Chat. Las capturas correspondientes se encuentran en la carpeta `img/`.

### Prueba del límite de seguridad

Se intentó acceder mediante MCP a un archivo situado fuera de los directorios autorizados. Se documentará la respuesta real del servidor y se explicará si el acceso fue rechazado por la validación de rutas.

### Conclusión de la implementación

La práctica permitió comprobar cómo un cliente compatible con MCP puede descubrir e invocar herramientas de un servidor local para trabajar con archivos mediante instrucciones en lenguaje natural. El servidor delimita los directorios accesibles; por ello, la configuración de las rutas autorizadas forma parte esencial de la seguridad de la implementación.
