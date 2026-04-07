package com.biblio.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.biblio.modelo.Comic;
import com.biblio.modelo.Genero;
import com.biblio.modelo.LibroTecnico;
import com.biblio.modelo.Novela;
import com.biblio.modelo.Publicacion;

public class DialogoPublicacion extends JDialog {


private JTextField txtTitulo;
private JTextField txtAutor;
private JTextField txtAnio;
private JComboBox<String> comboTipo;

private Publicacion creada;

public DialogoPublicacion(JFrame parent) {
    super(parent, "Nueva publicación", true);
    setSize(400, 250);
    setLocationRelativeTo(parent);
    setLayout(new GridLayout(5, 2));

    add(new JLabel("Título:"));
    txtTitulo = new JTextField();
    add(txtTitulo);

    add(new JLabel("Autor:"));
    txtAutor = new JTextField();
    add(txtAutor);

    add(new JLabel("Año:"));
    txtAnio = new JTextField();
    add(txtAnio);

    add(new JLabel("Tipo:"));
    comboTipo = new JComboBox<>(new String[]{"Comic", "Novela", "Tecnico"});
    add(comboTipo);

    JButton btnCrear = new JButton("Crear");
    btnCrear.addActionListener(e -> crear());

    add(new JLabel());
    add(btnCrear);
}

private void crear() {
    try {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int anio = Integer.parseInt(txtAnio.getText());

        String[] etiquetas = {"general"};

        String tipo = comboTipo.getSelectedItem().toString();

        switch (tipo) {
            case "Comic":
                creada = new Comic(titulo, autor, anio, Genero.COMIC, etiquetas);
                break;

            case "Novela":
                creada = new Novela(titulo, autor, anio, Genero.NOVELA, etiquetas);
                break;

            case "Tecnico":
                creada = new LibroTecnico(
                        titulo,
                        autor,
                        anio,
                        Genero.TECNICO,
                        etiquetas,
                        1,
                        "General"
                );
                break;
        }

        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

public Publicacion getPublicacion() {
    return creada;
}


}
