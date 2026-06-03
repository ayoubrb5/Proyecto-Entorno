package com.proyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.time.LocalDate;

public class Pedido {

    private static int contadorPedidos = 1;

    private int idPedido;
    private LocalDate fecha;
    private Cliente cliente;
    private LinkedHashMap<Producto, Integer> cantidades;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El pedido debe tener un cliente");
        }
        this.idPedido   = contadorPedidos;
        contadorPedidos++;
        this.fecha      = LocalDate.now();
        this.cliente    = cliente;
        this.cantidades = new LinkedHashMap<>();
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new NullPointerException("El producto no puede ser null");
        }
        cantidades.put(producto, 1);
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new NullPointerException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        cantidades.put(producto, cantidad);
    }

    public void eliminarProducto(Producto producto) {
        cantidades.remove(producto);
    }

    public double calcularTotal() {
        if (cantidades.isEmpty()) {
            throw new IllegalStateException("El pedido no tiene productos");
        }
        double total = 0;
        for (Map.Entry<Producto, Integer> entrada : cantidades.entrySet()) {
            total += entrada.getKey().calcularPrecioFinal() * entrada.getValue();
        }
        return total;
    }

    public void mostrarResumen() {
        System.out.println("====================================");
        System.out.println("        RESUMEN DEL PEDIDO #" + idPedido);
        System.out.println("        Fecha: " + fecha);
        System.out.println("====================================");
        System.out.println(cliente);
        System.out.println("------------------------------------");
        System.out.println("Productos comprados:");

        for (Map.Entry<Producto, Integer> entrada : cantidades.entrySet()) {
            Producto producto = entrada.getKey();
            int cantidad      = entrada.getValue();
            System.out.println("  - " + producto + " x" + cantidad);
            System.out.println("    Precio final: "
                    + String.format("%.2f", producto.calcularPrecioFinal() * cantidad) + "€");
        }

        System.out.println("------------------------------------");
        System.out.println("TOTAL A PAGAR: " + String.format("%.2f", calcularTotal()) + "€");
        System.out.println("====================================");
    }

    // Getters
    public int getIdPedido() {
        return idPedido;
    }

    public int getNumeroPedido() {
        return idPedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(cantidades.keySet());
    }

    public Map<Producto, Integer> getCantidades() {
        return Collections.unmodifiableMap(cantidades);
    }
}
