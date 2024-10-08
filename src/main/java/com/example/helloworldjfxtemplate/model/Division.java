package com.example.helloworldjfxtemplate.model;

import java.time.LocalDateTime;

/**
 * Model Class for Divisions
 * */

public class Division {

    private int divisionID;
    private String divisionName;
    private int countryId;


    /**
     * Constructor to initialize a division with an ID and name.
     *
     * @param divisionID - the ID of the division.
     * @param divisionName - the name of the division.
     */
    public Division(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }


    /**
     * Constructor to initialize a division with additional details.
     *
     * @param divisionID - the ID of the division.
     * @param divisionName - the name of the division.
     * @param countryId - the country ID associated with the division.
     * @param createDate - the date when the division was created.
     * @param createdBy - the user who created the division.
     * @param lastUpdated - the date when the division was last updated.
     * @param lastUpdatedBy - the user who last updated the division.
     */
    public Division(int divisionID, String divisionName, int countryId, LocalDateTime createDate,
                            String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;


    }


    /**
     * Gets the division ID.
     *
     * @return divisionID - the ID of the division.
     */
    public int getDivisionId() {
        return divisionID;
    }

    /**
     * Sets the division ID.
     *
     * @param divisionId - the ID to set for the division.
     */
    public void setDivisionId(int divisionId) {
        this.divisionID = divisionID;
    }

    /**
     * Gets the name of the division.
     *
     * @return divisionName - the name of the division.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets the name of the division.
     *
     * @param divisionName - the name to set for the division.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Gets the country ID associated with the division.
     *
     * @return countryId - the country ID of the division.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the country ID associated with the division.
     *
     * @param countryId - the country ID to set for the division.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


    /**
     * Override to display Division Name as String
     *
     * @return Division Name
     */
    @Override
    public String toString() {
        return (divisionName);
    }
}