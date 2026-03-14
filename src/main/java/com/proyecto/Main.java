package com.proyecto;
 
public class Main {
    public static void main(String[] args) {
 
        // ---- PEDIDO 1 ----
        Cliente cliente1 = new Cliente("María López", "maria@gmail.com", "Calle Sol 25, Valencia");
 
        Producto p1 = new ProductoFisico("Teclado gaming", 69.99, 6.99);
        Producto p2 = new ProductoFisico("Ratón inalámbrico", 34.50, 4.50);
        Producto p3 = new ProductoDigital("Microsoft Office 2024", 149.99, "Licencia permanente");
        Producto p4 = new ProductoDigital("Adobe Photoshop", 239.88, "Suscripción anual");
 
        Pedido pedido1 = new Pedido(cliente1);
        pedido1.agregarProducto(p1);
        pedido1.agregarProducto(p2);
        pedido1.agregarProducto(p3);
        pedido1.agregarProducto(p4);
 
        pedido1.mostrarResumen();
 
        System.out.println();
 
        // ---- PEDIDO 2 ----
        Cliente cliente2 = new Cliente("Carlos Ruiz", "carlos@hotmail.com", "Avenida Mar 10, Madrid");
 
        Producto p5 = new ProductoFisico("Monitor 27\"", 299.00, 12.00);
        Producto p6 = new ProductoDigital("Antivirus Pro", 39.99, "Suscripción anual");
 
        Pedido pedido2 = new Pedido(cliente2);
        pedido2.agregarProducto(p5);
        pedido2.agregarProducto(p6);
 
        pedido2.mostrarResumen();
    }
}