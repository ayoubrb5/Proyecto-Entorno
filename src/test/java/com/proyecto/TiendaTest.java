package com.proyecto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {

    private Tienda tienda;
    private Cliente clienteNormal;
    private Cliente clienteVip;
    private Cliente clienteAntiguo;

    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        // 1 año, sin VIP, España → sin descuento
        clienteNormal  = new Cliente("C001", "Juan Lopez",  "juan@test.com",  "Calle A 1", 1, false, "España");
        // 5 años, VIP, España → descuento 15%
        clienteVip     = new Cliente("C002", "Maria Ruiz",  "maria@test.com", "Calle B 2", 5, true,  "España");
        // 5 años, sin VIP, España → descuento 10%
        clienteAntiguo = new Cliente("C003", "Pedro Gil",   "pedro@test.com", "Calle C 3", 5, false, "España");
    }

    // ── PRUEBAS UNITARIAS ────────────────────────────────────────────────────

    @Test
    @DisplayName("T55 - realizarVenta devuelve una Factura no nula")
    void testRealizarVentaDevuelveFactura() {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Raton", 30.0, 0.0));
        Factura factura = tienda.realizarVenta(clienteNormal, pedido);
        assertNotNull(factura);
    }

    @Test
    @DisplayName("T56 - totalNeto es la suma de los precios base de todos los productos")
    void testTotalNetoEsSumaPrecios() {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Raton", 30.0, 0.0));
        pedido.agregarProducto(new ProductoDigital("Office", 70.0, "Anual"));
        Factura factura = tienda.realizarVenta(clienteNormal, pedido);
        assertEquals(100.0, factura.getTotalNeto(), 0.01); // 30 + 70
    }

    @Test
    @DisplayName("T57 - cliente VIP recibe exactamente un 15% de descuento")
    void testDescuentoClienteVip() {
        Pedido pedido = new Pedido(clienteVip);
        pedido.agregarProducto(new ProductoFisico("Monitor", 100.0, 0.0));
        Factura factura = tienda.realizarVenta(clienteVip, pedido);
        assertEquals(15.0, factura.getTotalDescuento(), 0.01); // 100 * 15%
    }

    @Test
    @DisplayName("T58 - cliente con más de 3 años recibe exactamente un 10% de descuento")
    void testDescuentoClienteAntiguo() {
        Pedido pedido = new Pedido(clienteAntiguo);
        pedido.agregarProducto(new ProductoFisico("Teclado", 100.0, 0.0));
        Factura factura = tienda.realizarVenta(clienteAntiguo, pedido);
        assertEquals(10.0, factura.getTotalDescuento(), 0.01); // 100 * 10%
    }

    @Test
    @DisplayName("T59 - cliente sin VIP ni suficiente antigüedad no recibe descuento")
    void testSinDescuento() {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Auriculares", 50.0, 0.0));
        Factura factura = tienda.realizarVenta(clienteNormal, pedido);
        assertEquals(0.0, factura.getTotalDescuento(), 0.01);
    }

    @Test
    @DisplayName("T60 - el IVA se calcula únicamente sobre los productos físicos")
    void testIvaSoloEnFisicos() {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Teclado", 100.0, 0.0));
        pedido.agregarProducto(new ProductoDigital("Antivirus", 50.0, "Anual")); // no genera IVA
        Factura factura = tienda.realizarVenta(clienteNormal, pedido);
        assertEquals(21.0, factura.getTotalIva(), 0.01); // solo 100 * 21%
    }

    // ── PRUEBAS DE EXCEPCIONES ───────────────────────────────────────────────

    @Test
    @DisplayName("T61 - pedido sin productos lanza IllegalStateException")
    void testPedidoVacioLanzaExcepcion() {
        Pedido pedido = new Pedido(clienteNormal);
        assertThrows(IllegalStateException.class, () -> {
            tienda.realizarVenta(clienteNormal, pedido);
        });
    }

    @Test
    @DisplayName("T62 - cliente null lanza NullPointerException")
    void testClienteNuloLanzaExcepcion() {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Raton", 30.0, 0.0));
        assertThrows(NullPointerException.class, () -> {
            tienda.realizarVenta(null, pedido);
        });
    }

    @Test
    @DisplayName("T63 - pedido null lanza NullPointerException")
    void testPedidoNuloLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> {
            tienda.realizarVenta(clienteNormal, null);
        });
    }

    // ── PRUEBAS PARAMETRIZADAS ───────────────────────────────────────────────

    @ParameterizedTest
    @DisplayName("T64 - totalEnvio calculado correctamente según el país del cliente")
    @CsvSource({
        "España,    0.0",
        "Francia,   5.0",
        "Italia,    5.0",
        "Portugal,  5.0",
        "Alemania, 10.0",
        "EEUU,     10.0"
    })
    void testEnvioSegunPais(String pais, double envioEsperado) {
        Cliente clientePais = new Cliente("C099", "Test User", "test@test.com", "Calle Z 0", 0, false, pais);
        Pedido pedido = new Pedido(clientePais);
        pedido.agregarProducto(new ProductoFisico("Producto", 100.0, 0.0));
        Factura factura = tienda.realizarVenta(clientePais, pedido);
        assertEquals(envioEsperado, factura.getTotalEnvio(), 0.01);
    }

    @ParameterizedTest
    @DisplayName("T65 - totalFinal correcto con distintos precios base (sin descuento, España)")
    @CsvSource({
        " 50.0,  60.50",   //  50 + 50*0.21 + 0 = 60.5
        "200.0, 242.00",   // 200 + 42      + 0 = 242
        " 75.0,  90.75",   //  75 + 15.75   + 0 = 90.75
        "100.0, 121.00",   // 100 + 21      + 0 = 121
        " 30.0,  36.30"    //  30 + 6.3     + 0 = 36.3
    })
    void testTotalFinalParametrizado(double precio, double totalEsperado) {
        Pedido pedido = new Pedido(clienteNormal);
        pedido.agregarProducto(new ProductoFisico("Producto", precio, 0.0));
        Factura factura = tienda.realizarVenta(clienteNormal, pedido);
        assertEquals(totalEsperado, factura.getTotalFinal(), 0.01);
    }

    // ── PRUEBAS DE INTEGRACIÓN (flujo completo Cliente→Pedido→Tienda→Factura) ─

    @Test
    @DisplayName("INT-01 - Cliente VIP en España: descuento 15%, IVA físico, envío gratis")
    void testIntegracionClienteVipEspana() {
        // totalNeto=180, descuento=27(15%), IVA=21, envío=0 → totalFinal=174
        Cliente cliente = new Cliente("I001", "Ana Garcia", "ana@test.com", "Gran Via 1", 5, true, "España");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new ProductoFisico("Teclado", 100.0, 5.0));
        pedido.agregarProducto(new ProductoDigital("Office", 80.0, "Permanente"));

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertNotNull(factura);
        assertTrue(factura.getCodigoFactura().startsWith("FAC-"));
        assertEquals(180.0, factura.getTotalNeto(),      0.01);
        assertEquals(27.0,  factura.getTotalDescuento(), 0.01);
        assertEquals(21.0,  factura.getTotalIva(),       0.01);
        assertEquals(0.0,   factura.getTotalEnvio(),     0.01);
        assertEquals(174.0, factura.getTotalFinal(),     0.01);
    }

    @Test
    @DisplayName("INT-02 - Cliente con antigüedad en Francia: descuento 10%, envío Europa")
    void testIntegracionClienteAntiguoFrancia() {
        // totalNeto=250, descuento=25(10%), IVA=52.5, envío=10 → totalFinal=287.5
        Cliente cliente = new Cliente("I002", "Pierre Dupont", "pierre@test.com", "Rue 5", 4, false, "Francia");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new ProductoFisico("Monitor", 200.0, 0.0));
        pedido.agregarProducto(new ProductoFisico("Webcam",   50.0, 0.0));

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertEquals(250.0, factura.getTotalNeto(),      0.01);
        assertEquals(25.0,  factura.getTotalDescuento(), 0.01);
        assertEquals(52.5,  factura.getTotalIva(),       0.01);
        assertEquals(10.0,  factura.getTotalEnvio(),     0.01);
        assertEquals(287.5, factura.getTotalFinal(),     0.01);
    }

    @Test
    @DisplayName("INT-03 - Cliente normal en Alemania: sin descuento, envío internacional")
    void testIntegracionClienteNormalAlemania() {
        // totalNeto=30, descuento=0, IVA=6.3, envío=10 → totalFinal=46.3
        Cliente cliente = new Cliente("I003", "Hans Muller", "hans@test.com", "Berlinerstr 7", 1, false, "Alemania");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new ProductoFisico("Raton", 30.0, 0.0));

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertEquals(30.0, factura.getTotalNeto(),      0.01);
        assertEquals(0.0,  factura.getTotalDescuento(), 0.01);
        assertEquals(6.3,  factura.getTotalIva(),       0.01);
        assertEquals(10.0, factura.getTotalEnvio(),     0.01);
        assertEquals(46.3, factura.getTotalFinal(),     0.01);
    }

    @Test
    @DisplayName("INT-04 - Pedido solo con digitales: sin IVA ni envío")
    void testIntegracionSoloProductosDigitales() {
        // totalNeto=180, descuento=0, IVA=0, envío=0 → totalFinal=180
        Cliente cliente = new Cliente("I004", "Laura Perez", "laura@test.com", "Calle Luna 3", 0, false, "España");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new ProductoDigital("Antivirus", 120.0, "Anual"));
        pedido.agregarProducto(new ProductoDigital("Office",     60.0, "Permanente"));

        Factura factura = tienda.realizarVenta(cliente, pedido);

        assertEquals(180.0, factura.getTotalNeto(),      0.01);
        assertEquals(0.0,   factura.getTotalDescuento(), 0.01);
        assertEquals(0.0,   factura.getTotalIva(),       0.01);
        assertEquals(0.0,   factura.getTotalEnvio(),     0.01);
        assertEquals(180.0, factura.getTotalFinal(),     0.01);
    }

    // REGRESIÓN: T57/T58/T59 garantizan que los tres tramos de descuento
    // (VIP, antigüedad, ninguno) funcionan independientemente del país
}
