package com.mycompany.transportenavex.Frontend;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;

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

        //Boton eliminar
        JButton eliminarButton = new JButton("Eliminar Pasajero");
        eliminarButton.addActionListener(e -> VentanaEliminarPAsajero());
        panel.add(eliminarButton);

        //botton modificar
        JButton modificarButton = new JButton("Modificar Pasajero");
        modificarButton.addActionListener(e ->
        ventanaModificarPasajero());
        panel.add(modificarButton);

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
         new agregarPasajeroOld(numeroAvioneta, listaPasajeros);
         dispose();
    }

    private void  VentanaEliminarPAsajero() {
         int cantidadDePasajeros = listaPasajeros.cantidadPasajeros();
        if (cantidadDePasajeros!=0) {
           new eliminarPasajero(numeroAvioneta, listaPasajeros);
            dispose();  
        }
        else{
            JOptionPane.showMessageDialog(this, "Lista de pasajeros vacia");
        }
    }

    private void ventanaModificarPasajero(){
        int cantidadDePasajeros = listaPasajeros.cantidadPasajeros();
        if (cantidadDePasajeros!=0) {
           new modificarPasajero(numeroAvioneta, listaPasajeros);
            dispose();  
        }
        else{
            JOptionPane.showMessageDialog(this, "Lista de pasajeros vacia");
        }
    }


    private void actualizarLista() {
        listaTextArea.setText(listaPasajeros.obtenerTextoPasajeros());
    }
}
