package com.proyecto;

/**
 * Producto digital, con descuento y licencia.
 */
public class ProductoDigital extends Producto {

    private static final double DESCUENTO_DIGITAL = 0.10;
    private static final double IVA_GENERAL       = 0.21;
    private static final double IVA_REDUCIDO      = 0.10;
    private static final double IVA_SUPER         = 0.04;

    private String licencia;

    /**
     * Crea un producto digital.
     *
     * @param nombre     nombre del producto
     * @param precioBase precio base (no negativo)
     * @param licencia   tipo de licencia
     * @throws IllegalArgumentException si el precio es negativo
     */
    public ProductoDigital(String nombre, double precioBase, String licencia) {
        super(nombre, precioBase);
        this.licencia = licencia;
    }

    /**
     * Devuelve la licencia del producto.
     *
     * @return licencia del producto
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * Calcula el precio final con el descuento digital.
     *
     * @return precio final
     */
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * (1 - DESCUENTO_DIGITAL);
    }

    /**
     * Aplica un tipo de IVA al precio base.
     *
     * @param tipoIva "GENERAL", "REDUCIDO" o "SUPER"
     * @return precio base con IVA
     * @throws IllegalArgumentException si el tipo de IVA no es válido
     */
    public double aplicarIVA(String tipoIva) {
        double porcentaje;
        switch (tipoIva) {
            case "GENERAL":
                porcentaje = IVA_GENERAL;
                break;
            case "REDUCIDO":
                porcentaje = IVA_REDUCIDO;
                break;
            case "SUPER":
                porcentaje = IVA_SUPER;
                break;
            default:
                throw new IllegalArgumentException("Tipo de IVA no reconocido: " + tipoIva);
        }
        return getPrecioBase() * (1 + porcentaje);
    }

    /**
     * Devuelve el producto como texto.
     *
     * @return descripción del producto
     */
    @Override
    public String toString() {
        int descuentoPct = (int) (DESCUENTO_DIGITAL * 100);
        return getNombre() + " (digital) - " + getPrecioBase() + "€ (" + descuentoPct
                + "% descuento) | Licencia: " + licencia;
    }
}
