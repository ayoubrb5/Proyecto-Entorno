package com.proyecto;

/**
 * Producto físico, con IVA y coste de envío.
 */
public class ProductoFisico extends Producto {

    public static final double TASA_IVA                  = 0.21;
    public static final double COSTE_ENVIO_EUROPA        = 5.0;
    public static final double COSTE_ENVIO_INTERNACIONAL = 10.0;

    private double costeEnvioFijo;
    private double peso;

    /**
     * Crea un producto físico.
     *
     * @param nombre         nombre del producto
     * @param precioBase     precio base (no negativo)
     * @param costeEnvioFijo coste de envío fijo (no negativo)
     * @throws IllegalArgumentException si el precio o el envío son negativos
     */
    public ProductoFisico(String nombre, double precioBase, double costeEnvioFijo) {
        super(nombre, precioBase);
        if (costeEnvioFijo < 0) {
            throw new IllegalArgumentException("El coste de envio no puede ser negativo");
        }
        this.costeEnvioFijo = costeEnvioFijo;
        this.peso           = 0;
    }

    /**
     * Devuelve el coste de envío fijo.
     *
     * @return coste de envío fijo
     */
    public double getCosteEnvio() {
        return costeEnvioFijo;
    }

    /**
     * Devuelve el peso del producto.
     *
     * @return peso del producto
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del producto.
     *
     * @param peso nuevo peso (no negativo)
     * @throws IllegalArgumentException si el peso es negativo
     */
    public void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo");
        }
        this.peso = peso;
    }

    /**
     * Calcula el coste de envío según el país de destino.
     *
     * @param pais país de destino
     * @return coste de envío
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

    /**
     * Calcula el precio final con IVA y envío.
     *
     * @return precio final
     */
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * (1 + TASA_IVA) + costeEnvioFijo;
    }

    /**
     * Devuelve el producto como texto.
     *
     * @return descripción del producto
     */
    @Override
    public String toString() {
        return getNombre() + " (físico) - " + getPrecioBase() + "€ + envío " + costeEnvioFijo + "€";
    }
}
