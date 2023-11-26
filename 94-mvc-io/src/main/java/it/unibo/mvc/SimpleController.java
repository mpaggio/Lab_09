package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private List<String> history = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextStringToPrint(String string) {
        if(string != null){
            this.nextString = string;
        }
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getHistoryOfPrintedString() {
        return this.history;
    }

    @Override
    public void printString() {
        if(this.nextString == null){
            throw new IllegalStateException("String is not set yet");
        }
        this.history.add(this.nextString);
        System.out.println(this.nextString);
    }

}
