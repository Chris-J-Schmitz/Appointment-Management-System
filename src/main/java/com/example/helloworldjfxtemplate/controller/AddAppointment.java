package com.example.helloworldjfxtemplate.controller;


import com.example.helloworldjfxtemplate.DAO.AppointmentsQuery;
import com.example.helloworldjfxtemplate.DAO.ContactQuery;
import com.example.helloworldjfxtemplate.DAO.CustomerQuery;
import com.example.helloworldjfxtemplate.DAO.UserQuery;
import com.example.helloworldjfxtemplate.model.Appointment;
import com.example.helloworldjfxtemplate.model.Contact;
import com.example.helloworldjfxtemplate.model.Customer;
import com.example.helloworldjfxtemplate.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Add Appointment class
 *
 * **/

public class AddAppointment implements Initializable {
    Stage stage;
    Parent scene;

    /** Buttons to save or cancel
     * **/
    public Button saveButton, cancelButton;
    /** public text fields within the form
     * **/
    public TextField appointmentID, title, type, description, aptLocation;
    /** Date Pickers**/
    public DatePicker startDate,endDate;
    /** Local time Combo Boxes*/
    public ComboBox<LocalTime> startTime, endTime;
    /** Customer Combo Boxes*/
    public ComboBox<Customer> customerID;
    /** User Combo Boxes*/
    public ComboBox<User> userID;
    /** Contact Combo Boxes*/
    public  ComboBox<Contact> contact;

    /**
     * Populate combo boxes with correct info
     * @param url url
     * @param resourceBundle  resourceBundle
     * **/
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        //Fill combo boxes
        startTime.setItems(Appointment.getTimes());
        endTime.setItems(Appointment.getTimes());
        customerID.setItems(CustomerQuery.getCustomerList());
        userID.setItems(UserQuery.getUserList());
        contact.setItems(ContactQuery.getContactList());
    }

    /**
     * Saves the content provided into the form.
     * Validates all info is enterd properly before saving and adding appointment to the database
     *
     * @param actionEvent handles button click
     *
     * @throws SQLException handles SQL Exception
     * @throws IOException handles unknown exception
     * **/
    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        // Collect the input values from the fields
        String aTitle = title.getText();
        String aType = type.getText();
        String aDesc = description.getText();
        String aLocation = aptLocation.getText();

        // Get values from ComboBoxes
        Contact aContact = contact.getValue();
        Customer aCustomer = customerID.getValue();
        User aUser = userID.getValue();

        // Combine the selected date and time for appointment start and end
        LocalDate aStartDate = startDate.getValue();
        LocalDate aEndDate = endDate.getValue();
        LocalTime aStartTime = startTime.getValue();
        LocalTime aEndTime = endTime.getValue();

        // Validate fields
        if (aTitle.isEmpty() || aType.isEmpty() || aDesc.isEmpty() || aLocation.isEmpty() ||
                aStartDate == null || aEndDate == null || aStartTime == null || aEndTime == null ||
                aCustomer == null || aUser == null || aContact == null) {

            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled out before saving.");
            return; // Stop execution if validation fails

        }

        // Ensure start and End date are the same.
        if (!(aStartDate.equals(aEndDate))) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Appointment cannot start and end on separate days.");
            return; // Stop execution if validation fails
        }


        // Combine date and time into LocalDateTime
        LocalDateTime aStartDateTime = LocalDateTime.of(aStartDate, aStartTime);
        LocalDateTime aEndDateTime = LocalDateTime.of(aEndDate, aEndTime);

        // Get the current user (logged-in user)
        String currentUser = UserSession.getLoggedInUser();

        // Use current time for createdDate and lastUpdate
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime lastUpdate = LocalDateTime.now();

        //Validates Business hours
        if (Appointment.businessHoursCheck(aStartDateTime,aEndDateTime)) {
            return; //Stop execution if validation fails
        }

        //Validates start and end times are in order
        if (aEndDateTime.isBefore(aStartDateTime)) {
            showAlert(Alert.AlertType.ERROR,"Error","Appointment start time and end time are invalid");
            return; //Stop execution if validation fails
        }
        //Validates overlap
        if(Appointment.overlapCheck(0,aCustomer.getCustomerId(),aStartDateTime,aEndDateTime)){
            return; //Stop execution if validation fails
        }

        //Provided all validation passes, call the add method and return to main Dashboard
        AppointmentsQuery.addAppointment(aTitle, aDesc, aLocation, aType, aStartDateTime, aEndDateTime,
                createdDate, currentUser, lastUpdate, currentUser,
                aCustomer.getCustomerId(), aUser.getUserId(), aContact.getContactId());

        // Show a success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment added successfully!");

        // After saving, return to the main dashboard
        onCancel(actionEvent);
    }


    /** Returns user to main menu
     * @param event button click
     *
     * @throws IOException handles exceptions
     * **/
    public void onCancel(ActionEvent event) throws IOException {


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Main Menu");
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * Shows an alert on screen
     * @param alertType type of alert
     * @param title alert title
     * @param content alert content
     * **/
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
