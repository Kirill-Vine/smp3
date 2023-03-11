package project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class TuitionManagerController {


    @FXML
    private Label welcomeText;

    @FXML
    protected void testEvent() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}