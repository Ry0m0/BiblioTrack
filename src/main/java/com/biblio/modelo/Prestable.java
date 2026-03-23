package com.biblio.modelo;

public interface Prestable {
    /** Presta la publicación */
    void prestar();
    
    /** Devuelve la publicación */
    void devolver();
    
    /** Verifica si está prestada */
    boolean isPrestado();
}