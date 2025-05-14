package com.mycompany.transportenavex.Frontend;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mycompany.transportenavex.Controllers.AccionesController;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;

public class modificarPasajero extends JFrame {
    private int numeroAvioneta;
    private ListaDoblementeEnlazada listaPasajeros;

    private JComboBox<String> pasajeroComboBox;
    private JTextField nombreField;
    private JComboBox<Integer> asientoComboBox;

    public modificarPasajero(int numeroAvioneta, ListaDoblementeEnlazada listaPasajeros) {
        this.numeroAvioneta = numeroAvioneta;
        this.listaPasajeros = listaPasajeros;

        setTitle("Modificar Pasajero " + numeroAvioneta);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pasajeroComboBox = new JComboBox<>(getNombreList().toArray(new String[0]));
        pasajeroComboBox.addActionListener(e -> cargarDatosPasajero());

        panel.add(new JLabel("Seleccione un pasajero:"));
        panel.add(pasajeroComboBox);
        panel.add(Box.createVerticalStrut(10));

        nombreField = new JTextField(15);
        asientoComboBox = new JComboBox<>();

        for (int i = 1; i <= 10; i++) {
            asientoComboBox.addItem(i);
        }

        panel.add(new JLabel("Nuevo nombre:"));
        panel.add(nombreField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Nuevo número de asiento:"));
        panel.add(asientoComboBox);
        panel.add(Box.createVerticalStrut(10));

        JButton modificarButton = new JButton("Modificar pasajero");
        modificarButton.addActionListener(e -> modificarPasajero());
        panel.add(modificarButton);

        add(panel);
    }

    private void cargarDatosPasajero() {
        String nombreSeleccionado = (String) pasajeroComboBox.getSelectedItem();

        if (nombreSeleccionado != null) {
            Pasajero pasajero = getPasajeroPorNombre(nombreSeleccionado);

            if (pasajero != null) {
                nombreField.setText(pasajero.getNombre());
                asientoComboBox.setSelectedItem(pasajero.getNumeroAsiento());
            }
        }
    }

    private void modificarPasajero() {
        String nombreSeleccionado = (String) pasajeroComboBox.getSelectedItem();

        if (nombreSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un pasajero.");
            return;
        }

        String nuevoNombre = nombreField.getText().trim();
        Integer nuevoAsiento = (Integer) asientoComboBox.getSelectedItem();
        String dpi = getDpiPorNombre(nombreSeleccionado);

        if (dpi == null) {
            JOptionPane.showMessageDialog(this, "Error al obtener el DPI del pasajero.");
            return;
        }

        if (nuevoNombre == null || nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            return;
        }

        boolean modificado = AccionesController.modificarPasajero(listaPasajeros, dpi, nuevoNombre, nuevoAsiento);

        if (modificado) {
            JOptionPane.showMessageDialog(this, "Pasajero modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar el pasajero. Verifique el asiento.");
        }

        dispose();
        new VentanaAvioneta(numeroAvioneta, listaPasajeros);
    }

    private List<String> getNombreList() {
        List<String> nombreList = new ArrayList<>();

        for (Pasajero pasajero : listaPasajeros.obtenerTodosPasjaeros()) {
            nombreList.add(pasajero.getNombre());
        }

        return nombreList;
    }

    private Pasajero getPasajeroPorNombre(String nombre) {
        for (Pasajero pasajero : listaPasajeros.obtenerTodosPasjaeros()) {
            if (pasajero.getNombre().equals(nombre)) {
                return pasajero;
            }
        }

        return null;
    }

    private String getDpiPorNombre(String nombre) {
        Pasajero pasajero = getPasajeroPorNombre(nombre);

        if (pasajero != null) {
            return pasajero.getDpi();
        } else {
            return null;
        }
    }
}
