package UsHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class UsHandlerTests extends Assertions{

    UsHandlerForTest handler = new UsHandlerForTest();

    @Test
    @DisplayName("NotExistingFileForLog")
    public void NotExistingFileTestForLog() throws IOException {
        handler.createLog(new File("text.txt"), "test");
        assertTrue(new File("text.txt").exists());
    }


}
