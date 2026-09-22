# 2. El Problema del Aislamiento

## La naturaleza de texto de los LLMs
Por diseño, un Modelo de Lenguaje Grande (LLM) es un sistema estrictamente matemático y aislado. Su funcionamiento base se reduce a recibir una cadena de texto (input) y generar otra cadena de texto (output). 

Un LLM, por sí mismo, carece de un entorno de ejecución asociado; es decir, no está conectado a una terminal ni tiene la capacidad nativa de ejecutar llamadas al sistema operativo (*syscalls* como `open`, `read` o `write`). Si se le pide que lea un archivo, el modelo solo puede generar un texto que diga "leyendo archivo...", pero no tiene el mecanismo técnico para solicitarle al sistema operativo del usuario que acceda al sistema de archivos local. Para que un modelo interactúe con el entorno, requiere una capa intermedia que traduzca el texto generado en acciones ejecutables.

## Razones de Arquitectura vs. Razones de Seguridad
La desconexión entre el modelo y los archivos locales no es un error de diseño, sino una limitante impuesta por dos grandes categorías de razones:

### Razones de Arquitectura
Tienen que ver con la ubicación física y la infraestructura donde se ejecuta el modelo.
* **Ejecución remota:** Los LLMs más potentes requieren clústeres masivos de GPUs para realizar la inferencia. Por lo tanto, el modelo se ejecuta en un servidor remoto, mientras que los archivos que el usuario quiere modificar están en su disco duro local.
* **Ausencia de un canal bidireccional de I/O:** La API tradicional de los modelos de lenguaje está diseñada como una arquitectura sin estado. Se envía un prompt y se recibe un texto. No existe un canal abierto persistente o un protocolo integrado nativamente en la inferencia que permita al servidor remoto navegar por el disco local del cliente.

### Razones de Seguridad
Incluso si el modelo se ejecutara localmente en la máquina del usuario, otorgarle acceso directo al sistema de archivos representaría un riesgo crítico.
* **Aislamiento:** Las aplicaciones modernas se diseñan bajo el principio de privilegio mínimo. Un modelo que procesa texto arbitrario debe estar confinado para evitar que un comportamiento inesperado o una alucinación corrompa el sistema operativo.
* **Consentimiento y Privacidad:** El modelo no debe tener la capacidad de explorar el disco duro a voluntad. El usuario debe mantener el control absoluto sobre qué directorios y archivos específicos expone a la IA, garantizando que no se filtren contraseñas, llaves SSH o documentos personales.
* **Riesgo de Inyección de Instrucciones:** Si un modelo tuviera acceso directo a los archivos, un atacante podría colocar un archivo en el disco con instrucciones maliciosas ocultas. Cuando el modelo lea el archivo, interpretaría ese texto como una instrucción válida, comprometiendo la máquina del usuario. Limitar y controlar rigurosamente lo que el modelo puede hacer mitiga estos vectores de ataque.