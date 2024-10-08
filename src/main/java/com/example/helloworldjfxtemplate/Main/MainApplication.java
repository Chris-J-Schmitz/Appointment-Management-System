package com.example.helloworldjfxtemplate.Main;


import com.example.helloworldjfxtemplate.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 *  The main class of the application.
 * **/
public class MainApplication extends Application {
    /**
     * Opens the Log In screen.
     *
     * @param mainStage setting stage
     *
     */
    @Override
    public void start(Stage mainStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            mainStage.setTitle("Login");
            mainStage.setScene(new Scene(root, 650, 400));
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Connects and Disconnects from database.
     * @param args args
     *
     * @throws  SQLException unhandled exception
     *
     * **/
    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        launch();


        JDBC.closeConnection();
    }
}