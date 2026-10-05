package com.proyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.time.LocalDate;

/**
 * Representa un pedido de un cliente con sus productos y cantidades.
 */
public class Pedido {

    private static int contadorPedidos = 1;

    private int idPedido;
    private LocalDate fecha;
    private Cliente cliente;
    private LinkedHashMap<Producto, Integer> cantidades;

    /**
     * Crea un pedido vacío para un cliente.
     *
     * @param cliente cliente del pedido
     * @throws NullPointerException si el cliente es null
     */
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

    /**
     * Agrega un producto al pedido con cantidad 1.
     *
     * @param producto producto a agregar
     * @throws NullPointerException si el producto es null
     */
    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new NullPointerException("El producto no puede ser null");
        }
        cantidades.put(producto, 1);
    }

    /**
     * Agrega un producto al pedido con una cantidad.
     *
     * @param producto producto a agregar
     * @param cantidad cantidad (mayor que 0)
     * @throws NullPointerException     si el producto es null
     * @throws IllegalArgumentException si la cantidad no es mayor que 0
     */
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new NullPointerException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        cantidades.put(producto, cantidad);
    }

    /**
     * Elimina un producto del pedido.
     *
     * @param producto producto a eliminar
     */
    public void eliminarProducto(Producto producto) {
        cantidades.remove(producto);
    }

    /**
     * Calcula el total del pedido.
     *
     * @return total a pagar
     * @throws IllegalStateException si el pedido no tiene productos
     */
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

    /**
     * Muestra por consola el resumen del pedido.
     *
     * @throws IllegalStateException si el pedido no tiene productos
     */
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

    /**
     * Devuelve el id del pedido.
     *
     * @return id del pedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Devuelve el número del pedido.
     *
     * @return número del pedido
     */
    public int getNumeroPedido() {
        return idPedido;
    }

    /**
     * Devuelve la fecha del pedido.
     *
     * @return fecha del pedido
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Devuelve el cliente del pedido.
     *
     * @return cliente del pedido
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve la lista de productos del pedido.
     *
     * @return lista de productos
     */
    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(cantidades.keySet());
    }

    /**
     * Devuelve los productos con sus cantidades.
     *
     * @return mapa de producto a cantidad (solo lectura)
     */
    public Map<Producto, Integer> getCantidades() {
        return Collections.unmodifiableMap(cantidades);
    }
}
