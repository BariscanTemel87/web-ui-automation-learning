package StepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
 * This is a test class for adding a record.
 */
public class AddRecordTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up the driver
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        // options.addArguments("--incognito");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

    }

    @Test(priority = 1)
    public void pageIsDisplayedTest() {


        driver.get("https://demoqa.com/webtables");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement pageTitle = driver.findElement(By.tagName("h1"));

        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Web Tables");

    }

    @Test(priority = 2)
    public void addRecordButtonClick() {

        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SZ2tf")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("SZ2tf")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement btnClick = driver.findElement(By.cssSelector("#addNewRecordButton"));
        btnClick.click();

        WebElement registrationFormLabel = driver.findElement(By.cssSelector("#registration-form-modal"));

        assertTrue(registrationFormLabel.isDisplayed());
        assertEquals(registrationFormLabel.getText(), "Registration Form");

    }

    @Test(priority = 3)
    public void fillRegistrationForm() {

        driver.findElement(By.cssSelector("#firstName")).sendKeys("Bariscan");
        driver.findElement(By.cssSelector("#lastName")).sendKeys("TEMEL");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("bariscan.temel@gmail.com");
        driver.findElement(By.cssSelector("#age")).sendKeys("37");
        driver.findElement(By.cssSelector("#salary")).sendKeys("10000");
        driver.findElement(By.cssSelector("#department")).sendKeys("IT");

        WebElement btnClick = driver.findElement(By.cssSelector("#submit"));
        btnClick.click();

        //WebElement element = driver.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(1)"));

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
