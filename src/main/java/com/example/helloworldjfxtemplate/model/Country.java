package com.example.helloworldjfxtemplate.model;

/**
 *  Model class for Country
 *
 */

public class Country {
    private int countryId;
    private String countryName;
    private String countryMonth;
    private int countryMonthTotal;


    /**
     * Constructor to initialize a country with a month and total.
     *
     * @param countryMonth - the country's month.
     * @param countryMonthTotal - the total for the country in the given month.
     */
    public Country(String countryMonth, int countryMonthTotal) {
        this.countryMonth = countryMonth;
        this.countryMonthTotal = countryMonthTotal;
    }

    /**
     * Constructor to initialize a country with an ID and name.
     *
     * @param countryId - the country's ID.
     * @param countryName - the country's name.
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }


    /**
     * Gets the country month.
     *
     * @return countryMonth - the country's month.
     */
    public String getCountryMonth() {
        return countryMonth;
    }


    /**
     * Gets the country's total for the month.
     *
     * @return countryMonthTotal - the country's total for the month.
     */
    public int getCountryMonthTotal() {
        return countryMonthTotal;
    }


    /**
     * Gets the country ID.
     *
     * @return countryId - the country's ID.
     */
    public int getCountryId() {
        return countryId;
    }


    /**
     * Sets the country ID.
     *
     * @param countryId - the country's ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


    /**
     * Gets the country name.
     *
     * @return countryName - the country's name.
     */
    public String getCountryName() {
        return countryName;
    }


    /**
     * Sets the country name.
     *
     * @param countryName - the country's name to set.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    /**
     * Override to display countryName as String
     *
     * @return countryName
     */
    @Override
    public String toString() {
        return (countryName);
    }
}