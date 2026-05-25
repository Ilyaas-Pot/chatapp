/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;  

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Mainapp {
    /**
     * 
     * @param args 
     */
    public static void main(String[]args){
    
    //Scanner allows the user to enter information
    Scanner input = new Scanner(System.in);
    
    //Create and object of the login class so we can call its methods
    Login login = new Login();

    String response;
    
    System.out.println("==================");
    System.out.println("Welcome to ChatApp");
    System.out.println("==================");
    
    // Registration Setion looped
    do {

    System.out.println("\n==USER REGISTRATION==");

    // FIRST NAME
    System.out.print("Enter your first name: ");
    String firstName = input.nextLine();

    // SURNAME
    System.out.print("Enter your surname: ");
    String surname = input.nextLine();


    // USERNAME VALIDATION
    String username;

    do {

        System.out.print("Enter a username: ");
        username = input.nextLine();

        if (login.checkUserName(username)) {

            System.out.println("Username successfully captured.");

        } else {

            System.out.println(
                "Username is not correctly formatted; "
              + "please ensure it contains an underscore "
              + "and is no more than 5 characters."
            );
        }

    } while (!login.checkUserName(username));


    // PASSWORD VALIDATION
    String password;

    do {

        System.out.print("Enter a password: ");
        password = input.nextLine();

        if (login.checkPasswordComplexity(password)) {

            System.out.println("Password successfully captured.");

        } else {

            System.out.println(
                "Password is not correctly formatted; "
              + "please ensure it contains at least "
              + "8 characters, a capital letter, "
              + "a number, and a special character."
            );
        }

    } while (!login.checkPasswordComplexity(password));


    // PHONE NUMBER VALIDATION
    String phoneNumber;

    do {

        System.out.print(
            "Enter your South African phone number (+27...): "
        );

        phoneNumber = input.nextLine();

        if (login.checkCellPhoneNumber(phoneNumber)) {

            System.out.println(
                "Cell phone number successfully added."
            );

        } else {

            System.out.println(
                "Cell phone number incorrectly formatted "
              + "or missing international code."
            );
        }

    } while (!login.checkCellPhoneNumber(phoneNumber));


    // REGISTER USER
    response = login.registerUser(
        username,
        password,
        phoneNumber
    );

    System.out.println(response);

} while (!response.equals("User registered successfully!"));
    //register loop end
    
    //LOGIN SECTION looped with three attempts 
    int attempts = 0;
    boolean loggedIn = false;
    
    while (!loggedIn && attempts <3 ){
    
    System.out.println("==User Login==");
    
    System.out.print("Enter your username: ");
    String loginUsername = input.nextLine();
    
    System.out.print("Enter your password: ");
    String loginPassword = input.nextLine();
    
    //Call loginUser to check if details match the stored ones
     loggedIn = login.loginUser(loginUsername.trim(), loginPassword.trim());
    
    //Print out the correct logine message
    String loginMessage = login.returnLoginStatus(loggedIn);
    System.out.println(loginMessage);
    
    attempts++;
    
    if(!loggedIn && attempts <3){
        System.out.println("Attempts remaining: " + (3 - attempts) + "\n");
    }
   }
    
   
            } else {
            System.out.println("Too many failed attempts. Program exiting.");
            }
              input.close();
         
 }   
}
