package com.example.helloworldjfxtemplate.model;

import java.time.LocalDateTime;
/**
 * Model Class for User
 *
 * **/

public class User {

    private int userId;
    private String userName;
    private String password;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdatedDate;
    private String lastUpdatedBy;


    /**
     * Constructor to initialize a user with detailed information.
     *
     * @param userId - the ID of the user.
     * @param userName - the name of the user.
     * @param password - the password for the user.
     * @param createdDate - the date the user was created.
     * @param createdBy - the user who created this user.
     * @param lastUpdatedDate - the date the user was last updated.
     * @param lastUpdatedBy - the user who last updated this user.
     */
    public User (int userId, String userName, String password, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdatedDate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;

    }

    /**
     * Constructor to initialize a user with minimal information.
     *
     * @param userId - the ID of the user.
     * @param userName - the name of the user.
     */
    public User (int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * Gets the user ID.
     *
     * @return userId - the ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId - the ID to set for the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user name.
     *
     * @return userName - the name of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName - the name to set for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the created date of the user.
     *
     * @return createdDate - the date the user was created.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date of the user.
     *
     * @param createdDate - the date to set for when the user was created.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the name of the user who created this user.
     *
     * @return createdBy - the user who created this user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the name of the user who created this user.
     *
     * @param createdBy - the name to set for the creator of this user.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last updated date of the user.
     *
     * @return lastUpdatedDate - the date the user was last updated.
     */
    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the last updated date of the user.
     *
     * @param lastUpdatedDate - the date to set for when the user was last updated.
     */
    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * Gets the name of the user who last updated this user.
     *
     * @return lastUpdatedBy - the user who last updated this user.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the name of the user who last updated this user.
     *
     * @param lastUpdatedBy - the name to set for the last updater of this user.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Override to display User ID as String
     *
     * @return User Id
     */
    @Override
    public  String toString() {
        return Integer.toString(userId);
    }
}
