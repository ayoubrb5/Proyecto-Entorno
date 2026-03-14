package com.proyecto;

import java.util.ArrayList;
import java.time.LocalDate;

public class Pedido {

    private int numeroPedido;
    private LocalDate fecha;
    private Cliente cliente;
    private ArrayList<Producto> productos;

    // Contador estático para generar IDs automáticos
    private static int contadorPedidos = 1;

    public Pedido(Cliente cliente) {
    if (cliente == null) {
        throw new NullPointerException("El pedido debe tener un cliente");
    }
    this.numeroPedido = contadorPedidos;
    contadorPedidos++;
    this.fecha = LocalDate.now();
    this.cliente = cliente;
    this.productos = new ArrayList<>();
}

public void agregarProducto(Producto p) {
    if (p == null) {
        throw new NullPointerException("El producto no puede ser null");
    }
    productos.add(p);
}

    // Suma el precio final de todos los productos
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.calcularPrecioFinal();
        }
        return total;
    }

    // Muestra toda la información del pedido por pantalla
    public void mostrarResumen() {
        System.out.println("====================================");
        System.out.println("        RESUMEN DEL PEDIDO #" + numeroPedido);
        System.out.println("        Fecha: " + fecha);
        System.out.println("====================================");
        System.out.println(cliente);
        System.out.println("------------------------------------");
        System.out.println("Productos comprados:");

        for (Producto p : productos) {
            System.out.println("  - " + p);
            System.out.println("    Precio final: " + String.format("%.2f", p.calcularPrecioFinal()) + "€");
        }

        System.out.println("------------------------------------");
        System.out.println("TOTAL A PAGAR: " + String.format("%.2f", calcularTotal()) + "€");
        System.out.println("====================================");
    }

    // Getters
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}