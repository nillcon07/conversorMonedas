package com.Proyecto.ConversorMonedas;

import com.Proyecto.ConversorMonedas.Modelos.TasasConversion;
import com.Proyecto.ConversorMonedas.Modelos.Transaccion;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorWeb {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // Esto maneja la página principal (GET)
    @GetMapping("/")
    public String inicio(Model model, HttpSession session) {
        // Obtenemos o creamos la lista solo para el usuario (privacidad)
        List<Transaccion> Usuario = (List<Transaccion>) session.getAttribute("");
        if (Usuario == null) {
            Usuario = new ArrayList<>();
            session.setAttribute("", Usuario);
        }
        model.addAttribute("", Usuario);
        return "index";
    }

    // Esto maneja el botón "" (POST)
    @PostMapping("/")
    public String (
            @RequestParam double cantidad,
            @RequestParam String origen,
            @RequestParam String destino,
            RedirectAttributes redirectAttributes, // Usamos RedirectAttributes para el error F5
            HttpSession session // <--- Agregamos HttpSession aquí también
    ) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/20a9bbf5f7866569b0bc4943/latest/" + origen;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            TasasConversion respuesta = gson.fromJson(response.body(), TasasConversion.class);

            if (respuesta.conversion_rates() != null && respuesta.conversion_rates().containsKey(destino)) {
                Double tasa = respuesta.conversion_rates().get(destino);
                double resultado = cantidad * tasa;

                Transaccion nuevaTx = new Transaccion(origen, destino, cantidad, resultado, LocalDateTime.now());

                // --- AQUÍ VA EL CÓDIGO QUE PREGUNTASTE ---
                @SuppressWarnings("unchecked")
                List<Transaccion> historialUsuario = (List<Transaccion>) session.getAttribute("historial");
                if (historialUsuario == null) {
                    historialUsuario = new ArrayList<>();
                    session.setAttribute("historial", historialUsuario);
                }
                historialUsuario.add(0, nuevaTx);
                session.setAttribute("historial", historialUsuario); 
                // ------------------------------------------

                redirectAttributes.addFlashAttribute("resultado", resultado);
                redirectAttributes.addFlashAttribute("mensaje", "Conversión exitosa");

                // Mantener selección
                redirectAttributes.addFlashAttribute("monedaOrigen", origen);
                redirectAttributes.addFlashAttribute("monedaDestino", destino);
            } else {
                redirectAttributes.addFlashAttribute("error", "Moneda no encontrada o API falló.");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error técnico: " + e.getMessage());
        }

        return "redirect:/";
    }

}

