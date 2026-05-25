/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestMessage;

import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DTC
 */
public class MessageTest {
    
   @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(1, "+27838968976", "Hello there");

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testRecipientCellSuccess() {

        Message msg = new Message(1, "+27838968976", "Hello there");

        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFail() {

        Message msg = new Message(1, "0838968976", "Hello there");

        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {

        Message msg = new Message(1, "+27838968976", "Hi Mike");

        assertNotNull(msg.createMessageHash());
    }

    @Test
    public void testTotalMessages() {

        new Message(1, "+27838968976", "Hello");
        new Message(2, "+27838968976", "How are you");

        assertEquals(2, Message.returnTotalMessages());
    }
    
}
