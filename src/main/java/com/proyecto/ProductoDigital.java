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

    public double aplicarIVA(String tipoIva) {
        double porcentaje;
        switch (tipoIva) {
            case "GENERAL":
                porcentaje = 0.21;
                break;
            case "REDUCIDO":
                porcentaje = 0.10;
                break;
            case "SUPER":
                porcentaje = 0.04;
                break;
            default:
                throw new IllegalArgumentException("Tipo de IVA no reconocido: " + tipoIva);
        }
        return precio * (1 + porcentaje);
    }

    @Override
    public String toString() {
        return nombre + " (digital) - " + precio + "€ (10% descuento) | Licencia: " + licencia;
    }
}