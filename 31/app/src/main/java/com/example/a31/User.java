package com.example.a31;

// User class representing a user of the application
public class User {

    // Member variables
    private String name;
    private static User instance;

    // Private constructor to prevent direct instantiation
    private User(String name) {
        this.name = name;
    }

    // Method to get the singleton instance of User class
    public static User getInstance() {
        // Check if instance is null
        if (instance == null) {
            instance = new User("UserName"); // Create new instance with default name if null
        }
        return instance; // Return the singleton instance
    }

    // Method to get the user's name
    public String getName() {
        return name;
    }

    // Method to set the user's name
    public void setName(String name) {
        this.name = name;
    }
}
