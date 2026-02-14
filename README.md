# 💱 Conversor de Monedas 

![Java](https://img.shields.io/badge/Java-21_LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

Una aplicación web moderna y elegante para realizar conversiones de divisas en tiempo real. Desarrollada como solución para el desafío de Backend, utiliza la API de ExchangeRate para obtener tasas de cambio actualizadas y ofrece una interfaz de usuario pulida con animaciones CSS.

### 🔗 Demo en Vivo
👉 **[Ver Aplicación Desplegada](https://conversor-monedas-j0fw.onrender.com)**
> *⚠️ Nota: Al estar alojado en un servidor gratuito, la primera carga puede demorar hasta 1 minuto en "despertar" el servicio. Por favor, ten paciencia.*

## 🚀 Características Principales

* **Conversión en Tiempo Real:** Consume la API de ExchangeRate-API para obtener cotizaciones precisas al instante.
* **Interfaz Moderna:** Diseño responsive utilizando **Bootstrap 5.3** y **FontAwesome**, con fondos animados y efectos de vidrio (glassmorphism).
* **Historial de Sesión:** Gestión de estado mediante `HttpSession` para visualizar las últimas conversiones de forma privada por usuario.
* **Validaciones:** Manejo de errores visuales (Input controls) y protección contra reenvío de formularios (Patrón PRG).
* **Arquitectura MVC:** Separación clara entre Modelos (Records), Vista (Thymeleaf) y Controlador.

## 🛠️ Stack Tecnológico

Este proyecto utiliza tecnologías modernas del ecosistema Java:

* **Lenguaje:** Java 21 LTS (Configurado para compatibilidad con contenedores).
* **Framework:** Spring Boot 3.
* **Infraestructura:** Docker & Render (Deploy).
* **Motor de Plantillas:** Thymeleaf.
* **Cliente HTTP:** `java.net.http.HttpClient` nativo.
* **Procesamiento JSON:** Google Gson.
* **Frontend:** HTML5, CSS3, Bootstrap 5.

## ⚙️ Instalación y Ejecución Local

### Prerrequisitos

* JDK 21 o superior.
* Maven.

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/conversor-monedas.git](https://github.com/tu-usuario/conversor-monedas.git)
    cd ConversorMonedas
    ```

2.  **Compilar y Ejecutar:**
    Puedes usar el wrapper de Maven incluido:
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Acceder a la aplicación:**
    Abre tu navegador y navega a: `http://localhost:8080`

## 🧩 Estructura del Proyecto

El proyecto sigue una estructura estándar de Spring Boot:

* `ControladorWeb.java`: Maneja las rutas HTTP, la lógica de negocio y la inyección de dependencias.
* `Modelos/ConversionRates.java`: Java Record (inmutable) para mapear la respuesta JSON de la API.
* `Modelos/Transaccion.java`: Clase que modela cada operación.
* `templates/index.html`: Vista principal con Thymeleaf y estilos integrados.

## 📡 API Reference

Este proyecto interactúa con **ExchangeRate-API**.
* Endpoint utilizado: `https://v6.exchangerate-api.com/v6/{API_KEY}/latest/{MONEDA_ORIGEN}`.

---
