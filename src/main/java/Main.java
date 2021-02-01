import Reader.XMLReader;
import UsHandler.UsHandler;
import Writer.Writer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        try {

            XMLReader reader = new XMLReader();
            reader.createDocument("src/main/resources/txt.xml");
            Writer writer = new Writer();
            writer.writeToTxt(reader.getPOJO());
        } catch (Exception e){
            new UsHandler(e, "Main");
        }
    }
}
