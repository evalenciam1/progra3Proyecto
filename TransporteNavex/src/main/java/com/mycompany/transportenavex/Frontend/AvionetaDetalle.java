package com.mycompany.transportenavex.Frontend;

import com.mycompany.transportenavex.Models.Pasajero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvionetaDetalle implements Initializable {
    @FXML
    private ImageView avionetaImage1;

    @FXML
    private Label nombreAvioneta;

    @FXML
    private Label capacidadAvioneta;

    @FXML
    private Label descripcionAvioneta;

    @FXML
    private Label pasajerosActuales;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ComboBox<String> ordenComboBox;

    @FXML
    private Button agregarPasajeroBtn;

    @FXML private TableView<Pasajero> tablePasajeros;
    @FXML private TableColumn<Pasajero, String> colNombre;
    @FXML private TableColumn<Pasajero, String> colDPI;
    @FXML private TableColumn<Pasajero, Integer> colAsiento;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image avioneta1 = new Image(getClass().getResource("/assets/avioneta2.jpg").toExternalForm());
        avionetaImage1.setImage(avioneta1);
        nombreAvioneta.setText("Avioneta 1");
        capacidadAvioneta.setText("Capacidad: 10 pasajeros");
        descripcionAvioneta.setText("Descripción: Avioneta de transporte de carga y pasajeros.");
        pasajerosActuales.setText("Pasajeros actuales: 5");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDPI.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        colAsiento.setCellValueFactory(new PropertyValueFactory<>("numeroAsiento"));

        ObservableList<Pasajero> pasajeros = FXCollections.observableArrayList(
                new Pasajero("1234567890101", "Carlos López", 1),
                new Pasajero( "1234567890102", "María Pérez", 2),
                new Pasajero("1234567890103", "Juan García", 3)
        );

        tablePasajeros.setItems(pasajeros);
    }

    @FXML
    private void abrirVentanaAgregarPasajero() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/agregarPasajero.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        // Cargar y aplicar el archivo CSS al modal
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Hace que sea modal
        modalStage.setTitle("Agregar Pasajero");
        modalStage.setScene(scene);
        modalStage.showAndWait(); // Espera a que se cierre la ventana
    }

}
