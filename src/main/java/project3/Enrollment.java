package project3;

import java.text.DecimalFormat;

/**
 * Class made to control the enrollment of students.
 * Uses an array of @EnrollStudent objects to keep manage the data.
 *
 * @author Michael Burton
 * @author Kirill Vine
 */
public class Enrollment {

    private int size = 1;
    private EnrollStudent[] enrollStudents = new EnrollStudent[size];


    /**
     * adds an EnrollStudent to the enrollment roster.
     *
     * @param enrollStudent student to be added to enrollment roster.
     */
    public void add(EnrollStudent enrollStudent) {
        if (enrollStudent == null) {
            return;
        }
        EnrollStudent[] newarr = new EnrollStudent[size + 1];
        newarr[0] = enrollStudent;
        for (int i = 1; i < size; i++) {
            if (enrollStudents[i-1] != null) {
                if (enrollStudents[i-1].equals(enrollStudent)) {
                    return;
                }
                newarr[i] = enrollStudents[i-1];
            }
        }
        size += 1;
        enrollStudents = newarr;
    } //add to the end of array

    /**
     * find number of students in enrollment
     * @return number of students in enrollment
     */
    public int count() {
        int output = 0;
        for(int i = 0; i < enrollStudents.length;i++) {
            output++;
        }
        return output;
    }

    /**
     * remove class for UI purposes
     *
     * @param enrollStudent student to be removed from the roster
     */
    public void removeUI(EnrollStudent enrollStudent) {
        if (enrollStudent == null) {
            return;
        }
        if (!contains(enrollStudent)) {
            return;
        }
        remove(enrollStudent);
    }

    /**
     * Remove a student from the roster if he is present.
     *
     * @param enrollStudent student to be removed from the roster.
     */
    public void remove(EnrollStudent enrollStudent) {
        for (int i = 0; i < enrollStudents.length; i++) {
            if (enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
                enrollStudents[i] = null;
                break;
            }
        }
        //move array down so null value is always at the end
        for (int i = 1; i < enrollStudents.length; i++) {
            if (enrollStudents[i] != null && enrollStudents[i - 1] == null) {
                enrollStudents[i - 1] = enrollStudents[i];
                enrollStudents[i] = null;
            }
        }
    }

    /**
     * Test whether the enrollment roster contains a student.
     *
     * @param enrollStudent test whether this student is in the enrollment roster.
     * @return true if the enrollStudent is in the roster, and false if otherwise.
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < enrollStudents.length; i++) {
            if (enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test whether the enrollment roster is empty.
     *
     * @return true if the roster contains no students, false if otherwise.
     */
    public boolean isEmpty() {
        for (int i = 0; i < enrollStudents.length; i++) {
            if (enrollStudents[i] != null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Print every student enrolled in the enrollment roster as well as their tuition due.
     *
     * @param roster roster to print the students and tuition of.
     */
    public void printAllTuition(Roster roster) {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        String output;
        for (EnrollStudent enrollStudent : enrollStudents) {
            for (Student student : roster.getRoster()) {
                if (student != null && enrollStudent != null && student.getProfile().equals(enrollStudent.getProfile())) {
                    output = student.getProfile().toString();
                    if (student instanceof Resident) {
                        output += " (Resident)";
                    } else if (student instanceof TriState) {
                        TriState triState = (TriState) student;
                        output += "(Tri-State " + triState.getState().toUpperCase() + ")";

                    } else if (student instanceof International) {
                        International international = (International) student;
                        output += "(International student ";
                        if (international.getAbroad()) {
                            output += "studying abroad)";
                        } else {
                            output += ")";
                        }
                    } else if (student instanceof NonResident) {
                        output += " (Non-Resident)";
                    }
                    output += " credits enrolled: " + enrollStudent.getCredits() + " tuitionDue: $"
                            + df.format(student.tuitionDue(enrollStudent.getCredits()));
                }
            }
        }
    }

    /**
     * Returns the current enrollment roster
     *
     * @return the current enrollment roster
     */
    public EnrollStudent[] getEnrollment() {
        return enrollStudents;
    }

    /**
     * Add credits to each student enrolled in the enrollment roster as the semester ends.
     *
     * @param roster roster of students to have credits added.
     */
    public void endSemester(Roster roster) {
        Student[] rosterList = roster.getRoster();
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < enrollStudents.length; i++) {
            for (int j = 0; j < rosterList.length; j++) {
                if (enrollStudents[0] != null && rosterList[j] != null && rosterList[j].getProfile().equals(enrollStudents[0].getProfile())) {
                    rosterList[j].addCredits(enrollStudents[0].getCredits());
                    remove(enrollStudents[0]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Roster roster = new Roster();
        Profile profile = new Profile("l", "f", new Date("12/31/2000"));
        Student student = new Resident(profile, Major.CS, 50);
        Enrollment enrollment = new Enrollment();
        EnrollStudent enrollStudent = new EnrollStudent(profile, 100);
        roster.add(student);
        enrollment.add(enrollStudent);
        enrollment.endSemester(roster);

    }
}