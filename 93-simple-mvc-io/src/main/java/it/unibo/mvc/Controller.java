package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE_NAME  = "output.txt";
    private File currentFile = new File(HOME + SEPARATOR + DEFAULT_FILE_NAME);

    public void setCurrentFile(final File inputFile){
        if(inputFile.exists()){
            this.currentFile = inputFile;
        }
        else{
            throw new IllegalArgumentException("Argument file does not exist");
        }
    }

    public File getCurrentFile(){
        return this.currentFile;
    }

    public String getPath(){
        return this.currentFile.getPath();
    }

    public void saveContentOnFile(final String contentString) throws IOException{
        try(final PrintStream printStream = new PrintStream(currentFile, StandardCharsets.UTF_8)){
            printStream.println(contentString);
        }
    }

}
