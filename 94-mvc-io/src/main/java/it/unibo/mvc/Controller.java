package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    public void setNextStringToPrint(String string);

    public String getNextStringToPrint();
    
    public List<String> getHistoryOfPrintedString();

    public void printString();
}
