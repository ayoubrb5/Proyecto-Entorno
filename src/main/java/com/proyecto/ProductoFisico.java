package com.proyecto;

public class ProductoFisico extends Producto {

    public static final double TASA_IVA                  = 0.21;
    public static final double COSTE_ENVIO_EUROPA        = 5.0;
    public static final double COSTE_ENVIO_INTERNACIONAL = 10.0;

    private double costeEnvioFijo;
    private double peso;

    public ProductoFisico(String nombre, double precioBase, double costeEnvioFijo) {
        super(nombre, precioBase);
        if (costeEnvioFijo < 0) {
            throw new IllegalArgumentException("El coste de envio no puede ser negativo");
        }
        this.costeEnvioFijo = costeEnvioFijo;
        this.peso           = 0;
    }

    public double getCosteEnvio() {
        return costeEnvioFijo;
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
     * España: gratuito; Francia, Italia y Portugal: tarifa europea;
     * resto del mundo: tarifa internacional.
     *
     * @param pais país de destino del envío
     * @return coste de envío en euros, {@value #COSTE_ENVIO_EUROPA} o {@value #COSTE_ENVIO_INTERNACIONAL}
     */
    public double calcularCosteEnvio(String pais) {
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
        return getPrecioBase() * (1 + TASA_IVA) + costeEnvioFijo;
    }

    @Override
    public String toString() {
        return getNombre() + " (físico) - " + getPrecioBase() + "€ + envío " + costeEnvioFijo + "€";
    }
}
