package com.proyecto;

public class ProductoDigital extends Producto {

    private static final double DESCUENTO_DIGITAL = 0.10;
    private static final double IVA_GENERAL       = 0.21;
    private static final double IVA_REDUCIDO      = 0.10;
    private static final double IVA_SUPER         = 0.04;

    private String licencia;

    public ProductoDigital(String nombre, double precioBase, String licencia) {
        super(nombre, precioBase);
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase * (1 - DESCUENTO_DIGITAL);
    }

    /**
     * Devuelve el precio del producto con el tipo de IVA indicado aplicado.
     * @param tipoIva
     * @return 
     * @throws IllegalArgumentException 
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
        return precioBase * (1 + porcentaje);
    }

    @Override
    public String toString() {
        return nombre + " (digital) - " + precioBase + "€ (10% descuento) | Licencia: " + licencia;
    }
}
