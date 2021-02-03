package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Класс информация о студенте
 * Содержит информацию о студенте, его имя, предмет и оценку по предмету
 * @author OLEG
 * @version 1.3
 * @since 1.1
 */

public class StudentInformation implements Serializable {

    /**
     * поле имя студента
     */

    private String student;

    /**
     * динамический список типа {@link ProgressStudent}
     */

    private ArrayList<ProgressStudent> progressStudents = new ArrayList<>();

    /**
     * Вернет объект
     * @return student
     * @see StudentInformation
     */

    public String getStudent() {
        return student;
    }

    /**
     * Вернет динамический массив
     * @return progressStudents {@link ArrayList} типа {@link ProgressStudent}
     * @see StudentInformation
     * @since 1.0
     */

    public ArrayList<ProgressStudent> getProgressStudents() {
        return progressStudents;
    }

    /**
     * Создает запись о студенте
     * @param student имя студента
     * @throws NumberFormatException использован неверный тип данных {@link StudentInformation#createStudent(String)}
     * @see StudentInformation
     * @see Student
     * @since 1.0
     */

    public void createStudent(String student) throws NumberFormatException{
        this.student = student;
    }

    /**
     * Создает запись пары предмет : оценка
     * @param progressStudent объект типа {@link ProgressStudent}
     * @throws NumberFormatException использован неверный тип данных {@link StudentInformation#addProgressStudent(ProgressStudent)}
     * @see StudentInformation
     * @see Subject
     * @see Mark
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
