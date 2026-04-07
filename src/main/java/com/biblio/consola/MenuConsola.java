package com.biblio.consola;

import java.util.ArrayList;
import java.util.Scanner;

import com.biblio.modelo.Comic;
import com.biblio.modelo.Genero;
import com.biblio.modelo.Publicacion;

public class MenuConsola {


public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Publicacion> lista = new ArrayList<>();

    while (true) {
        System.out.println("1. Añadir comic");
        System.out.println("2. Ver");
        System.out.println("0. Salir");

        int op = sc.nextInt();

        if (op == 1) {
            lista.add(new Comic("Demo", "Autor", 2024, Genero.COMIC, new String[]{"x"}));
        }

        if (op == 2) {
            lista.forEach(p -> System.out.println(p.toResumen()));
        }

        if (op == 0) break;
    }
}


}

