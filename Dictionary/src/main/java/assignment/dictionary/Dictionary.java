/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Group 2: Kene, Isaiah, Skylar
 * @author Professor: Ouda
 *
 * Created a GUI application that loads words from a file, and colors the words in
 * another file depending on whether they were in the former file. It uses a
 * dictionary/hashtable to store words from the original file and tests the words in
 * the other. The hashtable does not
 * use java.util.Hashtable<K,V>, or Object.hashCode() to implement hashing or storage.
 */

 public class Dictionary extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dictionary-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:src/main/resources/assignment/dictionary/UMIcon.png"));
        stage.setTitle("Spelling Checker (SampleApplication)");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
