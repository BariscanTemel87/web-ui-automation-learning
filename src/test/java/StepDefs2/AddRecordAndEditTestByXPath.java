package StepDefs2;

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
 * This is a test class for adding a record and editing it.
 */
public class AddRecordAndEditTestByXPath {

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
    public void pageIsDisplayedTestByXPath() {


        driver.get("https://demoqa.com/webtables");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement pageTitle = driver.findElement(By.tagName("h1"));

        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Web Tables");

    }

    @Test(priority = 2)
    public void addRecordButtonClickByXPath() {

        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SZ2tf")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("SZ2tf")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement btnClick = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        btnClick.click();

        //WebElement registrationFormLabel = driver.findElement(By.xpath("//div[@id='registration-form-modal']"));
        WebElement registrationFormLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='registration-form-modal']")));
        assertTrue(registrationFormLabel.isDisplayed());
        assertEquals(registrationFormLabel.getText(), "Registration Form");

    }

    @Test(priority = 3)
    public void fillRegistrationFormByXPath() {

        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Bariscan");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("TEMEL");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("bariscan.temel@gmail.com");
        driver.findElement(By.xpath("//input[@id='age']")).sendKeys("37");
        driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("10000");
        driver.findElement(By.xpath("//input[@id='department']")).sendKeys("IT");

        WebElement btnClick = driver.findElement(By.xpath("//button[@id='submit']"));
        btnClick.click();


        WebElement row = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div[1]"));

        String firstName = row.findElement(By.xpath("//div[contains(text(),'Bariscan')]")).getText();
        String lastName = row.findElement(By.xpath("//div[contains(text(),'TEMEL')]")).getText();
        String age = row.findElement(By.xpath("//div[contains(text(),'37')]")).getText();
        String email = row.findElement(By.xpath("//div[contains(text(),'bariscan.temel@gmail.com')]")).getText();
        String salary = row.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div[1]/div[5]")).getText();
        String department = row.findElement(By.xpath(" //div[contains(text(),'IT')]")).getText();

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

    @Test(priority = 4)
    public void updateRegistrationFormByXPath() {

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Web Tables");

        WebElement row = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div[1]"));

        WebElement editButton = driver.findElement(By.xpath("//*[@id='edit-record-4']"));
        editButton.click();

        WebElement ageField = driver.findElement(By.xpath("//input[@id='age']"));
        ageField.clear();
        ageField.sendKeys("38");

        WebElement btnClick = driver.findElement(By.xpath("//button[@id='submit']"));
        btnClick.click();

        String updatedAge = row.findElement(By.xpath("//div[contains(text(),'38')]")).getText();
        assertEquals(updatedAge, "38");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
