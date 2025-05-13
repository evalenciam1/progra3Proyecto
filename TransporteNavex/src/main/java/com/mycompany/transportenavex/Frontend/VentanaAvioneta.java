package com.mycompany.transportenavex.Frontend;

import javax.swing.*;

import com.mycompany.transportenavex.Controllers.AccionesController;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

//import java.awt.*;
//import java.awt.event.*;

public class VentanaAvioneta extends JFrame {
    private int numeroAvioneta;
    private ListaDoblementeEnlazada listaPasajeros;

    // Elementos visuales
    private JTextArea listaTextArea;

    public VentanaAvioneta(int numeroavioneta, ListaDoblementeEnlazada listaPasajeros) {
        this.numeroAvioneta = numeroavioneta;
        this.listaPasajeros = listaPasajeros;

        //Configuracion del Jframe
        setTitle("Avioneta " + numeroAvioneta);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    // Inicializando componentes
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Botón agregar
        JButton agregarButton = new JButton("Agregar pasajero");
        agregarButton.addActionListener(e -> ventanaAgregarPasajero());
        panel.add(agregarButton);

        panel.add(Box.createVerticalStrut(15));

        // Área para mostrar pasajeros
        listaTextArea = new JTextArea(10, 40);
        listaTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(listaTextArea);
        panel.add(new JLabel("Lista de pasajeros:"));
        panel.add(scroll);

        // Agregar panel
        add(panel);
        actualizarLista();
    }

    private void ventanaAgregarPasajero() {
         new agregarPasajero(numeroAvioneta, listaPasajeros);
    }

    private void actualizarLista() {
        listaTextArea.setText(listaPasajeros.obtenerTextoPasajeros());
    }
}
