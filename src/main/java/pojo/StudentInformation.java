package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class StudentInformation
 * Contains info about student, his name, subject and mark
 * @author OLEG
 * @version 1.3
 * @since 1.1
 */

public class StudentInformation implements Serializable {

    /**
     * student name field
     */

    private String student;

    /**
     * dynamic list type of {@link ProgressStudent}
     */

    private ArrayList<ProgressStudent> progressStudents = new ArrayList<>();

    /**
     * Returns object
     * @return Student
     * @see StudentInformation
     */

    public String getStudent() {
        return student;
    }

    /**
     * Return dynamic list
     * @return progressStudents {@link ArrayList} type of {@link ProgressStudent}
     * @see StudentInformation
     * @since 1.0
     */

    public ArrayList<ProgressStudent> getProgressStudents() {
        return progressStudents;
    }

    /**
     * Creates data about students
     * @param student student name
     * @throws NumberFormatException used incorrect data type {@link StudentInformation#createStudent(String)}
     * @see StudentInformation
     * @since 1.0
     */

    public void createStudent(String student) throws NumberFormatException{
        this.student = student;
    }

    /**
     * Creates subject : mark pair
     * @param progressStudent object type of {@link ProgressStudent}
     * @throws NumberFormatException used incorrect data type {@link StudentInformation#addProgressStudent(ProgressStudent)}
     * @see StudentInformation
     * @see ProgressStudent
     * @since 1.0
     */

    public void addProgressStudent(ProgressStudent progressStudent) throws NumberFormatException{
        progressStudents.add(progressStudent);
    }

    @Override
    public String toString() {
        return "{" +
                "student='" + student + '\'' +
                ", progressStudents=" + progressStudents +
                '}';
    }
}
