package project3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;

public class TuitionManagerController {




    @FXML
    protected void readFile() {
        FileChooser fc = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("TEXT Files","*.txt");
        fc.getExtensionFilters().add(filter);
        File f = fc.showOpenDialog(null);

        if (f!= null) {
            System.out.println("worked?");
        } else {
            System.out.println("no work ._.");
        }
    }

}