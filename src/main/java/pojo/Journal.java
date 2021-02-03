package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Класс журнал
 * Содержит сведения о студентах, их предметах и оценкам по ним
 * @author OLEG
 * @version 1.3
 */

public class Journal implements Serializable {

    /**
     * динамисческий список типа {@link StudentInformation}, содержащий студентов
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
     * Add student and his marks
     * @param nameStudent Student name
     * @param progressStudent Pair subject - mark (ArrayList)
     * @throws NumberFormatException invalid data type used {@link Journal#addStudent(String, ArrayList)}
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
     * Returns dynamic array
     * @return students {@link ArrayList} type of {@link StudentInformation}
     * @see Journal
     * @since 1.0
     */

    public ArrayList<StudentInformation> getStudents() {
        return students;
    }
}
