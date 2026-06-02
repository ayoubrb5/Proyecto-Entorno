package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {

    // ── TESTS UNITARIOS 
    @Test
    @DisplayName("T66 - el código de factura comienza con el prefijo 'FAC-'")
    void testCodigoFacturaTienePrefijo() {
        Factura factura = new Factura(100.0, 0.0, 21.0, 5.0, 126.0);
        assertTrue(factura.getCodigoFactura().startsWith("FAC-"));
    }

    @Test
    @DisplayName("T67 - la fecha de emisión coincide con la fecha de hoy")
    void testFechaEmisionEsHoy() {
        Factura factura = new Factura(100.0, 0.0, 21.0, 5.0, 126.0);
        assertEquals(LocalDate.now(), factura.getFechaEmision());
    }

    @Test
    @DisplayName("T68 - getTotalNeto devuelve el valor correcto")
    void testGetTotalNeto() {
        Factura factura = new Factura(150.0, 10.0, 21.0, 5.0, 166.0);
        assertEquals(150.0, factura.getTotalNeto(), 0.01);
    }

    @Test
    @DisplayName("T69 - getTotalDescuento devuelve el valor correcto")
    void testGetTotalDescuento() {
        Factura factura = new Factura(150.0, 10.0, 21.0, 5.0, 166.0);
        assertEquals(10.0, factura.getTotalDescuento(), 0.01);
    }

    @Test
    @DisplayName("T70 - getTotalIva devuelve el valor correcto")
    void testGetTotalIva() {
        Factura factura = new Factura(100.0, 0.0, 21.0, 5.0, 126.0);
        assertEquals(21.0, factura.getTotalIva(), 0.01);
    }

    @Test
    @DisplayName("T71 - getTotalEnvio devuelve el valor correcto")
    void testGetTotalEnvio() {
        Factura factura = new Factura(100.0, 0.0, 21.0, 5.0, 126.0);
        assertEquals(5.0, factura.getTotalEnvio(), 0.01);
    }

    @Test
    @DisplayName("T72 - getTotalFinal devuelve el valor correcto")
    void testGetTotalFinal() {
        Factura factura = new Factura(100.0, 0.0, 21.0, 5.0, 126.0);
        assertEquals(126.0, factura.getTotalFinal(), 0.01);
    }

    @Test
    @DisplayName("T73 - mostrarDesglose no lanza ninguna excepción")
    void testMostrarDesgloseNoLanzaExcepcion() {
        Factura factura = new Factura(100.0, 15.0, 21.0, 5.0, 111.0);
        assertDoesNotThrow(() -> factura.mostrarDesglose());
    }

    // ── PRUEBA DE INTEGRACIÓN
    // Verifica la comunicación real entre Tienda, Pedido, Cliente y Factura

    @Test
    @DisplayName("INT-F01 - mostrarDesglose funciona correctamente en un flujo real completo")
    void testMostrarDesgloseEnFlujoReal() {
        Cliente cliente = new Cliente("F001", "Sofia Torres", "sofia@test.com", "Paseo 5", 2, false, "España");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new ProductoFisico("Impresora", 150.0, 0.0));
        pedido.agregarProducto(new ProductoDigital("Antivirus",  40.0, "Anual"));

        Factura factura = new Tienda().realizarVenta(cliente, pedido);

        assertNotNull(factura);
        assertEquals(190.0, factura.getTotalNeto(), 0.01); 
        assertEquals(0.0,   factura.getTotalDescuento(), 0.01); 
        assertEquals(31.5,  factura.getTotalIva(), 0.01);       
        assertEquals(0.0,   factura.getTotalEnvio(), 0.01);     
        assertEquals(221.5, factura.getTotalFinal(), 0.01);     

        assertDoesNotThrow(() -> factura.mostrarDesglose());
    }

}
