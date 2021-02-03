package Reader;

import UsHandler.UsHandler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

import pojo.ProgressStudent;
import pojo.Journal;


/**
 * Class XMLParser
 * Class reads XML file and transform it into a POJO
 * @author Duboviy Zhuk
 * @version 0.0001
 */

public class XMLReader {

    Document document = null;
    Journal journal = new Journal();

    /**
     * Method creates a parser
     * @param filePath path to XML file
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */

    public void createDocument(String filePath) throws ParserConfigurationException, IOException, SAXException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        }catch (Exception e) {
            UsHandler.HandlerException(e, "XMLReader");
        }
    }

    /**
     *
     * @return pojo objects filled by xml file
     */

    public Journal getPOJO() throws IOException {
        try {

            NodeList nList = document.getElementsByTagName("student");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    String name = elem.getAttribute("name");
                    NodeList nList1 = elem.getElementsByTagName("course");
                    ArrayList<ProgressStudent> marks = new ArrayList<>();
                    for (int j = 0; j < nList1.getLength(); j++) {
                        Node nNode1 = nList1.item(j);
                        Element elem1 = (Element) nNode1;
                        marks.add(new ProgressStudent(elem1.getAttribute("name"), elem1.getAttribute("mark")));
                    }
                    journal.addStudent(name, marks);
                }
            }
        }catch (Exception e) {
            UsHandler.HandlerException(e, "XMLReader");
        }
            return journal;

    }
}



