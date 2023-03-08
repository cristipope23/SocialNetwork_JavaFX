package com.example.socialnetwork_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("NetworkFX");
        stage.setScene(scene);
        stage.show();

        //open another window
        Stage stage1 = new Stage();
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 400);
        stage1.setTitle("NetworkFX");
        stage1.setScene(scene1);
        stage1.show();
    }

    public static void main(String[] args) {
        launch();
    }
}