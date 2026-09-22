# 1. Evolución de los Modelos

## De los Modelos de Lenguaje (LM) a los Modelos de Lenguaje Grandes (LLM)

Un **Modelo de Lenguaje (LM)** es un sistema probabilístico diseñado para predecir la siguiente palabra en una secuencia de texto basándose en el contexto anterior. Históricamente, estos modelos utilizaban enfoques estadísticos como los *n-gramas* o arquitecturas de redes neuronales recurrentes (RNN y LSTM). Aunque eran útiles para tareas simples como el autocompletado en teclados o la corrección ortográfica, tenían una memoria a corto plazo muy limitada y no lograban mantener la coherencia en textos largos.

La evolución hacia los **Modelos de Lenguaje Grandes (LLM)** ocurrió gracias a un cambio radical en la arquitectura: la introducción del Transformer en 2017. Esta arquitectura, basada en el mecanismo de "atención", permitió procesar secuencias de texto enteras en paralelo en lugar de palabra por palabra. 

Al poder paralelizar el entrenamiento, fue posible inyectar cantidades masivas de datos de internet y aumentar exponencialmente el tamaño de las redes neuronales. Este aumento masivo en escala provocó la aparición de "capacidades emergentes": los LLMs ya no solo predecían la siguiente palabra de forma mecánica, sino que demostraron la capacidad de traducir, resumir, escribir código y seguir instrucciones complejas (*zero-shot* y *few-shot learning*), comportamientos para los que no fueron explícitamente programados en un inicio.

## Modelos con Razonamiento Explícito

Conforme los LLMs crecieron, quedó claro que simplemente predecir el siguiente token de forma rápida limitaba su capacidad para resolver problemas matemáticos, lógicos o de programación complejos. Para solucionar esto, surgieron los **modelos con razonamiento explícito**.

Estos modelos están diseñados para "pensar" antes de emitir una respuesta final. En lugar de generar la solución de un solo golpe, generan una "cadena de pensamiento" interna, dividiendo un problema complejo en pasos lógicos más pequeños, evaluando alternativas e incluso autocorigiendo sus errores antes de mostrar la salida al usuario.

El razonamiento explícito proviene de dos factores técnicos fundamentales:

1. **Técnicas de entrenamiento especializadas:** A diferencia de los LLMs tradicionales que se pre-entrenan solo imitando texto, los modelos de razonamiento son sometidos a un entrenamiento intensivo con Aprendizaje por Refuerzo. Se les recompensa no solo por dar la respuesta correcta, sino por el proceso lógico que siguieron para llegar a ella.

2. **Cómputo adicional en el momento de la inferencia:** En un LLM tradicional, el costo computacional de generar una palabra es estático. En los modelos de razonamiento explícito, se invierte energía y tiempo de procesamiento extra durante la inferencia. El modelo gasta ciclos de cómputo adicionales explorando múltiples caminos de solución, verificando sus propias hipótesis y reflexionando en un espacio de texto oculto antes de imprimir el resultado final.