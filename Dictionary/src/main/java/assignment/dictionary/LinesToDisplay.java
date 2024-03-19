package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 10;     // Display 10 lines
    private AList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    @SuppressWarnings("unchecked")
    public LinesToDisplay() {
        //ADD CODE FOR THE CONSTRUCTOR
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        currentLine = 1;
        lines = (AList<Wordlet>[]) new AList[LINES];
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE

//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        lines[currentLine].add(lines[currentLine].getLength(), w);
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     *
     */
    public void nextLine() {
        //ADD CODE TO HANDLE THE NEXT LINE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        if (currentLine + 1 > LINES) {
            //Delete line one and replace push all lines upward
            for(int i = 1; i <= LINES; ++i){
                lines[i].clear();
                for(int j = 1; j <= lines[i+1].getLength(); ++j) {
                    lines[i].add(lines[i+1].getEntry(j));
                }
            }
        } else {
            currentLine++;
        }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }
    public int getCurrentLine(){
        return currentLine;
    }

    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
