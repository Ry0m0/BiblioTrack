package com.biblio.modelo;

public interface Resenable {
    void agregarResena(String comentario, int puntuacion);
    double obtenerCalificacionPromedio();
    int getNumeroResenas();
    String getUltimaResenia();
}