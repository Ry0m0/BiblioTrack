package com.biblio.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import com.biblio.colecciones.Catalogo;
import com.biblio.persistencia.GestorFicheros;
import com.biblio.modelo.*;

public class VentanaPrincipal extends JFrame {
    
    private Catalogo catalogo;
    private GestorFicheros gestor;
    private DefaultTableModel modeloTabla;
    private JTextField buscador;
    
    public VentanaPrincipal(Catalogo catalogo, GestorFicheros gestor) {
        this.catalogo = catalogo;
        this.gestor = gestor;
        setTitle("BiblioTrack");
        setSize(700, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Título", "Autor", "Año", "Tipo"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        // Buscador
        buscador = new JTextField(15);
        JPanel norte = new JPanel();
        norte.add(new JLabel("Buscar:"));
        norte.add(buscador);
        add(norte, BorderLayout.NORTH);
        
        // Botones
        JPanel sur = new JPanel();
        JButton btnAdd = new JButton("Añadir");
        JButton btnDel = new JButton("Eliminar");
        JButton btnRes = new JButton("Reseñar");
        JButton btnSave = new JButton("Guardar");
        sur.add(btnAdd);
        sur.add(btnDel);
        sur.add(btnRes);
        sur.add(btnSave);
        add(sur, BorderLayout.SOUTH);
        
        // Listeners
        btnAdd.addActionListener(e -> new DialogoPublicacion(this, catalogo).setVisible(true));
        btnSave.addActionListener(e -> guardar());
        btnRes.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                String titulo = (String) modeloTabla.getValueAt(fila, 0);
                for (Publicacion p : catalogo.getPublicaciones()) {
                    if (p.getTitulo().equals(titulo) && p instanceof Resenable) {
                        new DialogoResena(this, (Resenable) p).setVisible(true);
                        break;
                    }
                }
            }
        });
        
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    String titulo = (String) modeloTabla.getValueAt(fila, 0);
                    catalogo.eliminarPorTitulo(titulo);
                    cargarTabla();
                }
            }
        });
        
        buscador.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                cargarTablaFiltrada();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Guardar antes de salir?");
                if (opcion == JOptionPane.YES_OPTION) guardar();
                dispose();
            }
        });
        
        cargarTabla();
        setVisible(true);
    }
    
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Publicacion p : catalogo.getPublicaciones()) {
            modeloTabla.addRow(new Object[]{p.getTitulo(), p.getAutor(), p.getAnio(), p.getTipo()});
        }
    }
    
    private void cargarTablaFiltrada() {
        String texto = buscador.getText().toLowerCase();
        modeloTabla.setRowCount(0);
        for (Publicacion p : catalogo.getPublicaciones()) {
            if (p.getTitulo().toLowerCase().contains(texto) || p.getAutor().toLowerCase().contains(texto)) {
                modeloTabla.addRow(new Object[]{p.getTitulo(), p.getAutor(), p.getAnio(), p.getTipo()});
            }
        }
    }
    
    private void guardar() {
        try {
            gestor.guardar(catalogo.getPublicaciones());
            JOptionPane.showMessageDialog(this, "Guardado");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar");
        }
    }
}