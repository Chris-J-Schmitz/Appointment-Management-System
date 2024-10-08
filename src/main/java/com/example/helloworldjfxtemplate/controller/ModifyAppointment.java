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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Modify Appointment Class.
 * **/

public class ModifyAppointment {
    Stage stage;
    Parent scene;

    public Button saveButton, cancelButton;
    public TextField appointmentID, title, type, description, aptLocation;

    public DatePicker startDate,endDate;
    public ComboBox<LocalTime> startTime, endTime;
    public ComboBox<Customer> customerID;
    public  ComboBox<User> userID;
    public  ComboBox<Contact> contact;
    private Appointment appointment;


    /**
     * Saves the content provided into the form.
     * Validates all info is enterd properly before saving and adding appointment to the database
     *
     * @param actionEvent handles button click
     *
     * @throws SQLException handles SQL Exception
     * @throws IOException handles unknown exception
     * **/
    public void onSave(ActionEvent actionEvent) throws IOException, SQLException {
        // Collect the input values from the fields
        int id = Integer.parseInt(String.valueOf(appointmentID.getText()));
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

        // Use current time for  lastUpdate
        LocalDateTime now = LocalDateTime.now();

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
        if(Appointment.overlapCheck(appointment.getAppointmentId(),aCustomer.getCustomerId(),aStartDateTime,aEndDateTime)){
            return; //Stop execution if validation fails
        }

        //Provided all validation passes, call the add method and return to main Dashboard
        AppointmentsQuery.modifyAppointment(id,aTitle,aDesc,aLocation,aType,aStartDateTime,aEndDateTime,aCustomer.getCustomerId(), aUser.getUserId(), aContact.getContactId());
        // Show a success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment modified successfully!");

        // After saving, return to the main dashboard
        onCancel(actionEvent);
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

    /** Returns user to main menu
     *
     * @throws IOException handles exceptions
     * **/

    public void onCancel(ActionEvent event) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Main Menu");
        stage.show();
        stage.centerOnScreen();
    }


    /**
     * Populates form with info from selected appointment.
     *
     * @throws SQLException handles sql exceptions
     *
     * **/
    private void populateFields() throws SQLException {
        appointmentID.setText(String.valueOf(appointment.getAppointmentId()));
        title.setText(appointment.getAppointmentTitle());
        type.setText(appointment.getAppointmentType());
        description.setText(appointment.getAppointmentDescription());
        aptLocation.setText(appointment.getAppointmentLocation());


        //Set Customer
        Customer customer = CustomerQuery.getCustomer(appointment.getAppointmentCustomerId());
        customerID.setValue(customer);
        //Set User
        User user = UserQuery.getUser(appointment.getAppointmentUserId());
        userID.setValue(user);
        //Set Contact
        Contact c = ContactQuery.getContact(appointment.getAppointmentContact());
        contact.setValue(c);

        //Populate combo boxes with choices
        startTime.setItems(Appointment.getTimes());
        endTime.setItems(Appointment.getTimes());
        customerID.setItems(CustomerQuery.getCustomerList());
        userID.setItems(UserQuery.getUserList());
        contact.setItems(ContactQuery.getContactList());
    }

    /**
     * Calls the populate fields function to populate the form fields.
     *
     * @param appointment appointment passed into form
     *
     * @throws SQLException handles sql exceptions
     * **/

    public void initialize(Appointment appointment) throws SQLException {
        this.appointment = appointment;
        populateFields();


    }
}
