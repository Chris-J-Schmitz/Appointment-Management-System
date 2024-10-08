package com.example.helloworldjfxtemplate.DAO;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** SQL Queries for Division info from the database**/
public class DivisionQuery {



    /**
     * Retrieves a division from the database based on the division ID.
     *
     * @param divisionId the ID of the division to retrieve
     * @return a Division object representing the division with the specified ID
     * @throws RuntimeException if there is an SQL error during the operation
     */
    public static Division returnDivisionLevel(int divisionId) {
        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, divisionId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedDivisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            Division s = new Division(searchedDivisionId, divisionName);
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of divisions associated with a specific country from the database.
     *
     * @param countryId the ID of the country for which to retrieve divisions
     * @return an ObservableList of Division objects representing the divisions in the specified country
     * @throws SQLException if there is an error executing the SQL statement
     */
    public static ObservableList<Division> displayDivision(int countryId) throws SQLException {
        ObservableList<Division> divisionCountryOptions = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();

        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            countryId = rs.getInt("Country_ID");
            Timestamp Create_Date = rs.getTimestamp("Create_Date");
            LocalDateTime createDate = Create_Date.toLocalDateTime();
            String createBy = rs.getString("Created_By");
            Timestamp Last_Updated = rs.getTimestamp("Last_Update");
            LocalDateTime lastUpdated = Last_Updated.toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            Division c = new Division(divisionId, divisionName, countryId, createDate, createBy, lastUpdated, lastUpdatedBy);
            divisionCountryOptions.add(c);
        }
        return divisionCountryOptions;
    }
}