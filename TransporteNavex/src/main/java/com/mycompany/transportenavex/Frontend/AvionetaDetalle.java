package com.mycompany.transportenavex.Frontend;

import com.mycompany.transportenavex.Controllers.AccionesController;
import com.mycompany.transportenavex.Controllers.CSV;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.Models.Pasajero;
import com.mycompany.transportenavex.TransporteNavex;
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
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    @FXML
    private Button cargarCSV;

    @FXML
    private Button guardarCSV;

    @FXML
    private TableView<Pasajero> tablePasajeros;
    @FXML
    private TableColumn<Pasajero, String> colNombre;
    @FXML
    private TableColumn<Pasajero, String> colDPI;
    @FXML
    private TableColumn<Pasajero, Integer> colAsiento;
    @FXML
    private TableColumn<Pasajero, Void> colAcciones;

    ListaDoblementeEnlazada listaPasajeros;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image avioneta1 = new Image(getClass().getResource("/assets/avioneta2.jpg").toExternalForm());
        avionetaImage1.setImage(avioneta1);
        nombreAvioneta.setText("Avioneta 1");
        capacidadAvioneta.setText("Capacidad: 10 pasajeros");
        descripcionAvioneta.setText("Descripción: Avioneta de transporte de carga y pasajeros.");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDPI.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        colAsiento.setCellValueFactory(new PropertyValueFactory<>("numeroAsiento"));
        agregarColumnaAcciones();
        // No llamar a recargarTabla() aquí
    }

    public void inicializar(int numeroAvioneta, ListaDoblementeEnlazada listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
        recargarTabla();
    }

    private void recargarTabla() {
        ObservableList<Pasajero> pasajeros = FXCollections.observableArrayList();
        List<Pasajero> pasajerosAsList = this.listaPasajeros.obtenerTodosPasjaeros();
        pasajeros.addAll(pasajerosAsList);

        pasajerosActuales.setText("Pasajeros actuales: " + pasajeros.size());
        System.out.println(pasajerosAsList);
        tablePasajeros.setItems(pasajeros);
        tablePasajeros.refresh();
    }

    public String agregarPasajero(Pasajero pasajero) {
        Pasajero nuevo = pasajero;
        String respuesta = AccionesController.agregarPasajero(this.listaPasajeros, nuevo);

        return respuesta;
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

        AgregarPasajero controller = fxmlLoader.getController();
        Pasajero pasajeroCreado = controller.getPasajeroCreado();
        String respuestaCreado = agregarPasajero(pasajeroCreado);
        if (respuestaCreado == "PASAJERO AGREGADO CORRECTAMENTE") {
            recargarTabla();
        }
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(respuestaCreado);
        alerta.showAndWait();

    }

    private void agregarColumnaAcciones() {
        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox contenedor = new HBox(10, btnEditar, btnEliminar);

            {
                btnEditar.getStyleClass().add("editar-btn");
                btnEliminar.getStyleClass().add("eliminar-btn");
                btnEditar.setOnAction(e -> {
                    Pasajero pasajero = getTableView().getItems().get(getIndex());
                    try {
                        abrirVentanaEditarPasajero(pasajero);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                });
                btnEliminar.setOnAction(e -> {
                    Pasajero pasajero = getTableView().getItems().get(getIndex());
                    // Acción eliminar
                    eliminarPasajero(pasajero.getDpi());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(contenedor);
                }
            }
        });
    }

    public void eliminarPasajero(String dpi) {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro de que desea eliminar al pasajero con DPI: " + dpi + "?");

        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No");

        alerta.getButtonTypes().setAll(botonSi, botonNo);

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isEmpty() || resultado.get() == botonNo) {
            return;
        }

        boolean eliminado = AccionesController.eliminarPasajero(this.listaPasajeros, dpi);
        if (eliminado) {
            recargarTabla();
        }
    }

    public void mostrarVentanaPrincipal() {
        TransporteNavex.mostrarVentanaPrincipal();
    }

    public void abrirVentanaEditarPasajero(Pasajero pasajero) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editarPasajero.fxml"));
        Parent root = fxmlLoader.load();

        EditarPasajero controller = fxmlLoader.getController();
        controller.setPasajero(pasajero); // Cambia esto por el DPI del pasajero que deseas editar

        Scene scene = new Scene(root);

        // Cargar y aplicar el archivo CSS al modal
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Hace que sea modal
        modalStage.setTitle("Agregar Pasajero");
        modalStage.setScene(scene);
        modalStage.showAndWait(); // Espera a que se cierre la ventana

        Pasajero nuevoPasajero = controller.getNuevoPasajero();
        System.out.println("Pasajero editado: " + nuevoPasajero);
        boolean response = AccionesController.modificarPasajero(this.listaPasajeros, nuevoPasajero.getDpi(), nuevoPasajero.getNombre(), nuevoPasajero.getNumeroAsiento());
        System.out.println(this.listaPasajeros.obtenerTextoPasajeros());
        if (response) {
            recargarTabla();
        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("No se pudo actualizar el pasajero");
            alerta.showAndWait();
        }
    }

    public ListaDoblementeEnlazada getListaPasajeros() {
        return listaPasajeros;
    }

    @FXML
    private void reordenarLista() {
        int index = ordenComboBox.getSelectionModel().getSelectedIndex();

        ObservableList<Pasajero> pasajeros = FXCollections.observableArrayList();
        List<Pasajero> pasajerosAsList = this.listaPasajeros.obtenerTodosPasjaeros();

        if (index == 1) {
            Collections.reverse(pasajerosAsList);
        }

        pasajeros.addAll(pasajerosAsList);

        pasajerosActuales.setText("Pasajeros actuales: " + pasajeros.size());
        System.out.println(pasajerosAsList);

        tablePasajeros.setItems(pasajeros);
        tablePasajeros.refresh();
    }

    @FXML
    private void filtrar() {
        String dpi = campoBusqueda.getText().trim();

        if (dpi.isEmpty()) {
            recargarTabla();
            return;
        }

        ObservableList<Pasajero> pasajeros = FXCollections.observableArrayList();
        List<Pasajero> pasajerosAsList = this.listaPasajeros.obtenerTodosPasjaeros()
                .stream()
                .filter(p -> p.getDpi().contains(dpi))
                .toList();

        pasajeros.addAll(pasajerosAsList);

        pasajerosActuales.setText("Pasajeros actuales: " + pasajeros.size());
        System.out.println(pasajerosAsList);

        tablePasajeros.setItems(pasajeros);
        tablePasajeros.refresh();
    }

    @FXML
    private void cargarCSV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        // (Opcional) establecer filtros
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Archivos de texto (*.scv)", "*.csv");
        fileChooser.getExtensionFilters().add(filtro);

        // Obtener la ventana actual (Stage)
        Stage stage = (Stage) cargarCSV.getScene().getWindow(); // Reemplaza tuNodo por cualquier nodo de la vista (ej. un botón o un TextField)

        File archivoSeleccionado = fileChooser.showOpenDialog(stage);
        if (archivoSeleccionado != null) {
            System.out.println("Archivo seleccionado: " + archivoSeleccionado.getAbsolutePath());
            CSV.cargarDesdeArchivoCSV(archivoSeleccionado, this.listaPasajeros);
            recargarTabla();
        }
    }

    @FXML
    private void guardarCSV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo CSV");

        // Filtro para archivos CSV
        FileChooser.ExtensionFilter filtroCSV = new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(filtroCSV);

        // Establecer nombre por defecto
        fileChooser.setInitialFileName("pasajeros.csv");

        // Obtener el Stage desde algún nodo de la interfaz
        Stage stage = (Stage) guardarCSV.getScene().getWindow(); // reemplaza con un @FXML real, como un botón

        File archivoSeleccionado2 = fileChooser.showSaveDialog(stage);

        if (archivoSeleccionado2 != null) {
            CSV.guardarEnArchivoCSV(archivoSeleccionado2, listaPasajeros);
        }
    }



}
