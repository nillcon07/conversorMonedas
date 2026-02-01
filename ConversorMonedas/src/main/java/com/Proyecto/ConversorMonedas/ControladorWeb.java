package com.Proyecto.ConversorMonedas;

import com.Proyecto.ConversorMonedas.Modelos.ConversionRates;
import com.Proyecto.ConversorMonedas.Modelos.Transaccion;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorWeb {

    private final List<Transaccion> historial = new ArrayList<>();
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // Esto maneja la página principal (GET)
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("historial", historial);
        return "index"; // Esto busca el archivo index.html
    }

    // Esto maneja el botón "Convertir" (POST)
    @PostMapping("/convertir")
    public String convertir(
            @RequestParam double cantidad,
            @RequestParam String origen,
            @RequestParam String destino,
            Model model
    ) {
        try {
            // TU LÓGICA ORIGINAL DE CONEXIÓN
            String url = "https://v6.exchangerate-api.com/v6/20a9bbf5f7866569b0bc4943/latest/" + origen;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ConversionRates respuesta = gson.fromJson(response.body(), ConversionRates.class);

            // Verificamos si existe la moneda destino
            if (respuesta.conversion_rates() != null && respuesta.conversion_rates().containsKey(destino)) {
                Double tasa = respuesta.conversion_rates().get(destino);
                double resultado = cantidad * tasa;

                // Crear y guardar transacción
                Transaccion nuevaTx = new Transaccion(origen, destino, cantidad, resultado, LocalDateTime.now());
                historial.add(0, nuevaTx);

                // Mandar datos al HTML
                model.addAttribute("resultado", resultado);
                model.addAttribute("mensaje", "Conversión exitosa");
            } else {
                model.addAttribute("error", "Moneda no encontrada o API falló.");
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error técnico: " + e.getMessage());
        }

        // Siempre devolvemos el historial y volvemos a la misma página
        model.addAttribute("historial", historial);
        return "index";
    }
}