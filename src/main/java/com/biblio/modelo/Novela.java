package com.biblio.modelo;



public class Novela extends Publicacion {


public Novela(String titulo, String autor, int anio, Genero genero, String[] etiquetas) {
    super(titulo, autor, anio, genero, etiquetas);
}

@Override
public String getTipo() { return "NOVELA"; }

@Override
public String toResumen() {
    return getTitulo() + " (Novela)";
}

@Override
public String toCsv() {
    return "NOVELA;" + getTitulo();
}

@Override
public String toJson() {
    return "{\"tipo\":\"NOVELA\",\"titulo\":\"" + getTitulo() + "\"}";
}


}
