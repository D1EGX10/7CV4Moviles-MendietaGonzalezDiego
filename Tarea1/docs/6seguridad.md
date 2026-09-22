# 6. Seguridad

## Riesgos Concretos
La integración de modelos de lenguaje con sistemas de archivos locales introduce vulnerabilidades específicas que deben ser gestionadas cuidadosamente:

*   **Inyección de instrucciones a través del contenido de un archivo:** Si el modelo lee un documento que contiene texto malicioso oculto, podría interpretar ese texto como una orden directa y alterar su comportamiento. Un atacante podría esconder estas instrucciones en un archivo descargado de internet o dentro de un repositorio de código de terceros.
*   **Acceso a rutas fuera del directorio autorizado:** Existe el peligro de que el modelo intente leer información confidencial del sistema operativo, credenciales de seguridad o configuraciones personales de otros proyectos utilizando técnicas para escalar a directorios superiores.
*   **Escritura o borrado no deseados:** Una alucinación del modelo o una interpretación errónea de una petición ambigua por parte del usuario puede llevar a sobrescribir código importante, alterar archivos vitales o eliminar documentos de forma irreversible.

## Mitigaciones
Para contrarrestar estos riesgos, el protocolo y los clientes implementan diversas barreras arquitectónicas y operativas:

*   **Confirmación humana antes de ejecutar:** Las aplicaciones cliente integran un mecanismo donde las llamadas a herramientas de escritura o modificación se pausan. El sistema exige la aprobación explícita del usuario mediante un botón o comando antes de que la acción impacte el disco duro.
*   **Alcance limitado a un directorio:** El servidor se inicializa con parámetros estrictos que restringen su operación a una carpeta de trabajo específica. Cualquier intento lógico de navegación hacia la raíz del disco duro es interceptado y bloqueado por el servidor local.
*   **Permisos de solo lectura:** Cuando la tarea requerida es únicamente de análisis o revisión de código, se puede configurar el servidor para que exponga de forma exclusiva herramientas de lectura, anulando por completo la posibilidad de daño a los archivos.
*   **Revisión de lo que el servidor expone:** La persona que configura el entorno debe auditar el catálogo de herramientas habilitadas, asegurándose de proporcionar al modelo únicamente las capacidades estrictamente necesarias para el flujo de trabajo actual.