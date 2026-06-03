package com.proyecto;

import java.util.Map;


public class Tienda {

    private static final double DESCUENTO_VIP           = 0.15;
    private static final double DESCUENTO_ANTIGUEDAD    = 0.10;
    private static final int    ANIOS_MINIMOS_DESCUENTO = 3;

    /**
     * Procesa un pedido para un cliente y genera la factura correspondiente.
     * Aplica un descuento si el cliente es VIP (15% sobre la base neta), o si tiene
     * años de antigüedad (10% sobre la base neta).
     * El descuento VIP tiene prioridad sobre el de antigüedad. 
     *
     * @param cliente el cliente que realiza la compra
     * @param pedido  el pedido con los productos a facturar
     * @throws NullPointerException  
     * @throws IllegalStateException 
     */
    public Factura realizarVenta(Cliente cliente, Pedido pedido) {

        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser null");
        }
        if (pedido == null) {
            throw new NullPointerException("El pedido no puede ser null");
        }
        if (pedido.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede facturar un pedido sin productos");
        }

        double totalNeto  = 0;
        double totalIva   = 0;
        double totalEnvio = 0;

        for (Map.Entry<Producto, Integer> entrada : pedido.getCantidades().entrySet()) {
            Producto producto = entrada.getKey();
            int cantidad      = entrada.getValue();

            totalNeto += producto.getPrecioBase() * cantidad;

            if (producto instanceof ProductoFisico) {
                ProductoFisico productoFisico = (ProductoFisico) producto;
                totalIva   += productoFisico.getPrecioBase() * ProductoFisico.TASA_IVA * cantidad;
                totalEnvio += productoFisico.calcularCosteEnvio(cliente.getPais()) * cantidad;
            }
        }

        double descuento = 0;
        if (cliente.isVip()) {
            descuento = totalNeto * DESCUENTO_VIP;
        } else if (cliente.getAñosAntiguedad() > ANIOS_MINIMOS_DESCUENTO) {
            descuento = totalNeto * DESCUENTO_ANTIGUEDAD;
        }

        double totalFinal = (totalNeto - descuento) + totalIva + totalEnvio;

        return new Factura(totalNeto, descuento, totalIva, totalEnvio, totalFinal);
    }
}
