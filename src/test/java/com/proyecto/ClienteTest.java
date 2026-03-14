package com.proyecto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("T28 - El nombre del cliente se guarda correctamente")
    void testGetNombre() {
        Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
        assertEquals("Maria", c.getNombre());
    }

    @Test
    @DisplayName("T29 - El email del cliente se guarda correctamente")
    void testGetEmail() {
        Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
        assertEquals("maria@gmail.com", c.getEmail());
    }

    @Test
    @DisplayName("T30 - La direccion del cliente se guarda correctamente")
    void testGetDireccion() {
        Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
        assertEquals("Calle Sol 25", c.getDireccion());
    }

    @Test
    @DisplayName("T31 - toString contiene el nombre del cliente")
    void testToStringContieneNombre() {
        Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
        assertTrue(c.toString().contains("Maria"));
    }

    @Test
    @DisplayName("T32 - toString contiene el email del cliente")
    void testToStringContieneEmail() {
        Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
        assertTrue(c.toString().contains("maria@gmail.com"));
    }

    @Test
    @DisplayName("T40 - toString contiene la direccion")
    void testToStringContieneDireccion() {
    Cliente c = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
    assertTrue(c.toString().contains("Calle Sol 25"));
    }

    @Test
    @DisplayName("T41 - dos clientes con mismo nombre son iguales en nombre")
    void testDosClientesMismoNombre() {
    Cliente c1 = new Cliente("Maria", "maria@gmail.com", "Calle Sol 25");
    Cliente c2 = new Cliente("Maria", "otro@gmail.com", "Otra calle");
    assertEquals(c1.getNombre(), c2.getNombre());
    }
    
}