package com.biblio.modelo;

public class LibroTecnico extends Publicacion {


private int edicion;
private String area;

public LibroTecnico(String titulo, String autor, int anio, Genero genero,
                    String[] etiquetas, int edicion, String area) {
    super(titulo, autor, anio, genero, etiquetas);
    this.edicion = edicion;
    this.area = area;
}

@Override
public String getTipo() { return "TECNICO"; }

@Override
public String toResumen() {
    return getTitulo() + " (Ed." + edicion + ")";
}

@Override
public String toCsv() {
    return "TECNICO;" + getTitulo();
}

@Override
public String toJson() {
    return "{\"tipo\":\"TECNICO\",\"titulo\":\"" + getTitulo() + "\"}";
}


}
