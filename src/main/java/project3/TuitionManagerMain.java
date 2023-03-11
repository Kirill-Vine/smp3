package project3;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TuitionManagerMain extends Application {
    void intializeFileMenus(Menu menuFile) {
        Menu readFileFileSubMenu = new Menu("Read File");
        menuFile.getItems().add(readFileFileSubMenu);
    }
    void initializeEnrollmentMenus(Menu menuEnrollment) {
        Menu enrollStudentEnrollmentSubMenu = new Menu("Enroll Student");
        Menu dropStudentEnrollmentSubMenu = new Menu("Drop Student");
        Menu viewEnrollmentEnrollmentSubMenu = new Menu("View Enrollment");
        Menu viewTuitionEnrollmentSubMenu = new Menu("View Tuition");
        Menu endSemesterEnrollmentSubMenu = new Menu("End Semester");
        menuEnrollment.getItems().add(enrollStudentEnrollmentSubMenu);
        menuEnrollment.getItems().add(dropStudentEnrollmentSubMenu);
        menuEnrollment.getItems().add(viewTuitionEnrollmentSubMenu);
        menuEnrollment.getItems().add(endSemesterEnrollmentSubMenu);
    }
    void initializeViewMenus(Menu menuView) {
        Menu sortListViewSubMenu = new Menu("Sort List");
        Menu nameSortSubMenu = new Menu("Sort by Name");
        Menu schoolSortSubMenu = new Menu("Sort by School");
        Menu standingSortSubMenu = new Menu("Sort by Standing");
        Menu viewSchoolViewSubMenu = new Menu("View School");
        menuView.getItems().add(sortListViewSubMenu);
        sortListViewSubMenu.getItems().add(nameSortSubMenu);
        sortListViewSubMenu.getItems().add(schoolSortSubMenu);
        sortListViewSubMenu.getItems().add(standingSortSubMenu);
        sortListViewSubMenu.getItems().add(viewSchoolViewSubMenu);
    }
    void initializeEditMenus(Menu menuEdit) {
        Menu addStudentEditSubMenu = new Menu("Add Student");
        Menu removeStudentEditSubMenu = new Menu("Remove Student");
        Menu changeMajorEditSubMenu = new Menu("Change Major");
        menuEdit.getItems().add(addStudentEditSubMenu);
        menuEdit.getItems().add(removeStudentEditSubMenu);
        menuEdit.getItems().add(changeMajorEditSubMenu);
    }
    private void initializeMenus(MenuBar menuBar) {
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuView = new Menu("View");
        Menu menuEnrollment = new Menu("Enrollment");
        menuBar.getMenus().addAll(menuFile,menuEdit,menuView,menuEnrollment);
        intializeFileMenus(menuFile);
        initializeEditMenus(menuEdit);
        initializeViewMenus(menuView);
        initializeEnrollmentMenus(menuEnrollment);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerMain.class.getResource("TuitionManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        MenuBar menuBar = new MenuBar();
        Pane pane = new Pane();
        initializeMenus(menuBar);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}