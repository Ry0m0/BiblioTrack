package com.biblio.consola;

import java.util.Scanner;
import com.biblio.colecciones.Catalogo;
import com.biblio.modelo.*;

public class MenuConsola {
    
    private Catalogo catalogo;
    private Scanner sc = new Scanner(System.in);
    
    public MenuConsola(Catalogo catalogo) {
        this.catalogo = catalogo;
    }
    
    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== BIBLIOTRACK ===");
            System.out.println("1. Ver publicaciones");
            System.out.println("2. Añadir publicación");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Eliminar por título");
            System.out.println("5. Año promedio");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            
            switch (opcion) {
                case 1 -> catalogo.mostrarPublicaciones();
                case 2 -> agregarPublicacion();
                case 3 -> buscarPorAutor();
                case 4 -> eliminarPorTitulo();
                case 5 -> System.out.println("Promedio: " + catalogo.anioPromedio());
                case 0 -> System.out.println("Adiós");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }
    
    private void agregarPublicacion() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());
        String[] etiquetas = {"general"};
        
        Publicacion p = new Novela(titulo, autor, anio, Genero.NOVELA, etiquetas);
        catalogo.agregar(p);
        System.out.println("Añadido!");
    }
    
    private void buscarPorAutor() {
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        for (Publicacion p : catalogo.buscarPorAutor(autor)) {
            System.out.println("- " + p.getTitulo());
        }
    }
    
    private void eliminarPorTitulo() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.println(catalogo.eliminarPorTitulo(titulo) ? "Eliminado" : "No encontrado");
    }
}

