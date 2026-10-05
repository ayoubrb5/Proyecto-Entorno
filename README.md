# Proyecto-Entorno

Un pequeño sistema de pedidos para una tienda, hecho en Java. Sirve para crear clientes, productos, pedidos y facturas, y calcula solo el IVA, el envío y los descuentos.

## ¿Qué hace?

- Tiene dos tipos de producto: **físicos** (llevan IVA y envío) y **digitales** (llevan un descuento).
- Cada cliente hace **pedidos** con los productos que quiere comprar.
- La **tienda** convierte un pedido en una **factura** con el desglose de lo que hay que pagar.

Los descuentos funcionan así: un cliente VIP tiene un 15% y uno con más de 3 años de antigüedad un 10%. No se suman, se aplica el VIP.

## Qué necesitas

- Java 17 o superior
- Maven
- Git

## Cómo ponerlo en marcha

1. Descarga el proyecto:

   ```bash
   git clone https://github.com/ayoubrb5/Proyecto-Entorno.git
   cd Proyecto-Entorno
   ```

2. Compílalo:

   ```bash
   mvn compile
   ```

3. Lanza los tests:

   ```bash
   mvn test
   ```

4. Ejecútalo:

   ```bash
   java -cp target/classes com.proyecto.Main
   ```

Si usas VS Code, también puedes abrir `Main.java` y darle a **Run**.

## ¿Quieres colaborar?

Échale un vistazo a [CONTRIBUTING.md](CONTRIBUTING.md), ahí explico cómo trabajar con el repositorio.


Ayoub te cambio esto por que me da la gana pringao