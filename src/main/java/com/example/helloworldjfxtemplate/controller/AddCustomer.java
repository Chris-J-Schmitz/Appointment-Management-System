package com.example.helloworldjfxtemplate.controller;


import com.example.helloworldjfxtemplate.DAO.CountryQuery;
import com.example.helloworldjfxtemplate.DAO.DivisionQuery;
import com.example.helloworldjfxtemplate.model.Country;
import com.example.helloworldjfxtemplate.model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.helloworldjfxtemplate.DAO.CustomerQuery.addCustomer;

/** Add Customer class
 * **/

public class AddCustomer implements Initializable {
    Stage stage;
    Parent scene;
    /** Text Fields*/
    public TextField customerID, customerName, customerAddress, postalCode, phoneNumber;
    /** Country Combo Box*/
    public ComboBox<Country> countryBox;
    /** Division Combo Box*/
    public ComboBox<Division> stateOrProvince;
    /** Buttons */
    public Button saveCustomer, cancelButton;



 /**
  * Fills combo boxes with appropriate content.
  * **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Fills combo box with available countries
        countryBox.setItems(CountryQuery.getAll());
        //Fills combo box with correct state or province
        countryBox.setOnAction(event -> {
            stateOrProvinceBox(event);
        });
    }

    /** Displays correct content in the state or province box.
     * Gathers the selection made in the country box.
     *
     * @param actionEvent  handles selection of box.
     * **/
    public void stateOrProvinceBox(ActionEvent actionEvent) {
        Country C = countryBox.getValue();
        try {
            stateOrProvince.setItems(DivisionQuery.displayDivision(C.getCountryId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    /**
     * Validates content input into form and adds the customer to the database.
     *
     * @param actionEvent handles save button
     *
     * @throws SQLException handles sql exceptions
     * @throws  IOException handles unknown exceptions
     *
     * **/

    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        // Retrieve values from fields
        String name = customerName.getText();
        String address = customerAddress.getText();
        String postal = postalCode.getText();
        String phone = phoneNumber.getText();
        Country country = countryBox.getValue();
        Division division = stateOrProvince.getValue();


        //
        // Validate fields
        if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty() || country == null || division == null) {
            showAlert("Validation Error", "All fields must be filled out before saving.");
            return; // Stop execution if validation fails
        }



        // Get current timestamp and user info
        LocalDateTime now = LocalDateTime.now();
        String currentUser = UserSession.getLoggedInUser();

        // Call the addCustomer method
        addCustomer(name, address, postal, phone, now, currentUser, now, currentUser, division.getDivisionId());

        // Show confirmation
        showAlert("Success", "Customer added successfully!");

        //Go back to the dashboard
        onCancel(actionEvent);

    }




    /**
     * Shows an alert on screen.
     *
     * @param title alert title
     * @param message alert message
     * **/
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /** Returns user to main menu.
     * @param event handles button click.
     *
     * @throws  IOException handles unkown exceptions
     * **/
    public void onCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Main Menu");
        stage.show();
        stage.centerOnScreen();

    }


}
