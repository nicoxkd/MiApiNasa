# Consumo de la API de la NASA - Práctica PMD

Este proyecto es una aplicación Android desarrollada en Java para el módulo de Programación de Multimedia y Dispositivos Móviles. El objetivo principal es el consumo de servicios REST externos y la manipulación de datos JSON.

## Objetivos cumplidos

### Objetivo 1: Configuración de API Key
- Se ha generado y configurado una clave de acceso personal en la plataforma de la NASA para autenticar las peticiones.

### Objetivo 2: Modificación de Llamadas (Fecha Aleatoria)
- Se ha implementado el método `getRandomDate()` en la clase `NasaApodClient.java`.
- La aplicación genera automáticamente una fecha aleatoria de los últimos 10 años para cada consulta, cumpliendo con el requisito de modificación de la URL.

### Interfaz de Usuario Avanzada
- **Extracción de datos:** Se procesa el objeto JSON para obtener el título, la fecha y la explicación detallada.
- **Componentes UI:** Se han añadido y vinculado `TextViews` y un `ImageView` en el layout `activity_main.xml` para mostrar toda la información recibida.
- **Carga de Imagen:** Implementación de descarga de imagen para una experiencia de usuario completa.

## 🛠Tecnologías utilizadas
- **Lenguaje:** Java.
- **Entorno:** Android Studio (API 24+).
- **Networking:** `HttpURLConnection` para peticiones GET.
- **Parsing:** `org.json` para el manejo de la respuesta del servidor.
- **Concurrency:** Uso de hilos secundarios (`Thread`) para evitar el bloqueo del hilo principal de la UI.

## Estructura del Proyecto
- `NasaApodClient.java`: Lógica de conexión y generación de parámetros aleatorios.
- `MainActivity.java`: Control de la actividad, gestión del ciclo de vida y actualización de la interfaz.
- `activity_main.xml`: Diseño visual con soporte para scroll para textos largos.
