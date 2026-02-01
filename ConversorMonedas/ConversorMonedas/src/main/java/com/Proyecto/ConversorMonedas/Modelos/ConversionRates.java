package com.Proyecto.ConversorMonedas.Modelos;

import java.nio.DoubleBuffer;
import java.util.Map;

public record ConversionRates(String result, String base_code, Map<String, Double> conversion_rates) {
}
