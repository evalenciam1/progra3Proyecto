package com.mycompany.transportenavex.Frontend;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PantallaSeleccionAvioneta extends JFrame {
    public PantallaSeleccionAvioneta() {
        setTitle("Seleccionar Avioneta");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] avionetas = {"Cessna 172", "Piper PA-28", "Beechcraft Bonanza"};
        JComboBox<String> comboBox = new JComboBox<>(avionetas);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> {
            String seleccion = (String) comboBox.getSelectedItem();
            int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Confirmas la selección de: " + seleccion + "?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                dispose();
                new PantallaDetalleAvioneta(seleccion);
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(new JLabel("Selecciona una avioneta:"));
        panel.add(comboBox);
        panel.add(btnConfirmar);

        add(panel);
        setVisible(true);
    }
}
