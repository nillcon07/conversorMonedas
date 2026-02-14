package com.Proyecto.ConversorMonedas.Modelos;

import java.util.Map;

public record TasasConversion(String result, String base_code, Map<String, Double> conversion_rates) {
}
