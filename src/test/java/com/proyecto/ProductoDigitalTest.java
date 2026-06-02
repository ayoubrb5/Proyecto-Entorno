package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ProductoDigitalTest {

    // ── TESTS POSITIVOS ──────────────────────────────────────────────────────

    @Test
    @DisplayName("T11 - El precio final aplica el 10% de descuento correctamente")
    void testPrecioFinalConDescuento() {
        ProductoDigital p = new ProductoDigital("Office", 100.0, "Licencia permanente");
        assertEquals(90.0, p.calcularPrecioFinal(), 0.01);
    }

    @Test
    @DisplayName("T12 - La licencia se guarda correctamente")
    void testGetLicencia() {
        ProductoDigital p = new ProductoDigital("Antivirus", 39.99, "Suscripcion anual");
        assertEquals("Suscripcion anual", p.getLicencia());
    }

    @Test
    @DisplayName("T13 - toString contiene la palabra digital")
    void testToStringContieneDigital() {
        ProductoDigital p = new ProductoDigital("Photoshop", 239.88, "Suscripcion anual");
        assertTrue(p.toString().toLowerCase().contains("digital"));
    }

    // ── TESTS NEGATIVOS -
    @Test
    @DisplayName("T14 - El precio final NO es igual al precio base sin descuento")
    void testPrecioFinalNoEsIgualAlBase() {
        ProductoDigital p = new ProductoDigital("Office", 100.0, "Permanente");
        assertNotEquals(100.0, p.calcularPrecioFinal(), 0.01);
    }

    @Test
    @DisplayName("T15 - Precio negativo lanza IllegalArgumentException")
    void testPrecioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductoDigital("Raro", -20.0, "Licencia");
        });
    }

    @Test
    @DisplayName("T38 - toString contiene el precio")
    void testToStringContienePrecio() {
        ProductoDigital p = new ProductoDigital("Office", 100.0, "Permanente");
        assertTrue(p.toString().contains("100.0"));
    }

    @Test
    @DisplayName("T39 - toString contiene la licencia")
    void testToStringContieneLicencia() {
        ProductoDigital p = new ProductoDigital("Office", 100.0, "Permanente");
        assertTrue(p.toString().contains("Permanente"));
    }

    @Test
    @DisplayName("T44 - aplicarIVA GENERAL aplica el 21%")
    void testAplicarIvaGeneral() {
        ProductoDigital p = new ProductoDigital("Software", 100.0, "Anual");
        assertEquals(121.0, p.aplicarIVA("GENERAL"), 0.01);
    }

    @Test
    @DisplayName("T45 - aplicarIVA REDUCIDO aplica el 10%")
    void testAplicarIvaReducido() {
        ProductoDigital p = new ProductoDigital("Software", 100.0, "Anual");
        assertEquals(110.0, p.aplicarIVA("REDUCIDO"), 0.01);
    }

    @Test
    @DisplayName("T46 - aplicarIVA SUPER aplica el 4%")
    void testAplicarIvaSuper() {
        ProductoDigital p = new ProductoDigital("Software", 100.0, "Anual");
        assertEquals(104.0, p.aplicarIVA("SUPER"), 0.01);
    }

    @Test
    @DisplayName("T47 - aplicarIVA con tipo inválido lanza IllegalArgumentException")
    void testAplicarIvaTipoInvalido() {
        ProductoDigital p = new ProductoDigital("Software", 100.0, "Anual");
        assertThrows(IllegalArgumentException.class, () -> {
            p.aplicarIVA("INVALIDO");
        });
    }

    @ParameterizedTest
    @DisplayName("T48 - aplicarIVA parametrizado con distintos tipos y precios")
    @CsvSource({
        "GENERAL, 100.0, 121.0",
        "REDUCIDO, 100.0, 110.0",
        "SUPER,    100.0, 104.0",
        "GENERAL,   50.0,  60.5",
        "REDUCIDO, 200.0, 220.0"
    })
    void testAplicarIvaParametrizado(String tipoIva, double precio, double esperado) {
        ProductoDigital p = new ProductoDigital("Producto", precio, "Licencia");
        assertEquals(esperado, p.aplicarIVA(tipoIva), 0.01);
    }
}
