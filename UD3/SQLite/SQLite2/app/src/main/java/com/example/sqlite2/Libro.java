package com.example.sqlite2;

public class Libro {
    private int cod;
    private String titulo;
    private String autor;
    private int prestado;

    public Libro(int cod, String titulo, String autor, int prestado) {
        this.cod = cod;
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = prestado;
    }

    public int getCod() {
        return cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPrestado() {
        return prestado;
    }
}
