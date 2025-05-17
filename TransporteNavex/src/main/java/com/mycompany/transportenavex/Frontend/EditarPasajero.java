package com.mycompany.transportenavex.Frontend;

import com.mycompany.transportenavex.Models.Pasajero;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarPasajero implements Initializable {
    Pasajero pasajero;
    Pasajero nuevoPasajero;
    @FXML
    private TextField nombreField;

    @FXML
    private TextField dpiField;

    @FXML
    private ComboBox asientoCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(this.pasajero != null){
            cargarPasajero();
        }
    }

    public void cargarPasajero(){
        nombreField.setText(this.pasajero.getNombre());
        dpiField.setText(this.pasajero.getDpi());
        asientoCombo.getSelectionModel().select((this.pasajero.getNumeroAsiento() - 1));
    }

    @FXML
    public void guardarPasajero(){
        this.nuevoPasajero = new Pasajero(dpiField.getText(), nombreField.getText(), Integer.parseInt(asientoCombo.getValue().toString()));

        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
        cargarPasajero();
    }

    @FXML
    public void cerrarVentana(){
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    public Pasajero getNuevoPasajero() {
        return nuevoPasajero;
    }
}
