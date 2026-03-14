package com.proyecto;

public class ProductoDigital extends Producto {

    private String licencia;

    public ProductoDigital(String nombre, double precio, String licencia) {
        super(nombre, precio);
        this.licencia = licencia;
    }

    // Getter
    public String getLicencia() {
        return licencia;
    }

    // Precio final: precio base con 10% de descuento
    @Override
    public double calcularPrecioFinal() {
        return precio * 0.90;
    }

    @Override
    public String toString() {
        return nombre + " (digital) - " + precio + "€ (10% descuento) | Licencia: " + licencia;
    }
}