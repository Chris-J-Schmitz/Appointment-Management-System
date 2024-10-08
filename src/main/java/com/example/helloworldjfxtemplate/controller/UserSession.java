package com.example.helloworldjfxtemplate.controller;

/**
 * User Session Class.
 * Sets the currently logged-in user.
 * **/

public class UserSession {
    private static String loggedInUser;


    private UserSession() {}


    public static void setLoggedInUser(String username) {
        loggedInUser = username;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void clearSession() {
        loggedInUser = null;
    }
}
