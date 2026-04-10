package com.biblio.colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.biblio.modelo.Publicacion;

public class Catalogo {

    private List<Publicacion> publicaciones;
    private Map<String, List<Publicacion>> porAutor;  // NUEVO

    public Catalogo() {
        publicaciones = new ArrayList<>();
        porAutor = new HashMap<>();  // NUEVO
    }

    // Añadir una publicación
    public void agregar(Publicacion p) {
        publicaciones.add(p);
        // actualizar HashMap
        String autor = p.getAutor();
        if (!porAutor.containsKey(autor)) {
            porAutor.put(autor, new ArrayList<>());
        }
        porAutor.get(autor).add(p);
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void mostrarPublicaciones() {
        if (publicaciones.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
    
        System.out.printf("%-3s %-20s %-15s %-8s%n", "#", "Título", "Autor", "Tipo");
        for (int i = 0; i < publicaciones.size(); i++) {
            Publicacion p = publicaciones.get(i);
            System.out.printf("%-3d %-20s %-15s %-8s%n", 
                i+1, p.getTitulo(), p.getAutor(), p.getTipo());
        }
    }

    public Publicacion getPublicacion(int index) {
        if (index < 0 || index >= publicaciones.size()) return null;
        return publicaciones.get(index);
    }

    public int size() {
        return publicaciones.size();
    }

    // NUEVO: buscar por autor
    public List<Publicacion> buscarPorAutor(String autor) {
        return porAutor.getOrDefault(autor, new ArrayList<>());
    }

    //eliminar con Iterator
    public boolean eliminarPorTitulo(String titulo) {
        Iterator<Publicacion> it = publicaciones.iterator();
        while (it.hasNext()) {
            if (it.next().getTitulo().equalsIgnoreCase(titulo)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    //  método genérico
    public <T extends Publicacion> List<T> getSoloTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Publicacion p : publicaciones) {
            if (tipo.isInstance(p)) {
                resultado.add(tipo.cast(p));
            }
        }
        return resultado;
    }

    //  Stream - año promedio
    public double anioPromedio() {
        return publicaciones.stream().mapToInt(Publicacion::getAnio).average().orElse(0);
    }

    //JSON manual
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"libros\":[");
        for (int i = 0; i < publicaciones.size(); i++) {
            sb.append(publicaciones.get(i).toJson());
            if (i < publicaciones.size()-1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}