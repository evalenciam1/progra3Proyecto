/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.transportenavex;

import com.mycompany.transportenavex.Frontend.AvionetaDetalle;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Esteban Valencia
 */
public class TransporteNavex extends Application{
    private static Stage primaryStageRef;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle("Transporte Navex");
        stage.setScene(scene);
        stage.show();

        primaryStageRef = stage;
    }

    public static void main(String[] args) {
        //new VentanaPrincipal();
        launch(args);
    }

    public static void mostrarAvionetaDetalle(int idAvioneta) {
        try {
            FXMLLoader loader = new FXMLLoader(TransporteNavex.class.getResource("/AvionetaDetalle.fxml"));
            Parent root = loader.load();

            AvionetaDetalle controller = loader.getController();
            controller.inicializar(idAvioneta);

            Scene scene = new Scene(root);
            primaryStageRef.setScene(scene);
            primaryStageRef.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(TransporteNavex.class.getResource("/vista.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStageRef.setScene(scene);
            primaryStageRef.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

