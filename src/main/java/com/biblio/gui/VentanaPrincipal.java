package com.biblio.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.biblio.modelo.Publicacion;

public class VentanaPrincipal extends JFrame {

    private DefaultListModel<String> modelo = new DefaultListModel<>();
    private JList<String> lista = new JList<>(modelo);
    private ArrayList<Publicacion> datos = new ArrayList<>();

    public VentanaPrincipal() {
        setTitle("Biblioteca");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarPublicacion());

        JPanel panel = new JPanel();
        panel.add(btnAgregar);

        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    // Simple method to add a publication
    private void agregarPublicacion() {
        DialogoPublicacion dialogo = new DialogoPublicacion(this);
        dialogo.setVisible(true);

        Publicacion p = dialogo.getPublicacion();
        if (p != null) {
            datos.add(p);
            modelo.addElement(p.toResumen());
        }
    }
}
