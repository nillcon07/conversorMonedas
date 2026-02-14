package com.Proyecto.ConversorMonedas.Principal;

import com.Proyecto.ConversorMonedas.Modelos.Transaccion;
import com.Proyecto.ConversorMonedas.Servicios.ServicioCambiario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorWeb {
    private final ServicioCambiario servicio;

    public ControladorWeb(ServicioCambiario servicio) { this.servicio = servicio; }

    @GetMapping("/")
    public String inicio(Model model, HttpSession session) {
        List<Transaccion> historial = (List<Transaccion>) session.getAttribute("historial");
        if (historial == null) { historial = new ArrayList<>(); session.setAttribute("historial", historial); }
        model.addAttribute("historial", historial);
        return "index";
    }

    @PostMapping("/convertir")
    public String convertir(@RequestParam double cantidad, @RequestParam String origen, @RequestParam String destino, RedirectAttributes attr, HttpSession session) {
        try {
            if (origen.equals(destino)) throw new IllegalArgumentException("Monedas iguales.");

            double tasa = servicio.obtenerTasaConversion(origen, destino);
            double resultado = servicio.calcularConversion(cantidad, tasa);

            Transaccion tx = new Transaccion(origen, destino, cantidad, resultado, LocalDateTime.now());

            List<Transaccion> historial = (List<Transaccion>) session.getAttribute("historial");
            if (historial == null) historial = new ArrayList<>();
            historial.add(0, tx);
            if (historial.size() > 10) historial = historial.subList(0, 10);
            session.setAttribute("historial", historial);

            attr.addFlashAttribute("resultado", resultado);
            attr.addFlashAttribute("mensaje", "Éxito");
            attr.addFlashAttribute("monedaOrigen", origen); // Para mantener la selección
            attr.addFlashAttribute("monedaDestino", destino); // Para mantener la selección

        } catch (Exception e) {
            attr.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/";
    }
}