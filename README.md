# 💱 Conversor de Monedas 

Una aplicación web moderna y elegante para realizar conversiones de divisas en tiempo real. Desarrollada como solución para el desafío de Backend, utiliza la API de ExchangeRate para obtener tasas de cambio actualizadas y ofrece una interfaz de usuario pulida con animaciones CSS.

## 🚀 Características Principales

* **Conversión en Tiempo Real:** Consume la API de ExchangeRate-API para obtener cotizaciones precisas al instante.
* **Interfaz Moderna:** Diseño responsive utilizando **Bootstrap 5.3** y **FontAwesome**, con fondos animados y efectos de vidrio (glassmorphism).
* **Historial de Sesión:** Visualización inmediata de las últimas conversiones realizadas durante la sesión actual.
* **Validaciones:** Manejo de errores visuales si el usuario ingresa montos inválidos o selecciona la misma moneda de origen y destino.
* **Arquitectura MVC:** Separación clara entre Modelos (Records), Vista (Thymeleaf) y Controlador.

## 🛠️ Stack Tecnológico

Este proyecto utiliza las versiones más recientes del ecosistema Java:

* **Lenguaje:** Java 25 (Configurado en `pom.xml`).
* **Framework:** Spring Boot 4.0.2.
* **Motor de Plantillas:** Thymeleaf.
* **Cliente HTTP:** `java.net.http.HttpClient` nativo para consumo de APIs.
* **Procesamiento JSON:** Google Gson 2.13.2.
* **Frontend:** HTML5, CSS3 (Animaciones personalizadas), Bootstrap 5.

## ⚙️ Instalación y Ejecución

### Prerrequisitos

* JDK 25 (o compatible con la configuración del proyecto).
* Maven.

### Pasos

1. **Clonar el repositorio:**
```bash
git clone https://github.com/tu-usuario/conversor-monedas.git
cd ConversorMonedas

```


2. **Compilar y Ejecutar:**
Puedes usar el wrapper de Maven incluido:
```bash
./mvnw spring-boot:run

```


3. **Acceder a la aplicación:**
Abre tu navegador y navega a:
```
http://localhost:8080

```



## 🧩 Estructura del Proyecto

El proyecto sigue una estructura estándar de Spring Boot:

* `src/main/java/.../ControladorWeb.java`: Maneja las rutas HTTP (`GET /`, `POST /convertir`) y la lógica de negocio.
* `src/main/java/.../Modelos/ConversionRates.java`: Java Record para mapear la respuesta JSON de la API.
* `src/main/java/.../Modelos/Transaccion.java`: Clase que modela cada operación y formatea la salida para el historial.
* `src/main/resources/templates/index.html`: Vista principal con Thymeleaf y estilos integrados.

## 📡 API Reference

Este proyecto interactúa con **ExchangeRate-API**.

* Endpoint utilizado: `https://v6.exchangerate-api.com/v6/{API_KEY}/latest/{MONEDA_ORIGEN}`.

---

