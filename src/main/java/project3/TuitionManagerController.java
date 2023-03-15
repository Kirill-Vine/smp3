package project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TuitionManagerController {

    //Add Student UI Elements
    public VBox nonResidentVBox;
    public CheckBox internationalCheckBox;
    public HBox triStateHBox;
    public RadioButton internationalButton;
    public RadioButton triStateButton;
    public TextField fNameText;
    public TextField lNameText;
    public DatePicker dobPicker;
    public TextField creditsText;
    public RadioButton csButton;
    public RadioButton mathButton;
    public RadioButton eeButton;
    public RadioButton itiButton;
    public RadioButton baitButton;
    public RadioButton nyButton;
    public RadioButton ctButton;
    public RadioButton njButton;
    public Label errorMessage;
    public Label rosterStudent1;
    public Label rosterStudent2;
    public Label rosterStudent3;
    public Label rosterStudent4;
    public Label rosterStudent5;
    public Label rosterStudent6;
    public Label rosterStudent7;
    public Label rosterStudent8;
    Label[] rosterLabelList = new Label[8];
    public Button rosterRemoveButton1;
    public Button rosterRemoveButton2;
    public Button rosterRemoveButton3;
    public Button rosterRemoveButton4;
    public Button rosterRemoveButton5;
    public Button rosterRemoveButton6;
    public Button rosterRemoveButton7;
    public Button rosterRemoveButton8;
    Button[] removeButtonList = new Button[8];
    public Button rosterEnrollButton1;
    public Button rosterEnrollButton2;
    public Button rosterEnrollButton3;
    public Button rosterEnrollButton4;
    public Button rosterEnrollButton5;
    public Button rosterEnrollButton6;
    public Button rosterEnrollButton7;
    public Button rosterEnrollButton8;
    Button[] enrollButtonList = new Button[8];
    public MenuButton rosterMajorButton1;
    public MenuButton rosterMajorButton2;
    public MenuButton rosterMajorButton3;
    public MenuButton rosterMajorButton4;
    public MenuButton rosterMajorButton5;
    public MenuButton rosterMajorButton6;
    public MenuButton rosterMajorButton7;
    public MenuButton rosterMajorButton8;
    MenuButton[] majorButtonList = new MenuButton[8];


    Date dob;
    Major major;
    int creditsCompleted;
    int currentPage = 1;
    Roster roster = new Roster();
    //god help me I'm so sorry Student Roster


//UI events Controls for Add Student
    @FXML
    protected void toggleNonResidentVBox() {
        if(nonResidentVBox.isDisabled()) {
            nonResidentVBox.setDisable(false);
        } else {
            nonResidentVBox.setDisable(true);
        }
    }
    @FXML protected void toggleTriStateInternational() {
        toggleInternationalCheckBox();
        toggleTriStateHBox();
    }
    @FXML protected void toggleInternationalCheckBox() {
        if(internationalButton.isSelected()) {
            internationalCheckBox.setDisable(false);
        } else {
            internationalCheckBox.setDisable(true);
        }
    }
    @FXML protected void toggleTriStateHBox() {
        if(triStateButton.isSelected()) {
            triStateHBox.setDisable(false);
        } else {
            triStateHBox.setDisable(true);
        }
    }

    void updateErrorMessage(String s) {
        errorMessage.setText(s);
    }

    //adding student methods
    public void updatedob() {
        LocalDate date = dobPicker.getValue();
        String day = ""+date.getDayOfMonth();
        String month = "" + date.getMonthValue();
        String year = "" + date.getYear();
        dob = new Date(day + "/" + month + "/" + year);
    }
    public void updateCredits() {
        try {
            creditsCompleted = Integer.parseInt(creditsText.getText());
        } catch(NumberFormatException e) {
            creditsCompleted = -1;
        }
        if(creditsCompleted < 0 || creditsText.getText().equals("")) {
            creditsCompleted = -1;
        }
    }
    public void updateMajor() {
        if(csButton.isSelected()) {
            major = Major.CS;
        } else if(mathButton.isSelected()) {
            major = Major.MATH;
        }else if(eeButton.isSelected()) {
            major = Major.EE;
        }else if(itiButton.isSelected()) {
            major = Major.ITI;
        }else if(baitButton.isSelected()) {
            major = Major.BAIT;
        }
    }

    private String getStateString() {
        if(njButton.isSelected()) {
            return njButton.getText();
        } else if(nyButton.isSelected()) {
            return nyButton.getText();
        } else if(ctButton.isSelected()) {
            return njButton.getText();
        } else {
            return "";
        }
    }

    void resetStudentVariables() {
        fNameText.setText("");
        lNameText.setText("");
        dobPicker.getEditor().clear();
        creditsText.setText("");
        creditsCompleted = -1;
        major=  null;
    }
    boolean studentError() {
        updateMajor();
        if(fNameText.getText().equals("")) {
            errorMessage.setText("Student must have a first name");
            return false;
        } else if(lNameText.getText().equals("")) {
            errorMessage.setText("Student must have a last name");
            return false;
        } else if(dobPicker.getValue() == null) {
            errorMessage.setText("Student must have a date of birth");
            return false;
        } else if(major == null) {
            errorMessage.setText("Student must have a major");
            return false;
        } else if (creditsCompleted == -1) {
            errorMessage.setText("Completed Credits must be a positive integer");
            return false;
        }
        return true;
    }

    public void addStudent() {
        updateCredits();
        if(!studentError()){
            return;
        }
        Profile profile = new Profile(fNameText.getText(),lNameText.getText(),dob);
        if(nonResidentVBox.isDisabled()) {
            Resident res = new Resident(profile,major,creditsCompleted);
            System.out.println(res);
            roster.add(res);
        } else {
            if(internationalButton.isSelected()) {
                International international = new International(profile,major,creditsCompleted,internationalCheckBox.isSelected());
                roster.add(international);
            } else if(triStateButton.isSelected()) {
                String state = getStateString();
                if (state.equals("")) {
                    updateErrorMessage("TriState students must be in one of the selected states");
                    return;
                }
                TriState triState = new TriState(profile, major, creditsCompleted,state);
                roster.add(triState);
            } else {
                NonResident nonResident = new NonResident(profile,major,creditsCompleted);
                roster.add(nonResident);
            }
        }
        updateErrorMessage("Student Added");
        resetStudentVariables();
    }
 // roster methods------------
    public void removeButton1() {

    }
    //stupid methods because of javafx ;-;

    void initializeRosterLabelList() {
        rosterLabelList[0] = rosterStudent1;
        rosterLabelList[1] = rosterStudent2;
        rosterLabelList[2] = rosterStudent3;
        rosterLabelList[3] = rosterStudent4;
        rosterLabelList[4] = rosterStudent5;
        rosterLabelList[5] = rosterStudent6;
        rosterLabelList[6] = rosterStudent7;
        rosterLabelList[7] = rosterStudent8;
    }
    void initializeRemoveButtonList() {
        removeButtonList[0] = rosterRemoveButton1;
        removeButtonList[1] = rosterRemoveButton2;
        removeButtonList[2] = rosterRemoveButton3;
        removeButtonList[3] = rosterRemoveButton4;
        removeButtonList[4] = rosterRemoveButton5;
        removeButtonList[5] = rosterRemoveButton6;
        removeButtonList[6] = rosterRemoveButton7;
        removeButtonList[7] = rosterRemoveButton8;
    }
    void initializeEnrollButtonList() {
        enrollButtonList[0] = rosterEnrollButton1;
        enrollButtonList[1] = rosterEnrollButton2;
        enrollButtonList[2] = rosterEnrollButton3;
        enrollButtonList[3] = rosterEnrollButton4;
        enrollButtonList[4] = rosterEnrollButton5;
        enrollButtonList[5] = rosterEnrollButton6;
        enrollButtonList[6] = rosterEnrollButton7;
        enrollButtonList[7] = rosterEnrollButton8;
    }
    void initializeMajorButtonList(){
        majorButtonList[0] = rosterMajorButton1;
        majorButtonList[1] = rosterMajorButton2;
        majorButtonList[2] = rosterMajorButton3;
        majorButtonList[3] = rosterMajorButton4;
        majorButtonList[4] = rosterMajorButton5;
        majorButtonList[5] = rosterMajorButton6;
        majorButtonList[6] = rosterMajorButton7;
        majorButtonList[7] = rosterMajorButton8;
    }
    public void updateRoster() {
        initializeRosterLabelList();
        initializeMajorButtonList();
        initializeEnrollButtonList();
        initializeRemoveButtonList();
        Student[] tempRoster = roster.getRoster();
        for(int i = 0; i < rosterLabelList.length; i++) {
            int currentIndex = (currentPage-1)*rosterLabelList.length+i;
            if(tempRoster.length > currentIndex && tempRoster[currentIndex] != null) {
                rosterLabelList[i].setText(tempRoster[currentIndex].toString());
                majorButtonList[i].setVisible(true);
                enrollButtonList[i].setVisible(true);
                removeButtonList[i].setVisible(true);
            } else if(rosterLabelList[i] != null){
                rosterLabelList[i].setText("");
                majorButtonList[i].setVisible(false);
                enrollButtonList[i].setVisible(false);
                removeButtonList[i].setVisible(false);
            }
        }
    }
    public void removeStudent(int i) {
        roster.remove(roster.getRoster()[(currentPage-1)*rosterLabelList.length + i]);
        updateRoster();
    }
    public void removeSlot0() {
        removeStudent(0);

    }
    public void removeSlot1() {
        removeStudent(1);
    }
    public void removeSlot2() {
        removeStudent(2);
    }
    public void removeSlot3() {
        removeStudent(3);
    }
    public void removeSlot4() {
        removeStudent(4);
    }
    public void removeSlot5() {
        removeStudent(5);
    }
    public void removeSlot6() {
        removeStudent(6);
    }
    public void removeSlot7() {
        removeStudent(7);
    }






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