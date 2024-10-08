package com.example.helloworldjfxtemplate.DAO;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * SQL Queries for Customer info from the database.
 *
 * **/
public abstract class CustomerQuery {

    /**
     * Adds a new customer to the database.
     *
     * @param Name the name of the customer
     * @param Address the address of the customer
     * @param PostalCode the postal code of the customer
     * @param Phone the phone number of the customer
     * @param createdDate the date the customer was created
     * @param createdBy the user who created the customer record
     * @param lastUpdated the date the customer record was last updated
     * @param updatedBy the user who last updated the customer record
     * @param divisionId the ID of the division the customer belongs to
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static void addCustomer(String Name, String Address, String PostalCode, String Phone, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdated, String updatedBy, int divisionId) throws SQLException {

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, Name);
        ps.setString(2, Address);
        ps.setString(3, PostalCode);
        ps.setString(4, Phone);
        ps.setTimestamp(5, Timestamp.valueOf(createdDate));
        ps.setString(6, createdBy);
        ps.setTimestamp(7, Timestamp.valueOf(lastUpdated));
        ps.setString(8, updatedBy);
        ps.setInt(9, divisionId);
        ps.executeUpdate();

    }

    /**
     * Deletes a customer from the database based on the customer ID.
     *
     * @param customerID the ID of the customer to delete
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    /**
     * Modifies an existing customer's details in the database.
     *
     * @param customerId the ID of the customer to modify
     * @param customerName the new name for the customer
     * @param customerAddress the new address for the customer
     * @param customerPostalCode the new postal code for the customer
     * @param customerPhone the new phone number for the customer
     * @param lastUpdatedBy the user who last updated the customer record
     * @param lastUpdated the date the customer record was last updated
     * @param customerDivisionId the ID of the division the customer belongs to
     * @param countryId the ID of the customer's country
     */
    public static void modifyCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode,
                                      String customerPhone, String lastUpdatedBy, Timestamp lastUpdated, int customerDivisionId, int countryId) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Last_Update = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostalCode);
            ps.setString(4, customerPhone);
            ps.setString(5, lastUpdatedBy);
            ps.setTimestamp(6, lastUpdated);
            ps.setInt(7, customerDivisionId);
            ps.setInt(8, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return an ObservableList of all customers
     */
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Create_Date, customers.Last_Update, customers.Created_By, customers.Last_Updated_By, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String createdBy = rs.getString("Created_By");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerDivisionId = rs.getInt("Division_ID");
                String customerDivisionName = rs.getString("Division");
                int customerCountryId = rs.getInt("Country_ID");
                String customerCountryName = rs.getString("Country");
                Customer c = new Customer(customerName, customerAddress, customerPostalCode, customerPhone, createdBy, lastUpdatedBy, customerDivisionId, customerDivisionName, customerCountryId, customerCountryName, customerId);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    /**
     * Retrieves a customer from the database based on the customer ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return a Customer object representing the customer with the specified ID
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static Customer getCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.execute();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int searchedCustomerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");

            Customer c = new Customer(searchedCustomerId, customerName);
            return c;
        }
        return null;
    }

    /**
     * Retrieves a list of customers along with the total number of appointments
     * for each customer.
     *
     * @return an ObservableList of Customer objects containing customer names
     *         and their appointment counts
     */
    public static ObservableList<Customer> getCustomerAppTotal() {
        ObservableList<Customer> customerAppTotal = FXCollections.observableArrayList();
        try{
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, COUNT(appointments.Appointment_ID) AS appointmentCount " +
                    "FROM customers " +
                    "LEFT JOIN appointments ON customers.Customer_ID = appointments.Customer_ID " +
                    "GROUP BY customers.Customer_ID, customers.Customer_Name";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                int appointmentCount = rs.getInt("appointmentCount");

                Customer customer = new Customer(customerId,customerName,appointmentCount);
                customerAppTotal.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerAppTotal;
    }
}
