# 4. Arquitectura de MCP

## El Modelo Host, Cliente y Servidor
La arquitectura del Model Context Protocol (MCP) se basa en una topología de tres actores principales:

*   **Host:** Es la aplicación final con la que interactúa el usuario. Ejemplos: Claude Desktop, VS Code, Cursor.
*   **Client:** Es el componente de software  que implementa el protocolo MCP y mantiene conexiones 1:1 con uno o varios servidores.
*   **Server:** Es el programa ligero e independiente que expone capacidades, herramientas o datos específicos a través del protocolo MCP.

## Primitivas del Servidor
Un servidor MCP expone tres tipos principales de primitivas para que el modelo las descubra y utilice:

*   **Resources:** Son datos legibles por el modelo que actúan como contexto. Pueden ser archivos de texto, esquemas de bases de datos o respuestas de APIs locales. Generalmente son de solo lectura y se identifican mediante URIs.
*   **Tools:** Son funciones ejecutables que permiten al modelo realizar acciones en el mundo real. A diferencia de los recursos, las herramientas tienen efectos secundarios.
*   **Prompts:** Son flujos de trabajo o plantillas de instrucciones reutilizables definidas por el servidor. Ayudan a los usuarios a interactuar fácilmente con los datos del servidor sin tener que escribir instrucciones complejas desde cero.

## Primitivas del Cliente
El protocolo es bidireccional, por lo que el cliente también expone primitivas hacia el servidor:

*   **Roots:** Permiten al cliente definir límites de contexto o directorios base. Informan al servidor sobre en qué carpetas o espacios de trabajo tiene permitido operar, ayudando a restringir el acceso por seguridad.
*   **Sampling / Elicitation:** Es una capacidad que permite al servidor MCP solicitar al cliente que realice una inferencia en su nombre. Esto permite arquitecturas agenticas más complejas donde el servidor puede orquestar pequeños pasos lógicos.

## Capa de Transporte
MCP es agnóstico respecto a la red, pero define oficialmente dos mecanismos de transporte para intercambiar sus mensajes JSON-RPC 2.0:

1.  **stdio (Standard Input/Output):** Diseñado para servidores locales. El cliente lanza el servidor como un **proceso hijo** en la misma máquina. La comunicación ocurre de forma segura y directa leyendo y escribiendo en la entrada y salida estándar del proceso, sin abrir puertos de red.
2.  **SSE (Server-Sent Events) / Streamable HTTP:** Diseñado para servidores remotos. Utiliza HTTP para enviar peticiones del cliente al servidor y mantiene una conexión SSE abierta para que el servidor pueda empujar mensajes y notificaciones al cliente en tiempo real.

## Versión de la Especificación
**Pendiente**