package com.proyecto;

public class ProductoFisico extends Producto {

    private double costeEnvio;
    private double peso;

    public ProductoFisico(String nombre, double precio, double costeEnvio) {
        super(nombre, precio);
        if (costeEnvio < 0) {
            throw new IllegalArgumentException("El coste de envio no puede ser negativo");
        }
        this.costeEnvio = costeEnvio;
        this.peso = 0;
    }

    // Getter del coste de envío
    public double getCosteEnvio() {
        return costeEnvio;
    }

    // Getter y setter de peso
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        this.peso = peso;
    }

    // Calcula el coste de envío según el país del cliente
    public double costeEnvio(String pais) {
        if (pais.equals("España")) {
            return 0;
        } else if (pais.equals("Francia") || pais.equals("Italia") || pais.equals("Portugal")) {
            return 5;
        } else {
            return 10;
        }
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
