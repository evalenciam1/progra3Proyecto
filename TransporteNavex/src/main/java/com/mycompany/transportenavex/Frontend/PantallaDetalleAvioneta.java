package com.mycompany.transportenavex.Frontend;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PantallaDetalleAvioneta extends JFrame {
    public PantallaDetalleAvioneta(String avioneta) {
        setTitle("Detalle de Avioneta");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Detalles de: " + avioneta, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton regresar = new JButton("Volver");
        regresar.addActionListener(e -> {
            dispose();
            new PantallaSeleccionAvioneta();
        });

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(regresar, BorderLayout.SOUTH);
        setVisible(true);
    }
}
