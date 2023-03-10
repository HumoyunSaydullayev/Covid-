package com.example.covid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserFXmls/User.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Asosiy_page.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OperatorFXmls/Operator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1550, 800);
        stage.setTitle("Covid-19 vaccination system");
        scene.getStylesheets().add(String.valueOf(getClass().getResource("Style.css")));
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }
 
    public static void main(String[] args) {
        launch();
    }
}