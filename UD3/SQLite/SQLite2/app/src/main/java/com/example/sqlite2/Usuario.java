package com.example.sqlite2;

public class Usuario {
    private int id;
    private String nombre;
    private int libroPrestado;

    public Usuario(int id, String nombre, int libroPrestado) {
        this.id = id;
        this.nombre = nombre;
        this.libroPrestado = libroPrestado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLibroPrestado() {
        return libroPrestado;
    }
}
