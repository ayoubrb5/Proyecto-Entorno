package com.proyecto;

public class Cliente {

    private String id;
    private String nombre;
    private String email;
    private String direccion;
    private int añosAntiguedad;
    private boolean esVip;
    private String pais;

    public Cliente(String id, String nombre, String email, String direccion, int añosAntiguedad, boolean esVip, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.añosAntiguedad = añosAntiguedad;
        this.esVip = esVip;
        this.pais = pais;
    }

    // Constructor 
    public Cliente(String nombre, String email, String direccion) {
        this("", nombre, email, direccion, 0, false, "");
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getAñosAntiguedad() {
        return añosAntiguedad;
    }

    public boolean isVip() {
        return esVip;
    }

    public String getPais() {
        return pais;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAñosAntiguedad(int añosAntiguedad) {
        this.añosAntiguedad = añosAntiguedad;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + "\nEmail: " + email + "\nDirección: " + direccion +
               "\nPaís: " + pais + "\nAntiguedad: " + añosAntiguedad + " años\nVIP: " + esVip;
    }
}