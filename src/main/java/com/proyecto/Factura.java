package com.proyecto;

import java.time.LocalDate;


public class Factura {

    private static int contadorFacturas = 1;

    private String    codigoFactura;
    private LocalDate fechaEmision;
    private double    totalNeto;
    private double    totalDescuento;
    private double    totalIva;
    private double    totalEnvio;
    private double    totalFinal;

    /**
     * Crea una nueva factura con los importes
     * El código de factura se genera automáticamente
     * @param totalNeto 
     * @param totalDescuento 
     * @param totalIva       
     * @param totalEnvio     
     * @param totalFinal     
     */
    public Factura(double totalNeto, double totalDescuento, double totalIva,
                   double totalEnvio, double totalFinal) {
        this.codigoFactura  = "FAC-" + String.format("%03d", contadorFacturas);
        contadorFacturas++;
        this.fechaEmision   = LocalDate.now();
        this.totalNeto      = totalNeto;
        this.totalDescuento = totalDescuento;
        this.totalIva       = totalIva;
        this.totalEnvio     = totalEnvio;
        this.totalFinal     = totalFinal;
    }

    public void mostrarDesglose() {
        System.out.println("========================================");
        System.out.println("  FACTURA: " + codigoFactura);
        System.out.println("  Fecha:   " + fechaEmision);
        System.out.println("========================================");
        System.out.printf("  Base neta:        %8.2f€%n", totalNeto);
        if (totalDescuento > 0) {
            System.out.printf("  Descuento:       -%8.2f€%n", totalDescuento);
        }
        System.out.printf("  IVA (21%%):        %8.2f€%n", totalIva);
        System.out.printf("  Gastos de envío:  %8.2f€%n", totalEnvio);
        System.out.println("----------------------------------------");
        System.out.printf("  TOTAL A PAGAR:    %8.2f€%n", totalFinal);
        System.out.println("========================================");
    }

    /**
     * Devuelve el código único de la factura
     * @return código de factura
     */
    public String getCodigoFactura() {
        return codigoFactura;
    }

    /**
     * Devuelve la fecha en que se emitió la factura.
     * @return fecha de emisión
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Devuelve la suma de los precios base de todos los productos
     * @return total neto
     */
    public double getTotalNeto() {
        return totalNeto;
    }

    /**
     * Devuelve el importe descontado
     * @return descuento aplicado o 0 
     */
    public double getTotalDescuento() {
        return totalDescuento;
    }

    /**
     * Devuelve el importe total de IVA a los productos físicos.
     * @return total de IVA
     */
    public double getTotalIva() {
        return totalIva;
    }

    /**
     * Devuelve el coste total de envío calculado según el país del cliente.
     * @return total de gastos de envío
     */
    public double getTotalEnvio() {
        return totalEnvio;
    }

    /**
     * Devuelve el importe final que debe pagar el cliente.
     * @return total final a pagar
     */
    public double getTotalFinal() {
        return totalFinal;
    }
}
