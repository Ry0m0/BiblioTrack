package com.biblio.gui;

import javax.swing.*;
import com.biblio.modelo.Resenable;

public class DialogoResena extends JDialog {
    
    private Resenable libro;
    private JTextField campoPuntuacion;
    private boolean guardado = false;
    
    public DialogoResena(JFrame parent, Resenable libro) {
        super(parent, "Añadir reseña", true);
        this.libro = libro;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(new JLabel("Puntuación (1-5):"));
        campoPuntuacion = new JTextField();
        add(campoPuntuacion);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }
    
    private void guardar() {
        try {
            int puntos = Integer.parseInt(campoPuntuacion.getText());
            if (puntos < 1 || puntos > 5) {
                throw new NumberFormatException();
            }
            libro.agregarResena("", puntos);
            guardado = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La puntuación debe ser 1-5");
        }
    }
    
    public boolean isGuardado() {
        return guardado;
    }
}