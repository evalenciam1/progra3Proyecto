package com.mycompany.transportenavex.Frontend;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;

//import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        
        setTitle("Seleccionar Avioneta");
        setSize(700, 300);
        setBackground(Color.WHITE);
        setForeground(getBackground());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Instanciamos las listas, una por cada avioneta, enviamos al constructor la cantidad de asientos que admite la avioneta
        ListaDoblementeEnlazada avioneta1 = new ListaDoblementeEnlazada(10);
        ListaDoblementeEnlazada avioneta2 = new ListaDoblementeEnlazada(10);  
        ListaDoblementeEnlazada avioneta3 = new ListaDoblementeEnlazada(10);

        //Creamos los elementos de UI
        JPanel panel = new JPanel();
        JLabel imagen = new JLabel();
        JButton boton1 = new JButton("Avioneta 1");
        JButton boton2 = new JButton("Avioneta 2");
        JButton boton3 = new JButton("Avioneta 3");

        
        //Configuracion de elementos UI
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/mycompany/resources/LogoBlanco 600x100.jpg"));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(600, 100, Image.SCALE_SMOOTH);
        imagen.setIcon(new ImageIcon(imagenEscalada));
        boton1.addActionListener(e -> abrirVentanaAvioneta(1,avioneta1));
        boton2.addActionListener(e -> abrirVentanaAvioneta(2, avioneta2));
        boton3.addActionListener(e -> abrirVentanaAvioneta(3, avioneta3));
        

        //Agregar al panel cada elemento
        panel.add(imagen);
        panel.add(Box.createVerticalStrut(200));
        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);

        //Agregar panel a la pantalla
        add(panel);
        setVisible(true);
    }

    private void abrirVentanaAvioneta(int numeroAvioneta ,ListaDoblementeEnlazada listaPasajeros) {
        // Se pasa el número para que la otra ventana sepa cuál avioneta es
        new VentanaAvioneta(numeroAvioneta, listaPasajeros);

    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
