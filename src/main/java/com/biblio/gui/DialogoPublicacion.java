package com.biblio.gui;

import java.util.Scanner;

import com.biblio.modelo.Comic;
import com.biblio.modelo.Genero;
import com.biblio.modelo.LibroTecnico;
import com.biblio.modelo.Novela;
import com.biblio.modelo.Publicacion;

public class DialogoPublicacion {

    private Scanner sc;

    public DialogoPublicacion(Scanner sc) {
        this.sc = sc;
    }

    public Publicacion crearPublicacion() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Año: ");
        int anio = Integer.parseInt(sc.nextLine());

        System.out.print("Tipo (Comic, Novela, Tecnico): ");
        String tipo = sc.nextLine();

        String[] etiquetas = {"general"};

        switch (tipo.toLowerCase()) {
            case "comic":
                return new Comic(titulo, autor, anio, Genero.COMIC, etiquetas);
            case "novela":
                return new Novela(titulo, autor, anio, Genero.NOVELA, etiquetas);
            case "tecnico":
                return new LibroTecnico(titulo, autor, anio, Genero.TECNICO, etiquetas, 1, "General");
            default:
                System.out.println("Tipo inválido");
                return null;
        }
    }
}
