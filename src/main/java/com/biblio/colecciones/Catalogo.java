package com.biblio.colecciones;

import com.biblio.modelo.Publicacion;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private List<Publicacion> publicaciones;

    public Catalogo() {
        publicaciones = new ArrayList<>();
    }

    // Añadir una publicación
    public void agregar(Publicacion p) {
        publicaciones.add(p);
    }

    // Obtener todas las publicaciones
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    // Mostrar todas las publicaciones en consola
    public void mostrarPublicaciones() {
        if (publicaciones.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }

        for (int i = 0; i < publicaciones.size(); i++) {
            System.out.println((i + 1) + ". " + publicaciones.get(i).toResumen());
        }
    }

    // Obtener publicación por índice (para reseñar)
    public Publicacion getPublicacion(int index) {
        if (index < 0 || index >= publicaciones.size()) return null;
        return publicaciones.get(index);
    }

    // Número de publicaciones
    public int size() {
        return publicaciones.size();
    }
}
