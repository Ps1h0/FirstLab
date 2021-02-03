package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class student information
 * Contains info about student (name, subject, mark)
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
     * returns object
     * @return student
     * @see StudentInformation
     */

    public String getStudent() {
        return student;
    }

    /**
     * returns dynamic list
     * @return progressStudents {@link ArrayList} type of {@link ProgressStudent}
     * @see StudentInformation
     * @since 1.0
     */

    public ArrayList<ProgressStudent> getProgressStudents() {
        return progressStudents;
    }

    /**
     * Creates record about student
     * @param student student name
     * @throws NumberFormatException used incorrect data type {@link StudentInformation#createStudent(String)}
     * @see StudentInformation
     * @see Student
     * @since 1.0
     */

    public void createStudent(String student) throws NumberFormatException{
        this.student = student;
    }

    /**
     * Creates pair of subject: mark
     * @param progressStudent object type of {@link ProgressStudent}
     * @throws NumberFormatException used incorrect data type {@link StudentInformation#addProgressStudent(ProgressStudent)}
     * @see StudentInformation
     * @see Subject
     * @see Mark
     * @see ProgressStudent
     * @since 1.0
     */

    public void addProgressStudent(ProgressStudent progressStudent) throws NumberFormatException{
        progressStudents.add(progressStudent);
    }
}
