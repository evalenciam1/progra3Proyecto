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

import com.mycompany.transportenavex.Controllers.AccionesController;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;



public class eliminarPasajero extends  JFrame {
   private int numeroAvioneta;
   private ListaDoblementeEnlazada listaPasajeros;
   private JComboBox<String> pasajerComboBox;

   // private JTextField dpiField; 

    public eliminarPasajero(int numeroAvioneta,ListaDoblementeEnlazada listaPasajeros)
   {
        this.numeroAvioneta = numeroAvioneta;
        this.listaPasajeros = listaPasajeros;

         setTitle("Eliminar Pasajero" + numeroAvioneta);
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

        //Creacion de comboBox nombres de los pasajeros
        List<String> nombreList = getNombreList();
        pasajerComboBox = new JComboBox<>(nombreList.toArray(new String[0]));
        panel.add(new JLabel("Seleccione un pasajero"));
        panel.add(pasajerComboBox);
        panel.add(Box.createVerticalStrut(10));

        /* Campos de entrada
        dpiField = new JTextField(15);
        panel.add(new JLabel("DPI:"));
        panel.add(dpiField);
        panel.add(Box.createVerticalStrut(10)); */

         JButton EliminarButton = new JButton("Eliminar pasajero");
        EliminarButton.addActionListener(e -> eliminardpiPasajero());
        panel.add(EliminarButton);

        panel.add(Box.createVerticalStrut(15));

        // Agregar panel
        add(panel);
    }

    private void eliminardpiPasajero(){
        String nombreSeleccionado = (String) pasajerComboBox.getSelectedItem();
    if(nombreSeleccionado == null || nombreSeleccionado.isEmpty()){
        JOptionPane.showMessageDialog(this, "Porfavor seleccione un pasajero");
        return;
    }
        String dpi = getNombreDpi(nombreSeleccionado);

        if(dpi.isEmpty()){
            JOptionPane.showMessageDialog(this, "Porfavor ingrese un dpi");
            return;
        }
        boolean eliminado = AccionesController.eliminarPasajero(listaPasajeros, dpi);
        String respuesta ;
        if(eliminado){
            respuesta = "Pasajero eliminado correctamente";
        }else{
            respuesta = "No se ncontro el pasajero con el dpi";
        }
         JOptionPane.showMessageDialog(this, respuesta);
        dispose();
        new VentanaAvioneta(numeroAvioneta, listaPasajeros);
   dispose();
    }

    private List<String>getNombreList(){
        List<String> nombreList= new ArrayList<>();
        for(Pasajero p : listaPasajeros.obtenerTodosPasjaeros()){
            nombreList.add((p.getNombre()));
        }
        return nombreList;
    }

    private String getNombreDpi(String nombre){
        for(Pasajero p: listaPasajeros.obtenerTodosPasjaeros()){
            if(p.getNombre().equals(nombre)){
                return p.getDpi();
            }
        }
        return  null;
    }
}
