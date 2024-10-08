package com.example.helloworldjfxtemplate.DAO;

import com.example.helloworldjfxtemplate.helper.JDBC;
import com.example.helloworldjfxtemplate.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL Queries for User info from the database
 *
 * **/
public class UserQuery {


    /**
     * Validates the user's credentials by checking if the provided username and password
     * exist in the database.
     *
     * @param user the username entered by the user
     * @param password the password entered by the user
     * @return true if the username and password match an entry in the database, false otherwise
     * @throws RuntimeException if there is an SQL error during the operation
     */

    public static boolean userValidation (String user, String password) {
        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /**
     * Checks if the provided username exists in the database.
     *
     * @param user the username to check
     * @return true if the username exists in the database, false otherwise
     * @throws RuntimeException if there is an SQL error during the operation
     */
    public static boolean userNameLogin(String user) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1,user);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return an ObservableList of User objects representing all users in the database
     * @throws RuntimeException if there is an SQL error during the operation
     */
    public static ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = ("User_Name");

                User user = new User(userID,userName);
                userList.add((user));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * Retrieves a specific user from the database based on the user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a User object representing the user with the specified ID
     * @throws RuntimeException if there is an SQL error during the operation
     */
    public static User getUser(int userId) {
        try {
            String sql = "SELECT User_ID, User_Name FROM users WHERE User_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedUserId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            User u = new User(searchedUserId, userName);
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
