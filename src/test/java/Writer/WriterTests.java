package Writer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Journal;
import pojo.ProgressStudent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WriterTests extends Assertions {

    WriterForTest writer = new WriterForTest();
    Journal jur = new Journal();

    @Test
    @DisplayName("NotExistingFile")
    public void NotExistingFileTest() throws IOException {
        writer.writeToTxt(jur);
        assertTrue(new File("src/test/java/Writer/output.txt").exists());
    }

    @Test
    @DisplayName("WorkingTest")
    public void WorkingTest() throws IOException {
        ArrayList<ProgressStudent> ps = new ArrayList<>();
        ps.add(new ProgressStudent("math", "5"));
        jur.addStudent("vasya", ps);
        File file = new File("src/test/java/Writer/output.txt");
        long previousSize = file.length();
        writer.writeToTxt(jur);
        long newSize = file.length();
        assertTrue(newSize > previousSize);
    }
}
