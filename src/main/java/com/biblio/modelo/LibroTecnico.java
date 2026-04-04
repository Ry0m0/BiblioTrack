package com.biblio.modelo;

/** Subclase que representa un libro técnico */
public class LibroTecnico extends Publicacion {

    private int edicion;
    private String areaTematica;
    private boolean incluyeRecursosDigitales;

    /** Constructor — llama a super() obligatoriamente */
    public LibroTecnico(String titulo, String autor, int anio, Genero genero,
            String[] etiquetas, int edicion, String areaTematica,
            boolean incluyeRecursosDigitales) {
        super(titulo, autor, anio, genero, etiquetas);
        if (edicion < 1)
            throw new IllegalArgumentException("La edición debe ser mayor que 0");
        if (areaTematica == null || areaTematica.isBlank())
            throw new IllegalArgumentException("El área temática no puede estar vacía");
        this.edicion = edicion;
        this.areaTematica = areaTematica;
        this.incluyeRecursosDigitales = incluyeRecursosDigitales;
    }

    /** Precio base 25€ por edición, +15€ si tiene recursos digitales */
    public double calcularPrecioReposicion() {
        double base = 25.0 * edicion;
        if (incluyeRecursosDigitales)
            base += 15.0;
        return base;
    }

    @Override
    public String getTipo() {
        return "TECNICO";
    }

    @Override
    public String toResumen() {
        return String.format("TÉCNICO | %-25s | %-15s | Ed.%d | %.2f€",
                getTitulo(), getAutor(), edicion, calcularPrecioReposicion());
    }

    @Override
    public String toCsv() {
        return "TECNICO;" + getTitulo() + ";" + getAutor() + ";" + getAnio() + ";"
                + getGenero() + ";" + edicion + ";" + areaTematica + ";" + incluyeRecursosDigitales;
    }

    @Override
    public String toJson() {
        return "{\"tipo\":\"TECNICO\",\"titulo\":\"" + getTitulo() + "\",\"autor\":\"" + getAutor()
                + "\",\"edicion\":" + edicion + ",\"area\":\"" + areaTematica + "\"}";
    }

    // Getters
    public int getEdicion() {
        return edicion;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public boolean isIncluyeRecursosDigitales() {
        return incluyeRecursosDigitales;
    }
}