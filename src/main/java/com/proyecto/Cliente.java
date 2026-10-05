package com.proyecto;

/**
 * Representa a un cliente de la tienda.
 */
public class Cliente {

    private String id;
    private String nombre;
    private String email;
    private String direccion;
    private int añosAntiguedad;
    private boolean esVip;
    private String pais;

    /**
     * Crea un cliente con todos sus datos.
     *
     * @param id             identificador
     * @param nombre         nombre
     * @param email          correo electrónico
     * @param direccion      dirección
     * @param añosAntiguedad años de antigüedad
     * @param esVip          si es VIP
     * @param pais           país
     */
    public Cliente(String id, String nombre, String email, String direccion, int añosAntiguedad, boolean esVip, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.añosAntiguedad = añosAntiguedad;
        this.esVip = esVip;
        this.pais = pais;
    }

    /**
     * Crea un cliente solo con los datos básicos.
     *
     * @param nombre    nombre
     * @param email     correo electrónico
     * @param direccion dirección
     */
    public Cliente(String nombre, String email, String direccion) {
        this("", nombre, email, direccion, 0, false, "");
    }

    // Getters

    /**
     * Devuelve el id.
     *
     * @return id del cliente
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre.
     *
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el email.
     *
     * @return email del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Devuelve la dirección.
     *
     * @return dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve los años de antigüedad.
     *
     * @return años de antigüedad
     */
    public int getAñosAntiguedad() {
        return añosAntiguedad;
    }

    /**
     * Indica si el cliente es VIP.
     *
     * @return true si es VIP
     */
    public boolean isVip() {
        return esVip;
    }

    /**
     * Devuelve el país.
     *
     * @return país del cliente
     */
    public String getPais() {
        return pais;
    }

    // Setters

    /**
     * Establece el id.
     *
     * @param id nuevo id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Establece el nombre.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el email.
     *
     * @param email nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece la dirección.
     *
     * @param direccion nueva dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Establece los años de antigüedad.
     *
     * @param añosAntiguedad nuevos años de antigüedad
     */
    public void setAñosAntiguedad(int añosAntiguedad) {
        this.añosAntiguedad = añosAntiguedad;
    }

    /**
     * Establece si el cliente es VIP.
     *
     * @param esVip true si es VIP
     */
    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    /**
     * Establece el país.
     *
     * @param pais nuevo país
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Devuelve el cliente como texto.
     *
     * @return datos del cliente
     */
    @Override
    public String toString() {
        return "Cliente: " + nombre + "\nEmail: " + email + "\nDirección: " + direccion +
               "\nPaís: " + pais + "\nAntiguedad: " + añosAntiguedad + " años\nVIP: " + esVip;
    }
}
