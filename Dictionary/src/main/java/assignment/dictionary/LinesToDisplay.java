package assignment.dictionary;

import java.util.Iterator;


/**
 * @author Kene, Skylar, Isaiah.
 *
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
        currentLine = 0;
        lines = (AList<Wordlet>[]) new AList[LINES];

        // Instantiate the list in every line.
        for (int i = 0; i < LINES; i++) {
            lines[i] = new AList<>();
        }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE

//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>
        // Add the wordlet to the current line
        lines[currentLine].add(w);
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

        // Check if current is last index of lines.
        if (currentLine == LINES - 1) {
            // Push all lines upward, and delete last line.
            for (int i = 0; i < LINES - 1; i++) {
                lines[i] = lines[i + 1];
            }
            lines[LINES - 1] = new AList<>();
        }
        else // Go to next line
            currentLine++;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }
    public int getCurrentLine(){
        return currentLine;
    }

    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
