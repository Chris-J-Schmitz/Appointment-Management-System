package com.example.helloworldjfxtemplate.DAO;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL Queries for Contact info from the database
 *
 * **/
public class ContactQuery {
    /**
     * Retrieves a list of all contacts from the database.
     *
     * @return an ObservableList of all contacts
     */
    public static ObservableList<Contact> getContactList() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement contacts = JDBC.connection.prepareStatement(sql);
            ResultSet rs = contacts.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contact d = new Contact(contactId, contactName, contactEmail);
                contactList.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    /**
     * Retrieves a contact by its ID from the database.
     *
     * @param contactId the ID of the contact to retrieve
     * @return a Contact object representing the contact with the specified ID,
     *         or null if no contact is found
     * @throws RuntimeException if there is an error executing the SQL statement
     */
    public static Contact getContact(int contactId) {
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery(); // Use executeQuery() instead of execute()

            if (rs.next()) { // Changed while loop to if since you're only expecting one contact
                int searchedContactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                return new Contact(searchedContactId, contactName, contactEmail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null; // Return null if no contact is found
    }

    /**
     * Retrieves the ID of a contact by its name from the database.
     *
     * @param contactName the name of the contact whose ID is to be retrieved
     * @return the ID of the contact with the specified name
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static int getContactId(String contactName) throws SQLException {
        int contactId = 0;
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contactId = rs.getInt("Contact_ID");
        }
        return contactId;
    }

}
