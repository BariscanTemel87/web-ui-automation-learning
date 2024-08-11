package StepDefs2;

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
 * This is a test class for clicking a button in an end-to-end scenario.
 */
public class ButtonClickTestByXPath {

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void endToEndButtonClickTestByXPath() {
        // 1. Navigate to the Elements page and click the "Buttons" item
        driver.get("https://demoqa.com/elements");
        driver.findElement(By.xpath("//*[@id=\"item-4\"]/span")).click();

        // 2. Verify the page title is "Buttons"
        WebElement pageTitle = driver.findElement(By.tagName("h1"));
        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Buttons");

        // 3. Click on the "Click Me" button
        WebElement btnClick = driver.findElement(By.xpath("//button[text()='Click Me']"));
        btnClick.click();

        // 4. Verify the dynamic click message
        WebElement dynamicClickMessage = driver.findElement(By.tagName("p"));
        assertTrue(dynamicClickMessage.isDisplayed());
        assertEquals(dynamicClickMessage.getText(), "You have done a dynamic click");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
