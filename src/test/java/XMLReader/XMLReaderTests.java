package XMLReader;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

class XMLReaderTests extends Assertions {

    private static final XMLReaderForTest reader = new XMLReaderForTest();

    @Test
    @DisplayName("NotExistingFile")
    public void NotExistingFileTest() throws IOException, SAXException, ParserConfigurationException {
        Throwable thrown = assertThrows(FileNotFoundException.class, () -> {
            reader.createDocument("NotExistingFile.txt");
        });
    }

    @Test
    @DisplayName("EmptyFile")
    public void EmptyFileTest() throws IOException, SAXException, ParserConfigurationException {
        Throwable thrown = assertThrows(SAXParseException.class, () -> {
            reader.createDocument("src/test/java/XMLReader/EmptyFile.xml");
        });
    }

    @Test
    @DisplayName("NotValidFile")
    public void NotValidFileTest() throws IOException, SAXException, ParserConfigurationException {
        Throwable thrown = assertThrows(SAXParseException.class, () -> {
            reader.createDocument("src/test/java/XMLReader/NotValidFile.xml");
        });
    }
}
