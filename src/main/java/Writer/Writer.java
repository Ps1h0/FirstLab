package Writer;

import pojo.Journal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    File file = new File("output.txt");
    FileWriter fileWriter;

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