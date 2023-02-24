package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class YusufOpenMrsProject extends UtilityClass {
    public static void main(String[] args) throws InterruptedException {

//  Step 1: Verify that admin button is displayed
//  Step 2: Hover over "Admin" button and verify that "My Account" button is displayed.

        driver.get("https://demo.openmrs.org/openmrs/login.htm");

        WebElement logIn = driver.findElement(By.id("username"));
        logIn.sendKeys("Admin" + Keys.TAB + "Admin123" + Keys.ENTER);

        List<WebElement> locationsList = driver.findElements(By.cssSelector("#sessionLocation>li"));
        List<String> locationsNamesOnLogin = new ArrayList<>();
        List<String> locationsColorBefore = new ArrayList<>();
        for (WebElement location : locationsList) {
            locationsColorBefore.add(location.getCssValue("background-color"));
            locationsNamesOnLogin.add(location.getText());
        }
        Actions actions = new Actions(driver);
        for (WebElement location : locationsList) {
            Action hoverOver = actions.moveToElement(location).build();
          hoverOver.perform();
          Thread.sleep(500);

        }
        locationsList.get((int) (Math.random() * 6)).click();
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();


            System.out.println("-".repeat(50));
            WebElement adminMenu = driver.findElement(By.cssSelector(".nav-item.identifier"));


            if (adminMenu.isDisplayed()) {
                System.out.println("Step 1 is Passed");
            } else {
                System.out.println("Step 1 is Failed");
            }
            System.out.println("-".repeat(50));
            actions.moveToElement(adminMenu).perform();
            WebElement myAccount = driver.findElement(By.partialLinkText("My Account"));
            if (myAccount.isDisplayed()) {
                System.out.println("Step 2 is Passed");
            } else {
                System.out.println("Step 2 is Failed");
            }

            quitDriver(3);

        }
    }
