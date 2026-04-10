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
    private JTable tabla;
    
    public VentanaPrincipal(Catalogo catalogo, GestorFicheros gestor) {
        this.catalogo = catalogo;
        this.gestor = gestor;
        setTitle("BiblioTrack");
        setSize(700, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        cargarTabla();
        configurarListeners();
        
        setVisible(true);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        String[] columnas = {"Título", "Autor", "Año", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(new JLabel("Buscar:"));
        buscador = new JTextField(20);
        panelNorte.add(buscador);
        add(panelNorte, BorderLayout.NORTH);
        
        JPanel panelSur = new JPanel();
        JButton btnAdd = new JButton("Añadir");
        JButton btnDel = new JButton("Eliminar");
        JButton btnRes = new JButton("Reseñar");
        JButton btnPrestar = new JButton("Prestar");
        JButton btnSave = new JButton("Guardar");
        panelSur.add(btnAdd);
        panelSur.add(btnDel);
        panelSur.add(btnRes);
        panelSur.add(btnPrestar);
        panelSur.add(btnSave);
        add(panelSur, BorderLayout.SOUTH);
        
        btnAdd.addActionListener(e -> {
            new DialogoPublicacion(this, catalogo).setVisible(true);
            refrescarTabla();
        });
        
        btnSave.addActionListener(e -> guardarDatos());
        
        btnRes.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                String titulo = (String) modeloTabla.getValueAt(fila, 0);
                boolean encontrado = false;
                for (Publicacion p : catalogo.getPublicaciones()) {
                    if (p.getTitulo().equals(titulo) && p instanceof Resenable) {
                        DialogoResena dialog = new DialogoResena(this, (Resenable) p);
                        dialog.setVisible(true);
                        refrescarTabla();
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(this, "Solo se pueden reseñar Comics");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una publicación");
            }
        });
        
        btnPrestar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                String titulo = (String) modeloTabla.getValueAt(fila, 0);
                for (Publicacion p : catalogo.getPublicaciones()) {
                    if (p.getTitulo().equals(titulo)) {
                        if (p.estaDisponible()) {
                            String usuario = JOptionPane.showInputDialog(this, "Nombre del usuario:");
                            if (usuario != null && !usuario.trim().isEmpty()) {
                                p.prestar(usuario);
                                JOptionPane.showMessageDialog(this, "Prestado a " + usuario);
                            }
                        } else {
                            int confirm = JOptionPane.showConfirmDialog(this, 
                                "Devolver libro?", "Devolver", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                p.devolver();
                                JOptionPane.showMessageDialog(this, "Libro devuelto");
                            }
                        }
                        refrescarTabla();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una publicación");
            }
        });
        
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    String titulo = (String) modeloTabla.getValueAt(fila, 0);
                    int confirm = JOptionPane.showConfirmDialog(VentanaPrincipal.this,
                        "¿Eliminar " + titulo + "?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        catalogo.eliminarPorTitulo(titulo);
                        refrescarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Seleccione una publicación");
                }
            }
        });
    }
    
    private void configurarListeners() {
        buscador.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String texto = buscador.getText().toLowerCase();
                modeloTabla.setRowCount(0);
                for (Publicacion p : catalogo.getPublicaciones()) {
                    if (p.getTitulo().toLowerCase().contains(texto) ||
                        p.getAutor().toLowerCase().contains(texto)) {
                        String estado = p.estaDisponible() ? "Disponible" : "Prestado";
                        modeloTabla.addRow(new Object[]{p.getTitulo(), p.getAutor(), p.getAnio(), p.getTipo(), estado});
                    }
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(VentanaPrincipal.this,
                    "¿Guardar antes de salir?", "Salir",
                    JOptionPane.YES_NO_CANCEL_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    guardarDatos();
                    dispose();
                } else if (opcion == JOptionPane.NO_OPTION) {
                    dispose();
                }
            }
        });
    }
    
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Publicacion p : catalogo.getPublicaciones()) {
            String estado = p.estaDisponible() ? "Disponible" : "Prestado";
            modeloTabla.addRow(new Object[]{p.getTitulo(), p.getAutor(), p.getAnio(), p.getTipo(), estado});
        }
    }
    
    public void refrescarTabla() {
        cargarTabla();
    }
    
    private void guardarDatos() {
        try {
            gestor.guardar(catalogo.getPublicaciones());
            JOptionPane.showMessageDialog(this, "Datos guardados");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }
}