# 5. El Servidor de Sistema de Archivos

## "FS" no es el protocolo
Es fundamental aclarar que "FS" (Filesystem) **no es una parte interna del Model Context Protocol (MCP)**. MCP es únicamente el estándar de comunicación. El servidor de sistema de archivos es, en cambio, uno de los **servidores MCP de referencia** desarrollado para demostrar cómo un modelo puede interactuar con el almacenamiento local. Es solo un servidor entre muchos otros posibles.

## Herramientas que expone y delimitación de alcance
El servidor de sistema de archivos expone un conjunto de herramientas específicas que el modelo de lenguaje puede invocar para operar sobre el disco local. Estas incluyen:

*   **Listar un directorio:** Ver los archivos y subcarpetas contenidos en una ruta específica.
*   **Leer un archivo:** Extraer el contenido en texto plano de un archivo existente.
*   **Escribir y crear archivos:** Insertar contenido nuevo, lo cual crea el archivo si no existe o lo sobrescribe si ya está presente.
*   **Mover archivos:** Renombrar o cambiar la ubicación de un documento.
*   **Buscar archivos:** Encontrar archivos por nombre o patrones dentro del entorno de trabajo.

Para operar, este servidor implementa un estricto control de alcance. Cuando se inicializa, se le deben pasar como argumentos las **rutas absolutas de los directorios permitidos**. Antes de ejecutar cualquier herramienta, el servidor normaliza la ruta solicitada por el modelo y verifica que se encuentre estrictamente dentro de los límites de estos directorios autorizados.

## El propósito del límite de seguridad
Este límite existe para aplicar el principio de **privilegio mínimo** y confinar la ejecución del modelo. Dado que el modelo actúa basándose en texto que puede provenir de fuentes externas o no confiables, el límite previene el acceso indiscriminado al sistema operativo subyacente.

**¿Qué pasaría sin este límite?**
Si el servidor se ejecutara con acceso total a la raíz del disco (ej. `C:\` en Windows o `/` en sistemas Unix), el entorno quedaría completamente expuesto a vulnerabilidades críticas:
1.  **Exfiltración de datos:** Un ataque de inyección de instrucciones podría forzar al modelo a leer y revelar archivos altamente sensibles, como llaves SSH (`~/.ssh/id_rsa`), credenciales de nube o archivos `.env` que contienen contraseñas.
2.  **Destrucción del sistema:** Una alucinación del modelo o una instrucción maliciosa dirigida podría resultar en la sobrescritura, el movimiento o el borrado de archivos críticos del sistema operativo, corrompiendo la máquina del usuario de forma irreversible.