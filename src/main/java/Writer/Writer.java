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

public class Writer {

    File file = new File("output.txt");
    FileWriter fileWriter;

    /**
     *
     * @param journal
     */

    public void writeToTxt(Journal journal) {
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(journal));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}