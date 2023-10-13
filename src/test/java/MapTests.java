import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MapTests extends BaseTest{
    @Test

    public void signIn() throws InterruptedException{
        clickSignIn();
        provideEmail("vysotskydiana@gmail.com");
        providePassword("TestArcGIS2023");
        clickSubmit();

        WebElement accountMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#esri-header-account-control")));
        Assert.assertTrue(accountMenuButton.isDisplayed());
    }
    @Test

    public void openMap() throws InterruptedException{
        signIn();
        WebElement mapMenuHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#esri-header-menus-link-desktop-0-3")));
        mapMenuHeader.click();

        WebElement OpenInMapViewerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a#esri-header-menus-link-desktop-1-0")));
        OpenInMapViewerButton.click();
    }
    @Test
    public void zoomMap() throws InterruptedException{
        openMap();

        WebElement BaseMapMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#webmap-basemap_label")));
        BaseMapMenuButton.click();

        WebElement BaseMapMenuOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#basemap-gallery")));

        WebElement navigationMap = BaseMapMenuOptions.findElement(By.cssSelector("#galleryNode_61ffcf610f314933916e4b2c0e477b29 > a > img"));
        navigationMap.click();

        WebElement zoomInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".esriSimpleSliderIncrementButton")));
        for (int i = 0; i < 3; i++) {
            zoomInButton.click();
        }
      }
    }

