package testsuite;

import browserfactory.BaseTest;
import graphql.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl ="http://the-internet.herokuapp.com/login";
    @Before
    public void setup(){
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldLoginSuccessfullyWithValidCredentials(){
        //Enter valid username
        WebElement username = driver.findElement(By.name("username"));
        //sending field valid username
        username.sendKeys("tomsmith");

        // Find the valid password field element
        WebElement passwordField = driver.findElement(By.name("password"));
        //     Sending valid Password to password field element
        passwordField.sendKeys("SuperSecretPassword!");


        //Find the login button and click on it
        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();

        //This is from requirement
        String expectedMessage = "Secure Area";
        //find the secure Area text element and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//body[1]/div[2]"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //validate actual and expected message
    }

    @Test
    public void verifyTheUsernameErrorMessage(){
        //Enter invalid username
        WebElement username = driver.findElement(By.id("username"));
        //sending field valid username
        username.sendKeys("tomsmith1");

        // Find the valid password field element
        WebElement passwordField = driver.findElement(By.name("password"));
        //     Sending valid Password to password field element
        passwordField.sendKeys("SuperSecretPassword!");

        //Find the login button and click on it
        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();
        //This is from requirement
        String expectedMessage = " Your username is invalid!";
        // verify the error message your username is invalid
        WebElement actualMessageElement = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //validate actual and expected message
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //Enter valid username
        WebElement username = driver.findElement(By.id("username"));
        //sending field valid username
        username.sendKeys("tomsmith");

        // Find the invalid password field element
        WebElement passwordField = driver.findElement(By.name("password"));
        //     Sending invalid Password to password field element
        passwordField.sendKeys("SuperSecretPassword");

        //Find the login button and click on it
        WebElement loginButton = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/button[1]/i[1]"));
        loginButton.click();
        //This is from requirement
        String expectedMessage = "Your password is invalid!";
        // verify the error message your username is invalid
        WebElement actualMessageElement = driver.findElement(By.xpath("//body"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //validate actual and expected message
    
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found";
        String actualErrorMessage = driver.findElement(By.className("button button-primary g-recaptcha")).getText();

        // Validate actual and expected message
        org.junit.Assert.assertEquals("Error message not displayed", expectedMessage, actualMessage);

    }



    @After
    public void tearDown(){
        closeBrowser();
    }
}


