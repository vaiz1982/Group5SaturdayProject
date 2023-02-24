package Group5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

    public class ValidateLocationIconFunctionality extends UtilityClass {
        public static void main(String[] args) {

            /**
             * As a User I should be able to login by entering correct username and correct password
             * and selecting a location field. Then When I click on login button I should be navigated to HomePage
             ****
             * 1- Once you are in Login page
             * 2- Enter valid username into the “Username” field
             * 3- Enter valid password in to the “Password” field
             * 4- Select one of the locations
             * 5- Click on “Login” button
             */

            driver.get("https://demo.openmrs.org/openmrs/login.htm");
            WebElement logIn = driver.findElement(By.id("username"));
            logIn.sendKeys("Admin" + Keys.TAB + "Admin123" + Keys.ENTER);
            WebElement location2 = driver.findElement(By.xpath("//*[@id=\"Isolation Ward\"]"));
            location2.click();
            logIn.sendKeys(Keys.ENTER);
            if (driver.getTitle().equals("Home")){
                System.out.println("Validation is PASSED");
            }else {
                System.out.println("Validation is FAILED");
            }


        }
    }
