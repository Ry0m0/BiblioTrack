package com.biblio;

import com.biblio.colecciones.Catalogo;
import com.biblio.persistencia.GestorFicheros;
import com.biblio.gui.VentanaPrincipal;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        GestorFicheros gestor = new GestorFicheros();
        
        // Cargar datos al iniciar
        try {
            for (Publicacion p : gestor.cargar()) {
                catalogo.agregar(p);
            }
            System.out.println("Datos cargados: " + catalogo.size() + " publicaciones");
        } catch (IOException e) {
            System.out.println("No se pudo cargar: " + e.getMessage());
        }
        
        // Abrir ventana
        new VentanaPrincipal(catalogo, gestor);
    }
}