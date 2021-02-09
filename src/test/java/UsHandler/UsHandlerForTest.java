package UsHandler;

import Reader.XMLReader;
import org.xml.sax.SAXException;
import pojo.Journal;
import pojo.ProgressStudent;
import pojo.StudentInformation;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class UsHandler
 * Class for catching exceptions and changes
 * @author Danil Belonogov
 * @version 1.0
 */
public class UsHandlerForTest extends Throwable {
    //Дата
    private Date date = new Date();
    private static final Logger logger = Logger.getLogger(UsHandlerForTest.class.getName());


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
            logger.log(Level.INFO, "Error ArithmeticException in a class " + classname, e);
            System.exit(-1);
        }
        //InputMismatchException
        if( e instanceof InputMismatchException){
            logger.log(Level.INFO, "Error InputMismatchException in a class " + classname, e);
            System.exit(-1);
        }
        //FileNotFoundException файл не найден
        if (e instanceof FileNotFoundException){
            logger.log(Level.WARNING, "Error FileNotFoundException in a class " + classname+"\nНе удалось найти файл");
            System.exit(-1);
        }
        //XMLStreamException не удалось прочитать XML
        if (e instanceof XMLStreamException){
            logger.log(Level.WARNING, "Error XMLStreamException in a class " + classname, e );
            System.exit(-1);
        }
        //InterruptedException ошибка потока
        if (e instanceof InterruptedException){
            logger.log(Level.WARNING, "Error InterruptedException in a class " + classname, e );
            System.exit(-1);
        }
        //ParserConfigurationException ошибка конфигурации?
        if (e instanceof ParserConfigurationException){
            logger.log(Level.WARNING, "Error ParserConfigurationException in a class " + classname, e );
            System.exit(-1);
        }
        //SAXException ошибка анализатора xml(SAX)
        if (e instanceof SAXException){
            logger.log(Level.WARNING, "Error SAXException in a class " + classname, e );
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
                        result.append(String.format("%s: Changed for %s %s -> %s\n", date.toString(),name, progressLast, progressNew));
                    }
                    check = true;
                    break;
                }
            }
            if (!check) {
                result.append(String.format("%s: Deleted {%s} for %s\n", date.toString(), progressLast.toString(), name));
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
                result.append(String.format("%s: Added {%s} for %s\n", date.toString(), progressNew.toString(), name));
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
                result.append(String.format("%s: Deleted %s\n", date.toString(), a));
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
                result.append(String.format("%s: Added %s\n", date.toString(), a));
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
    public Journal getLastJournal() throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        if (hasLastJournal()) {
            FileInputStream fileInputStream = new FileInputStream("tempJournal");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Journal lastJournal = (Journal) objectInputStream.readObject();
            objectInputStream.close();
            return lastJournal;
        } else{
            System.out.println("Previous journal version is not found");
            createTempJournal();
            return getLastJournal();
        }

    }

    /**
     *
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void createTempJournal() throws IOException, ParserConfigurationException, SAXException {
        XMLReader reader = new XMLReader();
        reader.createDocument("src/main/resources/text.xml");
        FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/UsHandler/tempJournal");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(reader.getPOJO());
        objectOutputStream.close();
    }
}