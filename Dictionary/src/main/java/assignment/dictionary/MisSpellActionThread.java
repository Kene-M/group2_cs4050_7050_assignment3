package assignment.dictionary;

import java.io.*;
import java.util.*;
import javafx.application.Platform;

/**
 *
 * @author Kene, Skylar, Isaiah.
 * A Thread that contains the application we are going to animate
 *
 */

public class MisSpellActionThread implements Runnable {

    DictionaryController controller;
    private final String textFileName;
    private final String dictionaryFileName;

    private LinesToDisplay myLines;
    private DictionaryInterface<String, String> myDictionary;
    private boolean dictionaryLoaded;

    /**
     * Constructor for objects of class MisspellActionThread
     *
     * @param controller
     */
    public MisSpellActionThread(DictionaryController controller) {
        super();

        this.controller = controller;
        textFileName = "src/main/resources/assignment/dictionary/check.txt";
        dictionaryFileName = "src/main/resources/assignment/dictionary/sampleDictionary.txt";

        myDictionary = new HashedMapAdaptor<String, String>();
        myLines = new LinesToDisplay();
        dictionaryLoaded = false;

    }

    @Override
    public void run() {

        loadDictionary(dictionaryFileName, myDictionary);


        Platform.runLater(() -> {
            if (dictionaryLoaded) {
               controller.SetMsg("The Dictionary has been loaded"); 
            } else {
               controller.SetMsg("No Dictionary is loaded"); 
            }
        });
        
        checkWords(textFileName, myDictionary);

    }

    /**
     * Load the words into the dictionary.
     *
     * @param theFileName The name of the file holding the words to put in the
     * dictionary.
     * @param theDictionary The dictionary to load.
     */
    public void loadDictionary(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
// ADD CODE HERE
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            File fileIn = new File(theFileName);
            input = new Scanner(fileIn);

            String word;
            while(input.hasNextLine()){
                 word = input.nextLine();
                theDictionary.add(word, word);
            }

            System.out.println(theDictionary); // TODO FOR DEBUGGING
            System.out.println(theDictionary.isEmpty());

            input.close();
            dictionaryLoaded = true;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         


        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get the words to check, check them, then put Wordlets into myLines. When
     * a single line has been read do an animation step to wait for the user.
     *
     */
    public void checkWords(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        String tempString; //temporary string used to hold the current line that is being scanned
        boolean wordValdidity; //boolean variable that stores if the current word is in the dictionary or not
        try {
 
// ADD CODE HERE
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            File fileIn = new File(theFileName);
            input = new Scanner(fileIn);
            while(input.hasNextLine()){
                tempString = input.nextLine();

                //for loop that iterates through each word in the current line
                for(String currentWord : tempString.split("\\s+")) {
                    // Remove all punctuation attached to the word.
                    String cleanWord = currentWord.replaceAll("\\p{Punct}", "");

                    // Sets wordValidity to the return value of checkWord on the current word
                    wordValdidity = checkWord(cleanWord, theDictionary);

                    // Adds a new wordlet of the currentWord and its validity to myLines
                    myLines.addWordlet(new Wordlet(currentWord + " ", wordValdidity));
                }

                // Display the current file line in correct color.
                showLines(myLines);
                myLines.nextLine();
            }
            input.close();

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Check the spelling of a single word.
     *
     */
    public boolean checkWord(String word, DictionaryInterface<String, String> theDictionary) {
        boolean result = false;

        // ADD CODE HERE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if(theDictionary.contains(word))
            return true;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        return result;

    }

    private void showLines(LinesToDisplay lines) {
        try {
            Thread.sleep(500);
            Platform.runLater(() -> {
                if (myLines != null) {
                    controller.UpdateView(lines);
                }
            });
        } catch (InterruptedException ex) {
        }
    }

} // end class MisspellActionThread

