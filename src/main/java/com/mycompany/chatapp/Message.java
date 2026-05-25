/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author DTC
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int totalMessages = 0;

    public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageID = createMessageID();
        this.messageHash = createMessageHash();

        totalMessages++;
    }

    public String createMessageID() {
        long number = (long)(Math.random() * 1000000000L);
        return String.format("%010d", number);
    }
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    public boolean checkRecipientCell() {
        return recipient.startsWith("+27") && recipient.length() == 12;
    }

    public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2)
            + ":" + messageNumber
            + ":" + firstWord.toUpperCase()
            + lastWord.toUpperCase();
        
    }
    public String sentMessage() {

        return "Message successfully sent.";
    }

    public String printMessages() {
       return "\n-----------------------------------"
         + "\nMessage Details"
         + "\n-----------------------------------"
         + "\nMessage ID: " + messageID
         + "\nMessage Hash: " + messageHash
         + "\nRecipient: " + recipient
         + "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }
     public void storeMessage() {

        JSONArray messageList = new JSONArray();

        JSONObject messageDetails = new JSONObject();

        messageDetails.put("MessageID", messageID);
        messageDetails.put("MessageHash", messageHash);
        messageDetails.put("Recipient", recipient);
        messageDetails.put("Message", messageText);

        messageList.add(messageDetails);

        try {
            FileWriter file = new FileWriter("messages.json");
            file.write(messageList.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            System.out.println("Error writing JSON file.");
        }
     }   
}
