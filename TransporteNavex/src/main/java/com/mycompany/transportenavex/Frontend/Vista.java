package com.mycompany.transportenavex.Frontend;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Vista implements Initializable {

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
}
