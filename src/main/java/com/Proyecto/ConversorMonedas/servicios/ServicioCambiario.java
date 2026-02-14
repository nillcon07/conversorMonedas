package com.Proyecto.ConversorMonedas.servicios;

import com.Proyecto.ConversorMonedas.modelos.TasasConversion;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ServicioCambiario {
    private final HttpClient client;
    private final Gson gson;

    @Value("${api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ServicioCambiario(HttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    public double obtenerTasaConversion(String origen, String destino) throws Exception {
        if (apiKey == null || apiKey.trim().isEmpty()) throw new IllegalStateException("API Key no configurada.");

        String url = BASE_URL + apiKey + "/latest/" + origen;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) throw new RuntimeException("Error API: " + response.statusCode());

        TasasConversion respuesta = gson.fromJson(response.body(), TasasConversion.class);

        if (respuesta == null || respuesta.conversion_rates() == null || !respuesta.conversion_rates().containsKey(destino)) {
            throw new IllegalArgumentException("Moneda no disponible.");
        }
        return respuesta.conversion_rates().get(destino);
    }

    public double calcularConversion(double cantidad, double tasa) {
        if (cantidad <= 0) throw new IllegalArgumentException("Monto debe ser positivo.");
        return cantidad * tasa;
    }
}