import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver = null;

    public static WebDriverWait wait = null;
    public static String url = "https://www.arcgis.com/";

    @BeforeSuite
    //Set up the ChromeDriver using WebDriverManager
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    // Set up ChromeDriver and open the browser
    public void launchBrowser(){
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(url);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    // Close the browser after the test
    public void closeBrowser(){
        driver.quit();
    }

    //Method to sign in to the ArcGIS app
    public static void signIn(){
        clickSignIn();
        provideEmail("vysotskydiana@gmail.com");
        providePassword("TestArcGIS2023");
        clickSubmit();
    }

    //Helper methods

    //Method to open Navigation Map
    public static void openNavigationMap(){
        clickMapMenuHeader();
        clickOpenInMapViewerButton();
        clickBaseMapMenuButton();
        clickNavigationMap();
    }

    //Click Sign In button to open sign in page
    public static void clickSignIn(){
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.esri-header-account-control")));
        signInButton.click();
    }

    //Enter email to the username field
    public static void provideEmail(String email){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='username']")));

        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }

    //Enter password to the password field
    public static void providePassword(String password){
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    //Click Submit button to sign in
    public static void clickSubmit(){
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        submitButton.click();
    }

    //Click Map on the menu header
    public static void clickMapMenuHeader(){
        WebElement mapMenuHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#esri-header-menus-link-desktop-0-3")));
        mapMenuHeader.click();
    }

    //Click Open in Map Viewer button
    public static void clickOpenInMapViewerButton(){
        WebElement OpenInMapViewerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#esri-header-menus-link-desktop-1-0")));
        OpenInMapViewerButton.click();
    }

    //Click Basemap menu button
    public static void clickBaseMapMenuButton(){
        WebElement BaseMapMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#webmap-basemap_label")));
        BaseMapMenuButton.click();
    }

    //Choose Navigation map from the Base map menu
    public static void clickNavigationMap(){
        WebElement BaseMapMenuOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#basemap-gallery")));

        WebElement navigationMap = BaseMapMenuOptions.findElement(By.cssSelector("#galleryNode_61ffcf610f314933916e4b2c0e477b29 > a > img"));
        navigationMap.click();
    }

    //Find zoom in button and click it
    public static void zoomIn() {
        WebElement zoomInButton = driver.findElement(By.cssSelector("div.esriSimpleSliderIncrementButton"));
        wait.until(ExpectedConditions.elementToBeClickable(zoomInButton)).click();
    }

    //Find zoom out button and click it
    public static void zoomOut() {
        WebElement zoomOutButton = driver.findElement(By.cssSelector("div.esriSimpleSliderDecrementButton"));
        wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
    }

}