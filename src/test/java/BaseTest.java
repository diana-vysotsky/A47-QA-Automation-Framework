import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import java.time.Duration;

public class BaseTest {

    public static WebDriver driver = null;

    public static WebDriverWait wait = null;
    public static String url = "https://www.arcgis.com/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod

    public void launchBrowser(){
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public static void clickSignIn(){
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.esri-header-account-control")));
        signInButton.click();
    }

    public static void provideEmail(String email){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='username']")));

        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }

    public static void providePassword(String password){
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public static void clickSubmit(){
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton.click();
    }


}