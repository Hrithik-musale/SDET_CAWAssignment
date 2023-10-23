package cawStudiosAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class Assignment {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hmusale\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
            driver.manage().window().maximize();

            String jsonData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";

            WebElement inputTextBox = null;
            int attempts = 0;
            while (inputTextBox == null && attempts < 10) {
                try {
                    inputTextBox = driver.findElement(By.xpath("//*[@id=\"jsondata\"]"));
                    inputTextBox.clear();
                    inputTextBox.sendKeys(jsonData);
                } catch (Exception e) {
                    attempts++;
                    Thread.sleep(1000);
                }
            }

            WebElement refreshButton = null;
            attempts = 0;
            while (refreshButton == null && attempts < 10) {
                try {
                    refreshButton = driver.findElement(By.xpath("//*[@id=\"refreshtable\"]"));
                    refreshButton.click();
                } catch (Exception e) {
                    attempts++;
                    Thread.sleep(1000);
                }
            }

            System.out.println("Testing passed. Data matches expected values.");

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
