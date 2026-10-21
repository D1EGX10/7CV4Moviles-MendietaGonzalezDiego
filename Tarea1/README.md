# TAREA 1. MCP Y SISTEMA DE ARCHIVOS: INVESTIGACIÓN E IMPLEMENTACIÓN

**Nombre:** Diego Mendieta González  
**Boleta:** 2024630077  
**Materia:** Desarrollo de Aplicaciones Móviles Nativas  
**Grupo:** 7CV4  
**Profesor:** Gabriel Hurtado Avilés  

---

## 1. Introducción

El Model Context Protocol (MCP) es un protocolo que permite conectar aplicaciones de inteligencia artificial con herramientas y fuentes de información externas mediante una interfaz estandarizada. En esta práctica se investigan sus características, arquitectura, mecanismos de seguridad y diferencias con el desarrollo de API tradicionales.

Como parte de la implementación, se configuró un servidor MCP para interactuar con el sistema de archivos local mediante instrucciones en lenguaje natural desde GitHub Copilot Chat en Visual Studio Code.

## 2. Objetivos

### Objetivo general

Investigar el funcionamiento del Model Context Protocol e implementar un servidor MCP que permita realizar operaciones controladas sobre archivos y directorios locales.

### Objetivos específicos

- Investigar la evolución y los fundamentos de MCP.
- Comprender el aislamiento, los permisos y los límites de acceso a los recursos.
- Comparar MCP con las API REST tradicionales.
- Identificar los componentes y el flujo de comunicación de la arquitectura MCP.
- Configurar un servidor MCP de sistema de archivos.
- Verificar las operaciones de listado, lectura, creación, modificación y búsqueda de archivos.
- Comprobar que el servidor restringe el acceso a directorios no autorizados.

## 3. Investigación

Los documentos de investigación se encuentran en la carpeta `docs/`:

| Archivo | Contenido |
|---|---|
| [`docs/1evolucion.md`](docs/1evolucion.md) | Evolución y antecedentes de MCP. |
| [`docs/2aislamiento.md`](docs/2aislamiento.md) | Aislamiento, permisos y control de acceso. |
| [`docs/3mcpyapi.md`](docs/3mcpyapi.md) | Comparación entre MCP y API. |
| [`docs/4arquitecturamcp.md`](docs/4arquitecturamcp.md) | Arquitectura y comunicación de MCP. |
| [`docs/5servidor.md`](docs/5servidor.md) | Funcionamiento y características de los servidores MCP. |
| [`docs/6seguridad.md`](docs/6seguridad.md) | Seguridad y buenas prácticas. |
| [`docs/7casos.md`](docs/7casos.md) | Casos de uso y aplicaciones. |

### 3.1 MCP y las API tradicionales

MCP estandariza la manera en que una aplicación cliente descubre y utiliza herramientas o recursos proporcionados por un servidor. Una API REST, en cambio, define endpoints HTTP que los clientes consumen siguiendo los contratos establecidos por cada servicio.

MCP no sustituye necesariamente a las API: puede complementarlas al ofrecer una interfaz uniforme para que un cliente compatible con modelos de inteligencia artificial interactúe con herramientas existentes.

## 4. Implementación del servidor MCP

### 4.1 Entorno utilizado

| Componente | Tecnología o versión |
|---|---|
| Sistema operativo | Windows |
| Editor | Visual Studio Code 1.141.0, arquitectura x64 |
| Cliente MCP | GitHub Copilot Chat, modo Agent |
| Runtime | Node.js v24.19.0 |
| Gestor de paquetes | npm 11.17.0 |
| Servidor | `@modelcontextprotocol/server-filesystem` |
| Transporte | stdio |

### 4.2 Estructura del proyecto

```text
Tarea1/
├── .vscode/
│   └── mcp.json
├── archivos-prueba/
│   └── ejemplo.txt
├── config/
├── docs/
│   ├── 1evolucion.md
│   ├── 2aislamiento.md
│   ├── 3mcpyapi.md
│   ├── 4arquitecturamcp.md
│   ├── 5servidor.md
│   ├── 6seguridad.md
│   └── 7casos.md
├── img/
│   ├── img01-servidor-conectado.png
│   ├── img02-listar-directorio.png
│   ├── img03-leer-archivo.png
│   ├── img04-crear-archivo.png
│   ├── img05-modificar-archivo.png
│   ├── img06-buscar-archivo.png
│   └── img07-limite-seguridad.png
└── README.md
```

### 4.3 Configuración

Se utilizó el servidor `@modelcontextprotocol/server-filesystem`, ejecutado mediante `npx` y comunicado con el cliente a través de `stdio`.

El archivo `.vscode/mcp.json` contiene la configuración del servidor MCP del proyecto. El directorio de trabajo para las pruebas es `archivos-prueba/`, situado dentro del proyecto.

Para reproducir la práctica en otra instalación, es necesario contar con Node.js y npm, abrir la carpeta `Tarea1` en Visual Studio Code y comprobar que la configuración de MCP apunta al directorio de pruebas correcto. Las rutas autorizadas deben limitarse a los archivos necesarios para la práctica.

### 4.4 Directorio de pruebas

El archivo inicial `archivos-prueba/ejemplo.txt` contiene el siguiente texto:

```text
Archivo inicial para verificar las herramientas MCP.
```

Este directorio se utiliza para probar las operaciones del servidor sin modificar los documentos de investigación.

## 5. Pruebas realizadas

Se verificaron las operaciones mediante las herramientas MCP disponibles en GitHub Copilot Chat. Las evidencias se encuentran en `img/`.

| Prueba | Evidencia | Resultado |
|---|---|---|
| Conexión del servidor | [img01-servidor-conectado.png](img/img01-servidor-conectado.png) | Se comprobó la conexión del cliente con el servidor MCP. |
| Listado de directorio | [img02-listar-directorio.png](img/img02-listar-directorio.png) | Se verificó el listado de los archivos del directorio autorizado. |
| Lectura de archivo | [img03-leer-archivo.png](img/img03-leer-archivo.png) | Se comprobó la lectura de un archivo existente. |
| Creación de archivo | [img04-crear-archivo.png](img/img04-crear-archivo.png) | Se verificó la creación de un archivo nuevo. |
| Modificación de archivo | [img05-modificar-archivo.png](img/img05-modificar-archivo.png) | Se comprobó la modificación de un archivo existente. |
| Búsqueda de archivo | [img06-buscar-archivo.png](img/img06-buscar-archivo.png) | Se verificó la búsqueda de archivos por nombre. |
| Límite de seguridad | [img07-limite-seguridad.png](img/img07-limite-seguridad.png) | Se documentó el intento de acceso fuera del directorio autorizado y el rechazo correspondiente. |

## 6. Seguridad y control de acceso

El servidor de sistema de archivos permite limitar las operaciones a los directorios que se autorizan durante su configuración. Esta restricción reduce el riesgo de que una herramienta acceda a archivos ajenos a la tarea.

Durante la práctica se probó el acceso a una ubicación fuera del directorio permitido. La evidencia de esta prueba se encuentra en `img/img07-limite-seguridad.png`.

Las medidas relevantes para esta implementación son:

- Autorizar únicamente los directorios necesarios.
- Evitar conceder acceso innecesario a carpetas personales o del sistema.
- Comprobar las rutas permitidas antes de realizar operaciones.
- Revisar las solicitudes de herramientas cuando puedan modificar o eliminar archivos.
- Mantener la configuración del servidor bajo control de versiones y evitar publicar información sensible.

La disponibilidad de herramientas de lectura o escritura no significa que deban utilizarse sin restricciones. La seguridad depende de la configuración del servidor, los permisos del sistema operativo y las decisiones del cliente.

## 7. Resultados

La implementación permitió comprobar la conexión de un cliente compatible con MCP a un servidor local de sistema de archivos. Se realizaron pruebas de listado, lectura, creación, modificación y búsqueda, además de una prueba destinada a verificar la restricción de acceso.

Las capturas de pantalla documentan el proceso y los resultados observados durante las pruebas.

## 8. Conclusiones

La práctica permitió comprender cómo MCP proporciona una interfaz estandarizada para conectar aplicaciones de inteligencia artificial con herramientas externas. Su arquitectura facilita la integración de capacidades sin que el cliente tenga que implementar individualmente cada operación del sistema de archivos.

La implementación también permitió identificar la importancia de limitar los directorios accesibles y verificar los permisos del servidor. La prueba de seguridad demuestra por qué la configuración de rutas autorizadas es un elemento fundamental al exponer herramientas que pueden leer o modificar información local.

En conclusión, MCP facilita la integración entre modelos de inteligencia artificial y herramientas, pero debe implementarse con controles de acceso adecuados, pruebas verificables y una configuración coherente con el alcance de cada proyecto.

## 9. Referencias

- Anthropic. (s. f.). *Introducing the Model Context Protocol*. https://www.anthropic.com/news/model-context-protocol
- Model Context Protocol. (s. f.). *MCP documentation*. https://modelcontextprotocol.io/
- Model Context Protocol. (s. f.). *Filesystem server*. GitHub. https://github.com/modelcontextprotocol/servers/tree/main/src/filesystem
- Microsoft. (s. f.). *Use MCP servers in Visual Studio Code*. Visual Studio Code Documentation. https://code.visualstudio.com/docs/copilot/chat/chat-mcp
- OpenJS Foundation. (s. f.). *Node.js documentation*. https://nodejs.org/docs/

