package StepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
 * This is a test class for adding a record in an end-to-end scenario.
 */
public class AddRecordTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up the driver
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void endToEndAddRecordTest() {
        // 1. Navigate to the Web Tables page
        driver.get("https://demoqa.com/webtables");

        // 2. Verify the page title is "Web Tables"
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Web Tables");

        // 3. Click the "Add New Record" button
        WebElement btnClick = driver.findElement(By.cssSelector("button[id='addNewRecordButton']"));
        btnClick.click();

        // 4. Wait for the registration form to be visible and verify
        WebElement registrationFormLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='registration-form-modal']")));
        assertTrue(registrationFormLabel.isDisplayed());
        assertEquals(registrationFormLabel.getText(), "Registration Form");

        // 5. Fill out the registration form
        driver.findElement(By.cssSelector("input[id='firstName']")).sendKeys("Bariscan");
        driver.findElement(By.cssSelector("input[id='lastName']")).sendKeys("TEMEL");
        driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("bariscan.temel@gmail.com");
        driver.findElement(By.cssSelector("input[id='age']")).sendKeys("37");
        driver.findElement(By.cssSelector("input[id='salary']")).sendKeys("10000");
        driver.findElement(By.cssSelector("input[id='department']")).sendKeys("IT");

        // 6. Submit the form
        WebElement submitBtnClick = driver.findElement(By.cssSelector("button[id='submit']"));
        submitBtnClick.click();

        // 7. Verify the new record is added correctly
        WebElement row = driver.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div"));

        String firstName = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(1)")).getText();
        String lastName = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(2)")).getText();
        String age = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(3)")).getText();
        String email = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(4)")).getText();
        String salary = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(5)")).getText();
        String department = row.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(6)")).getText();

        String expectedFirstName = "Bariscan";
        String expectedLastName = "TEMEL";
        String expectedAge = "37";
        String expectedEmail = "bariscan.temel@gmail.com";
        String expectedSalary = "10000";
        String expectedDepartment = "IT";

        assertEquals(firstName, expectedFirstName, "Bariscan");
        assertEquals(lastName, expectedLastName, "TEMEL");
        assertEquals(age, expectedAge, "37");
        assertEquals(email, expectedEmail, "bariscan.temel@gmail.com");
        assertEquals(salary, expectedSalary, "10000");
        assertEquals(department, expectedDepartment, "IT");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
