/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.transportenavex;

import com.mycompany.transportenavex.Frontend.AvionetaDetalle;
import com.mycompany.transportenavex.Frontend.VistaPrincipal;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author Esteban Valencia
 */
public class TransporteNavex extends Application {
    private static Stage primaryStageRef;
    public static ListaDoblementeEnlazada avioneta1 = new ListaDoblementeEnlazada(10);
    public static ListaDoblementeEnlazada avioneta2 = new ListaDoblementeEnlazada(10);
    public static ListaDoblementeEnlazada avioneta3 = new ListaDoblementeEnlazada(10);
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista.fxml"));
        Parent root = loader.load();
        VistaPrincipal controller = loader.getController();
        controller.setListaAvioneta1(avioneta1);
        controller.setListaAvioneta2(avioneta2);
        controller.setListaAvioneta3(avioneta3);
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

    public static void mostrarAvionetaDetalle(int idAvioneta, ListaDoblementeEnlazada listaPasajeros) {
        try {
            FXMLLoader loader = new FXMLLoader(TransporteNavex.class.getResource("/AvionetaDetalle.fxml"));
            Parent root = loader.load();

            AvionetaDetalle controller = loader.getController();
            controller.inicializar(idAvioneta, listaPasajeros);

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

            VistaPrincipal controller = loader.getController();
            controller.setListaAvioneta1(avioneta1);
            controller.setListaAvioneta2(avioneta2);
            controller.setListaAvioneta3(avioneta3);

            Scene scene = new Scene(root);
            primaryStageRef.setScene(scene);
            primaryStageRef.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

