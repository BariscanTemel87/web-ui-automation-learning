package StepDefs;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

/*
 * This is a test class for clicking a button.
 */
public class ButtonClickTest {

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
    public void testButtonClick(){

        // Click the button
        driver.get("https://demoqa.com/elements");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#item-4")));
        //driver.findElement(By.id("item-4")).click();
        driver.findElement(By.cssSelector("#item-4")).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        WebElement pageTitle = driver.findElement(By.tagName("h1"));

        assertTrue(pageTitle.isDisplayed());
        assertEquals(pageTitle.getText(), "Buttons");

    }

    @Test(priority = 2)
    public void clickOnClickMeButton(){

        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SZ2tf")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("SZ2tf")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //driver.findElement(By.cssSelector("#SZ2tf")).click();
        //Hocam ccs selector ile click me buttonuna tıklayamadım xpatch kullandım cunku button id'si dinamik yapıya sahip. Bunu size sormak istiyorum ekstra. Bilginize.
        WebElement btnClick = driver.findElement(By.xpath( "//button[text()=\"Click Me\"]"));
        btnClick.click();

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
