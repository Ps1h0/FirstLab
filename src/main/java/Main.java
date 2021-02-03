import Reader.XMLReader;
import UsHandler.UsHandler;
import Writer.Writer;
import org.xml.sax.SAXException;
import pojo.Journal;

import javax.xml.parsers.ParserConfigurationException;
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


            UsHandler usHandler = new UsHandler("Main");
            //usHandler.lastSerializableJournal(jur);
            //System.out.println(jur.getStudents().toString());
            //System.out.println("Last journal");

            //System.out.println("new journal");
           // System.out.println(reader.getPOJO());

            FileOutputStream fileOutputStream = new FileOutputStream("tempJournal");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(jur);
            objectOutputStream.close();



        } catch (Exception e){
            UsHandler.HandlerException(e, "Main");
        }
    }
}
