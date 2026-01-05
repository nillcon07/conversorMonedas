package Modelos;

import java.time.LocalDateTime;

public record Transaccion(String monedaOrigen, String monedaDestino, double monto, double resultado, LocalDateTime fecha) {
    @Override
    public String toString() {
        // Formato limpio para impresión
        return String.format("Fecha: %s | %.2f %s -> %.2f %s",
                fecha.toString().replace("T", " ").substring(0, 16), // Limpieza rápida de formato ISO
                monto, monedaOrigen, resultado, monedaDestino);
    }
}