package com.Proyecto.ConversorMonedas.modelos;

import java.util.Map;

public record TasasConversion(String result, String base_code, Map<String, Double> conversion_rates) {
}
