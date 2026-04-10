package com.biblio.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.biblio.modelo.Comic;
import com.biblio.modelo.Genero;
import com.biblio.modelo.LibroTecnico;
import com.biblio.modelo.Novela;
import com.biblio.modelo.Publicacion;

public class GestorFicheros {
    
    private static final String RUTA = "datos/biblioteca.csv";
    
    // Guardar (FileWriter + BufferedWriter)
    public void guardar(List<Publicacion> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Publicacion p : lista) {
                bw.write(p.toCsv());
                bw.newLine();
            }
        }
    }
    
    // Cargar (BufferedReader)
    public List<Publicacion> cargar() throws IOException {
        List<Publicacion> lista = new ArrayList<>();
        File f = new File(RUTA);
        if (!f.exists()) return lista;
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Publicacion p = convertirLineaAPublicacion(linea);
                if (p != null) lista.add(p);
            }
        }
        return lista;
    }
    
    // RandomAccessFile
    public String buscarPorPosicion(long posicion) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(RUTA, "r")) {
            raf.seek(posicion);
            return raf.readLine();
        }
    }
    
    // Convertir una línea CSV a un objeto Publicacion
    private Publicacion convertirLineaAPublicacion(String linea) {
        String[] partes = linea.split(";");
        if (partes.length < 5) return null;
        
        String tipo = partes[0];
        String titulo = partes[1];
        String autor = partes[2];
        int anio = Integer.parseInt(partes[3]);
        Genero genero = Genero.valueOf(partes[4]);
        String[] etiquetas = {"general"};
        
        if (tipo.equals("NOVELA")) {
            return new Novela(titulo, autor, anio, genero, etiquetas);
        }
        if (tipo.equals("TECNICO")) {
            return new LibroTecnico(titulo, autor, anio, genero, etiquetas, 1, "General");
        }
        if (tipo.equals("COMIC")) {
            return new Comic(titulo, autor, anio, genero, etiquetas);
        }
        return null;
    }
}