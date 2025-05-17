package com.mycompany.transportenavex.Frontend;
import com.mycompany.transportenavex.Models.ListaDoblementeEnlazada;
import com.mycompany.transportenavex.TransporteNavex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaPrincipal implements Initializable {

    ListaDoblementeEnlazada listaAvioneta1;
    ListaDoblementeEnlazada listaAvioneta2;
    ListaDoblementeEnlazada listaAvioneta3;

    @FXML
    private ImageView logoImage;

    @FXML
    private ImageView avionetaImage1;

    @FXML
    private ImageView avionetaImage2;

    @FXML
    private ImageView avionetaImage3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResource("/assets/LogoBlanco.jpg").toExternalForm());
        Image avioneta1 = new Image(getClass().getResource("/assets/avioneta2.jpg").toExternalForm());
        Image avioneta2 = new Image(getClass().getResource("/assets/avioneta2.jpg").toExternalForm());
        Image avioneta3 = new Image(getClass().getResource("/assets/avioneta2.jpg").toExternalForm());
        logoImage.setImage(image);
        avionetaImage1.setImage(avioneta1);
        avionetaImage2.setImage(avioneta2);
        avionetaImage3.setImage(avioneta3);
    }

    public void setListaAvioneta1(ListaDoblementeEnlazada listaAvioneta1) {
        this.listaAvioneta1 = listaAvioneta1;
    }

    public void setListaAvioneta2(ListaDoblementeEnlazada listaAvioneta2) {
        this.listaAvioneta2 = listaAvioneta2;
    }

    public void setListaAvioneta3(ListaDoblementeEnlazada listaAvioneta3) {
        this.listaAvioneta3 = listaAvioneta3;
    }

    public void detalleAvioneta1() {
        TransporteNavex.mostrarAvionetaDetalle(1, this.listaAvioneta1);
    }

    public void detalleAvioneta2() {
        TransporteNavex.mostrarAvionetaDetalle(2, this.listaAvioneta2);
    }

    public void detalleAvioneta3() {
        TransporteNavex.mostrarAvionetaDetalle(3, this.listaAvioneta3);
    }
}
