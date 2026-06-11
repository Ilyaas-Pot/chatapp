/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestMessage;

import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author DTC
 */
public class MessageTest {
    
    @BeforeEach
    public void setUp() {
    Message.resetTotalMessages();
    Message.resetArrays();
    }
    
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
        Message.resetTotalMessages();
        new Message(1, "+27838968976", "Hello");
        new Message(2, "+27838968976", "How are you");

        assertEquals(2, Message.returnTotalMessages());
    }
  // new tests
    @Test
public void testSentMessagesArray_correctlyPopulated() {
    // Reset arrays first
    Message.resetArrays();
    Message.resetTotalMessages();
    
    // Message 1 from POE test data - Flag: Sent
    Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
    msg1.sentMessage("Send");
    
    // Message 4 from POE test data - Flag: Sent (Developer number)
    Message msg4 = new Message(4, "0838884567", "It is dinner time!");
    msg4.sentMessage("Send");
    
    // Verify
    assertEquals(2, Message.getSentMessages().size());
    assertTrue(Message.getSentMessages().contains("Did you get the cake?"));
    assertTrue(Message.getSentMessages().contains("It is dinner time!"));
}

/**
 * Test 2: Display the longest message
 * After populating stored messages with the POE data, 
 * displayLongestMessage() must return the longest message
 */
@Test
public void testDisplayLongestMessage_returnsCorrectMessage() {
    Message.resetArrays();
    Message.resetTotalMessages();
    
    Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    Message.addStoredMessage(msg2);
    
    Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
    Message.addStoredMessage(msg5);
    
    String longest = Message.displayLongestMessage();
    assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
}

/**
 * Test 3: Search by message ID
 * Searching for message 4's ID (0838884567 as the developer number) 
 * must return 'It is dinner time!'
 */
@Test
public void testSearchByMessageID_returnsCorrectMessage() {
    // Reset
    Message.resetArrays();
    Message.resetTotalMessages();
    
    // Message 4
    Message msg4 = new Message(4, "0838884567", "It is dinner time!");
    msg4.sentMessage("Send");
    
    // Get the message ID (should be auto-generated as 10 digits)
    String msgId = Message.getMessageIDs().get(0);
    String result = Message.searchByMessageID(msgId);
    
    // Verify
    assertTrue(result.contains("It is dinner time!"));
}

/**
 * Test 4: Search by recipient
 * Searching for +27838884567 must return both message 2 and message 5
 */
@Test
public void testSearchByRecipient_returnsAllMatchingMessages() {
    // Reset
    Message.resetArrays();
    Message.resetTotalMessages();
    
    // Message 2
    Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    msg2.sentMessage("Send");
    
    // Message 5
    Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
    msg5.sentMessage("Send");
    
    // Search
    String result = Message.searchByRecipient("+27838884567");
    
    // Verify BOTH messages are returned
    assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
    assertTrue(result.contains("Ok, I am leaving without you."));
}

/**
 * Test 5: Delete by message hash
 * Deleting message 2 by its hash must return success message
 */
@Test
public void testDeleteByHash_removesCorrectMessage() {
    // Reset
    Message.resetArrays();
    Message.resetTotalMessages();
    
    // Message 2
    Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    msg2.sentMessage("Send");
    
    // Get hash and delete
    String hash = Message.getMessageHashes().get(0);
    String result = Message.deleteMessageByHash(hash);
    
    // Verify exact format
    assertEquals("Message: Where are you? You are late! I have asked you to be on time. successfully deleted.", result);
}

/**
 * Test 6: Display report
 * The report must contain the hash, recipient, and message for all sent messages
 */
@Test
public void testDisplayReport_containsRequiredFields() {
    // Reset
    Message.resetArrays();
    Message.resetTotalMessages();
    
    // Add sent messages
    Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
    msg1.sentMessage("Send");
    
    Message msg4 = new Message(4, "0838884567", "It is dinner time!");
    msg4.sentMessage("Send");
    
    // Generate report using printMessages()
    String report = Message.generateReport();
    
    // Verify all required fields are present
    assertTrue(report.contains("Message Hash:"));
    assertTrue(report.contains("Recipient:"));
    assertTrue(report.contains("Message:"));
    assertTrue(report.contains("Did you get the cake?"));
    assertTrue(report.contains("It is dinner time!"));
}

}
