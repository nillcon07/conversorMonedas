package com.Proyecto.ConversorMonedas.modelos;

import java.io.Serializable; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable {
    
    private static final long serialVersionUID = 1L; 

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

    public double getResultado() { return resultado; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%.2f %s ➔ %.2f %s (%s)",
                cantidad, origen, resultado, destino, fecha.format(formatter));
    }
}
