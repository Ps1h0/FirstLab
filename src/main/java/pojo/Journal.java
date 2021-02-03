package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class journal
 * Contains information about students, their subjects and marks
 * @author OLEG
 * @version 1.3
 */

public class Journal implements Serializable {

    /**
     * dynamic list type of {@link StudentInformation}, containing students
     */

    private ArrayList<StudentInformation> students = new ArrayList<>();

    @Override
    public String toString() {
        var ref = new Object() {
            String returnLine = "";
        };
        students.forEach(studentInformation -> {
            String student = studentInformation.getStudent();
            ref.returnLine += "\n" + student + " {\n";
            studentInformation.getProgressStudents().forEach(progressStudent -> {
                ref.returnLine += progressStudent.getSubject() + " : " + progressStudent.getMark() + "\n}";
            });
        });
        return ref.returnLine;
    }

    /**
     * Add student and subject: mark
     * @param nameStudent Student name
     * @param progressStudent Pair subject - mark type of ProgressStudent in the form of ArrayList
     * @throws NumberFormatException used incorrect data type {@link Journal#addStudent(String, ArrayList)}
     * @see Journal
     * @see ProgressStudent
     * @see StudentInformation
     * @see Student
     * @see Subject
     * @see Mark
     * @since 1.2
     */

    public void addStudent(String nameStudent, ArrayList<ProgressStudent> progressStudent) throws NumberFormatException {
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.createStudent(nameStudent);
        progressStudent.forEach(studentInformation::addProgressStudent);
        students.add(studentInformation);
    }

    /**
     * Returns dynamic list
     * @return students {@link ArrayList} type of {@link StudentInformation}
     * @see Journal
     * @since 1.0
     */

    public ArrayList<StudentInformation> getStudents() {
        return students;
    }
}
