import Reader.XMLReader;
import UsHandler.UsHandler;
import Writer.Writer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        try {

            XMLReader reader = new XMLReader();
            reader.createDocument("src/main/resources/text.xml");
            Writer writer = new Writer();
            writer.writeToTxt(reader.getPOJO());

            UsHandler usHandler = new UsHandler("Main");
            usHandler.lastSerializableJournal(reader.getPOJO());
            //System.out.println(reader.getPOJO());

            FileOutputStream fileOutputStream = new FileOutputStream("tempJournal");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(reader.getPOJO());
            objectOutputStream.close();



        } catch (Exception e){
            UsHandler.HandlerException(e, "Main");
        }
    }
}
