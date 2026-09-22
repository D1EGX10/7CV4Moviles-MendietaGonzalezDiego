# 3. MCP frente a una API

## ¿Qué es una API?
Una **API (Application Programming Interface)** es un contrato establecido entre dos programas de software para comunicarse entre sí. En el paradigma tradicional de las APIs, la integración es un proceso enteramente determinista y manual. La persona que desarrolla el software debe leer la documentación técnica, entender los endpoints disponibles, decidir cuál llamar para cumplir un objetivo, armar la petición  y escribir el código necesario para interpretar la respuesta. 

En este modelo, la decisión de qué endpoint se llama, bajo qué condiciones y con qué datos, queda escrita de antemano de forma rígida en el código fuente de la aplicación.

## ¿Qué es MCP?
El **Model Context Protocol (MCP)** es un protocolo abierto, fundamentado en el estándar JSON-RPC 2.0, diseñado específicamente para conectar Modelos de Lenguaje (LLMs) con fuentes de datos y herramientas externas. 

En lugar de requerir que un desarrollador programe llamadas específicas a endpoints, un servidor MCP publica dinámicamente un catálogo de sus capacidades. Este catálogo incluye el nombre de cada herramienta, una descripción en lenguaje natural de lo que hace y el esquema JSON de los parámetros que requiere. El cliente descubre este catálogo en tiempo de ejecución. Finalmente, es el propio modelo de lenguaje el que, basándose en la petición del usuario, decide de forma autónoma qué herramienta invocar y con qué parámetros.

## Tabla Comparativa

| Característica | API Tradicional | Model Context Protocol (MCP) |
| :--- | :--- | :--- |
| **Quién decide qué se invoca** | El desarrollador (definido previamente en el código fuente estático). | El Modelo de Lenguaje (decisión dinámica en tiempo de ejecución según el prompt). |
| **Cómo se descubren las capacidades** | De forma manual, mediante la lectura de documentación por parte del programador. | De forma automática, el servidor expone un catálogo legible por la máquina en tiempo de ejecución. |
| **Acoplamiento cliente-servicio** | Alto. El cliente se programa a la medida de los endpoints y estructuras de la API específica. | Bajo. El cliente es genérico y se adapta al catálogo de cualquier servidor MCP que se le conecte. |
| **Formato de los mensajes** | Variado (REST, GraphQL, gRPC, SOAP) usando típicamente HTTP. | Estandarizado sobre JSON-RPC 2.0, usando transportes como `stdio` o Streamable HTTP. |
| **Autenticación y consentimiento** | Programático (API Keys, OAuth), sin requerir intervención del usuario por cada llamada. | Enfocado en el usuario; el cliente suele requerir confirmación explícita para ejecutar herramientas. |
| **Reutilización entre aplicaciones** | Requiere escribir nuevo código de integración y adaptación para cada API distinta. | Un cliente compatible con MCP puede usar cualquier servidor MCP sin modificar su código. |

## MCP no sustituye a las APIs

Es fundamental aclarar de forma explícita que **MCP no sustituye ni vuelve obsoletas a las APIs**. 

MCP y las APIs operan en capas diferentes de abstracción. Un servidor MCP casi nunca implementa la lógica de negocio desde cero; en su lugar, **envuelve una API o un recurso ya existente**. MCP actúa simplemente como una capa de traducción o interfaz estandarizada por encima de la API tradicional, haciendo que esa API subyacente sea descubrible, comprensible y utilizable de forma autónoma por un modelo de inteligencia artificial.