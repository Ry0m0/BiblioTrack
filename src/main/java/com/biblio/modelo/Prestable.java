package com.biblio.modelo;

public interface Prestable {
    void prestar(String usuario);
    void devolver();
    boolean estaDisponible();
    String getUsuarioPrestamo();
}