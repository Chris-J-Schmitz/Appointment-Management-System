package com.example.helloworldjfxtemplate.model;


import java.time.LocalDateTime;

/**
 * Model class for Customer
 *
 * **/
public class Customer {

    private int customerId,customerDivisionId,customerCountryId, appointmentCount;
    private String customerName, customerAddress,customerPostalCode,customerDivisionName,customerCountryName,customerPhone,createdBy,lastUpdatedBy;
    private LocalDateTime lastUpdated, created_date;


    /**
     * Constructor to initialize a customer with detailed information.
     *
     * @param customerName - the customer's name.
     * @param customerAddress - the customer's address.
     * @param customerPostalCode - the customer's postal code.
     * @param customerPhone - the customer's phone number.
     * @param createdBy - the user who created the customer.
     * @param lastUpdatedBy - the user who last updated the customer.
     * @param customerDivisionId - the division ID of the customer.
     * @param customerDivisionName - the division name of the customer.
     * @param customerCountryId - the country ID of the customer.
     * @param customerCountryName - the country name of the customer.
     * @param customerId - the ID of the customer.
     */
    public Customer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, String createdBy, String lastUpdatedBy, int customerDivisionId, String customerDivisionName, int customerCountryId, String customerCountryName, int customerId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivisionName = customerDivisionName;
        this.customerCountryId = customerCountryId;
        this.customerCountryName = customerCountryName;
        this.customerPhone = customerPhone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor to initialize a customer with timestamps and detailed information.
     *
     * @param customerName - the customer's name.
     * @param customerAddress - the customer's address.
     * @param customerPostalCode - the customer's postal code.
     * @param customerPhone - the customer's phone number.
     * @param created_date - the date when the customer was created.
     * @param createdBy - the user who created the customer.
     * @param lastUpdated - the date when the customer was last updated.
     * @param lastUpdatedBy - the user who last updated the customer.
     * @param customerDivisionId - the division ID of the customer.
     * @param customerDivisionName - the division name of the customer.
     * @param customerCountryId - the country ID of the customer.
     * @param customerCountryName - the country name of the customer.
     * @param customerId - the ID of the customer.
     */
    public Customer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, LocalDateTime created_date, String createdBy,LocalDateTime lastUpdated, String lastUpdatedBy, int customerDivisionId, String customerDivisionName, int customerCountryId, String customerCountryName, int customerId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivisionName = customerDivisionName;
        this.customerCountryId = customerCountryId;
        this.customerCountryName = customerCountryName;
        this.customerPhone = customerPhone;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdated = lastUpdated;
        this.created_date = created_date;
    }

    /**
     * Constructor to initialize a customer with an ID and name.
     *
     * @param customerId - the customer's ID.
     * @param customerName - the customer's name.
     */
    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    /**
     * Constructor to initialize a customer with an ID, name, and appointment count.
     *
     * @param customerId - the customer's ID.
     * @param customerName - the customer's name.
     * @param appointmentCount - the number of appointments for the customer.
     */
    public Customer(int customerId, String customerName, int appointmentCount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.appointmentCount = appointmentCount;
    }


    /**
     * Gets the customer ID.
     *
     * @return customerId - the customer's ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId - the ID to set for the customer.
     */

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Gets the division ID of the customer.
     *
     * @return customerDivisionId - the division ID of the customer.
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * Sets the division ID of the customer.
     *
     * @param customerDivisionId - the division ID to set for the customer.
     */
    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Gets the country ID of the customer.
     *
     * @return customerCountryId - the country ID of the customer.
     */
    public int getCustomerCountryId() {
        return customerCountryId;
    }

    /**
     * Sets the country ID of the customer.
     *
     * @param customerCountryId - the country ID to set for the customer.
     */
    public void setCustomerCountryId(int customerCountryId) {
        this.customerCountryId = customerCountryId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return customerName - the name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customerName - the name to set for the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the address of the customer.
     *
     * @return customerAddress - the address of the customer.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the address of the customer.
     *
     * @param customerAddress - the address to set for the customer.
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Gets the postal code of the customer.
     *
     * @return customerPostalCode - the postal code of the customer.
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Sets the postal code of the customer.
     *
     * @param customerPostalCode - the postal code to set for the customer.
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Gets the division name of the customer.
     *
     * @return customerDivisionName - the division name of the customer.
     */
    public String getCustomerDivisionName() {
        return customerDivisionName;
    }

    /**
     * Sets the division name of the customer.
     *
     * @param customerDivisionName - the division name to set for the customer.
     */
    public void setCustomerDivisionName(String customerDivisionName) {
        this.customerDivisionName = customerDivisionName;
    }

    /**
     * Gets the country name of the customer.
     *
     * @return customerCountryName - the country name of the customer.
     */
    public String getCustomerCountryName() {
        return customerCountryName;
    }

    /**
     * Sets the country name of the customer.
     *
     * @param customerCountryName - the country name to set for the customer.
     */
    public void setCustomerCountryName(String customerCountryName) {
        this.customerCountryName = customerCountryName;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return customerPhone - the phone number of the customer.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param customerPhone - the phone number to set for the customer.
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Gets the user who created the customer.
     *
     * @return createdBy - the user who created the customer.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the customer.
     *
     * @param createdBy - the user who created the customer.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the user who last updated the customer.
     *
     * @return lastUpdatedBy - the user who last updated the customer.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the customer.
     *
     * @param lastUpdatedBy - the user who last updated the customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Override to display Customer Name as String
     *
     * @return Customer Name
     */
    @Override
    public String toString() {
        return  customerId + " - " + customerName;
    }

    /**
     * Gets the date when the customer was last updated.
     *
     * @return lastUpdated - the date when the customer was last updated.
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the date when the customer was last updated.
     *
     * @param lastUpdated - the date to set for the last update of the customer.
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the number of appointments for the customer.
     *
     * @return appointmentCount - the number of appointments for the customer.
     */
    public int getAppointmentCount() {
        return appointmentCount;
    }

    /**
     * Sets the number of appointments for the customer.
     *
     * @param appointmentCount - the number of appointments to set for the customer.
     */
    public void setAppointmentCount(int appointmentCount) {
        this.appointmentCount = appointmentCount;
    }

    /**
     * Gets the date when the customer was created.
     *
     * @return created_date - the creation date of the customer.
     */
    public LocalDateTime getCreated_date() {
        return created_date;
    }

    /**
     * Sets the date when the customer was created.
     *
     * @param created_date - the creation date to set for the customer.
     */
    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }
}
