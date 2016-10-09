/**
 * Main.java
 * Veronica Child and Adam Tigar
 *
 * Main program for a TRON lightcycle subgame in JavaFX.
 */

package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.scene.paint.Color;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("TRON: Lightcycles");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                           @Override
                                           public void handle(WindowEvent t) {
                                               Platform.exit();
                                               System.exit(0);
                                           }
                                       });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent)loader.load();
        Controller controller = loader.getController();

        // Sets up handler to respond to KeyBoard activity.
        root.setOnKeyPressed(controller);

        // Initializes visual scene of the game.
        Scene scene = new Scene(root, 700, 500, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
