package UsHandler;
import jdk.internal.org.xml.sax.SAXException;
import pojo.Journal;
import pojo.ProgressStudent;
import pojo.StudentInformation;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.logging.*;

/**
 * Class UsHandler
 * Class for catching exceptions and changes
 * @author Danil Belonogov
 * @version 1.0
 */
public class UsHandler extends Throwable {
    //Дата
    private Date date = new Date();
    private static final Logger logger = Logger.getLogger(UsHandler.class.getName());


    /**
     *Method that creates a file with a list of changes
     * @param file
     * @param log
     * @throws IOException
     */
    public void createLog(File file,String log) throws IOException {
        FileWriter fileWriter;
        fileWriter = new FileWriter(file,true);
        fileWriter.write(log);
        fileWriter.close();
    }

    /**
     *Method that catches exceptions
     * @param e
     * @param classname
     * @throws IOException
     */
    public static void HandlerException(Exception e, String classname) throws IOException {
        Handler filehandler = new FileHandler("debug.log");
        logger.addHandler(filehandler);
        //ArithmeticException
        if (e instanceof ArithmeticException){
            logger.log(Level.INFO, "Ошибка ArithmeticException  в классе " + classname, e);
            System.exit(-1);
        }
        //InputMismatchException
        if( e instanceof InputMismatchException){
            logger.log(Level.INFO, "Ошибка InputMismatchException в классе" + classname, e);
            System.exit(-1);
        }
        //FileNotFoundException файл не найден
        if (e instanceof FileNotFoundException){
            logger.log(Level.WARNING, "Ошибка FileNotFoundException в классе " + classname+"\nНе удалось найти файл");
            System.exit(-1);
        }
        //XMLStreamException не удалось прочитать XML
        if (e instanceof XMLStreamException){
            logger.log(Level.WARNING, "Ошибка XMLStreamException в классе " + classname, e );
            System.exit(-1);
        }
        //InterruptedException ошибка потока
        if (e instanceof InterruptedException){
            logger.log(Level.WARNING, "Ошибка InterruptedException в классе " + classname, e );
            System.exit(-1);
        }
        //ParserConfigurationException ошибка конфигурации?
        if (e instanceof ParserConfigurationException){
            logger.log(Level.WARNING, "Ошибка ParserConfigurationException в классе " + classname, e );
            System.exit(-1);
        }
        //SAXException ошибка анализатора xml(SAX)
        if (e instanceof SAXException){
            logger.log(Level.WARNING, "Ошибка SAXException в классе " + classname, e );
            System.exit(-1);
        }
    }
    /**
     * Returns true if the past log exists
     * @return the presence of a past log
     */
    private boolean hasLastJournal(){
        File lastJournal = new File("tempJournal");
        return lastJournal.exists();
    }

    /**
     *A method that returns the student's index by name.
     * @param journal
     * @param name
     * @return i - student's index
     */
    private int getIndex(Journal journal, String name){
        for (int i = 0; i < journal.getStudents().size(); i++) {
            if (journal.getStudents().get(i).getStudent().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *Method that returns a string with subjects and grades changed, added, or removed.
     * @param lastJournal
     * @param newJournal
     * @param name
     * @return result - Changes in grades and subjects
     */
    private String markDifference(Journal lastJournal, Journal newJournal, String name) {
        StringBuilder result = new StringBuilder();
        int indexLastJournal = getIndex(lastJournal, name);
        int indexNewJournal = getIndex(newJournal, name);
        for(ProgressStudent progressLast: lastJournal.getStudents().get(indexLastJournal).getProgressStudents()) {
            boolean check = false;
            for(ProgressStudent progressNew: newJournal.getStudents().get(indexNewJournal).getProgressStudents()) {
                if (progressLast.getSubject().equals(progressNew.getSubject())) {
                    if (!progressLast.getMark().equals(progressNew.getMark())) {
                        result.append(String.format("%s: Изменено %s -> %s\n", date.toString(), progressLast, progressNew));
                    }
                    check = true;
                    break;
                }
            }
            if (!check) {
                result.append(String.format("%s: Удален {%s} для %s\n", date.toString(), progressLast.toString(), name));
            }
        }
        for(ProgressStudent progressNew: newJournal.getStudents().get(indexLastJournal).getProgressStudents()) {
            boolean check = false;
            for(ProgressStudent progressLast: lastJournal.getStudents().get(indexNewJournal).getProgressStudents()) {
                if (progressLast.getSubject().equals(progressNew.getSubject())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                result.append(String.format("%s: Добавлен {%s} для %s\n", date.toString(), progressNew.toString(), name));
            }
        }
        return result.toString();
    }

    /**
     *Method that returns a string with information about adding or removing students.
     * @param lastJournal
     * @param newJournal
     * @return result - A line with information about changes in students and grades
     */
    public String changeLog(Journal lastJournal, Journal newJournal) {
        StringBuilder result = new StringBuilder();
        for (StudentInformation a: lastJournal.getStudents()){
            boolean check = false;

            for(StudentInformation b: newJournal.getStudents()) {
                if (a.getStudent().equals(b.getStudent())){
                    check = true;
                    break;
                }

            }
            if(!check) {
                result.append(String.format("%s: Удален %s\n", date.toString(), a));
            }
            check = false;
        }

        for (StudentInformation a: newJournal.getStudents()){
            boolean check = false;

            for(StudentInformation b: lastJournal.getStudents()) {
                if (a.getStudent().equals(b.getStudent())){
                    check = true;
                    break;
                }

            }
            if(!check) {
                result.append(String.format("%s: Добавлен %s\n", date.toString(), a));
            }
            check = false;
        }

        for (StudentInformation a: newJournal.getStudents()){
            for(StudentInformation b: lastJournal.getStudents()) {
                if (a.getStudent().equals(b.getStudent())) {
                    result.append(markDifference(lastJournal, newJournal, a.getStudent()));
                }

            }
        }
        return result.toString();
    }

    /**
     * Method that returns the previous version of the log, if it exists
     * @return lastJournal
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Journal getLastJournal() throws IOException, ClassNotFoundException{
        if (hasLastJournal()) {
            FileInputStream fileInputStream = new FileInputStream("tempJournal");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Journal lastJournal = (Journal) objectInputStream.readObject();
            objectInputStream.close();
            return lastJournal;
        } else{
            System.out.println("Прошлая версия журнала не найдена");
            return null;
        }

    }
}