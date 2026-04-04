package com.biblio.modelo;

/** Contrato de préstamo */
public interface Prestable {
    /** Presta la publicación a un usuario */
    void prestar(String usuario);

    /** Devuelve la publicación */
    void devolver();

    /** Verifica si está disponible */
    boolean estaDisponible();

    /** Devuelve el nombre del usuario que la tiene */
    String getUsuarioPrestamo();
}