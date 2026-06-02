package com.proyecto;

public abstract class Producto {
 
    protected String nombre;
    protected double precio;
 
   public Producto(String nombre, double precio) {
    if (precio < 0) {
        throw new IllegalArgumentException("El precio no puede ser negativo");
    }
    this.nombre = nombre;
    this.precio = precio;
}
    // Método abstracto: cada subclase lo implementa a su manera
    public abstract double calcularPrecioFinal();
 
    // Getters
    public String getNombre() {
        return nombre;
    }
 
    public double getPrecio() {
        return precio;
    }
 
    @Override
    public String toString() {
        return nombre + " - " + precio + "€";
    }
}