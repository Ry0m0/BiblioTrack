package com.biblio.gui;

import com.biblio.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class VentanaPrincipal {

    private ArrayList<Publicacion> datos = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void mostrar() {
        while (true) {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1. Agregar publicación");
            System.out.println("2. Ver publicaciones");
            System.out.println("3. Reseñar Comic");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> agregarPublicacion();
                case 2 -> verPublicaciones();
                case 3 -> resenar();
                case 0 -> {
                    System.out.println("¡Gracias por usar la biblioteca!");
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void agregarPublicacion() {
        DialogoPublicacion dialogo = new DialogoPublicacion(sc);
        Publicacion p = dialogo.crearPublicacion();
        if (p != null) {
            datos.add(p);
            System.out.println("Publicación agregada: " + p.toResumen());
        }
    }

    private void verPublicaciones() {
        System.out.println("\n--- Lista de publicaciones ---");
        for (int i = 0; i < datos.size(); i++) {
            System.out.println((i + 1) + ". " + datos.get(i).toResumen());
        }
    }

    private void resenar() {
        System.out.println("Selecciona el número de Comic para reseñar:");
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i) instanceof Resenable) {
                System.out.println((i + 1) + ". " + datos.get(i).getTitulo());
            }
        }
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        if (datos.get(idx) instanceof Resenable r) {
            System.out.print("Puntuación (1-5): ");
            int punt = Integer.parseInt(sc.nextLine());
            r.agregarResena("", punt);
            System.out.println("Reseña agregada!");
        } else {
            System.out.println("No es reseñable!");
        }
    }
}
