## Sistema de Gestión de Cooperativa de Taxis (Santa Marta)

Descripción del Problema y Alcance

Este proyecto simula el sistema de gestión operativa de una cooperativa de taxis en la ciudad de Santa Marta. El problema central es la coordinación eficiente entre tres actores: clientes que solicitan servicios de transporte, operadores que gestionan y asignan esas solicitudes, y conductores que las ejecutan en campo.

Sin un sistema centralizado, la cooperativa enfrenta dificultades para controlar qué conductores están disponibles, qué tipo de servicio ofrece cada uno y en qué orden deben atenderse los pedidos acumulados. Este programa resuelve esas necesidades mediante estructuras de datos de implementación propia, un modelo de enrutamiento urbano basado en grafos y un mecanismo de persistencia que conserva el estado del sistema entre sesiones.

## Alcance del Proyecto
* **Dentro del alcance:** Registro y administración de conductores, clientes y operadores; creación y procesamiento de solicitudes de viaje; cálculo automático de tarifas y tiempos estimados según la distancia entre barrios; enrutamiento sobre un mapa de 20 barrios de Santa Marta usando el algoritmo de Dijkstra.
* **Fuera del alcance:** Interfaz gráfica de usuario, integración con pasarelas de pago, geolocalización en tiempo real o soporte para otras ciudades.

## Requisitos Funcionales Implementados

### Gestión de Actores
* **Conductores:** Se registran con nombre, zona, código, placa y cédula. Al momento del registro se valida que la placa y el identificador no estén vacíos. Cada conductor puede tener habilitados uno o más tipos de servicio (Estándar, Baúl, Mascotas y Parrilla) y mantiene un estado interno de disponibilidad que cambia cuando se le asigna un viaje.
* **Clientes:** Se registran con nombre, zona y código único. Cada cliente lleva un historial de actividad implementado con una Pila, lo que permite consultar los viajes más recientes primero.
* **Operadores:** Se registran con nombre, zona, código y turno, siendo los únicos actores autorizados para procesar la cola de solicitudes pendientes.

### El Mapa y Enrutamiento (Grafos)
El mapa del sistema es un grafo bidireccional con pesos que representa 20 barrios de Santa Marta y sus conexiones viales con distancias en kilómetros.
* **Algoritmo principal:** El cálculo de la ruta más corta se realiza mediante el algoritmo de Dijkstra.
* **Algoritmos adicionales:** La capa del grafo también tiene implementados Bellman-Ford y Floyd-Warshall disponibles para uso futuro.
* **Dinámica:** Un administrador puede habilitar o deshabilitar conexiones entre barrios en tiempo de ejecución sin necesidad de reiniciar el sistema.

### Flujo de Solicitudes y Asignación
1. **Solicitud:** Cuando un cliente solicita un viaje, elige un barrio de origen, un barrio de destino y el tipo de taxi que necesita. El sistema verifica que exista una ruta válida entre los dos puntos en el grafo del mapa.
2. **Cálculo de Tarifa y Tiempo:** Si la ruta existe, se calcula la tarifa con la fórmula de cinco mil pesos de base más mil pesos por kilómetro de distancia, y se estima el tiempo de llegada multiplicando la distancia por dos.
3. **Encolamiento:** La solicitud se encola en una Cola FIFO (First In, First Out) de solicitudes pendientes.
4. **Despacho:** Cuando un operador elige atender la siguiente solicitud, el sistema desencola la primera de la fila y busca entre los conductores registrados uno que esté disponible y que ofrezca el tipo de servicio requerido. Si lo encuentra, le asigna la solicitud, lo marca como no disponible y actualiza el estado del viaje a "En camino". Si ningún conductor cumple las condiciones, la solicitud se re-encola al final y el sistema lanza una excepción informativa.

### Persistencia de Datos
Al seleccionar la opción de salida, todos los datos del sistema se serializan en un archivo llamado `cooperativa_datos.dat`. La próxima vez que se ejecute el programa, ese archivo se carga automáticamente y el sistema retoma el estado exacto en que se dejó. Si el archivo no existe o está corrupto, el sistema arranca con una cooperativa vacía sin interrumpir la ejecución.

### Sistema de Excepciones Personalizadas
El sistema cuenta con un conjunto de excepciones personalizadas para el control de errores:
* `ZonaNoEncontradaException`: Cuando no hay ruta entre el origen y el destino.
* `ConductorNoDisponibleException`: Cuando ningún conductor libre puede atender la solicitud bajo las condiciones dadas.
* `ConductorNoEncontradoException`: Cuando los datos de registro de un conductor son inválidos.
* `ServicioNoEncontradoException`: Cuando el tipo de servicio solicitado no existe en el catálogo.
* `ViaNoEncontradaException`: Cuando se referencia una conexión inexistente en el mapa.



<img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/5760de32-dec8-43c7-9305-8e79d0f57ffa" />

<img width="1600" height="900" alt="image" src="https://github.com/user-attachments/assets/fe11ae9d-8754-4aee-8ad4-7671066f00fc" />

