package com.biblio.modelo;

/** Subclase que representa un cómic */
public class Comic extends Publicacion {

    private String serie;
    private int numeroTomo;
    private String editorial;

    /** Constructor — llama a super() obligatoriamente */
    public Comic(String titulo, String autor, int anio, Genero genero,
            String[] etiquetas, String serie, int numeroTomo, String editorial) {
        super(titulo, autor, anio, genero, etiquetas);
        if (serie == null || serie.isBlank())
            throw new IllegalArgumentException("La serie no puede estar vacía");
        if (numeroTomo < 1)
            throw new IllegalArgumentException("El número de tomo debe ser mayor que 0");
        if (editorial == null || editorial.isBlank())
            throw new IllegalArgumentException("La editorial no puede estar vacía");
        this.serie = serie;
        this.numeroTomo = numeroTomo;
        this.editorial = editorial;
    }

    /** Precio base 12€, +1€ por cada tomo que supere el 10 */
    public double calcularPrecioReposicion() {
        return 12.0 + (numeroTomo > 10 ? numeroTomo - 10 : 0);
    }

    @Override
    public String getTipo() {
        return "COMIC";
    }

    @Override
    public String toResumen() {
        return String.format("CÓMIC   | %-25s | %-15s | T.%02d | %.2f€",
                getTitulo(), getSerie(), numeroTomo, calcularPrecioReposicion());
    }

    @Override
    public String toCsv() {
        return "COMIC;" + getTitulo() + ";" + getAutor() + ";" + getAnio() + ";"
                + getGenero() + ";" + serie + ";" + numeroTomo + ";" + editorial;
    }

    @Override
    public String toJson() {
        return "{\"tipo\":\"COMIC\",\"titulo\":\"" + getTitulo() + "\",\"autor\":\"" + getAutor()
                + "\",\"serie\":\"" + serie + "\",\"tomo\":" + numeroTomo + "}";
    }

    // Getters
    public String getSerie() {
        return serie;
    }

    public int getNumeroTomo() {
        return numeroTomo;
    }

    public String getEditorial() {
        return editorial;
    }
}