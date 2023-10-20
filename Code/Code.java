package cawStudiosAssignment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Code {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hmusale\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        driver.manage().window().maximize();

        try {
            WebElement tableDataButton = driver.findElement(By.xpath("//*[contains(text(),\"Table Data\")]"));
            tableDataButton.click();

            String jsonData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";

            WebElement inputTextBox = driver.findElement(By.xpath("//*[@id=\"jsondata\"]"));
            inputTextBox.clear();
            inputTextBox.sendKeys(jsonData);

            WebElement refreshButton = driver.findElement(By.xpath("//*[@id=\"refreshtable\"]"));
            refreshButton.click();

            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='testTable']/tbody/tr"));
            for (int i = 0; i < rows.size(); i++) {
                WebElement row = rows.get(i);
                List<WebElement> cells = row.findElements(By.tagName("td"));

                String name = cells.get(0).getText();
                int age = Integer.parseInt(cells.get(1).getText());
                String gender = cells.get(2).getText();

                String[] names = {"Bob", "George", "Sara", "Conor", "Jennifer"};
                int[] ages = {20, 42, 42, 40, 42};
                String[] genders = {"male", "male", "female", "male", "female"};

                assert name.equals(names[i]) : "Name mismatch for row " + (i + 1);
                assert age == ages[i] : "Age mismatch for row " + (i + 1);
                assert gender.equals(genders[i]) : "Gender mismatch for row " + (i + 1);
            }

            System.out.println("testing passed as Both data should is matching");

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}