package project3;


/**
 * This class handles all of the methods used to print and adjust the student roster
 *
 * @author Michael Burton
 * @author Kirill Vine
 */

public class Roster {
    private Student[] roster = new Student[4];
    private int size = 4;

    final static int CHANGE_MAJOR_COMMAND_SIZE = 4;

    final static int GROWTH_INTERVAL = 4;

    /**
     * Sorts the student profiles based lexographically and based on date of birth
     */
    private void sortStudentProfiles() { //sort by lname, fname, dob
        for (int i = 0; i < size - 1; i++) {
            int currentMinimum = i;
            if (roster[i] == null) {
                continue;
            }
            for (int j = i + 1; j < size; j++) {
                if (roster[j] == null) {
                    continue;
                }
                if (this.roster[j].compareTo(this.roster[currentMinimum]) > 0) {
                    currentMinimum = j;
                }
            }
            Student temp = this.roster[currentMinimum];
            this.roster[currentMinimum] = this.roster[i];
            this.roster[i] = temp;
        }
    }

    /**
     * Determine whether a roster is empty.
     *
     * @return true if the roster has no students, false if otherwise.
     */
    private boolean isEmpty() {
        for (int i = 0; i < this.roster.length; i++) {
            if (this.roster[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests whether the roster contains a student.
     *
     * @param student the student to be checked whether it's in the roster.
     * @return true if student is in the roster, and false if otherwise.
     */
    public boolean contains(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i] != null && student.getProfile().equals(roster[i].getProfile())) {
                return true;
            }
        }
        return false;
    }

    /**
     * find number of values in roster that aren't null
     * @return int of values in roster that are not null
     */
    public int count(Student[] s) {
        int output = 0;
        for(int i =0; i < s.length;i++) {
            if(s[i] != null) {
                output++;
            }
        }
        return output;
    }

    /**
     * find the index of a given student in the roster
     *
     * @param student the student whose index is to be found
     * @return index of the student in the roster, -1 if the student is not in the roster.
     */
    private int find(Student student) {
        if (contains(student)) {
            for (int i = 0; i < size; i++) {
                if (student.getProfile().equals(roster[i].getProfile())) {
                    return i;
                }
            }
        }
        System.out.println("Student not in Roster");
        return -1;
    }

    /**
     * Increases the size of the roster array by the GROWTH_INTERVAL.
     */
    private void grow() {
        Student[] tempList = new Student[size];
        for (int i = 0; i < size; i++) {
            tempList[i] = roster[i];
        }
        size += GROWTH_INTERVAL;
        roster = new Student[size];
        for (int i = 0; i < size - GROWTH_INTERVAL; i++) {
            roster[i] = tempList[i];

        }
    }

    /**
     * Add a student to the roster.
     *
     * @param student student to be added to the roster
     * @return return true if the student was successfully added to the roster.
     */
    public boolean add(Student student) {
        if (student == null) {
            return false;
        }
        if (!contains(student)) {
            if (this.roster[this.size - 1] != null) {
                this.grow();
            }
            //goes through whole thing array and adds student to the end
            for (int i = 0; i < this.size; i++) {
                if (this.roster[i] == null) {
                    this.roster[i] = student;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }

    /**
     * remove a given student from the roster.
     *
     * @param student student to be removed from the roster.
     * @return true if the student has been removed from the roster, false if otherwise.
     */
    public boolean remove(Student student) {
        boolean output = false;
        if (!this.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (this.roster[i] != null && this.roster[i].equals(student)) {
                    roster[i] = null;
                    System.out.println(student.getProfile().toString() + " has been removed");
                    output = true;
                }
            }
            System.out.println("Student is not in roster");
        } else {
            System.out.println("Roster is empty!");
            return false;
        }
        //update list if student was removed
        if(output) {
            for(int i = 1; i < size;i++) {
                if(roster[i-1] == null && roster[i] != null) {
                    roster[i-1] = roster[i];
                    roster[i] = null;
                }
            }
        }
        return output;
    }

    /**
     * change a student's major
     *
     * @param student  student whose major should be staged.
     * @param newMajor String that represents the string.
     */
    public void changeMajor(Student student, String newMajor) {
        Major tempMajor = Major.stringToMajor(newMajor);
        ;
        if (tempMajor == null) {
            return;
        }
        if (student != null && contains(student)) {
            int index = find(student);
            if (index != -1) {
                if (tempMajor != null) {
                    roster[index].setMajor(Major.stringToMajor(newMajor));
                }
            }
        }
    }

    /**
     * Adds finds a resident student in the roster and gives them a scholarship.
     *
     * @param student           student to be awarded the scholarship.
     * @param scholarshipString amount student should be awarded in scholarship.
     */
    public void addScholarship(Enrollment enrollment, Student student, String scholarshipString) {
        int scholarship;
        try {
            scholarship = Integer.parseInt(scholarshipString);
        } catch (NumberFormatException nfe) {
            return;
        }
        if (contains(student) && enrollment.contains(new EnrollStudent(student.getProfile()))) {
            int studentID = find(student);
            int creditsEnrolled = 0;
            //find amount of credits student is currently taking
            for (EnrollStudent es : enrollment.getEnrollment()) {
                if (es != null && es.getProfile().equals(student.getProfile())) {
                    creditsEnrolled = es.getCredits();
                }
            }
            //if student is resident and full time award scholarship
            if (!(roster[studentID] instanceof Resident) || creditsEnrolled < Student.FULL_TIME) {
                System.out.println(student.getProfile() + " is ineligible for a scholarship");
            } else {
                Resident resident = (Resident) roster[studentID];
                resident.awardScholarship(scholarship);
            }
        } else {
            System.out.println("Student is not in roster");
        }
    }

    /**
     * increases a student's creditsCompleted.
     *
     * @param student student whose credits are added to.
     * @param credits number of credits added.
     */
    public void addCredits(Student student, int credits) {
        if (contains(student)) {
            int studentIndex = find(student);
            roster[studentIndex].addCredits(credits);
        }
    }


    /**
     * Print all students in the roster of a given school.
     *
     * @param school school that each student should be printed from.
     */
    public Student[] printAllStudentsInSchool(String school) {
        Student[] output = new Student[size];
        int index = 0;
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (roster[i] != null && roster[i].getMajor().getSchool().equals(school.toUpperCase())) {
                    output[index] = roster[i];
                    index++;
                }
            }
        }
        return output;
    }

    /**
     * Print the students in the roster sorted lexographically by last name, first name and date of birth.
     */
    public void print() {
        if (!isEmpty()) {
            sortStudentProfiles();
        }
    }

    /**
     * Print the each student in the roster, sorted by standing.
     */
    public void printByStanding() {
        final int FRESHMAN = 30;
        final int STANDING = 30;
        final int SOPHOMORE = 60;
        final int JUNIOR = 90;
        final int SENIOR = 120;
        final int INDEX_OF_SENIOR = 3;
        int currentIndex = 0;
        int[] standingsLexographically = {FRESHMAN, JUNIOR, SENIOR, SOPHOMORE};
        if (!isEmpty()) {
            for (int j = 0; j < standingsLexographically.length; j++) {
                for (int i = currentIndex; i < size; i++) {
                    if (roster[i] != null && ((roster[i].getCredits() < standingsLexographically[j]
                            && roster[i].getCredits() >= standingsLexographically[j] - STANDING) ||
                            (j == INDEX_OF_SENIOR && roster[i].getCredits() >= SENIOR))
                                    && currentIndex < roster.length) {
                        Student tempStudent = roster[currentIndex];
                        roster[currentIndex] = roster[i];
                        roster[i] = tempStudent;
                        currentIndex++;
                    }
                }
            }
        }
    }


    /**
     * print every student in the roster sorted by major.
     */
    public void printBySchoolMajor() {
        if (!isEmpty()) {
            String[] listOfMajors = {"BAIT", "CS", "MATH", "ITI", "EE"};
            int index = 0;
            for (int i = 0; i < listOfMajors.length; i++) {
                for (int j = 0; j < size; j++) {
                    if (roster[j] != null && roster[j].getMajor().equals(Major.stringToMajor(listOfMajors[i])) && index < roster.length) {
                        Student tempStudent = roster[j];
                        roster[j] = roster[index];
                        roster[index] = tempStudent;
                        index++;
                    }
                }
            }
        }
    }

    /**
     * returns the roster variable (student array)
     *
     * @return roster variable as student array.
     */
    public Student[] getRoster() {
        return roster;
    }

}