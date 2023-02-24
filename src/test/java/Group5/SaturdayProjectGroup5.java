package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SaturdayProjectGroup5 extends UtilityClass {
    /*1- Go to "https://demo.openmrs.org/openmrs/login.htm".
2- Try to log in with username="Admin" and password="Admin123"
   without choosing a location and verify that the error
   message is "You must choose a location!".
3- Enter the same username and password.
4- Hover over the location names one by one and check if
   the background color changes.
5- Choose one of the locations randomly and click on
   "Log In" button.
6- Click on the location icon
7- Click on every location one by one and check if it is
   among the locations on the login page and current location changes.
8- Verify that admin button is displayed
9- Hover over "Admin" button and verify that "My Account"
   button is displayed.
10- Click on "My Account" button and verify that the title of
    the page is "My Account".
11- Click on "My Languages" button.
12- Verify that the title of the page is "My Languages"
13- Select a random language from the drop down menu.
14- Check and uncheck the boxes one by one. Verify
    that the box is checked and unchecked each time.
15- Check all of the boxes and verify that they are all checked
16- Click on The "Save" button and verify error message is
    displayed.

Note: Create a new project. Create your Utility class and add all the methods you need
	including hard wait in case you need.*/
    public static void main(String[] args) throws InterruptedException {
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
        System.out.println("-".repeat(50));
//        logIn.sendKeys("Admin" + Keys.TAB + "Admin123");
        List<WebElement> locationsList = driver.findElements(By.cssSelector("#sessionLocation>li"));
        List<String> locationsNamesOnLogin = new ArrayList<>();
        List<String> locationsColorBefore = new ArrayList<>();
        for (WebElement location : locationsList) {
            locationsColorBefore.add(location.getCssValue("background-color"));
            locationsNamesOnLogin.add(location.getText());
        }
        Actions actions = new Actions(driver);
        List<String> locationsColorAfter = new ArrayList<>();
        for (WebElement location : locationsList) {
            Action hoverOver = actions.moveToElement(location).build();
            hoverOver.perform();
            Thread.sleep(500);
            locationsColorAfter.add(location.getCssValue("background-color"));
        }
        for (int i = 0; i < locationsColorBefore.size(); i++) {
            if (locationsColorAfter.get(i).equals(locationsColorBefore.get(i))) {
                System.out.println("Step 4 for location " + (i + 1) + " is Failed");
            } else {
                System.out.println("Step 4 for location " + (i + 1) + " is Passed");
            }
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
        int count = 1;
        for (String location : locationsNamesOnLogin) {
            if (locationsNamesAfterLogin.contains(location)) {
                System.out.println("Step 7 for location " + count + " is Passed");
                count++;
            }
        }
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
        WebElement adminMenu = driver.findElement(By.cssSelector(".nav-item.identifier"));

        // Yusuf Akcil's code part

        if (adminMenu.isDisplayed()) {
            System.out.println("Step 8 is Passed");
        } else {
            System.out.println("Step 8 is Failed");
        }
        System.out.println("-".repeat(50));
        actions.moveToElement(adminMenu).perform();
        WebElement myAccount = driver.findElement(By.partialLinkText("My Account"));
        if (myAccount.isDisplayed()) {
            System.out.println("Step 9 is Passed");
        } else {
            System.out.println("Step 9 is Failed");
        }
        System.out.println("-".repeat(50));
        myAccount.click();
        if (driver.getTitle().equals("My Account")) {
            System.out.println("Step 10 is Passed");
        } else {
            System.out.println("Step 10 is Failed");
        }
        System.out.println("-".repeat(50));
        WebElement languages = driver.findElement(By.cssSelector(".task>.icon-cog"));
        languages.click();
        if (driver.getTitle().equals("My Languages")) {
            System.out.println("Step 12 is Passed");
        } else {
            System.out.println("Step 12 is Failed");
        }
        System.out.println("-".repeat(50));
        WebElement languagesBox = driver.findElement(By.id("default-locale-field"));
        Select select = new Select(languagesBox);
        select.selectByIndex((int) (Math.random() * 5) + 1);
        List<WebElement> languagesList = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("validation of step 14");
        for (WebElement language : languagesList) {
            language.click();
            System.out.println(language.getAttribute("value") + " language is selected: " + language.isSelected());
        }
        System.out.println("-".repeat(50));
        for (WebElement language : languagesList) {
            language.click();
            System.out.println(language.getAttribute("value") + " language is selected: " + language.isSelected());
        }
        System.out.println("-".repeat(50));
        System.out.println("validation of step 15");
        for (WebElement language : languagesList) {
            if (language.isSelected()) {
                System.out.println(language.getAttribute("value") + " language is selected: " + language.isSelected());

            } else {
                language.click();
                System.out.println(language.getAttribute("value") + " language is selected: " + language.isSelected());
            }
        }
        System.out.println("-".repeat(50));
        WebElement saveButton = driver.findElement(By.cssSelector("input[type='submit']"));
        saveButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()='User defaults could not be updated.']"));

        String actualResult = errorMessage.getText();

        String expectedResult = "User defaults could not be updated.";
        if (actualResult.equals(expectedResult)) {
            System.out.println("Step 16 is Passed");
        } else {
            System.out.println("Step 16 is Failed");
        }


        quitDriver(4);
    }
}
