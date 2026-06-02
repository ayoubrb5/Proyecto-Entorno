package com.proyecto;

public class Tienda {

    public Factura realizarVenta(Cliente c, Pedido p) {

        if (c == null) {
            throw new NullPointerException("El cliente no puede ser null");
        }
        if (p == null) {
            throw new NullPointerException("El pedido no puede ser null");
        }
        if (p.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede facturar un pedido sin productos");
        }

        double totalNeto = 0;
        double totalIva = 0;
        double totalEnvio = 0;

        for (Producto producto : p.getProductos()) {
            totalNeto += producto.getPrecio();

            if (producto instanceof ProductoFisico) {
                ProductoFisico pf = (ProductoFisico) producto;
                totalIva += pf.getPrecio() * 0.21;
                totalEnvio += pf.costeEnvio(c.getPais());
            }
        }

        double descuento = 0;
        if (c.isEsVip()) {
            descuento = totalNeto * 0.15;
        } else if (c.getAñosAntiguedad() > 3) {
            descuento = totalNeto * 0.10;
        }

        double totalFinal = (totalNeto - descuento) + totalIva + totalEnvio;

        return new Factura(totalNeto, descuento, totalIva, totalEnvio, totalFinal);
    }
}
