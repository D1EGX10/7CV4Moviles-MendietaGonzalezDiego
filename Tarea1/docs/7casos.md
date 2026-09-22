# 7. Casos de Uso

## Herramientas actuales que implementan MCP

*   **Claude Desktop:** La aplicación nativa de Anthropic para sistemas operativos de escritorio. Utiliza el protocolo para conectar el modelo directamente con el entorno local del usuario. Esto le permite analizar documentos extensos almacenados en el equipo, consultar bases de datos locales o interactuar con herramientas del sistema.
*   **Google Antigravity:** Un entorno de desarrollo agéntico que integra inteligencia artificial en el flujo de programación. Implementa el protocolo para otorgar al agente capacidades avanzadas como la navegación profunda en el código fuente, la ejecución de pruebas automatizadas y la modificación de la base de código de forma autónoma.
*   **Cursor:** Un editor de código que utiliza modelos de lenguaje para asistir en la creación de software. Mediante este estándar de comunicación, el editor expone el contexto completo del proyecto al modelo subyacente, permitiendo que la inteligencia artificial comprenda la estructura de carpetas y sugiera refactorizaciones que abarcan múltiples documentos simultáneamente.

## Edición de repositorios sin carga manual

Estas herramientas cambian radicalmente el flujo de trabajo al eliminar la necesidad de subir archivos uno por uno a una interfaz web. Al implementar la arquitectura descrita anteriormente, la aplicación se ejecuta en la máquina del usuario e inicializa un servidor de sistema de archivos apuntando específicamente al directorio del proyecto.

Cuando la persona solicita un cambio o una nueva característica, el modelo de lenguaje primero explora el repositorio utilizando comandos de lectura para entender el contexto y las dependencias. Posteriormente, el modelo genera y envía instrucciones precisas estructuradas para invocar herramientas de escritura o reemplazo de texto. El cliente local recibe estos comandos y modifica los archivos directamente en el disco duro. De esta manera, el modelo edita y coordina cambios a través de repositorios completos en tiempo real.