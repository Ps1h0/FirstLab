package UsHandler;
import jdk.internal.org.xml.sax.SAXException;
import pojo.Journal;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.InputMismatchException;
import java.util.logging.*;
public class UsHandler extends Throwable {
    private static final Logger logger = Logger.getLogger(UsHandler.class.getName());

    private final String classname;

    public UsHandler(String classname) {
        this.classname = classname;
    }

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

    public boolean hasLastJournal(){
        File lastJournal = new File("tempJournal");
        return lastJournal.exists();
    }

    public void lastSerializableJournal(Journal journal) throws IOException, ClassNotFoundException {
        //Чтение прошлого журнала из файла
        FileInputStream fileInputStream = new FileInputStream("tempJournal");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Journal lastJournal = (Journal) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(lastJournal.toString());
        //lastJournal.getStudents().forEach(studentInformation -> {
        //    String student = studentInformation.getStudent();
       // });


    }


}