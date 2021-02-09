package UsHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import pojo.Journal;
import pojo.ProgressStudent;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class UsHandlerTests extends Assertions{

    UsHandlerForTest handler = new UsHandlerForTest();
    Journal newjur = new Journal();
    Journal lastjur = new Journal();


    @Test
    @DisplayName("NotExistingFileForLog")
    public void NotExistingFileTestForLog() throws IOException {
        handler.createLog(new File("text.txt"), "test");
        assertTrue(new File("text.txt").exists());
    }

    @Test
    @DisplayName("createTempJournal")
    public void createTempJournal() throws ParserConfigurationException, SAXException, IOException {
        UsHandlerForTest.createTempJournal();
        File file = new File("src/test/java/UsHandler/tempJournal");
        assertTrue(file.exists());
    }
    @Test
    @DisplayName("changeLog")
    public void changeLog() throws IOException {
        ArrayList<ProgressStudent> ps = new ArrayList<>();
        ps.add(new ProgressStudent("math", "5"));
        newjur.addStudent("vasya", ps);

        ArrayList<ProgressStudent> ps1 = new ArrayList<>();
        ps1.add(new ProgressStudent("math", "5"));
        ps1.add(new ProgressStudent("AI", "5"));
        lastjur.addStudent("vasya", ps1);
        File logFile = new File("src/test/java/UsHandler/log.txt");
        handler.createLog(logFile,"");
        long previousSize = logFile.length();
        handler.createLog(logFile,handler.changeLog(lastjur,newjur));
        long newSize = logFile.length();
        assertTrue(newSize > previousSize);
    }
}
