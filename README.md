# Conversor de Monedas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Process-orange?style=for-the-badge)

Solución al desafío de Backend de **Alura Latam + Oracle Next Education**. Una aplicación de consola robusta que realiza conversiones de divisas en tiempo real consumiendo la API de Exchange Rate.

##  Descripción del Proyecto
Este proyecto es un conversor de monedas que interactúa con una API externa para obtener tasas de cambio actualizadas. A diferencia de un conversor estático, este programa maneja respuestas JSON dinámicas, realiza la conversión basándose en datos en tiempo real y presenta una interfaz de usuario basada en menús interactivos en la consola.

El desarrollo siguió una metodología ágil utilizando **Trello** para la gestión de tareas (Backlog, En Progreso, Concluido).

##  Características
* **Consumo de API:** 
* **Deserialización de JSON:** 
* **Interfaz de Menú:** 
* **Manejo de Errores:** 
* **Soporte de Monedas:**
    * USD (Dólar Estadounidense)
    * ARS (Peso Argentino)
    * BRL (Real Brasileño)
    * COP (Peso Colombiano)
    * EUR (Euro)
    * CNY (Yuan)
    * JPY (Yen)
    * (Y otras soportadas por la API)
 * **Historial de conversiones con fecha**
  
##  Tecnologías Utilizadas
* **Java JDK 25** (Uso de `HttpClient`, `HttpRequest`, `HttpResponse`).
* **Gson (Google):** Librería para el parseo y mapeo de JSON a Objetos Java.
* **Maven:** Gestión de dependencias.
* **IntelliJ IDEA:** IDE de desarrollo.

##  Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone [https://github.com/tu-usuario/conversor-monedas-alura.git](https://github.com/tu-usuario/conversor-monedas-alura.git)
