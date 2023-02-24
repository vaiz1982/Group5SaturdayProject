package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownAndLanguageSelection extends UtilityClass {

    public static void main(String[] args) {

//        As a user, I should be able to click on drop down menu functionality , so that I can select any option from list.
//
//        1- Once you are in My Language page
//
//        2- Select a random language from drop-down menu
//
//        3- Check and uncheck the boxes one by one
//
//        4- Click on “save” button

//        1- User should be able to get the selected option.
//
//        2- User should validate that each box is checked and unchecked
//
//        3- User should get an error message (User defaults could not be updated) displayed.

        WebElement languagesBox = driver.findElement(By.id("default-locale-field"));
        Select select = new Select(languagesBox);
        select.selectByIndex((int) (Math.random() * 5) + 1);
        List<WebElement> languagesList = driver.findElements(By.cssSelector("input[type='checkbox']"));
        System.out.println("validation of dropdown menu");

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
        System.out.println("Validation of language selection:");
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
            System.out.println("Passed: Error message is displayed.");
        } else {
            System.out.println("Failed: Unexpected / undisplayed error message.");


            quitDriver(4);


        }
    }
}
