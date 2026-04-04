package com.biblio.modelo;

/** Subclase que representa una novela */
public class Novela extends Publicacion {

    private int numeroPaginas;
    private boolean edicionEspecial;
    private String isbn;

    /** Constructor — llama a super() obligatoriamente */
    public Novela(String titulo, String autor, int anio, Genero genero,
            String[] etiquetas, int numeroPaginas, boolean edicionEspecial, String isbn) {
        super(titulo, autor, anio, genero, etiquetas);
        if (numeroPaginas <= 0)
            throw new IllegalArgumentException("Las páginas deben ser mayor que 0");
        if (isbn == null || !isbn.matches("\\d{13}"))
            throw new IllegalArgumentException("El ISBN debe tener 13 dígitos");
        this.numeroPaginas = numeroPaginas;
        this.edicionEspecial = edicionEspecial;
        this.isbn = isbn;
    }

    /** Precio = páginas x 0.025, x1.4 si es edición especial */
    public double calcularPrecioReposicion() {
        double base = numeroPaginas * 0.025;
        return edicionEspecial ? base * 1.4 : base;
    }

    @Override
    public String getTipo() {
        return "NOVELA";
    }

    @Override
    public String toResumen() {
        return String.format("NOVELA  | %-25s | %-15s | %4d págs | %.2f€",
                getTitulo(), getAutor(), numeroPaginas, calcularPrecioReposicion());
    }

    @Override
    public String toCsv() {
        return "NOVELA;" + getTitulo() + ";" + getAutor() + ";" + getAnio() + ";"
                + getGenero() + ";" + numeroPaginas + ";" + edicionEspecial + ";" + isbn;
    }

    @Override
    public String toJson() {
        return "{\"tipo\":\"NOVELA\",\"titulo\":\"" + getTitulo() + "\",\"autor\":\"" + getAutor()
                + "\",\"paginas\":" + numeroPaginas + ",\"isbn\":\"" + isbn + "\"}";
    }

    // Getters
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public boolean isEdicionEspecial() {
        return edicionEspecial;
    }

    public String getIsbn() {
        return isbn;
    }
}