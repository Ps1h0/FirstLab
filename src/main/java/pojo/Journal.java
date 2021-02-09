package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Journal
 * Contains data about students, their subjects, and their marks

 * @author OLEG
 * @version 1.3
 */

public class Journal implements Serializable {

    /**
     * Dynamic list type of {@link StudentInformation}, containing students
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
                ref.returnLine += progressStudent.getSubject() + " : " + progressStudent.getMark() + "\n";
            });
            ref.returnLine += "}\n";
        });
        return ref.returnLine;
    }

    /**
     * Add student and his marks
     * @param nameStudent Student name
     * @param progressStudent Pair subject - mark (ArrayList)
     * @throws NumberFormatException invalid data type used {@link Journal#addStudent(String, ArrayList)}
     * @see Journal
     * @see ProgressStudent
     * @see StudentInformation
     * @since 1.2
     */

    public void addStudent(String nameStudent, ArrayList<ProgressStudent> progressStudent) throws NumberFormatException {
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.createStudent(nameStudent);
        progressStudent.forEach(studentInformation::addProgressStudent);
        students.add(studentInformation);
    }

    /**
     * Returns dynamic array
     * @return students {@link ArrayList} type of {@link StudentInformation}
     * @see Journal
     * @since 1.0
     */

    public ArrayList<StudentInformation> getStudents() {
        return students;
    }
}
