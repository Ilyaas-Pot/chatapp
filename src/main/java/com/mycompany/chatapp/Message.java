/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author DTC
 */
public class Message {

     /**
     *
     */
     public static void resetTotalMessages() {
         totalMessages = 0;
     }
     
     public static void resetArrays() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipientList.clear();
     }
  
     private String messageID;
     private int messageNumber;
     private String recipient;
     private String messageText;
     private String messageHash;
     // Static variable to keep track of total messages created
     private static int totalMessages = 0;
    
     // Part 3 Arrays - populated as messages are sent, stored, or discarded
       private static java.util.ArrayList<String> sentMessages = new java.util.ArrayList<>();
       private static java.util.ArrayList<String> disregardedMessages = new java.util.ArrayList<>();
       private static java.util.ArrayList<String> storedMessages = new java.util.ArrayList<>();

       private static java.util.ArrayList<String> messageHashes = new java.util.ArrayList<>();
       private static java.util.ArrayList<String> messageIDs = new java.util.ArrayList<>();
       private static java.util.ArrayList<String> recipientList = new java.util.ArrayList<>();

     public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        // Generate message ID and hash automatically
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
        // Increase total message count
        totalMessages++;
     }
     
    /**
     * Creates a unique 10-digit message ID using random number generation.
     * The ID is zero-padded to always be exactly 10 digits long.
     * 
     * @return String containing 10-digit message ID
     */

     public String createMessageID() {
        //Generate random number
        long number = (long)(Math.random() * 1000000000L);
        return String.format("%010d", number);
     }
     /**
     * Validates that the message ID is not more than 10 characters long.
     * This ensures the ID meets the length requirement for storage.
     * 
     * @return true if message ID length is 10 or less, false otherwise
     */
     public boolean checkMessageID() {
        // Format number to always contain 10 digits
        return messageID.length() <= 10;
     }
     /**
     * Validates recipient cell phone number format.
     * Must start with international code +27 and be exactly 12 characters long.
     * Note: Invalid numbers are still stored per POE specification.
     * 
     * @return true if recipient number is valid format, false otherwise
     */
     public boolean checkRecipientCell() {
        return recipient.startsWith("+27") && recipient.length() == 12;
     }
     /**
     * Creates a unique message hash using string manipulation.
     * Format: first 2 digits of ID + message number + first word + last word (all caps)
     * Example: "12:3:HELLOWORLD"
     * 
     * @return String containing the formatted message hash
     */
     public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2)
            + ":" + messageNumber
            + ":" + firstWord.toUpperCase()
            + lastWord.toUpperCase();
        
     }
     /**
     * Processes the message based on user's choice and populates parallel arrays.
     * 
     * @param choice The user's selection: "Send", "Store", or "Discard"
     * @return Result message indicating what happened to the message, including
     *         Message ID and Hash for Send/Store operations
     */
     public String sentMessage(String choice) {
    switch (choice) {
        case "Send":
            // Store the STRING, not the Message object
            sentMessages.add(this.messageText);
            messageHashes.add(this.messageHash);
            messageIDs.add(this.messageID);
            recipientList.add(this.recipient);
            return "Message successfully sent.\nMessage ID: " + this.messageID + "\nMessage Hash: " + this.messageHash;

        case "Store":
            
            storeMessage();
            messageHashes.add(this.messageHash);
            messageIDs.add(this.messageID);
            recipientList.add(this.recipient);
            return "Message successfully stored.\nMessage ID: " + this.messageID + "\nMessage Hash: " + this.messageHash;

        case "Discard":
            disregardedMessages.add(this.messageText);
            return "Message discarded.";

        default:
            return "Invalid choice.";
    }
}
      /**
     * Displays individual message details for a SINGLE message instance.
     * 
     * @return Formatted string containing Message ID, Hash, Recipient, and Message text
     */
     public String printMessages() {
       return "\n-----------------------------------"
         + "\nMessage Details"
         + "\n-----------------------------------"
         + "\nMessage ID: " + messageID
         + "\nMessage Hash: " + messageHash
         + "\nRecipient: " + recipient
         + "\nMessage: " + messageText;
     }
     
      /**
     * Returns the total number of messages created (not just sent).
     * 
     * @return int count of all Message objects created
     */
     public static int returnTotalMessages() {
        return totalMessages;
        
     }
    /**
     * Stores the current message to the messages.json file.
     * Attribution: json-simple library - https://code.google.com/archive/p/json-simple/
     * Each message is written as one JSON object per line for easy reading.
     * 
     */
     public void storeMessage() {
    JSONObject messageDetails = new JSONObject();
    messageDetails.put("MessageID", messageID);
    messageDetails.put("MessageHash", messageHash);
    messageDetails.put("Recipient", recipient);
    messageDetails.put("Message", messageText);
    
    try (FileWriter file = new FileWriter("messages.json", true)) {
        file.write(messageDetails.toJSONString());
        file.write("\n");  // Add newline after each message
        file.flush();
    } catch (IOException e) {
        System.out.println("Error writing JSON file: " + e.getMessage());
    }
}
     public String getMessageID() {
      return messageID;
     }
     /**
     * Gets the recipient cell number of this message.
     * 
     * @return String containing the recipient's phone number
     */
     public String getRecipient() {
      return recipient;
     }
     /**
     * Gets the text content of this message.
     * 
     * @return String containing the message text
     */
     public String getMessageText() {
      return messageText;
     }
      /**
     * Gets the hash value of this message.
     * 
     * @return String containing the formatted message hash
     */
     public String getMessageHash() {
      return messageHash;
     }
     /**
     * Adds a message directly to the sentMessages array (used for testing).
     * 
     * @param msg The Message object to add to the sentMessages array
     */
      public static void addSentMessage(Message msg) {

       sentMessages.add(msg.getMessageText());
       messageHashes.add(msg.getMessageHash());
       messageIDs.add(msg.getMessageID());
       recipientList.add(msg.getRecipient());
     }
      /**
     * Adds a message directly to the disregardedMessages array (used for testing).
     * 
     * @param msg The Message object to add to the disregardedMessages array
     */
      public static void addDisregardedMessage(Message msg) {

       disregardedMessages.add(msg.getMessageText());
     }
      /**
     * Adds a message directly to the storedMessages array (used for testing).
     * 
     * @param msg The Message object to add to the storedMessages array
     */
      public static void addStoredMessage(Message msg) {
       storedMessages.add(msg.getMessageText());  
       messageHashes.add(msg.getMessageHash());
       messageIDs.add(msg.getMessageID());
       recipientList.add(msg.getRecipient());
}
       /**
     * Searches for a message by its ID using parallel array indexing.
     * 
     * @param id The message ID to search for
     * @return Formatted string with recipient and message, or "Message not found"
     */
       public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
         if (messageIDs.get(i).equals(id)) {
            String message = "";
            String recipient = "";
            
            // Find which array contains this message
            if (i < sentMessages.size()) {
                message = sentMessages.get(i);
            } else if (i < storedMessages.size()) {
                message = storedMessages.get(i);
            }
            
            if (i < recipientList.size()) {
                recipient = recipientList.get(i);
            }
            
            return "Recipient: " + recipient + "\nMessage: " + message;
        }
    }
    return "Message not found";
}
        /**
     * Searches for all messages sent to a specific recipient.
     * 
     * @param recipientNum The recipient phone number to search for
     * @return All messages found for that recipient, or "No messages found"
     */
         public static String searchByRecipient(String recipientNum) {
          StringBuilder result = new StringBuilder();
    
          for (int i = 0; i < recipientList.size(); i++) {
           if (recipientList.get(i) != null && recipientList.get(i).equals(recipientNum)) {
              if (i < sentMessages.size()) {
                result.append(sentMessages.get(i)).append("\n");
             } else if (i < storedMessages.size()) {
                result.append(storedMessages.get(i)).append("\n");
             }
         }
     }
    
    if (result.length() == 0) {
        return "No messages found";
    }
    return result.toString().trim();
}
      /**
     * Finds and returns the longest message in the storedMessages array.
     * 
     * @return The longest message string, or message indicating array is empty
     */
         public static String displayLongestMessage() {
          if (storedMessages.isEmpty()) {
           return "No stored messages available";
          }
    
          String longest = storedMessages.get(0);
          for (String msg : storedMessages) {
           if (msg.length() > longest.length()) {
            longest = msg;
          }
        }
          return longest;
       }
        /**
     * Deletes a message by its hash from all parallel arrays.
     * 
     * @param hash The message hash to delete
     * @return Success message with deleted text, or "Hash not found"
     */ 
        public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
         if (messageHashes.get(i).equals(hash)) {
            String deletedMessage = "";
            
            if (i < sentMessages.size()) {
                deletedMessage = sentMessages.get(i);
                sentMessages.remove(i);
            } else if (i < storedMessages.size()) {
                deletedMessage = storedMessages.get(i);
                storedMessages.remove(i);
            } else if (i < disregardedMessages.size()) {
                deletedMessage = disregardedMessages.get(i);
                disregardedMessages.remove(i);
            }
            
            messageHashes.remove(i);
            messageIDs.remove(i);
            if (i < recipientList.size()) {
                recipientList.remove(i);
            }
            
            return "Message: " + deletedMessage + " successfully deleted.";
        }
    }
    return "Hash not found.";
}
     /**
     * Displays all sent messages with their details.
     * 
     * @return Formatted string of all sent messages, or "No sent messages available"
     */
         public static String displaySentMessages() {
    if (sentMessages.isEmpty()) {
        return "No sent messages available.";
    }
    
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < sentMessages.size(); i++) {
        result.append("\n-----------------------------------");
        result.append("\nMessage ID: ").append(i < messageIDs.size() ? messageIDs.get(i) : "N/A");
        result.append("\nMessage Hash: ").append(i < messageHashes.size() ? messageHashes.get(i) : "N/A");
        result.append("\nRecipient: ").append(i < recipientList.size() ? recipientList.get(i) : "N/A");
        result.append("\nMessage: ").append(sentMessages.get(i));
    }
    return result.toString();
}
      /**
     * Loads stored messages from messages.json file into storedMessages array.
     * Attribution: json-simple library - https://code.google.com/archive/p/json-simple/
     */
         public static void loadStoredMessages() {
    storedMessages.clear();  // Clear before loading
    
    try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
        String line;
        JSONParser parser = new JSONParser();
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            
            try {
                JSONObject obj = (JSONObject) parser.parse(line);
                String msgText = (String) obj.get("Message");
                String msgRecip = (String) obj.get("Recipient");
                String msgHash = (String) obj.get("MessageHash");
                String msgID = (String) obj.get("MessageID");
                
                if (msgText != null) {
                    storedMessages.add(msgText);
                    if (msgHash != null) messageHashes.add(msgHash);
                    if (msgID != null) messageIDs.add(msgID);
                    if (msgRecip != null) recipientList.add(msgRecip);
                }
            } catch (Exception e) {
                // Skip malformed lines
            }
        }
        System.out.println("Loaded " + storedMessages.size() + " stored messages.");
    } catch (IOException e) {
        System.out.println("No stored messages file found yet.");
    }
}
      /**
     * Checks if there are any stored messages in the storedMessages array.
     * 
     * @return true if storedMessages is not empty, false otherwise
     */
         public static boolean hasStoredMessages() {
          return !storedMessages.isEmpty();
        }
     /**
     * Returns the total number of sent messages (not stored or discarded).
     * 
     * @return int count of messages flagged as "Send"
     */
         public static int returnTotalStoredMessages() {
         return storedMessages.size();
        }
        /**
     * Part 3: Displays a full report of all sent messages.
     * Includes Message Hash, Recipient, and Message for each entry.
     * 
     * @return Formatted report string, or "No messages to report" if empty
     */ 
        public static String generateReport() {
    if (sentMessages.isEmpty()) {
        return "No messages to report.";
    }
    
    StringBuilder report = new StringBuilder();
    for (int i = 0; i < sentMessages.size(); i++) {
        report.append("\n---------------------------");
        report.append("\nMessage Hash: ").append(i < messageHashes.size() ? messageHashes.get(i) : "N/A");
        report.append("\nRecipient: ").append(i < recipientList.size() ? recipientList.get(i) : "N/A");
        report.append("\nMessage: ").append(sentMessages.get(i));
    }
    report.append("\n---------------------------");
    return report.toString();
}
        // Getter methods for testing
        /**
     * Gets the sentMessages array for testing purposes.
     * 
     * @return ArrayList of sent message texts
     */
    public static java.util.ArrayList<String> getSentMessages() { return sentMessages; }
    
    /**
     * Gets the storedMessages array for testing purposes.
     * 
     * @return ArrayList of stored message texts
     */
    public static java.util.ArrayList<String> getStoredMessages() { return storedMessages; }
    
    /**
     * Gets the messageHashes array for testing purposes.
     * 
     * @return ArrayList of message hashes
     */
    public static java.util.ArrayList<String> getMessageHashes() { return messageHashes; }
    
    /**
     * Gets the messageIDs array for testing purposes.
     * 
     * @return ArrayList of message IDs
     */
    public static java.util.ArrayList<String> getMessageIDs() { return messageIDs; }
    
    /**
     * Gets the recipientList array for testing purposes.
     * 
     * @return ArrayList of recipients
     */
    public static java.util.ArrayList<String> getRecipientList() { return recipientList; }
}
