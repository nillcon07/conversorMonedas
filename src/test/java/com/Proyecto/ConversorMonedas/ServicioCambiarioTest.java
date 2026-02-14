package com.Proyecto.ConversorMonedas;

import com.Proyecto.ConversorMonedas.Servicios.ServicioCambiario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServicioCambiarioTest {
    @Autowired private ServicioCambiario servicio;

    @Test
    void testCalculoMatematico() {
        Assertions.assertEquals(200.0, servicio.calcularConversion(100, 2.0));
    }
}