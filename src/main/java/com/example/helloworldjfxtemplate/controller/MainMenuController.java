package com.example.helloworldjfxtemplate.controller;

import com.example.helloworldjfxtemplate.DAO.AppointmentsQuery;
import com.example.helloworldjfxtemplate.DAO.CustomerQuery;
import com.example.helloworldjfxtemplate.model.Appointment;
import com.example.helloworldjfxtemplate.model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Main Menu Class
 *
 * **/
public class MainMenuController {

    public Label aptOrder;
    public RadioButton aptAll;
    public ToggleGroup aptView;
    public RadioButton aptWeek;
    public RadioButton aptMonth;
    //Declaring Variables
    Stage stage;
    Parent scene;
    public TableView appointmentsTable;
    public TableColumn apt_id,appTitle,appDescription,appLocation,appContact,appType,appStart,appEnd,appCustomerID,appUserID;
    public TableView customerTable;
    public TableColumn custID,custName,custAddress,custCountry,custDivision,custPostalCode,custPhone;
    public boolean appointments = true;
    public Button logout, addButton, modifyButton, deleteButton;
    public Label upcomingAptMessage, header, userDash;
    public RadioButton appointmentsRadio, customersRadio;
    public ToggleGroup AptOrCustomer;
    private static boolean firstVisit = true;





    /**
     *  changes to the add customer or add appointment screen depending on the appointments boolean value.
     * **/
    public void onAdd(ActionEvent event) throws IOException {

        if(appointments) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/addAppointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Add Appointment");
            stage.show();
            stage.centerOnScreen();
        } else {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Add Customer");
            stage.show();
            stage.centerOnScreen();
        }

    }

    /**
     *  Changes to the modify customer or modify appointment screen depending on the appointments boolean value.
     *  Validates that a selection is made before executing.
     *
     * @param event event to change to modify screen
     *
     * @throws IOException  addresses numerous unhandled exceptions
     * @throws SQLException addresses SQL exceptions for validation
     * **/

    public void onModify(ActionEvent event) throws IOException, SQLException {
        //Validates a selection then loads the selection into the respective modify screen
        if(appointments) {
            Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();

            //Check for selected appointment
            if(selectedAppointment ==null) {
                showAlert(Alert.AlertType.WARNING, "Selection Error", "No appointment selected. Please select an appointment to modify.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyAppointment.fxml"));
            Parent modifyAppointmentView = loader.load();

            // Pass the selected customer
            ModifyAppointment modifyAppointmentController = loader.getController();
            modifyAppointmentController.initialize(selectedAppointment);

            // Set up new scene
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(modifyAppointmentView)); // Use modifyAppointmentView instead of loading again
            stage.setTitle("Modify Appointment");
            stage.show();
            stage.centerOnScreen();
        } else {
            Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
            //Check for selected customer
            if(selectedCustomer ==null) {
                showAlert(Alert.AlertType.WARNING, "Selection Error", "No customer selected. Please select a customer to modify.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/modifyCustomer.fxml"));
            Parent modifyCustomerView = loader.load();

            // Pass the selected customer
            ModifyCustomer modifyCustomerController = loader.getController();
            modifyCustomerController.initialize(selectedCustomer);

            // Set up the new scene
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(modifyCustomerView));
            stage.setTitle("Modify Customer");
            stage.show();
            stage.centerOnScreen();
        }
    }

    /**
     * Deletes a customer or appointment depending on appointments boolean value.
     *
     * @param event even to delete
     *
     * @throws  SQLException addresses SQL exception**/

    public void onDelete(ActionEvent event) throws SQLException {

        if (appointments) {
            // Get the selected appointment from the table
            Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();



            // Check if an appointment is selected
            if (selectedAppointment == null) {
                // Alert if no appointment is selected
                showAlert(Alert.AlertType.WARNING, "Delete Error", "No appointment selected. Please select an appointment to delete.");
                return;
            }

            // Confirm deletion with the user
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure you want to delete the appointment?");
            confirmationAlert.setContentText("Appointment ID: " + selectedAppointment.getAppointmentId() +
                    "\nTitle: " + selectedAppointment.getAppointmentTitle() +
                    "\nDescription: " + selectedAppointment.getAppointmentDescription() +
                    "\nType: " + selectedAppointment.getAppointmentDescription());

            // If user confirms deletion
            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                // Delete Appointment
                AppointmentsQuery.deleteAppointment(selectedAppointment.getAppointmentId());

                // Remove appointment from the table
                appointmentsTable.getItems().remove(selectedAppointment);

                // Success alert
                showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment deleted successfully.");
            }


        } else {
            // Get the selected customer from the table
            Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();

            // Check if a customer is selected
            if (selectedCustomer == null) {
                // Alert if no customer is selected
                showAlert(Alert.AlertType.WARNING, "Delete Error", "No customer selected. Please select a customer to delete.");
                return;
            }

            //Check for appointments tied to this customer
            //Lambda function 1of2
            ObservableList<Appointment> appointments = AppointmentsQuery.getAppointmentList();
            ObservableList<Appointment> customerAppointments = appointments.filtered(appointment ->
                    appointment.getAppointmentCustomerId() == selectedCustomer.getCustomerId());

            if (!customerAppointments.isEmpty()) {
                //Alert if there are appointments associated with the customer
                showAlert(Alert.AlertType.WARNING, "Delete Error", "Cannot delete customer with existing appointments. Please delete the appointments first.");
                return;
            }


            // Confirm deletion with the user
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure you want to delete the customer?");
            confirmationAlert.setContentText("Customer ID: " + selectedCustomer.getCustomerId() + "\nCustomer Name: " + selectedCustomer.getCustomerName());

            // If user confirms deletion
            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                // Delete Customer
                CustomerQuery.deleteCustomer(selectedCustomer.getCustomerId());
                // Remove customer from the table
                customerTable.getItems().remove(selectedCustomer);

                // Success alert
                showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully.");
            }
        }

    }



    /**
     * Listens for customers radio button selection.
     * Changes value for buttons and adjusts table visibility
     *
     * @param event for customer radio button**/
    public void setViewCustomers(ActionEvent event) {
        appointments = false;
        // Hide appointments table
        appointmentsTable.setVisible(false);
        appointmentsTable.setManaged(false);

        // Show customers table
        customerTable.setVisible(true);
        customerTable.setManaged(true);
        customerTable.toFront(); // Bring customerTable to front, ensure it's interactable
        addButton.setText("Add Customer");
        deleteButton.setText("Delete Customer");
        modifyButton.setText("Modify Customer");
        header.setText("Customers");

        //Apt views
        aptOrder.setVisible(false);
        aptAll.setVisible(false);
        aptAll.setManaged(false);
        aptMonth.setVisible(false);
        aptMonth.setManaged(false);
        aptWeek.setVisible(false);
        aptWeek.setManaged(false);
    }

    /**
     * Listens for appointments radio button selection.
     * Changes value for buttons and adjusts table visibility
     *
     * @param event for appointments radio button**/

    public void setViewAppointments(ActionEvent event) {
        appointments = true;
        // Show appointments table
        appointmentsTable.setVisible(true);
        appointmentsTable.setManaged(true);
        appointmentsTable.toFront(); // Bring appointmentsTable to front, ensure it's interactable

        // Hide customers table
        customerTable.setVisible(false);
        customerTable.setManaged(false);
        addButton.setText("Add Appointment");
        deleteButton.setText("Delete Appointment");
        modifyButton.setText("Modify Appointment");
        header.setText("Appointments");

        //Apt views
        aptOrder.setVisible(true);
        aptAll.setVisible(true);
        aptAll.setManaged(true);
        aptMonth.setVisible(true);
        aptMonth.setManaged(true);
        aptWeek.setVisible(true);
        aptWeek.setManaged(true);
    }

    /**
     * Checks for upcoming appointments within the next 15 minutes from the current time.
     * If an appointment is found, an information alert is displayed with the appointment details.
     * Otherwise, an alert indicates that there are no upcoming appointments.
     *
     * This method retrieves the current time and filters the appointment list to find any
     * appointments starting within the next 15 minutes. The filtering is done using a
     * lambda expression, which provides a concise way to define the criteria for finding
     * an appointment. The lambda checks if the time difference between the current time
     * and the appointment's start time is non-negative (meaning the appointment is
     * either now or in the future) and less than or equal to 15 minutes.
     *
     * @throws SQLException if there is an issue retrieving the appointment list from the database
     */
    private void checkForUpcomingAppointments() throws SQLException {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Retrieve the appointment list (assuming the list contains Appointment objects)
        ObservableList<Appointment> appointmentList = AppointmentsQuery.getAppointmentList();

        // Find the first appointment that is within 15 minutes of the current time
        Optional<Appointment> upcomingAppointment = appointmentList.stream()
                .filter(appointment -> {
                    LocalDateTime appointmentStart = appointment.getAppointmentStart();
                    Duration timeDifference = Duration.between(now, appointmentStart);
                    return timeDifference.toMinutes() >= 0 && timeDifference.toMinutes() <= 15;
                })
                .findFirst();

        // Check if an appointment was found
        if (upcomingAppointment.isPresent()) {
            // Display alert for the upcoming appointment
            Appointment appointment = upcomingAppointment.get();
            showAlert(Alert.AlertType.INFORMATION,
                    "Upcoming Appointment",
                    "You have an appointment within 15 minutes:\n" +
                            "Appointment ID: " + appointment.getAppointmentId() + "\n" +
                            "Date: " + appointment.getAppointmentStart().toLocalDate() + "\n" +
                            "Time: " + appointment.getAppointmentStart().toLocalTime());

            upcomingAptMessage.setText("Upcoming Appointment ID: " + appointment.getAppointmentId());
        } else {
            // Display a message indicating no upcoming appointments
            showAlert(Alert.AlertType.INFORMATION,
                    "No Upcoming Appointments",
                    "You have no appointments within the next 15 minutes.");

            upcomingAptMessage.setText("No upcoming appointments");
        }
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
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Initializes the dashboard by setting up user information, populating tables,
     * and managing logout functionality. If this is the user's first visit,
     * it checks for any upcoming appointments.
     *
     * This method configures the current user's dashboard label, sets the customer
     * and appointment tables with data from the database, and defines the action
     * for the logout button. The logout action utilizes a lambda expression to
     * handle the confirmation alert, which provides a clear and concise way to
     * manage user interactions without requiring a separate method. The lambda
     * is passed directly to the `ifPresent` method, which is invoked when the
     * alert's response is received, allowing for efficient and readable code
     * when processing user responses to the confirmation dialog.
     *
     * @throws SQLException if there is an issue retrieving customer or appointment lists from the database
     */

    public void initialize() throws SQLException {
        if (firstVisit) {
            checkForUpcomingAppointments();
            firstVisit = false;
        }

        //Label the current username's dashboard.
        String currentUser = UserSession.getLoggedInUser();
        userDash.setText(currentUser + "'s, Dashboard");

        //Populate customer table
        customerTable.setItems(CustomerQuery.getCustomerList());
        custID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custCountry.setCellValueFactory(new PropertyValueFactory<>("customerCountryName"));
        custPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custDivision.setCellValueFactory(new PropertyValueFactory<>("customerDivisionName"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        //Hide Customer table at start
        customerTable.setVisible(false);
        customerTable.setManaged(false);

        //Populate the appointment table
        appointmentsTable.setItems(AppointmentsQuery.getAppointmentList());

        apt_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appLocation.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appCustomerID.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appUserID.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));


        logout.setOnAction(event -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Logout");
            confirmationAlert.setHeaderText("Are you sure you want to logout?");
            confirmationAlert.setContentText("This will return you to the login screen");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response ==ButtonType.OK) {
                    try{
                        //Set First Visit back to true for next login
                        firstVisit = true;

                        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                        stage.centerOnScreen();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });



    }

    /**
     * Populates appointment table with all data
     *
     * @param event for radio button**/
    public void allAppointments(ActionEvent event) {
        appointmentsTable.setItems(AppointmentsQuery.getAppointmentList());
    }

    /**
     * Populates table with weekly appointments
     *
     * @param event for radio button
     * **/
    public void weeklyAppointments(ActionEvent event) {
        appointmentsTable.setItems(AppointmentsQuery.getWeeklyAppointments());
        appointmentsTable.setPlaceholder(new Label("Currently, no appointments exist within the next week."));
    }

    /**
     * Populates table with monthly appointments
     *
     * @param event for radio button
     * **/
    public void monthlyAppointments(ActionEvent event) {
        appointmentsTable.setItems(AppointmentsQuery.getMonthlyAppointments());
        appointmentsTable.setPlaceholder(new Label("Currently, no appointments exist within the next month."));
    }

    /**
     * Redirects user to reports page
     *
     * @param event handles button click.
     * @throws IOException handles exceptions
     * **/
    public void onReport(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Reports");
        stage.show();
        stage.centerOnScreen();

    }
}


