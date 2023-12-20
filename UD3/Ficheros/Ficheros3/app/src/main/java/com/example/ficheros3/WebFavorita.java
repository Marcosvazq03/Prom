package com.example.ficheros3;

public class WebFavorita {
    private String nombre;
    private String enlace;
    private int logo;
    private String id;

    public WebFavorita(String nombre, String enlace, int logo, String id) {
        this.nombre = nombre;
        this.enlace = enlace;
        this.logo = logo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEnlace() {
        return enlace;
    }
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    public int getLogo() {
        return logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
