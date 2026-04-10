package com.biblio.modelo;

public abstract class Publicacion implements Prestable {

    private String titulo;
    private String autor;
    private int anio;
    private Genero genero;
    private String[] etiquetas;
    
    // Para Prestable
    private boolean prestado;
    private String usuarioPrestamo;

    public Publicacion(String titulo, String autor, int anio, Genero genero, String[] etiquetas) {
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("Título no válido");
        if (autor == null || autor.trim().isEmpty())
            throw new IllegalArgumentException("Autor no válido");
        if (anio < 1800 || anio > 2025)
            throw new IllegalArgumentException("Año no válido");
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.etiquetas = etiquetas;
        this.prestado = false;
        this.usuarioPrestamo = null;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public Genero getGenero() { return genero; }
    public String[] getEtiquetas() { return etiquetas; }
    public void setEtiquetas(String[] etiquetas) { this.etiquetas = etiquetas; }

    // Métodos de Prestable
    @Override
    public void prestar(String usuario) {
        if (prestado) {
            throw new IllegalStateException("Ya está prestado");
        }
        this.prestado = true;
        this.usuarioPrestamo = usuario;
    }

    @Override
    public void devolver() {
        if (!prestado) {
            throw new IllegalStateException("No está prestado");
        }
        this.prestado = false;
        this.usuarioPrestamo = null;
    }

    @Override
    public boolean estaDisponible() {
        return !prestado;
    }

    @Override
    public String getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    // Métodos abstractos
    public abstract String getTipo();
    public abstract String toResumen();
    public abstract String toCsv();
    public abstract String toJson();
}
