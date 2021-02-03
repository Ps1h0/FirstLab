import Reader.XMLReader;
import Writer.Writer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        XMLReader reader = new XMLReader();
        reader.createDocument("src/main/resources/text.xml");
        Writer writer = new Writer();
        writer.writeToTxt(reader.getPOJO());
    }
}
