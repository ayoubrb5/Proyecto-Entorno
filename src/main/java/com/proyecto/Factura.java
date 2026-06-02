package com.proyecto;

import java.time.LocalDate;

public class Factura {

    private static int contadorFacturas = 1;

    private String codigoFactura;
    private LocalDate fechaEmision;
    private double totalNeto;
    private double totalDescuento;
    private double totalIva;
    private double totalEnvio;
    private double totalFinal;

    public Factura(double totalNeto, double totalDescuento, double totalIva, double totalEnvio, double totalFinal) {
        this.codigoFactura = "FAC-" + String.format("%03d", contadorFacturas);
        contadorFacturas++;
        this.fechaEmision = LocalDate.now();
        this.totalNeto = totalNeto;
        this.totalDescuento = totalDescuento;
        this.totalIva = totalIva;
        this.totalEnvio = totalEnvio;
        this.totalFinal = totalFinal;
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

    // Getters
    public String getCodigoFactura() {
        return codigoFactura;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public double getTotalEnvio() {
        return totalEnvio;
    }

    public double getTotalFinal() {
        return totalFinal;
    }
}
