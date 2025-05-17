package com.mycompany.transportenavex.Frontend;

import com.mycompany.transportenavex.Models.Pasajero;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarPasajero implements Initializable {

    Pasajero pasajeroCreado;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField dpiField;

    @FXML
    private ComboBox asientoCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void agregarPasajero() {
        String nombre = nombreField.getText();
        String dpi = dpiField.getText();
        int asiento = Integer.parseInt(asientoCombo.getValue().toString());

        Pasajero nuevoPasajero = new Pasajero(dpi, nombre, asiento);
        pasajeroCreado = nuevoPasajero;

        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cerrarVentana(){
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    public Pasajero getPasajeroCreado() {
        return pasajeroCreado;
    }
}
