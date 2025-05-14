package com.mycompany.transportenavex.Frontend;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mycompany.transportenavex.Controllers.AccionesController;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

//import java.awt.*;
//import java.awt.event.*;

public class agregarPasajero extends JFrame {
    private int numeroAvioneta;
    private ListaDoblementeEnlazada listaPasajeros;
    private JComboBox<Integer> asientoComboBox;


    // Elementos visuales
    private JTextField dpiField;
    private JTextField nombreField;
    private JTextField asientoField;
    private JTextArea listaTextArea;

    public agregarPasajero(int numeroavioneta, ListaDoblementeEnlazada listaPasajeros) {
        this.numeroAvioneta = numeroavioneta;
        this.listaPasajeros = listaPasajeros;

        //Configuracion del Jframe
        setTitle("Agregar Pasajero" + numeroAvioneta);
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
        asientoComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            asientoComboBox.addItem(i);
        }


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
        panel.add(asientoComboBox);
        panel.add(Box.createVerticalStrut(10));

        // Botón agregar
        JButton agregarButton = new JButton("Agregar pasajero");
        agregarButton.addActionListener(e -> agregandoPasajero());
        panel.add(agregarButton);

        panel.add(Box.createVerticalStrut(15));

        // Agregar panel
        add(panel);
    }

    private void agregandoPasajero() {
        String dpi = dpiField.getText().trim();
        String nombre = nombreField.getText().trim();
        int asiento=(int) asientoComboBox.getSelectedItem();

        Pasajero nuevo = new Pasajero(dpi, nombre, asiento);
        String respuesta = AccionesController.agregarPasajero(listaPasajeros, nuevo);
        JOptionPane.showMessageDialog(this, respuesta);

        if (respuesta=="PASAJERO AGREGADO CORRECTAMENTE") {
            dpiField.setText("");
            nombreField.setText("");
        }
        dispose();
        new VentanaAvioneta(numeroAvioneta, listaPasajeros);
           dispose();

    }

}