package com.example.helloworldjfxtemplate.DAO;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL Queries for Country info from the database
 *
 * **/
public class CountryQuery {

    /**
     * Retrieves a list of all countries from the database.
     *
     * @return an ObservableList of all countries
     * @throws RuntimeException if there is an error executing the SQL statement
     */

    public static ObservableList<Country> getAll() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId, countryName);
                countryList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryList;
    }

    /**
     * Retrieves a country by its ID from the database.
     *
     * @param countryId the ID of the country to retrieve
     * @return a Country object representing the country with the specified ID
     * @throws RuntimeException if there is an error executing the SQL statement
     */
    public static Country returnCountry(int countryId) {
        try {
            String sql = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedCountryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country c = new Country(searchedCountryId, countryName);
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
