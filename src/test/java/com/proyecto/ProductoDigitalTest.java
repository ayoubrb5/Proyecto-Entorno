package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    // ── TESTS NEGATIVOS ──────────────────────────────────────────────────────

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
    
}
