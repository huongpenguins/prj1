package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class PrimaryController {
    @FXML
    AnchorPane anchor;
    @FXML
    Button start;
    @FXML
    Button highPoint;
   
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
   

}
