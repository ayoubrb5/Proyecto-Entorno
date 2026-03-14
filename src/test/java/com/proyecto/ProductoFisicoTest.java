package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoFisicoTest {

    // ── TESTS POSITIVOS ──────────────────────────────────────────────────────

    @Test
    @DisplayName("T01 - El precio final incluye IVA 21% y coste de envio")
    void testPrecioFinalConIvaYEnvio() {
        ProductoFisico p = new ProductoFisico("Teclado", 100.0, 5.0);
        double esperado = 100.0 * 1.21 + 5.0; // 126.0
        assertEquals(esperado, p.calcularPrecioFinal(), 0.01);
    }

    @Test
    @DisplayName("T02 - El nombre del producto se guarda correctamente")
    void testGetNombre() {
        ProductoFisico p = new ProductoFisico("Raton", 30.0, 3.0);
        assertEquals("Raton", p.getNombre());
    }

    @Test
    @DisplayName("T03 - El precio base se guarda correctamente")
    void testGetPrecio() {
        ProductoFisico p = new ProductoFisico("Monitor", 200.0, 10.0);
        assertEquals(200.0, p.getPrecio(), 0.01);
    }

    @Test
    @DisplayName("T04 - El coste de envio se guarda correctamente")
    void testGetCosteEnvio() {
        ProductoFisico p = new ProductoFisico("Silla", 150.0, 12.50);
        assertEquals(12.50, p.getCosteEnvio(), 0.01);
    }

    @Test
    @DisplayName("T05 - toString contiene el nombre y el precio")
    void testToStringContieneNombreYPrecio() {
        ProductoFisico p = new ProductoFisico("Teclado", 69.99, 6.99);
        assertTrue(p.toString().contains("Teclado"));
        assertTrue(p.toString().contains("69.99"));
    }

    // ── TESTS NEGATIVOS ──────────────────────────────────────────────────────

    @Test
    @DisplayName("T06 - El precio final NO es igual al precio base sin IVA")
    void testPrecioFinalNoEsIgualAlBase() {
        ProductoFisico p = new ProductoFisico("Teclado", 100.0, 0.0);
        assertNotEquals(100.0, p.calcularPrecioFinal(), 0.01);
    }

    @Test
    @DisplayName("T07 - El precio final NO es negativo con valores normales")
    void testPrecioFinalNoEsNegativo() {
        ProductoFisico p = new ProductoFisico("Raton", 50.0, 5.0);
        assertFalse(p.calcularPrecioFinal() < 0);
    }

    @Test
    @DisplayName("T08 - Precio negativo lanza IllegalArgumentException")
    void testPrecioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductoFisico("Raro", -10.0, 5.0);
        });
    }

    @Test
    @DisplayName("T09 - Coste de envio negativo lanza IllegalArgumentException")
    void testEnvioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductoFisico("Raro", 50.0, -3.0);
        });
    }

    @Test
    @DisplayName("T10 - El precio final con envio 0 no es igual al precio base")
    void testPrecioFinalSinEnvioNoEsBase() {
        ProductoFisico p = new ProductoFisico("Casco", 80.0, 0.0);
        assertNotEquals(0.0, p.calcularPrecioFinal(), 0.01);
        assertNotEquals(80.0, p.calcularPrecioFinal(), 0.01);
    }

    @Test
    @DisplayName("T42 - toString contiene la palabra fisico")
    void testToStringContieneFisico() {
    ProductoFisico p = new ProductoFisico("Teclado", 50.0, 5.0);
    assertTrue(p.toString().toLowerCase().contains("físico"));
    }

    @Test
    @DisplayName("T43 - toString contiene el coste de envio")
    void testToStringContieneEnvio() {
    ProductoFisico p = new ProductoFisico("Teclado", 50.0, 5.0);
    assertTrue(p.toString().contains("5.0"));
    }
    
}
