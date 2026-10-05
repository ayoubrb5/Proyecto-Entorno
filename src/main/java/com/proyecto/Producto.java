package com.proyecto;

/**
 * Clase base abstracta de los productos de la tienda.
 */
public abstract class Producto {

    private static int contadorProductos = 1;
    private String id;
    private String nombre;
    private double precioBase;

    /**
     * Crea un producto con un identificador automático.
     *
     * @param nombre     nombre del producto
     * @param precioBase precio base (no negativo)
     * @throws IllegalArgumentException si el precio es negativo
     */
    public Producto(String nombre, double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.id         = "PROD-" + String.format("%03d", contadorProductos);
        contadorProductos++;
        this.nombre     = nombre;
        this.precioBase = precioBase;
    }

    /**
     * Calcula el precio final del producto.
     *
     * @return precio final
     */
    public abstract double calcularPrecioFinal();

    /**
     * Devuelve el id del producto.
     *
     * @return id del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio base.
     *
     * @return precio base
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Modifica el precio base.
     *
     * @param precioBase nuevo precio (no negativo)
     * @throws IllegalArgumentException si el precio es negativo
     */
    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioBase = precioBase;
    }

    /**
     * Devuelve el producto como texto.
     *
     * @return nombre y precio base
     */
    @Override
    public String toString() {
        return nombre + " - " + precioBase + "€";
    }
}
