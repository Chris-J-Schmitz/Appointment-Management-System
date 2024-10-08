package com.example.helloworldjfxtemplate.controller;


import com.example.helloworldjfxtemplate.DAO.CountryQuery;
import com.example.helloworldjfxtemplate.DAO.CustomerQuery;
import com.example.helloworldjfxtemplate.DAO.DivisionQuery;
import com.example.helloworldjfxtemplate.model.Country;
import com.example.helloworldjfxtemplate.model.Customer;
import com.example.helloworldjfxtemplate.model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.example.helloworldjfxtemplate.DAO.CustomerQuery.addCustomer;
import static com.example.helloworldjfxtemplate.DAO.CustomerQuery.modifyCustomer;

/**
 * Modify Customer Class
 * **/
public class ModifyCustomer {
    Stage stage;
    Parent scene;
    //All variable definitions
    public TextField customerID, customerName, customerAddress, postalCode, phoneNumber;
    public ComboBox<Country> countryBox;
    public ComboBox<Division> stateOrProvince;
    public Button saveCustomer, cancelButton;
    private Customer customer;



    /**
     * Validates content input into form and adds the customer to the database.
     *
     * @param actionEvent handles save button
     *
     * @throws SQLException handles sql exceptions
     * @throws  IOException handles unknown exceptions
     *
     * **/
    public void onSave(ActionEvent actionEvent) throws IOException, SQLException {
        // Retrieve values from fields
        int id = Integer.parseInt(customerID.getText());
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

        // Call the modifyCustomer method
        CustomerQuery.modifyCustomer(id,name,address,postal,phone,currentUser, Timestamp.valueOf(now), division.getDivisionId(), country.getCountryId());

        // Show confirmation
        showAlert("Success", "Customer modified successfully!");

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

    /** Returns user to main menu
     *
     * @throws IOException handles exceptions
     * **/

    public void onCancel(ActionEvent event) throws IOException {
        System.out.println("CancelButton Clicked!");

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Main Menu");
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * Calls populate fields function to fill in the form with proper customer info.
     *
     * @param customer the customer passed into the form.
     *
     * @throws SQLException handles sql exception
     *
     * **/

    public  void initialize(Customer customer) throws SQLException {
        this.customer = customer;
        populateFields();
    }


    /**
     *
     *  Fills the form with the proper customer info.
     *
     * @throws SQLException handles sql exceptions
     * **/
    private void populateFields() throws SQLException {
        customerID.setText(String.valueOf(customer.getCustomerId()));
        customerName.setText(customer.getCustomerName());
        customerAddress.setText(customer.getCustomerAddress());
        postalCode.setText(customer.getCustomerPostalCode());
        phoneNumber.setText(customer.getCustomerPhone());
        Country c = CountryQuery.returnCountry(customer.getCustomerCountryId());
        countryBox.setValue(c);
        Division d = DivisionQuery.returnDivisionLevel(customer.getCustomerDivisionId());
        stateOrProvince.setValue(d);
        //Fills combo box with available countries
        countryBox.setItems(CountryQuery.getAll());
        //Fills Division box with relevant divisions
        stateOrProvince.setItems(DivisionQuery.displayDivision(c.getCountryId()));
        //Updates the division options based on country selection if changed
        countryBox.setOnAction(event -> {
            stateOrProvinceBox((ActionEvent) event);
        });





    }

    /** Displays correct content in the state or province box.
     * Gathers the selection made in the country box.
     *
     * @param actionEvent  handles selection of box.
     * **/
    public void stateOrProvinceBox(ActionEvent actionEvent) {
        Country C = (Country) countryBox.getValue();
        try {
            stateOrProvince.setItems(DivisionQuery.displayDivision(C.getCountryId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
