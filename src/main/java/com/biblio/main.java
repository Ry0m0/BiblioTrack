package com.biblio;

import java.io.IOException;

import com.biblio.colecciones.Catalogo;
import com.biblio.gui.VentanaPrincipal;
import com.biblio.modelo.Publicacion;
import com.biblio.persistencia.GestorFicheros;

public class main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        GestorFicheros gestor = new GestorFicheros();
        
        try {
            for (Publicacion p : gestor.cargar()) {
                catalogo.agregar(p);
            }
            System.out.println("Cargados " + catalogo.size() + " libros");
        } catch (IOException e) {
            System.out.println("No se pudo cargar: " + e.getMessage());
        }
        
        new VentanaPrincipal(catalogo, gestor);
    }
}