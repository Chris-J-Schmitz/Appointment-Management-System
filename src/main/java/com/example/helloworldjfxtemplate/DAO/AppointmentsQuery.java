package com.example.helloworldjfxtemplate.DAO;


import java.sql.*;
import java.time.LocalDateTime;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * SQL  Queries to obtain specific appointment info from the database
 *
 * **/


public class AppointmentsQuery {

    /**
     * Adds a new appointment to the database.
     *
     * @param appointmentTitle     the title of the appointment
     * @param appointmentDescription the description of the appointment
     * @param appointmentLocation   the location of the appointment
     * @param appointmentType      the type of the appointment
     * @param appointmentStart     the start date and time of the appointment
     * @param appointmentEnd       the end date and time of the appointment
     * @param createdDate          the date and time the appointment was created
     * @param createdBy            the user who created the appointment
     * @param lastUpdate           the last date and time the appointment was updated
     * @param lastUpdatedBy        the user who last updated the appointment
     * @param appointmentCustomerId the ID of the customer associated with the appointment
     * @param appointmentUserId    the ID of the user associated with the appointment
     * @param appointmentContact    the ID of the contact associated with the appointment
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static void addAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int appointmentCustomerId, int appointmentUserId, int appointmentContact) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertAppoint = JDBC.connection.prepareStatement(sql);

        insertAppoint.setString(1, appointmentTitle);
        insertAppoint.setString(2, appointmentDescription);
        insertAppoint.setString(3, appointmentLocation);
        insertAppoint.setString(4, appointmentType);
        insertAppoint.setTimestamp(5, Timestamp.valueOf(appointmentStart));
        insertAppoint.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
        insertAppoint.setTimestamp(7, Timestamp.valueOf(createdDate));
        insertAppoint.setString(8, createdBy);
        insertAppoint.setTimestamp(9, Timestamp.valueOf(lastUpdate));
        insertAppoint.setString(10, lastUpdatedBy);
        insertAppoint.setInt(11,appointmentCustomerId);
        insertAppoint.setInt(12, appointmentUserId);
        insertAppoint.setInt(13,appointmentContact);
        insertAppoint.executeUpdate();

        System.out.println("Appointment added successfully");

    }


    /**
     * Deletes an appointment from the database by its ID.
     *
     * @param appointmentId the ID of the appointment to delete
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.executeUpdate();
    }


    /**
     * Modifies an existing appointment in the database.
     *
     * @param appointmentId        the ID of the appointment to modify
     * @param appointmentTitle     the new title of the appointment
     * @param appointmentDescription the new description of the appointment
     * @param appointmentLocation   the new location of the appointment
     * @param appointmentType      the new type of the appointment
     * @param appointmentStart     the new start date and time of the appointment
     * @param appointmentEnd       the new end date and time of the appointment
     * @param appointmentCustomerId the new ID of the customer associated with the appointment
     * @param appointmentUserId    the new ID of the user associated with the appointment
     * @param appointmentContact    the new ID of the contact associated with the appointment
     * @throws RuntimeException if there is an error executing the SQL statement
     */
    public static void modifyAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, int appointmentContact) {
        try {

            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);


            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            ps.setInt(7, appointmentCustomerId);
            ps.setInt(8, appointmentUserId);
            ps.setInt(9, appointmentContact);
            ps.setInt(10, appointmentId);
            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Retrieves a list of all appointments from the database.
     *
     * @return an ObservableList of all appointments
     */
    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");


                Appointment c = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                appointmentList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    /**
     * Retrieves a list of appointments associated with a specific contact.
     *
     * @param contactId the ID of the contact to filter appointments by
     * @return an ObservableList of appointments associated with the specified contact
     */
    public static ObservableList<Appointment> getAppointmentByContact(int contactId) {
        ObservableList<Appointment> contactAppointment = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID  = '" + contactId + "'";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment results = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                contactAppointment.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactAppointment;
    }

    /**
     * Retrieves a list of appointments scheduled for the current week.
     *
     * @return an ObservableList of appointments scheduled for the current week
     */
    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE YEARWEEK(START) = YEARWEEK(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                weeklyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weeklyList;
    }

    /**
     * Retrieves a list of appointments scheduled for the current month.
     *
     * @return an ObservableList of appointments scheduled for the current month
     */
    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthlyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                monthlyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthlyList;
    }

    /**
     * Retrieves the count of appointments grouped by month.
     *
     * @return an ObservableList containing appointment types and their counts for each month
     */
    public static ObservableList<Appointment> getAppointmentTypeMonth() {
        ObservableList<Appointment> appointmentTypeMonthTotal = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT(MONTHNAME(Start)) AS Month, Count(*) AS Num FROM appointments GROUP BY Month";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String appointmentType = rs.getString("Month");
                int appointmentTypeTotal = rs.getInt("Num");
                Appointment results = new Appointment(appointmentType, appointmentTypeTotal);
                appointmentTypeMonthTotal.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentTypeMonthTotal;
    }

    /**
     * Retrieves the count of appointments grouped by type.
     *
     * @return an ObservableList containing appointment types and their counts
     */
    public static ObservableList<Appointment> getAppointmentType() {
        ObservableList<Appointment> appointmentListType = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type, Count(*) AS Num FROM appointments GROUP BY Type";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String appointmentType = rs.getString("Type");
                int appointmentTypeTotal = rs.getInt("Num");
                Appointment results = new Appointment(appointmentType, appointmentTypeTotal);
                appointmentListType.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentListType;
    }

}
