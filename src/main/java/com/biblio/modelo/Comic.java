package com.biblio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Comic extends Publicacion implements Resenable {
    
    private List<Integer> puntuaciones = new ArrayList<>();
    private List<String> comentarios = new ArrayList<>();
    
    public Comic(String titulo, String autor, int anio, Genero genero, String[] etiquetas) {
        super(titulo, autor, anio, genero, etiquetas);
    }
    
    @Override
    public void agregarResena(String comentario, int puntuacion) {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("Puntuación 1-5");
        }
        puntuaciones.add(puntuacion);
        comentarios.add(comentario);
    }
    
    @Override
    public double obtenerCalificacionPromedio() {
        if (puntuaciones.isEmpty()) return 0;
        return puntuaciones.stream().mapToInt(i -> i).average().orElse(0);
    }
    
    @Override
    public int getNumeroResenas() {
        return puntuaciones.size();
    }
    
    @Override
    public String getUltimaResenia() {
        if (comentarios.isEmpty()) return "";
        return comentarios.get(comentarios.size() - 1);
    }
    
    @Override
    public String getTipo() {
        return "COMIC";
    }
    
    @Override
    public String toResumen() {
        return getTitulo() + " (" + getTipo() + ")";
    }
    
    @Override
    public String toCsv() {
        return "COMIC;" + getTitulo() + ";" + getAutor() + ";" + getAnio() + ";" + getGenero();
    }
    
    @Override
    public String toJson() {
        return "{\"tipo\":\"COMIC\",\"titulo\":\"" + getTitulo() + "\",\"autor\":\"" + getAutor() + "\"}";
    }
}