package com.proyecto;

import java.time.LocalDate;

/**
 * Representa la factura de una venta con el desglose de importes.
 */
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
     * Crea una factura con los importes indicados.
     *
     * @param totalNeto      total neto
     * @param totalDescuento total del descuento
     * @param totalIva       total del IVA
     * @param totalEnvio     total del envío
     * @param totalFinal     total final a pagar
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

    /**
     * Muestra por consola el desglose de la factura.
     */
    public void mostrarDesglose() {
        System.out.println("========================================");
        System.out.println("  FACTURA: " + codigoFactura);
        System.out.println("  Fecha:   " + fechaEmision);
        System.out.println("========================================");
        System.out.printf("  Base neta:        %8.2f€%n", totalNeto);
        if (totalDescuento > 0) {
            System.out.printf("  Descuento:       -%8.2f€%n", totalDescuento);
        }
        System.out.printf("  IVA:              %8.2f€%n", totalIva);
        System.out.printf("  Gastos de envío:  %8.2f€%n", totalEnvio);
        System.out.println("----------------------------------------");
        System.out.printf("  TOTAL A PAGAR:    %8.2f€%n", totalFinal);
        System.out.println("========================================");
    }

    /**
     * Devuelve el código de la factura.
     *
     * @return código de factura
     */
    public String getCodigoFactura() {
        return codigoFactura;
    }

    /**
     * Devuelve la fecha de emisión.
     *
     * @return fecha de emisión
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Devuelve el total neto.
     *
     * @return total neto
     */
    public double getTotalNeto() {
        return totalNeto;
    }

    /**
     * Devuelve el total del descuento.
     *
     * @return total del descuento
     */
    public double getTotalDescuento() {
        return totalDescuento;
    }

    /**
     * Devuelve el total del IVA.
     *
     * @return total del IVA
     */
    public double getTotalIva() {
        return totalIva;
    }

    /**
     * Devuelve el total del envío.
     *
     * @return total del envío
     */
    public double getTotalEnvio() {
        return totalEnvio;
    }

    /**
     * Devuelve el total final.
     *
     * @return total final a pagar
     */
    public double getTotalFinal() {
        return totalFinal;
    }
}
