package com.biblio.modelo;

import java.time.LocalDate;
import java.util.UUID;

/** Superclase abstracta de publicaciones */

public abstract class Publicacion implements Prestable, Resenable {
    private final String id;
    private String titulo, autor;
    private int anio;
    private Genero genero;
    private String[] etiquetas; //array RA6

    //Prestable
    private boolean disponible = true;
    private String usuarioPrestamo;
    //
}