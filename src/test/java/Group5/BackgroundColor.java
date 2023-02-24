package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class BackgroundColor extends UtilityClass {
    public static void main(String[] args) throws InterruptedException {
        driver.get("https://demo.openmrs.org/openmrs/login.htm");

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
        driver.quit();
    }
}
