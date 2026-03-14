package com.proyecto;
 
public class ProductoFisico extends Producto {
 
    private double costeEnvio;
 
    public ProductoFisico(String nombre, double precio, double costeEnvio) {
    super(nombre, precio);
    if (costeEnvio < 0) {
        throw new IllegalArgumentException("El coste de envio no puede ser negativo");
    }
    this.costeEnvio = costeEnvio;
}
 
    // Getter
    public double getCosteEnvio() {
        return costeEnvio;
    }
 
    // Precio final: precio base + 21% IVA + coste de envío
    @Override
    public double calcularPrecioFinal() {
        return precio * 1.21 + costeEnvio;
    }
 
    @Override
    public String toString() {
        return nombre + " (físico) - " + precio + "€ + envío " + costeEnvio + "€";
    }
}