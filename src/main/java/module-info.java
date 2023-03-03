module com.example.tuitionmanagermain {
    requires javafx.controls;
    requires javafx.fxml;


    opens project3 to javafx.fxml;
    exports project3;
}