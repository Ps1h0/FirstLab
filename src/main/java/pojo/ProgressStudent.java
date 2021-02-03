package pojo;

import javax.security.auth.Subject;

/**
 * Класс прогресс студента
 * Содержит данные о паре предмет : оценка
 * @author OLEG
 * @version 1.3
 * @since 1.1
 *
 * Изменено создание класса, теперь поля класса имею тип String и не создают дополнительный объект в виде
 * Subject и Mark как было раньше
 */

public class ProgressStudent {

    /**
     * поле предмет
     */

    private String subject;

    /**
     * поле оценка
     */

    private String mark;

    /**
     * Конструктор класса {@link ProgressStudent}
     * Создает объект с двумя полями {@link ProgressStudent#subject} и {@link ProgressStudent#mark}
     * @param subject предмет
     * @param mark оценка
     * @throws NumberFormatException использован неверный передаваемый тип данных класса {@link ProgressStudent}
     * @see ProgressStudent
     * @see Subject
     * @see Mark
     * @since 1.0
     */

    public ProgressStudent(String subject, String mark) throws NumberFormatException{
        this.subject = subject;
        this.mark = mark;
    }

    /**
     * Вернет объект предмет
     * @return subject типа {@link Subject}
     * @see ProgressStudent
     * @since 1.0
     */

    public String getSubject() {
        return subject;
    }

    /**
     * Вернет оценку
     * @return оценку
     * @see ProgressStudent
     * @since 1.0
     */

    public String getMark() {
        return mark;
    }
}
