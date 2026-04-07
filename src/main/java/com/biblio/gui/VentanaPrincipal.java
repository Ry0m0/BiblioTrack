package com.biblio.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.biblio.modelo.Comic;
import com.biblio.modelo.Genero;
import com.biblio.modelo.Publicacion;
import com.biblio.modelo.Resenable;

public class VentanaPrincipal extends JFrame {

private DefaultListModel<String> modelo = new DefaultListModel<>();
private JList<String> lista = new JList<>(modelo);
private ArrayList<Publicacion> datos = new ArrayList<>();

public VentanaPrincipal() {
    setTitle("Biblioteca");
    setSize(500, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JButton btnAdd = new JButton("Añadir");
    JButton btnResena = new JButton("Reseñar");

    btnAdd.addActionListener(e -> agregar());
    btnResena.addActionListener(e -> resenar());

    JPanel p = new JPanel();
    p.add(btnAdd);
    p.add(btnResena);

    add(new JScrollPane(lista), BorderLayout.CENTER);
    add(p, BorderLayout.SOUTH);
}

private void agregar() {
    String[] et = {"general"};
    Publicacion p = new Comic("Demo", "Autor", 2024, Genero.COMIC, et);
    datos.add(p);
    modelo.addElement(p.toResumen());
}

private void resenar() {
    int i = lista.getSelectedIndex();
    if (i == -1) return;

    Publicacion p = datos.get(i);

    if (p instanceof Resenable r) {
        String s = JOptionPane.showInputDialog("Puntuación 1-5");
        int val = Integer.parseInt(s);
        r.agregarResena("", val);
        modelo.set(i, p.toResumen());
    }
}


}
