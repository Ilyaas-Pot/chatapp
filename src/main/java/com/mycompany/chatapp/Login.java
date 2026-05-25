/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */

public class Login {
    
    // Saves the users data when registering
    // the users data is stored in these variable
    
    String username;
    String password;
    String phoneNumber;
    
    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    // Password validation
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++){
         char c = password.charAt(i);
         
         if (Character.isUpperCase(c)){
             hasCapital = true;
             
         } else if (Character.isDigit(c)) {
             hasNumber = true;
             
         } else if (!Character.isLetterOrDigit(c)) {
             hasSpecial = true;
         }
        }
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    
    // Phone number validation
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+27") && phoneNumber.length() == 12;
    }
    
    // Register user
    public String registerUser(String username, String password, String phoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure it contains an underscore and is no more than 5 characters.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; must be 8+ chars, capital letter, number, and special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or missing +27.";
        }
        
         // Store user details
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;

        return "User registered successfully!";
    }
    
    // User Login 
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null){
            return false;
        }
        return this.username.trim().equals(username.trim()) &&
               this.password.trim().equals(password.trim());
    }
    /**
     * 
     * @param success
     * @return 
     */
    public String returnLoginStatus(boolean success){
        if (success){
            return "Welcome " + username + ", it is great to see you again.";
        }else{
            return "Username or password incorrect, please try again.";
        }
    }
    
    
}
