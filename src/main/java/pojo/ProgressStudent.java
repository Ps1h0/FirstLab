package pojo;

import javax.security.auth.Subject;
import java.io.Serializable;

/**
 * Class ProgressStudent
 * Contains data about pair of subject: mark
 * @author OLEG
 * @version 1.3
 * @since 1.1
 */

public class ProgressStudent implements Serializable {

    /**
     * subject field
     */

    private String subject;

    /**
     * mark field
     */

    private String mark;

    /**
     * Class constructor {@link ProgressStudent}
     * Creates object with two fields {@link ProgressStudent#subject} and {@link ProgressStudent#mark}
     * @param subject subject
     * @param mark mark
     * @throws NumberFormatException used invalid data type {@link ProgressStudent}
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
     * Returns subject mark
     * @return subject type of {@link Subject}
     * @see ProgressStudent
     * @since 1.0
     */

    public String getSubject() {
        return subject;
    }

    /**
     * Returns mark
     * @return Mark
     * @see ProgressStudent
     * @since 1.0
     */

    public String getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "subject='" + subject + '\'' +
                ", mark='" + mark + '\'';
    }
}
