package com.example.helloworldjfxtemplate.controller;

import com.example.helloworldjfxtemplate.DAO.AppointmentsQuery;
import com.example.helloworldjfxtemplate.DAO.ContactQuery;
import com.example.helloworldjfxtemplate.DAO.CustomerQuery;
import com.example.helloworldjfxtemplate.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Reports class
 *
 * **/

public class Reports {
    public Button menuButton;
    public TableView apptTypeTable;
    public TableColumn apptType, appTypeTotal;
    public TableView apptMonthTable;
    public TableColumn apptMonth,apptMonthTotal;
    public TableView contactTable;
    public TableColumn aptId, aptTitle, aptType, aptDescription, aptStart, aptEnd, aptCustId;
    public TableView customerTable;
    public TableColumn custName, custTotal;
    public ComboBox<Contact> contactBox;


    /**
     * Populates all relevant info.
     *
     * Fills Tables and combo boxes with info related to the specific report.**/


    public void initialize () {
        //Appt month population
        apptMonthTable.setItems(AppointmentsQuery.getAppointmentTypeMonth());
        apptMonthTable.setPlaceholder(new Label("No available data for the month"));
        apptMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptMonthTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentTypeTotal"));

        //Appt type population
        apptTypeTable.setItems(AppointmentsQuery.getAppointmentType());
        apptMonthTable.setPlaceholder(new Label("No available data for type"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appTypeTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentTypeTotal"));

        //Appt by Customer
        customerTable.setItems(CustomerQuery.getCustomerAppTotal());
        customerTable.setPlaceholder(new Label("No available data for customers"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentCount"));

        //Appt by contact
        contactBox.setItems(ContactQuery.getContactList());
        contactBox.setOnAction(event -> {
            String contactName = String.valueOf(contactBox.getValue());
            int contactId = 0;
            try {
                contactId = ContactQuery.getContactId(contactName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(AppointmentsQuery.getAppointmentByContact(contactId).isEmpty()) {
                contactTable.setPlaceholder(new Label("No appointments for : " + contactName));
                contactTable.refresh();
            }
            contactTable.setItems(AppointmentsQuery.getAppointmentByContact(contactId));
        });


        contactTable.setPlaceholder(new Label("Please select a contact."));
        aptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        aptTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        aptType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        aptDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        aptStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        aptEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        aptCustId.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        contactTable.refresh();
    }

    /**
     * Returns user to main menu
     *
     * @throws IOException handles unknown exceptions
     *
     * **/
    public void onMenu(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.setTitle("Main Menu");
        stage.show();
        stage.centerOnScreen();

    }
}
