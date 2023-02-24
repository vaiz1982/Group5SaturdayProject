package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class LoginPageWithoutLocation extends UtilityClass {
    public static void main(String[] args) {
        /* Go to "https://demo.openmrs.org/openmrs/login.htm".
         1- Once you are in Login page

        2- Enter valid username into the “Username” field

        3- Enter valid password in to the “Password” field

        4- Click on “Login” button*/
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        WebElement logIn = driver.findElement(By.id("username"));
        logIn.sendKeys("Admin" + Keys.TAB + "Admin123" + Keys.ENTER);
        String actualErrorMessage = driver.findElement(By.id("sessionLocationError")).getText();
        String expectedErrorMessage = "You must choose a location!";
        if (expectedErrorMessage.equals(actualErrorMessage)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");

        }
        quitDriver(4);
    }
}


