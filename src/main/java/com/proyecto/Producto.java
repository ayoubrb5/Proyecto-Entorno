package com.proyecto;

public abstract class Producto {

    private static int contadorProductos = 1;

    private String id;
    private String nombre;
    private double precioBase;

    public Producto(String nombre, double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.id         = "PROD-" + String.format("%03d", contadorProductos);
        contadorProductos++;
        this.nombre     = nombre;
        this.precioBase = precioBase;
    }

    public abstract double calcularPrecioFinal();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioBase = precioBase;
    }

    @Override
    public String toString() {
        return nombre + " - " + precioBase + "€";
    }
}
