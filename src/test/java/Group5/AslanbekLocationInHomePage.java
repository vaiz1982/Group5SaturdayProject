package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AslanbekLocationInHomePage extends UtilityClass{
    public static void main(String[] args) {
        /**As a user I should be able to validate the number of locations are in same number both in Login Page and Home Page.

         1- Once you are in Home page

         2- Click on the Location Icon

         3- Click on every location one by one



         1- User should be able to see all 6 locations

         2- User should be able to validate that same locations are available in both login page and home page*/
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        WebElement logIn = driver.findElement(By.id("username"));
        logIn.sendKeys("Admin" + Keys.TAB + "Admin123");
        List<WebElement> locationsList = driver.findElements(By.cssSelector("#sessionLocation>li"));
        List<String> locationsNamesOnLogin = new ArrayList<>();
        for (WebElement location : locationsList) {
            locationsNamesOnLogin.add(location.getText());
        }
        locationsList.get((int) (Math.random() * 6)).click();
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();
        WebElement locationButton = driver.findElement(By.id("selected-location"));
        locationButton.click();
        List<WebElement> locationsAfterLogIn = driver.findElements(By.cssSelector(".select>li"));
        List<String> locationsNamesAfterLogin = new ArrayList<>();
        for (WebElement location : locationsAfterLogIn) {
            locationsNamesAfterLogin.add(location.getText());
        }
        System.out.println("-".repeat(50));
        for (String location : locationsNamesOnLogin) {
            if (locationsNamesAfterLogin.contains(location)) {
                System.out.println("The location " + location + " in home page is the same as in login page");

            }
        }
        System.out.println("-".repeat(50));
        String currentTemporaryLocation = "";
        String currentLocation = "";
        WebElement temporary;
        for (int i = 1; i <= locationsAfterLogIn.size(); i++) {
            driver.findElement(By.id("selected-location")).click();
            temporary = driver.findElement(By.xpath("(//ul[@class='select']/li)[" + i + "]"));
            currentTemporaryLocation = temporary.getText();
            temporary.click();
            driver.navigate().refresh();
            currentLocation = driver.findElement(By.tagName("h4")).getText();
            if (currentLocation.endsWith(currentTemporaryLocation + ".")) {
                System.out.println("After clicking on " + currentTemporaryLocation + " the current location changed to " + currentTemporaryLocation);
            } else {
                System.out.println("After clicking on " + currentTemporaryLocation + " the current location didn't change to " + currentTemporaryLocation);
            }
        }
        System.out.println("-".repeat(50));
        System.out.println("-".repeat(50));
        quitDriver(3);
    }
}
