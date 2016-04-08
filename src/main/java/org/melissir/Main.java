package org.melissir;
/**
 * Created by mrhein on 4/7/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Address.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        // lines added:
        stage.setWidth(700);
        stage.setHeight(500);
        stage.setTitle("Address Book");

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}