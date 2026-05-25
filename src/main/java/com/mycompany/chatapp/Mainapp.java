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
    if (loggedIn) {
        System.out.println("===================");
        System.out.println("Welcome to ChatApp.");
        System.out.println("===================");

            int choice = 0;

            while (choice != 3) {

                System.out.println("\n===== MENU =====");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");

                System.out.print("Choose an option: ");
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {

                    case 1:

                         System.out.print("How many messages would you like to send? ");
                         int numMessages = Integer.parseInt(input.nextLine());

                         for (int i = 1; i <= numMessages; i++) {

                         System.out.println("\nMessage " + i);
  
                         System.out.print("Enter recipient number: ");
                          String recipient = input.nextLine();

                         System.out.print("Enter your message: ");
                         String text = input.nextLine();

                        if (text.length() > 250) {

                         System.out.println(
                         "Message exceeds 250 characters by "
                          + (text.length() - 250)
                           );

                          continue;
                         }
 
                          Message msg =
                          new Message(i, recipient, text);

                             if (!msg.checkRecipientCell()) {

                              System.out.println(
                              "Cell number incorrectly formatted."
                             );

                             continue;
                             }


                              // MESSAGE OPTIONS
                              System.out.println(
                            "\nWhat would you like to do with this message?"
                               );

                             System.out.println("1) Send Message");
                             System.out.println("2) Disregard Message");
                             System.out.println("3) Store Message to send later");

                              int messageOption =
                              Integer.parseInt(input.nextLine());


                                // SEND
                                if (messageOption == 1) {

                               System.out.println(msg.sentMessage());

                               System.out.println(msg.printMessages());

                               }


                              // DISCARD
                               else if (messageOption == 2) {

                              System.out.println("Message disregarded.");

                              }


                               // STORE
                               else if (messageOption == 3) {

                               msg.storeMessage();

                               System.out.println(
                                  "Message successfully stored in messages.json"
                                );

                                }


                             // INVALID
                             else {

                             System.out.println(
                             "Invalid option selected."
                              );
        }
    }

    break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        System.out.println("Thank you for using Chatapp,Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
   
            } else {
            System.out.println("Too many failed attempts. Program exiting.");
            }
              input.close();
         
 }   
}
