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
    private JTextField dpiField;
    private JTextField nombreField;
    private JTextField asientoField;
    private JTextArea listaTextArea;

    public VentanaAvioneta(int numeroAvioneta, ListaDoblementeEnlazada listaPasajeros) {
        this.numeroAvioneta = numeroAvioneta;
        this.listaPasajeros = listaPasajeros;

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

        // Campos de entrada
        dpiField = new JTextField(15);
        nombreField = new JTextField(15);
        asientoField = new JTextField(5);

        // Agregando cuadro de entrada DPI
        panel.add(new JLabel("DPI:"));
        panel.add(dpiField);
        panel.add(Box.createVerticalStrut(10));
        // Agregando campo de entrada Nombre
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(Box.createVerticalStrut(10));
        // Agregando campo de entrada asiento
        panel.add(new JLabel("No. de asiento:"));
        panel.add(asientoField);
        panel.add(Box.createVerticalStrut(10));

        // Botón agregar
        JButton agregarButton = new JButton("Agregar pasajero");
        agregarButton.addActionListener(e -> agregarPasajero());
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

    private void agregarPasajero() {
        String dpi = dpiField.getText().trim();
        String nombre = nombreField.getText().trim();
        int asiento;

        try {
            asiento = Integer.parseInt(asientoField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de asiento inválido.");
            return;
        }

        Pasajero nuevo = new Pasajero(dpi, nombre, asiento);
        String respuesta = AccionesController.agregarPasajero(listaPasajeros, nuevo);
        JOptionPane.showMessageDialog(this, respuesta);

        if (respuesta=="PASAJERO AGREGADO CORRECTAMENTE") {
            dpiField.setText("");
            nombreField.setText("");
            asientoField.setText(""); 
        }
        

        actualizarLista();
    }

    private void actualizarLista() {
        listaTextArea.setText(listaPasajeros.obtenerTextoPasajeros());
    }
}
