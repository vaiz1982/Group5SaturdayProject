package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class LoginPageWithoutLocation extends UtilityClass {
    public static void main(String[] args) {
            /*1- Go to "https://demo.openmrs.org/openmrs/login.htm".
              2- Try to log in with username="Admin" and password="Admin123"
           without choosing a location and verify that the error
           message is "You must choose a location!".*/

        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        WebElement logIn = driver.findElement(By.id("username"));
        logIn.sendKeys("Admin" + Keys.TAB + "Admin123" + Keys.ENTER);
        String actualErrorMessage = driver.findElement(By.id("sessionLocationError")).getText();
        String expectedErrorMessage = "You must choose a location!";
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println("Step 2 is Passed");
        } else {
            System.out.println("Step 2 is Failed");

        }
        quitDriver(4);
    }
}


