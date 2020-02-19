package com.pauldesautels.todolist;

import com.pauldesautels.todolist.datamodel.ToDoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("Paul's Exceptional To Do List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception { // Runs when user exits the application
        try {
            ToDoData.getInstance().storeToDoItems();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception { // Started by the browser when program is loaded and ran - using this to load previously stored items
        try {
            ToDoData.getInstance().loadToDoItems();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
