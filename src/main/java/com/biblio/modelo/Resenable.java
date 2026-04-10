package com.biblio.modelo;

/** Conteato de reseñas */
public interface Resenable {
    /** Añade reseña (1-10) */
    void resenar(String texto, double puntuacion);

    /** Puntuacion media */
    double getPuntuacionMedia();

    /** Última reseña */
    String getUltimaResenia();
}
