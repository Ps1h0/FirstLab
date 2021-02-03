package UsHandler;
import jdk.internal.org.xml.sax.SAXException;
import pojo.Journal;
import pojo.ProgressStudent;
import pojo.StudentInformation;

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

    /**
     * Вернет строку состоящую из статуса изменения и того что изменилось
     * @param idComand 0 - добавление, 1 - изменение, 2 - удаление
     * @param lastValue старое значение {@link ProgressStudent}
     * @param newValue новое значение {@link ProgressStudent}
     * @return строка измение
     */
    public String getLog(int idComand, ProgressStudent lastValue, ProgressStudent newValue) {
        switch (idComand) {
            // добавление
            case 0: return "Добавление:\n" + newValue.toString();
            // изменение
            case 1: return "Изменение:\n" + lastValue.toString() + " -> " + newValue.toString();
            // удаление
            case 2: return "Удаление:\n" + lastValue.toString();
            default: return null;
        }
    }
    /**
     * Вернет строку состоящую из статуса изменения и того что изменилось
     * @param idComand 0 - добавление, 1 - изменение, 2 - удаление
     * @param lastValue старое значение {@link StudentInformation}
     * @param newValue новое значение {@link StudentInformation}
     * @return строка измение
     */
    public String getLog(int idComand, StudentInformation lastValue, StudentInformation newValue) {
        switch (idComand) {
            // добавление
            case 0: return "Добавление:\n" + newValue.toString();
            // изменение
            case 1: return "Изменение:\n" + lastValue.toString() + " -> " + newValue.toString();
            // удаление
            case 2: return "Удаление:\n" + lastValue.toString();
            default: return null;
        }
    }

    public String chandgeLog(Journal lastJournal, Journal newJournal) {
        StudentInformation lStudent = null;
        StudentInformation nStudent = null;
        ProgressStudent lSubject = null;
        ProgressStudent nSubject = null;
        for (int i = 0; i < newJournal.getStudents().size(); i++) {
            nStudent = newJournal.getStudents().get(i);
            for (int j = 0; j >= i; j++) {
                lStudent = lastJournal.getStudents().get(j);
                // если имя нового студента равно имени старого
                if ( nStudent.getStudent().equals( lStudent.getStudent() ) ) {
                    // то
                    for (int k = 0; k < newJournal.getStudents().get(i).getProgressStudents().size(); k++) {
                        nSubject = newJournal.getStudents().get(i).getProgressStudents().get(k);
                        for (int l = 0; l >= k; l++) {
                            lSubject = lastJournal.getStudents().get(j).getProgressStudents().get(l);
                            // если новый предмет студента не равен старому предмету студента
                            if ( !(nSubject.getSubject().equals( lSubject.getSubject() )) ) {
                                // то
                                System.out.println(getLog(1, lStudent, nStudent));
                            } else if ( !(nSubject.getMark().equals( lSubject.getMark() )) ) {
                                System.out.println( getLog(1, lStudent, nStudent) );
                            }
                        }
                    }
                    continue;
                } else {
                    System.out.println(getLog(0, lStudent, nStudent));
                }
            }
        }
        return null;
    }

    public void lastSerializableJournal(Journal journal) throws IOException, ClassNotFoundException {
        //Чтение прошлого журнала из файла
        FileInputStream fileInputStream = new FileInputStream("tempJournal");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Journal lastJournal = (Journal) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println("last\n"+lastJournal.toString());
        System.out.println("new\n"+journal.toString());
        for (int i =0; i<lastJournal.getStudents().size(); i++){
            for (int j =0; j<=i;j++){
                System.out.println(lastJournal.getStudents().get(i).getStudent());
                System.out.println(journal.getStudents().get(j).getStudent());
                if (lastJournal.getStudents().get(i).getStudent().equals(journal.getStudents().get(j).getStudent())){
                    for (int k = 0; k<lastJournal.getStudents().get(i).getProgressStudents().size();k++){
                        for (int m=0;m<=k;m++){
                            //last
                            System.out.printf("%s:%s\n",lastJournal
                                    .getStudents()
                                    .get(i)
                                    .getProgressStudents()
                                    .get(k)
                                    .getSubject()
                                    ,lastJournal
                                    .getStudents()
                                    .get(i)
                                    .getProgressStudents()
                                    .get(k)
                                    .getMark());
//                          System.out.println(lastJournal
//                                    .getStudents()
//                                    .get(i)
//                                    .getProgressStudents()
//                                    .get(k)
//                                    .getSubject());
                            //new
                            System.out.printf("%s:%s\n",journal
                                    .getStudents()
                                    .get(j)
                                    .getProgressStudents()
                                    .get(m)
                                    .getSubject()
                                    ,journal
                                    .getStudents()
                                    .get(j)
                                    .getProgressStudents()
                                    .get(m)
                                    .getMark());
//                            System.out.println(journal
//                                    .getStudents()
//                                    .get(i)
//                                    .getProgressStudents()
//                                    .get(m)
//                                    .getSubject());
                        }
                    }

                }
            }
        };
        //System.out.println(lastJournal.toString());
//        lastJournal.getStudents().forEach(studentInformation -> {
//            String laststudent = studentInformation.getStudent();
//            journal.getStudents().forEach(studentInformation1 -> {
//                String newstudent = studentInformation1.getStudent();
//                System.out.println(laststudent);
//                System.out.println(newstudent);
//                if (laststudent.equals(newstudent)){
//                    System.out.println("Da");
//                } else {
//                    System.out.println("Net");
//                }
//            });





    }


}