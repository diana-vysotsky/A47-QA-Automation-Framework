import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MapTests extends BaseTest{
    @Test

    public void testSignInValidEmailPassword() throws InterruptedException{
        signIn();

        //Verify that sign in was successful and account menu button is visible
        WebElement accountMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#esri-header-account-control")));
        Assert.assertTrue(accountMenuButton.isDisplayed());
    }
    @Test

    public void openMap() throws InterruptedException{
        signIn();

        openNavigationMap();

        //Verify the map is open and title MY Map is visible
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("My Map"));
    }
    @Test
    public void zoomMap() throws InterruptedException{
        signIn();

        openNavigationMap();

        WebElement scaleBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.esriScalebarLabel.esriScalebarFirstNumber")));

        // Get the initial scale bar text
        String initialScaleBarText = scaleBar.getText();

        zoomIn();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(scaleBar, initialScaleBarText)));

        String zoomedInScaleBarText = scaleBar.getText();

        Assert.assertNotEquals(initialScaleBarText, zoomedInScaleBarText);

        zoomOut();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(scaleBar, zoomedInScaleBarText)));

        String zoomedOutScaleBarText = scaleBar.getText();

        Assert.assertNotEquals(zoomedInScaleBarText, zoomedOutScaleBarText);
        
    }

      @Test
      public void testCityDistanceMeasurement() throws InterruptedException {


      }

}

