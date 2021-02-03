package pojo;

import javax.security.auth.Subject;
import java.io.Serializable;

/**
 * Class progress student
 * Contains data about subject : mark
 * @author OLEG
 * @version 1.3
 * @since 1.1
 *
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
     * Constructor of class {@link ProgressStudent}
     * Creates object with fields {@link ProgressStudent#subject} and {@link ProgressStudent#mark}
     * @param subject subject
     * @param mark mark
     * @throws NumberFormatException used incorrect data type {@link ProgressStudent}
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
     * Returns subject
     * @return subject type of {@link Subject}
     * @see ProgressStudent
     * @since 1.0
     */

    public String getSubject() {
        return subject;
    }

    /**
     * Returns mark
     * @return mark
     * @see ProgressStudent
     * @since 1.0
     */

    public String getMark() {
        return mark;
    }
}
