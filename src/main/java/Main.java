import Reader.XMLReader;
import UsHandler.UsHandler;
import Writer.Writer;
import pojo.Journal;
import pojo.StudentInformation;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        try {

            XMLReader reader = new XMLReader();
            reader.createDocument("src/main/resources/text.xml");
            Writer writer = new Writer();
            Journal jur = (Journal) reader.getPOJO();
            writer.writeToTxt(jur);


            UsHandler usHandler = new UsHandler();
            Journal lastJournal = usHandler.getLastJournal();
            File logFile = new File("log.txt");
            usHandler.createLog(logFile,usHandler.changeLog(lastJournal, jur));



            UsHandler.createTempJournal();




        } catch (Exception e){
            UsHandler.HandlerException(e, "Main");
        }
    }
}
