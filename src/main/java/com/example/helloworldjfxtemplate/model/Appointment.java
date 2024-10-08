package com.example.helloworldjfxtemplate.model;

import com.example.helloworldjfxtemplate.DAO.AppointmentsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * The Appointment Class
 *
 * **/
public class Appointment {



    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private int appointmentContact;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private Timestamp start;
    private Timestamp end;
    private LocalDateTime appointmentEnd;
    private int appointmentCustomerId;
    private int appointmentUserId;
    private String appointmentLocation;
    private int appointmentTypeTotal;
    private String appointmentContactName;

    private static final int BUSINESS_START_HOUR = 8;
    private static final int BUSINESS_END_HOUR = 22;
    private static final int BUSINESS_MINUTE = 0;




    /**
     * Overloaded constructor for Appointments that get the appointment types and totals.
     *
     *
     * @param appointmentType appointment type
     * @param appointmentTypeTotal appointment total for each type
     */

    public Appointment(String appointmentType, int appointmentTypeTotal) {
        this.appointmentType = appointmentType;
        this.appointmentTypeTotal = appointmentTypeTotal;
    }

    /**
     * Overloaded constructor for Appointments to retrieve information from the database
     *
     * @param appointmentId          appointment id
     * @param appointmentTitle       appointment title
     * @param appointmentDescription appointment description
     * @param appointmentContact     associated appointment contact Id
     * @param appointmentContactName associated contact name
     * @param appointmentType        appointment type
     * @param appointmentStart       appointment start date/time
     * @param appointmentEnd         appointment end date/time
     * @param appointmentCustomerId  associated appointment customer id
     * @param appointmentUserId      associated appointment user id
     * @param appointmentLocation    appointment location
     */
    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, int appointmentContact, String appointmentContactName, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentLocation) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentContactName = appointmentContactName;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCustomerId = appointmentCustomerId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Constructs an Appointment object with the specified details.
     *
     * @param id The appointment ID.
     * @param title The appointment title.
     * @param description The appointment description.
     * @param contactId The contact ID.
     * @param type The appointment type.
     * @param start The appointment start time.
     * @param end The appointment end time.
     * @param customerId The customer ID.
     * @param userId The user ID.
     * @param location The appointment location.
     */
    public Appointment(int id, String title, String description, int contactId, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, String location) {
        this.appointmentId = id;
        this.appointmentTitle = title;
        this.appointmentDescription = description;
        this.appointmentContact = contactId;
        this.appointmentType = type;
        this.appointmentStart = start;
        this.appointmentEnd = end;
        this.appointmentCustomerId = customerId;
        this.appointmentUserId = userId;
        this.appointmentLocation = location;
    }

    /**
     * Constructs an Appointment object with the specified details.
     *
     * @param appointmentId The appointment ID.
     * @param title The appointment title.
     * @param description The appointment description.
     * @param type The appointment type.
     * @param start The appointment start time.
     * @param end The appointment end time.
     */

    public Appointment(int appointmentId, String title, String description, String type, Timestamp start, Timestamp end) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = title;
        this.appointmentDescription = description;
        this.appointmentType = type;
        this.start = start;
        this.end = end;
    }

    /**
     * Getter for appt id
     *
     * @return appointmentID
     * **/
    public int getAppointmentId() {
        return appointmentId;
    }


    /** setter for appointment id.
     *
     * @param appointmentId id of appointment
     * **/
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter for appt Title
     *
     * @return appointmentTitle
     * **/
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /** setter for appointment title.
     *
     * @param appointmentTitle tile of appiontment
     * **/
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Getter for appt Description
     *
     * @return appointmentDescription
     * **/
    public String getAppointmentDescription() {
        return appointmentDescription;
    }


    /**
     * Setter for appointment contact
     *
     * @param appointmentDescription appointment contact
     *
     * **/
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Getter for appt Contact
     *
     * @return appointmentContact
     * **/
    public int getAppointmentContact() {
        return appointmentContact;
    }


    /**
     * Setter for appointment contact
     *
     * @param appointmentContact appointment contact
     *
     * **/
    public void setAppointmentContact(int appointmentContact) {
        this.appointmentContact = appointmentContact;
    }

    /**
     * Getter for appt ContactName
     *
     * @return appointmentContactName
     * **/
    public String getAppointmentContactName() {
        return appointmentContactName;
    }

    /**
     * Setter for appointment contact name
     *
     * @param appointmentContactName appointment contact name
     *
     * **/
    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }


    /**
     * Getter for appt Type
     *
     * @return appointmentType
     * **/
    public String getAppointmentType() {
        return appointmentType;
    }


    /**
     * Setter for appointment type of appointment
     *
     * @param appointmentType type of appointment
     *
     * **/
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }


    /**
     * Getter for appt Start
     *
     * @return appointmentStart
     * **/
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }


    /**
     * Setter for appointment start date/time
     *
     * @param appointmentStart appointment start date/time
     *
     * **/
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * Getter for appt End
     *
     * @return appointmentEnd
     * **/
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }


    /**
     * Setter for appointment end date/time
     *
     * @param appointmentEnd appt end date/time
     *
     * **/
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }


    /**
     * Getter for appt CustomerId
     *
     * @return appointmentCustomerId
     * **/
    public int getAppointmentCustomerId() {
        return appointmentCustomerId;
    }


    /**
     * Setter for appointment customer id
     *
     * @param appointmentCustomerId appointment customer id
     *
     * **/
    public void setAppointmentCustomerId(int appointmentCustomerId) {
        this.appointmentCustomerId = appointmentCustomerId;
    }


    /**
     * Getter for appt UserId
     *
     * @return appointmentUserId
     * **/
    public int getAppointmentUserId() {
        return appointmentUserId;
    }


    /**
     * Setter for appointment user id
     *
     * @param appointmentUserId user id of appt.
     *
     * **/
    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }


    /**
     * Getter for appt Location
     *
     * @return appointmentLocation
     * **/
    public String getAppointmentLocation() {
        return appointmentLocation;
    }


    /**
     * Setter for appointment location
     *
     * @param appointmentLocation location of appointment
     *
     * **/
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }


    /**
     * Getter for appt TypeTotal
     *
     * @return appointmentTypeTotal
     * **/
    public int getAppointmentTypeTotal() {
        return appointmentTypeTotal;
    }

    /**
     * Setter for appointment type total
     *
     * @param appointmentTypeTotal appointment type total
     *
     * **/
    public void setAppointmentTypeTotal(int appointmentTypeTotal) {
        this.appointmentTypeTotal = appointmentTypeTotal;
    }


    /**
     * Boolean method to check if an appointment for the selected contact is overlying with any existing appointments for
     * selected contact
     *
     * @param appointmentId    appointment id
     * @param customerId       customer id
     * @param appointmentStart appointment start date/time
     * @param appointmentEnd   appointment end date/time
     * @return true/false
     */
    public static boolean overlapCheck(int appointmentId,int customerId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ObservableList<Appointment> appointmentList = AppointmentsQuery.getAppointmentList();

        for (Appointment a : appointmentList) {
            //Skip for the current appointment being modified
            if(a.getAppointmentId() == appointmentId) {
                continue;
            }

            // Check only appointments for the same customer
            if (customerId != a.getAppointmentCustomerId()) {
                continue;
            }

            LocalDateTime checkApptStart = a.getAppointmentStart();
            LocalDateTime checkApptEnd = a.getAppointmentEnd();

            // Check if appointments overlap
            if (appointmentStart.isEqual(checkApptStart) || appointmentEnd.isEqual(checkApptEnd)) {
                showAlert("ERROR: Appointments must not start or end at the same time as existing customer appointments");
                return true;
            }

            if (appointmentStart.isBefore(checkApptEnd) && appointmentEnd.isAfter(checkApptStart)) {
                showAlert("ERROR: Appointment times overlap with an existing appointment");
                return true;
            }


        }

        return false;
    }

    /**
     * Shows Alert on screen.
     *
     * @param message alert message.
     *
     * **/
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }



    /**
     * Boolean method that establishes business hours in eastern time and converts the users local timezone to ensure
     * appointment times are within business hours.
     *
     * @param appointmentStart appointment start date/time
     * @param appointmentEnd   appointment end date/time
     * @return true/false
     */
    public static boolean businessHoursCheck(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        // Convert appointment times to EST
        LocalDateTime appStartEST = convertToZone(appointmentStart, localZone, estZone);
        LocalDateTime appEndEST = convertToZone(appointmentEnd, localZone, estZone);

        // Define business hours (8 AM to 10 PM EST)
        LocalDateTime businessStartEST = appStartEST.withHour(BUSINESS_START_HOUR).withMinute(BUSINESS_MINUTE);
        LocalDateTime businessEndEST = appEndEST.withHour(BUSINESS_END_HOUR).withMinute(BUSINESS_MINUTE);

        if (appStartEST.isBefore(businessStartEST) || appEndEST.isAfter(businessEndEST)) {
            showBusinessHoursAlert();
            return true;
        }

        return false;
    }

    /**
     * Converts a LocalDateTime from one time zone to another.
     *
     * @param time The LocalDateTime to be converted.
     * @param fromZone The ZoneId of the original time zone.
     * @param toZone The ZoneId of the target time zone.
     * @return The LocalDateTime converted to the target time zone.
     */

    private static LocalDateTime convertToZone(LocalDateTime time, ZoneId fromZone, ZoneId toZone) {
        return time.atZone(fromZone).withZoneSameInstant(toZone).toLocalDateTime();
    }

    /**
     * Shows an alert for appointments set outside of local business hours.
     * **/
    private static void showBusinessHoursAlert() {
        LocalTime localStart = Appointment.localStart();
        LocalTime localEnd = Appointment.localEnd();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Outside of Business Hours");
        alert.setContentText(String.format("Appointment is outside of business hours: 8:00AM to 10:00PM EST\n" +
                        "Please schedule between %s - %s PM local time.",
                localStart.format(DateTimeFormatter.ofPattern("hh:mm a")),
                localEnd.format(DateTimeFormatter.ofPattern("hh:mm a"))));
        alert.getDialogPane().setMinHeight(250);
        alert.getDialogPane().setMinWidth(400);
        alert.showAndWait();
    }



    /**
     * Returns the local start time of business hours, converted from Eastern Time (New York).
     * Business hours begin at 8:00 AM Eastern Time, and this method adjusts that time to the system's default time zone.
     *
     * @return businessStartLocal
     */
    public static LocalTime localStart() {
        LocalTime openingBusinessTime = LocalTime.of(8, 0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEastern = LocalDateTime.of(LocalDate.now(), openingBusinessTime);
        LocalDateTime businessLocal = businessEastern.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessStartLocal = businessLocal.toLocalTime();

        return businessStartLocal;
    }


    /**
     * Returns the local End time of business hours, converted from Eastern Time (New York).
     * Business hours begin at 10:00 PM Eastern Time, and this method adjusts that time to the system's default time zone.
     *
     * @return businessEndLocal
     */
    public static LocalTime localEnd() {
        LocalTime closingBusinessTime = LocalTime.of(22, 0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEndDT = LocalDateTime.of(LocalDate.now(), closingBusinessTime);
        LocalDateTime businessLocalDT = businessEndDT.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessEndLocal = businessLocalDT.toLocalTime();

        return businessEndLocal;
    }


    /**
     * Generates a list of available appointment times in 30-minute increments, starting from 1:00 AM to 11:00 PM.
     *
     * @return appointmentTimeList
     */

    public static ObservableList<LocalTime> getTimes() {
        ObservableList<LocalTime> appointmentTimeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(1, 00);
        LocalTime end = LocalTime.MIDNIGHT.minusHours(1);

        while (start.isBefore(end.plusSeconds(2))) {

            appointmentTimeList.add(start);
            start = start.plusMinutes(30);

        }
        return appointmentTimeList;
    }


    /**
     * Getter for appt start
     *
     * @return start
     *
     * **/
    public Timestamp getStart() {
        return start;
    }

    /** Setter for appt start
     *
     * @param start  appt start
     *
     * **/
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * Getter for appt End
     *
     * @return End
     *
     * **/
    public Timestamp getEnd() {
        return end;
    }

    /** Setter for appt end
     *
     * @param end  appt end
     *
     * **/

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}

