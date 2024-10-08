package com.example.helloworldjfxtemplate.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.helloworldjfxtemplate.DAO.UserQuery.*;

/**
 * Login class
 *
 */

public class Login implements Initializable {
    Stage stage;
    Parent scene;

    // variable definitions for login page
    public Label usernameLabel;
    public Label passwordLabel;
    public TextField usernameInput;
    public PasswordField passwordInput;
    public Button loginButton;
    public Label locationLabel;
    private static final String LOG_FILE = "login_activity.txt";


    /**
     * Validates user login and displays appropriate error or success messages, then logs
     * the attempts in the login_activity.txt file.
     *
     * @param event event for Login button to execute validation
     * @throws IOException  addresses numerous unhandled exceptions
     * @throws SQLException addresses SQL exceptions for validation
     */

    public void onLogin(ActionEvent event) throws IOException, SQLException {
        //get resource bundle for error translation
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());

        // Get user inputs
        String user = usernameInput.getText();
        String pass = passwordInput.getText();

        //Check if any fields are blank
        if (user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, rb.getString("inputErrorTitle"), rb.getString("inputErrorMessage"));
            return;
        }

        //Perform user validation (checking both username and password)
        if (userValidation(user, pass)) {
            // If both username and password are valid, proceed with login
            // Log the attempt
            loginAttempt(user, true);
            UserSession.setLoggedInUser(user);
            //Show main menu
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Menu");
            stage.show();
            stage.centerOnScreen();


        } else {
            // Step 3: If validation fails, check specific issues (wrong username or password)
            if (!userNameLogin(user)) {
                // Username doesn't exist
                showAlert(Alert.AlertType.ERROR, rb.getString("loginErrorTitle"), rb.getString("usernameNotExist"));
                usernameInput.setText("");
                passwordInput.setText("");
            } else {
                // Username exists, so the password must be wrong
                showAlert(Alert.AlertType.ERROR, rb.getString("loginErrorTitle"), rb.getString("passwordIncorrect"));
                passwordInput.setText("");
                loginAttempt(user,false);

            }
        }
    }

    /**
     * Shows an alert on screen
     * @param alertType type of alert
     * @param title alert title
     * @param message alert message
     * **/
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Logs the attempted login, whether successfull or not.
     *
     * @param success boolean of successfull attempt
     * @param username username entered on screen
     *
     * @throws  IOException addresses unhandled exception
     *
     * **/
    private void loginAttempt(String username, boolean success) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

        String logMessage = String.format("%s - Username: %s - Login %s",
                now.format(formatter),
                username,
                success?"Success" : "Failure");

        //Write log message to the file.
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true)){
            fileWriter.write(logMessage);
            fileWriter.write("\n");
        }  catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
    /**
     * Handles the timezone labeling as well as language translation.
     *
     * **/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle rb = ResourceBundle.getBundle("Language.lang", Locale.getDefault());

        //Display text labels and buttons with correct language
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        loginButton.setText(rb.getString("login"));
        //Display timezone
        String timeZone = ZoneId.systemDefault().toString();
        locationLabel.setText(rb.getString("location") + ": " + timeZone);
        //Display errors based on language


    }
}
