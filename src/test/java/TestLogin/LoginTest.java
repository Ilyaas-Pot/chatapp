/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestLogin;

import com.mycompany.chatapp.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DTC
 */
public class LoginTest {
    Login login = new Login();
    @Test
    public void testUsernameValid() {
        assertTrue(login.checkUserName("Kyl_1"));
    }

    @Test
    public void testInvalidUsername_NoUndersore() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testValidPhone() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testInvalidPhone() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
    @Test
    public void testRegisterSuccess() {
        String result = login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully!", result);
    }
    
     @Test
    public void testLoginSuccess() {
        login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("Kyl_1", "Ch&&sec@ke99!"));
    }
     @Test
    public void testLoginFail() {
        login.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "wrong"));
    }
    
    
}
