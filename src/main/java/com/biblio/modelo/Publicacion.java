package com.biblio.modelo;

public abstract class Publicacion {


private String titulo;
private String autor;
private int anio;
private Genero genero;
private String[] etiquetas;

public Publicacion(String titulo, String autor, int anio, Genero genero, String[] etiquetas) {
    this.titulo = titulo;
    this.autor = autor;
    this.anio = anio;
    this.genero = genero;
    this.etiquetas = etiquetas;
}

public String getTitulo() { return titulo; }
public String getAutor() { return autor; }
public int getAnio() { return anio; }
public Genero getGenero() { return genero; }
public String[] getEtiquetas() { return etiquetas; }

public abstract String getTipo();
public abstract String toResumen();
public abstract String toCsv();
public abstract String toJson();


}

