package com.biblio.modelo;

import java.util.UUID;
public abstract class Publicacion {

```
protected String titulo;
protected String autor;
protected int anioPublicacion;
protected Genero genero;

public Publicacion(String titulo, String autor, int anioPublicacion, Genero genero) {
    this.titulo = titulo;
    this.autor = autor;
    this.anioPublicacion = anioPublicacion;
    this.genero = genero;
}

public String getTitulo() {
    return titulo;
}

public String getAutor() {
    return autor;
}

public int getAnioPublicacion() {
    return anioPublicacion;
}

public Genero getGenero() {
    return genero;
}

public void mostrarInfo() {
    System.out.println("Título: " + titulo);
    System.out.println("Autor: " + autor);
    System.out.println("Año: " + anioPublicacion);
    System.out.println("Género: " + genero);
}
```

}
