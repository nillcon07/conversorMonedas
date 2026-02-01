package com.Proyecto.ConversorMonedas.Modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private String origen;
    private String destino;
    private double cantidad;
    private double resultado;
    private LocalDateTime fecha;

    public Transaccion(String origen, String destino, double cantidad, double resultado, LocalDateTime fecha) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    // Getters (necesarios si alguna vez quieres acceder a propiedades individuales)
    public double getResultado() { return resultado; }

    // ESTE MÉTODO ES CLAVE: Define cómo se ve la transacción en el historial del HTML
    @Override
    public String toString() {
        // Formato: "100.00 USD -> 95200.50 ARS (15:30)"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%.2f %s ➔ %.2f %s (%s)",
                cantidad, origen, resultado, destino, fecha.format(formatter));
    }
}