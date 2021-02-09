package Writer;

import pojo.Journal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class XMLReader
 * Class writes information from objects to .txt file
 * @author Nikita Platonov
 * @version 1.0
 */

public class WriterForTest {

    File file = new File("src/test/java/Writer/output.txt");
    FileWriter fileWriter;

    /**
     *
     * @param journal
     */

    public void writeToTxt(Journal journal) throws IOException {
            fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(journal));
            fileWriter.close();
    }
}