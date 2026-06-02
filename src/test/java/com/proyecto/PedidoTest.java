package com.proyecto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Cliente cliente;
    private Pedido pedido;

    // Se ejecuta antes de cada test, crea un cliente y pedido limpios
    @BeforeEach
    void setUp() {
        cliente = new Cliente("Maria Lopez", "maria@gmail.com", "Calle Sol 25");
        pedido = new Pedido(cliente);
    }

    // ── TESTS POSITIVOS ──────────────────────────────────────────────────────

    @Test
    @DisplayName("T16 - calcularTotal devuelve la suma correcta de productos fisicos")
    void testCalcularTotalProductosFisicos() {
        pedido.agregarProducto(new ProductoFisico("Teclado", 100.0, 5.0));
        pedido.agregarProducto(new ProductoFisico("Raton", 50.0, 3.0));
        // Teclado: 100*1.21+5 = 126.0 | Raton: 50*1.21+3 = 63.5
        assertEquals(189.5, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("T17 - calcularTotal devuelve la suma correcta de productos digitales")
    void testCalcularTotalProductosDigitales() {
        pedido.agregarProducto(new ProductoDigital("Office", 100.0, "Permanente"));
        pedido.agregarProducto(new ProductoDigital("Antivirus", 40.0, "Anual"));
        // Office: 90.0 | Antivirus: 36.0
        assertEquals(126.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("T18 - El pedido tiene el cliente correcto")
    void testClienteDelPedidoCorrecto() {
        assertEquals("Maria Lopez", pedido.getCliente().getNombre());
    }

    @Test
    @DisplayName("T19 - agregarProducto aumenta el numero de productos")
    void testAgregarProductoAumentaLista() {
        pedido.agregarProducto(new ProductoFisico("Teclado", 50.0, 5.0));
        pedido.agregarProducto(new ProductoDigital("Office", 100.0, "Permanente"));
        assertEquals(2, pedido.getProductos().size());
    }

    @Test
    @DisplayName("T20 - Un pedido nuevo tiene 0 productos")
    void testPedidoNuevoSinProductos() {
        assertEquals(0, pedido.getProductos().size());
    }

    // ── TESTS NEGATIVOS ──────────────────────────────────────────────────────

    @Test
    @DisplayName("T21 - calcularTotal no devuelve 0 si hay productos")
    void testTotalNoEsCeroConProductos() {
        pedido.agregarProducto(new ProductoFisico("Monitor", 200.0, 10.0));
        assertNotEquals(0.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("T22 - calcularTotal no devuelve negativo")
    void testTotalNoEsNegativo() {
        pedido.agregarProducto(new ProductoFisico("Teclado", 50.0, 5.0));
        assertFalse(pedido.calcularTotal() < 0);
    }

    @Test
    @DisplayName("T23 - Pedido sin cliente lanza NullPointerException")
    void testPedidoSinClienteLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> {
            new Pedido(null);
        });
    }

    @Test
    @DisplayName("T24 - El total de un pedido vacio es 0")
    void testTotalPedidoVacioEsCero() {
        assertEquals(0.0, pedido.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("T25 - Agregar producto nulo lanza NullPointerException")
    void testAgregarProductoNuloLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> {
            pedido.agregarProducto(null);
        });
    }

    // ── TESTS PARAMETRIZADOS ─────────────────────────────────────────────────

    @ParameterizedTest
    @DisplayName("T26 - calcularTotal con distintos productos fisicos")
    @CsvSource({
        "100.0, 5.0, 126.0",
        "50.0,  3.0, 63.5",
        "200.0, 10.0, 252.0",
        "30.0,  0.0, 36.3",
        "75.0,  7.5, 98.25"
    })
    void testCalcularTotalParametrizadoFisico(double precio, double envio, double esperado) {
        Pedido p = new Pedido(cliente);
        p.agregarProducto(new ProductoFisico("Producto", precio, envio));
        assertEquals(esperado, p.calcularTotal(), 0.01);
    }

    @ParameterizedTest
    @DisplayName("T27 - calcularTotal con distintos productos digitales")
    @CsvSource({
        "100.0, 90.0",
        "200.0, 180.0",
        "50.0,  45.0",
        "39.99, 35.991",
        "149.99, 134.991"
    })
    void testCalcularTotalParametrizadoDigital(double precio, double esperado) {
        Pedido p = new Pedido(cliente);
        p.agregarProducto(new ProductoDigital("Producto", precio, "Licencia"));
        assertEquals(esperado, p.calcularTotal(), 0.01);
    }

    @Test
    @DisplayName("T34 - getFecha devuelve la fecha de hoy")
    void testGetFecha() {
    assertNotNull(pedido.getFecha());
    }

    @Test
    @DisplayName("T35 - getNumeroPedido devuelve un numero mayor que 0")
    void testGetNumeroPedido() {
    assertTrue(pedido.getNumeroPedido() > 0);
    }

    @Test
    @DisplayName("T33 - mostrarResumen no lanza ninguna excepcion")
    void testMostrarResumenNoLanzaExcepcion() {
    pedido.agregarProducto(new ProductoFisico("Teclado", 50.0, 5.0));
    pedido.agregarProducto(new ProductoDigital("Office", 100.0, "Permanente"));
    assertDoesNotThrow(() -> pedido.mostrarResumen());
    }

    @Test
    @DisplayName("T36 - getProductos devuelve la lista correcta")
    void testGetProductos() {
    ProductoFisico p = new ProductoFisico("Teclado", 50.0, 5.0);
    pedido.agregarProducto(p);
    assertTrue(pedido.getProductos().contains(p));
    }

    @Test
    @DisplayName("T37 - calcularTotal mezcla fisicos y digitales correctamente")
    void testCalcularTotalMixto() {
    pedido.agregarProducto(new ProductoFisico("Teclado", 100.0, 5.0));
    pedido.agregarProducto(new ProductoDigital("Office", 100.0, "Permanente"));
    // 126.0 + 90.0 = 216.0
    assertEquals(216.0, pedido.calcularTotal(), 0.01);
    }

}
