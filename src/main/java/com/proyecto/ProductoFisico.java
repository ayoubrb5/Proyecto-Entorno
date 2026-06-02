package com.proyecto;

public class ProductoFisico extends Producto {

    public static final double TASA_IVA = 0.21;
    public static final double COSTE_ENVIO_EUROPA = 5.0;
    public static final double COSTE_ENVIO_INTERNACIONAL = 10.0;

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

    public double getCosteEnvio() {
        return costeEnvio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        this.peso = peso;
    }

    /**
     * Calcula el coste de envío según el país del cliente.
     * @param pais país del cliente
     * @return
     */
    public double costeEnvio(String pais) {
        if (pais.equals("España")) {
            return 0;
        } else if (pais.equals("Francia") || pais.equals("Italia") || pais.equals("Portugal")) {
            return COSTE_ENVIO_EUROPA;
        } else {
            return COSTE_ENVIO_INTERNACIONAL;
        }
    }

    @Override
    public double calcularPrecioFinal() {
        return precio * (1 + TASA_IVA) + costeEnvio;
    }

    @Override
    public String toString() {
        return nombre + " (físico) - " + precio + "€ + envío " + costeEnvio + "€";
    }
}
