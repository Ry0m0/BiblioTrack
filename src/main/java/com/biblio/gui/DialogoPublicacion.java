package com.biblio.gui;

import javax.swing.*;
import java.awt.*;
import com.biblio.colecciones.Catalogo;
import com.biblio.modelo.*;

public class DialogoPublicacion extends JDialog {
    
    private VentanaPrincipal ventana;
    
    public DialogoPublicacion(VentanaPrincipal parent, Catalogo catalogo) {  // ← 改 JFrame → VentanaPrincipal
        super(parent, "Añadir publicación", true);
        this.ventana = parent;
        setSize(300, 220);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 2));
        
        add(new JLabel("Título:"));
        JTextField titulo = new JTextField();
        add(titulo);
        
        add(new JLabel("Autor:"));
        JTextField autor = new JTextField();
        add(autor);
        
        add(new JLabel("Año:"));
        JTextField anio = new JTextField();
        add(anio);
        
        add(new JLabel("Tipo (Novela/Tecnico/Comic):"));
        JTextField tipo = new JTextField();
        add(tipo);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String[] etiquetas = {"general"};
            String t = tipo.getText().toLowerCase();
            int a = Integer.parseInt(anio.getText());
            Publicacion p = null;
            
            if (t.equals("novela")) {
                p = new Novela(titulo.getText(), autor.getText(), a, Genero.NOVELA, etiquetas);
            } else if (t.equals("tecnico")) {
                p = new LibroTecnico(titulo.getText(), autor.getText(), a, Genero.TECNICO, etiquetas, 1, "General");
            } else if (t.equals("comic")) {
                p = new Comic(titulo.getText(), autor.getText(), a, Genero.COMIC, etiquetas);
            }
            
            if (p != null) {
                catalogo.agregar(p);
                ventana.refrescarTabla();
                dispose();
            }
        });
        
        add(btnGuardar);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }
}