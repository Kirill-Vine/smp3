package project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * This class contains event handlers
 *
 * @author Michael Burton
 * @author Kirill Vine
 */
public class TuitionManagerController {
    //constants
    final static int ADD_COMMAND_SIZE = 6;
    final static int MIN_AGE = 16;
    /**
     * Adds student elements
     */
    Date dob;
    Major major;
    int creditsCompleted;
    Scanner sc;

    /**
     * Adds Student UI Elements
    */
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
    //roster variables
    Roster roster = new Roster();
    Student[] studentList = new Roster().getRoster();
    int currentPage = 1;
    int maxPage = 1;
    public RadioButton csRosterButton;
    public RadioButton mathRosterButton;
    public RadioButton eeRosterButton;
    public RadioButton itiRosterButton;
    public RadioButton baitRosterButton;
    public Button nextPage;
    public Button prevPage;
    public Label pageNum;
    public MenuButton sortButton;
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
    public Button rosterMajorButton1;
    public Button rosterMajorButton2;
    public Button rosterMajorButton3;
    public Button rosterMajorButton4;
    public Button rosterMajorButton5;
    public Button rosterMajorButton6;
    public Button rosterMajorButton7;
    public Button rosterMajorButton8;
    Button[] majorButtonList = new Button[8];
    /**
     * Enrollment variables
     */
    Enrollment enrollment = new Enrollment();
    int currentEnrollPage = 1;
    int maxEnrollPage = 1;
    public Label pageNumEnroll;
    public Button nextPageEnroll;
    public Button prevPageEnroll;
    public Label enrollStudent1;
    public Label enrollStudent2;
    public Label enrollStudent3;
    public Label enrollStudent4;
    public Label enrollStudent5;
    public Label enrollStudent6;
    public Label enrollStudent7;
    public Label enrollStudent8;
    Label[] enrollStudentList = new Label[8];
    public Label tuitionLabel1;
    public Label tuitionLabel2;
    public Label tuitionLabel3;
    public Label tuitionLabel4;
    public Label tuitionLabel5;
    public Label tuitionLabel6;
    public Label tuitionLabel7;
    public Label tuitionLabel8;
    Label[] tuitionLabelList = new Label[8];
    public TextField enrollCredits1;
    public TextField enrollCredits2;
    public TextField enrollCredits3;
    public TextField enrollCredits4;
    public TextField enrollCredits5;
    public TextField enrollCredits6;
    public TextField enrollCredits7;
    public TextField enrollCredits8;
    TextField[] enrollCreditsList = new TextField[8];
    public TextField scholarship1;
    public TextField scholarship2;
    public TextField scholarship3;
    public TextField scholarship4;
    public TextField scholarship5;
    public TextField scholarship6;
    public TextField scholarship7;
    public TextField scholarship8;
    TextField[] scholarshipList = new TextField[8];
    public Button dropButton1;
    public Button dropButton2;
    public Button dropButton3;
    public Button dropButton4;
    public Button dropButton5;
    public Button dropButton6;
    public Button dropButton7;
    public Button dropButton8;
    Button[] dropButtonList = new Button[8];


    /**
     * UI events Controls for Add Student
     */
    @FXML
    protected void toggleNonResidentVBox() {
        if(nonResidentVBox.isDisabled()) {
            nonResidentVBox.setDisable(false);
        } else {
            nonResidentVBox.setDisable(true);
        }
    }

    /**
     * toggles whether tristate checkbox is disabled
     */
    @FXML protected void toggleTriStateInternational() {
        toggleInternationalCheckBox();
        toggleTriStateHBox();
    }

    /**
     * Changes whether international checkbox is disabled
     */
    @FXML protected void toggleInternationalCheckBox() {
        if(internationalButton.isSelected()) {
            internationalCheckBox.setDisable(false);
        } else {
            internationalCheckBox.setDisable(true);
        }
    }

    /**
     * Sets HBox holding tristate variables depending on whether tristate is selected
     */
    @FXML protected void toggleTriStateHBox() {
        if(triStateButton.isSelected()) {
            triStateHBox.setDisable(false);
        } else {
            triStateHBox.setDisable(true);
        }
    }

    /**
     * Gives an error message
     * @param s
     */
    void updateErrorMessage(String s) {
        errorMessage.setText(s);
    }

    /**
     * Updates student's dob
     *
     */
    public void updatedob() {
        LocalDate date = dobPicker.getValue();
        String day = ""+date.getDayOfMonth();
        String month = "" + date.getMonthValue();
        String year = "" + date.getYear();
        dob = new Date(month + "/" + day + "/" + year);
    }

    /**
     * Updates student's credits
     */
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

    /**
     * Updates Major
     */
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

    /**
     * Returns the state the student selected by the UI
     * @return State as a string
     */
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

    /**
     * Resets ui elements for add student tabl
     */
    void resetStudentVariables() {
        fNameText.setText("");
        lNameText.setText("");
        dobPicker.getEditor().clear();
        creditsText.setText("");
        creditsCompleted = -1;
        major=  null;
    }

    /**
     * Tests student's DOB
     * @return Whether DOB is valid
     */
    boolean testDOB() {
        errorMessage.setText("Student is less than 16 years old");
        LocalDate today = LocalDate.now();
        LocalDate tempDOB = dobPicker.getValue();
        boolean output = true;
        if(today.getYear()-MIN_AGE < tempDOB.getYear()){
            output = false;
        } else if(today.getYear()-MIN_AGE == tempDOB.getYear()){
            if(today.getMonthValue() < tempDOB.getMonthValue()) {
                output = false;
            } else if(today.getMonthValue() == tempDOB.getMonthValue()) {
                if(today.getDayOfMonth() < tempDOB.getDayOfMonth()) {
                    output = false;
                }
            }
        }
        if(!output) {
            errorMessage.setText("Student is less than 16 years old");
        }
        return output;
    }


    /**
     * Create Date object from a list of strings.
     * @param inputString string to be converted into date
     * @return date object if string is valid, null if not.
     */
    private static Date setDate(String inputString) {
        Date dob = new Date(inputString);
        Date today = new Date();
        if (!dob.isValid()) {
            return null;
        }
        if (today.compareTo(dob) < MIN_AGE) {
            return null;
        }
        return dob;
    }

    /**
     * converts a string into an int
     * @param inputString string to be turned into credits int.
     * @return valid credits amount if string is valid, and -1 if not.
     */
    private static int setCredits(String inputString) {
        int credits;
        try {
            credits = Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        if (credits < 0) {
            return -1;
        }
        return credits;
    }

    /**
     * convert string into Resident student
     * @param inputStringList String array to be converted to Resident.
     * @return Resident object if string array is valid, null if not.
     */
    private static Resident setStudentResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            return new Resident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        }
        return null;
    }

    /**
     * convert string into NonResident student
     * @param inputStringList String array to be converted to NonResident.
     * @return NonResident object if string array is valid, null if not.
     */
    private static NonResident setStudentNonResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            return new NonResident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        }

        return null;
    }

    /**
     * convert string into International student
     * @param inputStringList String array to be converted to International.
     * @return International object if string array is valid, null if not.
     */
    private static International setStudentInternational(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE+1) {
            int credits = setCredits(inputStringList[5]);
            Date dob = setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            boolean abroad = Boolean.parseBoolean(inputStringList[6]);
            return new International(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits,abroad);
        }
        return null;
    }

    /**
     * convert string into TriState student
     * @param inputStringList String array to be converted to TriState.
     * @return TriState object if string array is valid, null if not.
     */
    private static TriState setStudentTriState(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE+1) {
            int credits = setCredits(inputStringList[5]);
            Date dob = setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            if(!inputStringList[6].toUpperCase().equals("NY") && !inputStringList[6].toUpperCase().equals("CT")
                    && !inputStringList[6].toUpperCase().equals("NJ")) {
                return null;
            }
            return new TriState(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits,inputStringList[6]);
        }
        return null;
    }

    /**
     * Reads file
     */
    @FXML
    protected void readFile() {
        FileChooser fc = new FileChooser();
        ExtensionFilter filter = new ExtensionFilter("TEXT Files","*.txt");
        fc.getExtensionFilters().add(filter);
        File f = fc.showOpenDialog(null);
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException fnfe) {
            return;
        }
        if (f!= null) {
            while (sc.hasNextLine()) {
                Student student = null;
                String fileInput = sc.nextLine();
                String[] fileInputList = fileInput.split(",");
                switch(fileInputList[0]) {
                    case "T":
                        student = setStudentTriState(fileInputList);
                        break;
                    case"R":
                        student = setStudentResident(fileInputList);
                        break;
                    case"N":
                        student = setStudentNonResident(fileInputList);
                        break;
                    case"I":
                        student = setStudentInternational(fileInputList);
                        break;
                }
                if(!roster.contains(student)) {
                    roster.add(student);
                }
            }
        } else {
            errorMessage.setText("File not found");
            return;
        }
        studentList = roster.getRoster();
        errorMessage.setText("File successfully read");
    }

    /**
     * Checks entered student's data
     * @return Whether the data entered is valid
     */
    boolean studentError() {
        boolean output;
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
        } else if(!testDOB()){
            return false;
        }
        return true;
    }

    /**
     * Adds student ot the roster
     */
    public void addStudent() {
        updateCredits();
        if(!studentError()){
            return;
        }

        Profile profile = new Profile(fNameText.getText(),lNameText.getText(),dob);
        Student tempStudent = new Resident(profile);
        if(roster.contains(tempStudent)) {
            errorMessage.setText("Student is already in roster");
            return;
        }
        if(nonResidentVBox.isDisabled()) {
            Resident res = new Resident(profile,major,creditsCompleted);
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
        studentList = roster.getRoster();
    }

    /**
     * Roster methods and array initialization
     */
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

    /**
     * Opens the next page
     *
     */
    public void nextPage() {
        if(currentPage != maxPage) {
            currentPage++;
        }
        updateRoster();
    }

    /**
     * Opens the previous page
     */
    public void prevPage() {
        if(currentPage!= 1) {
            currentPage--;
        }
        updateRoster();
    }

    /**
     * Sets page
     */
    void setPage() {
        maxPage = (int)Math.ceil((double)roster.count(studentList)/rosterLabelList.length);
        if(maxPage == 0) {
            maxPage = 1;
        }
        if(currentPage < maxPage) {
            nextPage.setDisable(false);
        } else {
            nextPage.setDisable(true);
        }
        if(currentPage > 1) {
            prevPage.setDisable(false);
        } else {
            prevPage.setDisable(true);
        }
        if(currentPage > maxPage) {
            currentPage = maxPage;
            updateRoster();
        }
        pageNum.setText("Page " + currentPage + "/" + maxPage);
    }

    /**
     * Updates the roster
     */
    public void updateRoster() {
        initializeRosterLabelList();
        initializeMajorButtonList();
        initializeEnrollButtonList();
        initializeRemoveButtonList();
        Student[] tempRoster = studentList;
        for(int i = 0; i < rosterLabelList.length; i++) {
            int currentIndex = (currentPage-1)*rosterLabelList.length+i;
            if(roster.count(tempRoster) > currentIndex && tempRoster[currentIndex] != null) {
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
        setPage();
    }

    /**
     * Sorts the roster by standing
     */
    public void sortByStanding() {
        studentList = roster.getRoster();
        roster.printByStanding();
        updateRoster();
    }

    /**
     * Prints students belonging to a particular school
     * @param school where the students who belong to will be printed
     */
    public void printSchool(String school) {
        studentList = roster.printAllStudentsInSchool(school);
        updateRoster();
    }

    /**
     * Removes list of students from the roster
     * @param s the student being removed
     */
    void removeStudentList(Student s) {
        boolean studentRemoved = false;
        //removeStudent
        for(int i =0;i < studentList.length; i++) {
            if(studentList[i]!= null && studentList[i].equals(s)) {
                studentList[i] = null;
                studentRemoved = true;
                break;
            }
        }
        //update studentList
        if(studentRemoved) {
            for(int i = 1; i < studentList.length; i++) {
                if(studentList[i-1] == null && studentList[i] != null) {
                    studentList[i-1] = studentList[i];
                    studentList[i] = null;
                }
            }
        }
    }

    /**
     * Removes a student from the roster
     * @param i student's index in the list
     */
    public void removeStudent(int i) {
        Student tempStudent = studentList[(currentPage-1)*rosterLabelList.length+i];
        removeStudentList(tempStudent);
            roster.remove(tempStudent);

        updateRoster();
    }

    /**
     * Changes student's major
     * @param i student's index in the list
     */
    public void changeMajorStudent(int i) {
        String majorString = "";
        if(csRosterButton.isSelected()) {
            majorString = "CS";
        } else if(eeRosterButton.isSelected()){
            majorString = "EE";
        }else if(baitRosterButton.isSelected()) {
            majorString = "BAIT";
        }else if(mathRosterButton.isSelected()) {
            majorString = "MATH";
        }else if(itiRosterButton.isSelected()) {
            majorString = "ITI";
        } else {
            return;
        }
        roster.changeMajor(studentList[(currentPage-1)*rosterLabelList.length + i],majorString);
        studentList[(currentPage-1)*rosterLabelList.length+i].setMajor(Major.stringToMajor(majorString));
        updateRoster();
    }

    /**
     * Sorts by major
     */
    public void sortByMajor() {

        roster.printBySchoolMajor();
        studentList= roster.getRoster();
        updateRoster();
    }

    /**
     * Sorts by profile
     */
    public void sortByProfile() {
        roster.print();
        studentList = roster.getRoster();
        updateRoster();
    }
    //school methods

    /**
     * Prints students in SAS
     */
    public void seeSAS() {
        printSchool("SAS");
    }
    /**
     * Prints students in SOE
     */
    public void seeSOE() {
        printSchool("SOE");
    }
    /**
     * Prints students in SC&I
     */
    public void seeSCnI() {
        printSchool("SC&I");
    }
    /**
     * Prints students in RBS
     */
    public void seeRBS() {
        printSchool("RBS");
    }
    /**
     * Changes majors for students in a particular slot
     */

    //specified button methods
    public void changeMajorSlot0() {changeMajorStudent(0);}
    public void changeMajorSlot1() {changeMajorStudent(1);}
    public void changeMajorSlot2() {changeMajorStudent(2);}
    public void changeMajorSlot3() {changeMajorStudent(3);}
    public void changeMajorSlot4() {changeMajorStudent(4);}
    public void changeMajorSlot5() {changeMajorStudent(5);}
    public void changeMajorSlot6() {changeMajorStudent(6);}
    public void changeMajorSlot7() {changeMajorStudent(7);}

    /**
     * Removes a particular slot
     */
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
    //Enrollment Methods


    //Enrollment array initialization methods

    /**
     * Initializes enrollment array
     */
    void initializeEnrollment() {
        enrollStudentList[0] = enrollStudent1;
        enrollStudentList[1] = enrollStudent2;
        enrollStudentList[2] = enrollStudent3;
        enrollStudentList[3] = enrollStudent4;
        enrollStudentList[4] = enrollStudent5;
        enrollStudentList[5] = enrollStudent6;
        enrollStudentList[6] = enrollStudent7;
        enrollStudentList[7] = enrollStudent8;
    }

    /**
     * Initializes Tuition Label array
     */
    void initializeTuitionLabel() {
        tuitionLabelList[0] = tuitionLabel1;
        tuitionLabelList[1] = tuitionLabel2;
        tuitionLabelList[2] = tuitionLabel3;
        tuitionLabelList[3] = tuitionLabel4;
        tuitionLabelList[4] = tuitionLabel5;
        tuitionLabelList[5] = tuitionLabel6;
        tuitionLabelList[6] = tuitionLabel7;
        tuitionLabelList[7] = tuitionLabel8;
    }

    /**
     * Initializes Credits array
     */
    void initializeCredits() {
        enrollCreditsList[0] = enrollCredits1;
        enrollCreditsList[1] = enrollCredits2;
        enrollCreditsList[2] = enrollCredits3;
        enrollCreditsList[3] = enrollCredits4;
        enrollCreditsList[4] = enrollCredits5;
        enrollCreditsList[5] = enrollCredits6;
        enrollCreditsList[6] = enrollCredits7;
        enrollCreditsList[7] = enrollCredits8;
    }

    /**
     * Initializes Scholarships
     */
    void initializeScholarships() {
        scholarshipList[0] = scholarship1;
        scholarshipList[1] = scholarship2;
        scholarshipList[2] = scholarship3;
        scholarshipList[3] = scholarship4;
        scholarshipList[4] = scholarship5;
        scholarshipList[5] = scholarship6;
        scholarshipList[6] = scholarship7;
        scholarshipList[7] = scholarship8;
    }

    /**
     * Initializes DropButtons
     */
    void initializeDropButtons(){
        dropButtonList[0] = dropButton1;
        dropButtonList[1] = dropButton2;
        dropButtonList[2] = dropButton3;
        dropButtonList[3] = dropButton4;
        dropButtonList[4] = dropButton5;
        dropButtonList[5] = dropButton6;
        dropButtonList[6] = dropButton7;
        dropButtonList[7] = dropButton8;
    }

    /**
     * Finds Student In the Roster
     * @param p Profile of the student
     * @return Student The student in the roster
     */
    Student findStudentInRoster(Profile p) {
        for(int i = 0; i < roster.getRoster().length; i++) {
            if(roster.getRoster()[i].getProfile().equals(p)) {
                return roster.getRoster()[i];
            }
        }
        return null;
    }

    /**
     * Opens next page in enroll tab
     */
    public void nextPageEnroll() {
        if(currentEnrollPage != maxEnrollPage) {
            currentEnrollPage++;
        }
        updateEnrollment();
    }
    /**
     * Opens previous page in enroll tab
     */
    public void prevPageEnroll() {
        if(currentEnrollPage!= 1) {
            currentEnrollPage--;
        }
        updateEnrollment();
    }
    /**
     * Sets page in enroll tab
     */
    void setPageEnroll() {
        maxEnrollPage = (int)Math.ceil((double)enrollment.count()/enrollStudentList.length);
        if(maxEnrollPage == 0) {
            maxEnrollPage = 1;
        }
        if(currentEnrollPage < maxEnrollPage) {
            nextPageEnroll.setDisable(false);
        } else {
            nextPageEnroll.setDisable(true);
        }
        if(currentPage > 1) {
            prevPageEnroll.setDisable(false);
        } else {
            prevPageEnroll.setDisable(true);
        }
        if(currentEnrollPage > maxEnrollPage) {
            currentEnrollPage = maxEnrollPage;
            updateEnrollment();
        }
        pageNumEnroll.setText("Page " + currentEnrollPage + "/" + maxEnrollPage);
    }

    /**
     * Updates enrollment
     */
    public void updateEnrollment() {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        initializeEnrollment();
        initializeCredits();
        initializeScholarships();
        initializeTuitionLabel();
        initializeDropButtons();
        EnrollStudent[] tempEnrollment = enrollment.getEnrollment();
        for(int i = 0; i < enrollStudentList.length; i++) {
            int currentIndex = (currentEnrollPage-1)*enrollStudentList.length+i;
            if(tempEnrollment.length > currentIndex && tempEnrollment[currentIndex] != null) {
                Student tempStudent = findStudentInRoster(tempEnrollment[currentIndex].getProfile());

                dropButtonList[i].setVisible(true);
                enrollCreditsList[i].setVisible(true);
                enrollCreditsList[i].setText(""+tempEnrollment[currentIndex].getCredits());
                if(tempStudent!= null && tempStudent instanceof Resident && tempEnrollment[currentIndex].getCredits() > Student.FULL_TIME) {
                    scholarshipList[i].setVisible(true);
                } else {
                    scholarshipList[i].setVisible(false);
                }
                tuitionLabelList[i].setVisible(true);
                tuitionLabelList[i].setText(""+df.format(tempStudent.tuitionDue(tempEnrollment[currentIndex].getCredits())));
                enrollStudentList[i].setVisible(true);
                enrollStudentList[i].setText(tempEnrollment[currentIndex].getProfile().toString());
            } else {
                tuitionLabelList[i].setVisible(false);
                dropButtonList[i].setVisible(false);
                enrollCreditsList[i].setVisible(false);
                scholarshipList[i].setVisible(false);
                enrollStudentList[i].setVisible(false);
            }
        }
        setPageEnroll();
    }

    /**
     * Enrolls student
     * @param i student's index in the list
     */
    void enrollStudent(int i) {
        Student tempStudent = studentList[(currentPage-1)*rosterLabelList.length+i];
        EnrollStudent newEnroll = new EnrollStudent(tempStudent.getProfile(),setCreditLimit(tempStudent,1));
        enrollment.add(newEnroll);
        updateEnrollment();
    }

    /**
     * Sets credit in the current enrollment
     * @param i student's index
     */
    void setCreditsEnroll(int i) {
        int newCredits;
        EnrollStudent tempEnroll = enrollment.getEnrollment()[(currentEnrollPage-1)*enrollStudentList.length+i];
        Student tempStudent = findStudentInRoster(tempEnroll.getProfile());
        try {
            newCredits = Integer.parseInt(enrollCreditsList[i].getText());
            newCredits = setCreditLimit(tempStudent,newCredits);
            enrollCreditsList[i].setText("" + newCredits);
        } catch(NumberFormatException nfe) {
            newCredits = setCreditLimit(tempStudent,3);
            enrollCreditsList[i].setText("" + newCredits);
        }
        tempEnroll.setCredits(newCredits);
        updateEnrollment();
    }

    /**
     * Sets credit limit
     * @param tempStudent current student
     * @param output number of credits to assign to the student
     * @return minimum or maximum allowed number of credits or
     * current credits if within allowed range
     */
    int setCreditLimit(Student tempStudent,int output) {
        if(tempStudent instanceof International) {
            International international = (International)tempStudent;
            if(international.getAbroad()){
                output = Math.max(Student.CREDITS_MIN,output);
                output = Math.min(Student.FULL_TIME,output);
            } else {
                output = Math.max(output,Student.FULL_TIME);
                output = Math.min(output,Student.CREDITS_MAX);
            }
        } else {
            output = Math.max(output,Student.CREDITS_MIN);
            output = Math.min(output,Student.CREDITS_MAX);
        }
        return output;
    }

    /**
     * Sets scholarship for student
     * @param i student's index in the list
     */
    void setScholarship(int i) {
        int output;
        try{
            output = Integer.parseInt(scholarshipList[i].getText());
            output = Math.min(output,Resident.SCHOLARSHIP_MAX);
            output = Math.max(output,0);
        } catch (NumberFormatException ne) {
            output = 0;
        }
        scholarshipList[i].setText("" + output);
        Student tempStudent = findStudentInRoster(enrollment.getEnrollment()[(currentEnrollPage-1)*enrollStudentList.length+i].getProfile());
       Resident resident = null;
        if(tempStudent instanceof Resident) {
            resident = (Resident)tempStudent;
        }
        resident.awardScholarship(output);
        updateEnrollment();
    }

    /**
     * Drops enrolled student
     * @param i student's index in the list
     */
    void dropEnrollStudent(int i) {
        enrollment.remove(enrollment.getEnrollment()[(currentEnrollPage-1)*enrollStudentList.length+i]);
        updateEnrollment();
    }

    /**
     * Ends semester
     */
    public void endSemester() {
        enrollment.endSemester(roster);
        updateEnrollment();

    }

    /**
     * Drops a particular student
     *
     */
    public void dropStudent1(){dropEnrollStudent(0);}
    public void dropStudent2(){dropEnrollStudent(1);}
    public void dropStudent3(){dropEnrollStudent(2);}
    public void dropStudent4(){dropEnrollStudent(3);}
    public void dropStudent5(){dropEnrollStudent(4);}
    public void dropStudent6(){dropEnrollStudent(5);}
    public void dropStudent7(){dropEnrollStudent(6);}
    public void dropStudent8(){dropEnrollStudent(7);}

    /**
     * Sets a particular scholarship
     */
    public void setScholarship1(){setScholarship(0);}
    public void setScholarship2(){setScholarship(1);}
    public void setScholarship3(){setScholarship(2);}
    public void setScholarship4(){setScholarship(3);}
    public void setScholarship5(){setScholarship(4);}
    public void setScholarship6(){setScholarship(5);}
    public void setScholarship7(){setScholarship(6);}
    public void setScholarship8(){setScholarship(7);}

    /**
     * Sets particular credits in enrollment
     */
    public void setCreditsEnroll1(){
        setCreditsEnroll(0);
    }
    public void setCreditsEnroll2(){
        setCreditsEnroll(1);
    }
    public void setCreditsEnroll3(){
        setCreditsEnroll(2);
    }
    public void setCreditsEnroll4(){
        setCreditsEnroll(3);
    }
    public void setCreditsEnroll5(){
        setCreditsEnroll(4);
    }
    public void setCreditsEnroll6(){
        setCreditsEnroll(5);
    }
    public void setCreditsEnroll7(){
        setCreditsEnroll(6);
    }
    public void setCreditsEnroll8(){
        setCreditsEnroll(7);
    }

    /**
     * Enrolls a particular student
     */
    public void enrollStudent1() {
        enrollStudent(0);
    }
    public void enrollStudent2() {
        enrollStudent(1);
    }
    public void enrollStudent3() {
        enrollStudent(2);
    }
    public void enrollStudent4() {
        enrollStudent(3);
    }
    public void enrollStudent5() {
        enrollStudent(4);
    }
    public void enrollStudent6() {
        enrollStudent(5);
    }
    public void enrollStudent7() {
        enrollStudent(6);
    }
    public void enrollStudent8() {
        enrollStudent(7);
    }
}