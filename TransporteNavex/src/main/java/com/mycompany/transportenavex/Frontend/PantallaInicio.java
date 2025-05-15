package com.mycompany.transportenavex.Frontend;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PantallaInicio extends JFrame {
    public PantallaInicio() {
        setTitle("Sistema de Avionetas");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Bienvenido al sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton boton = new JButton("Seleccionar Avioneta");
        boton.addActionListener(e -> {
            dispose();
            new PantallaSeleccionAvioneta();
        });

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.CENTER);
        add(boton, BorderLayout.SOUTH);
        setVisible(true);
    }
}
