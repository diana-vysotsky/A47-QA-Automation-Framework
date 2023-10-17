import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MapTests extends BaseTest {
    @Test

    public void testSignInValidEmailPassword() throws InterruptedException {
        signIn();

        //Verify that sign in was successful and account menu button is visible
        WebElement accountMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#esri-header-account-control")));
        Assert.assertTrue(accountMenuButton.isDisplayed());
    }

    @Test

    public void testOpenMap() throws InterruptedException {
        signIn();

        openNavigationMap();

        //Verify the map is open and title My Map is visible
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("My Map"));
    }

    @Test

    public void testZoomMap() throws InterruptedException {
        signIn();

        openNavigationMap();

        //Find scale bar on a map
        WebElement scaleBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.esriScalebarLabel.esriScalebarFirstNumber")));

        //Get the initial scale bar text value
        String initialScaleBarText = scaleBar.getText();

        //Zoom in a map
        zoomIn();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(scaleBar, initialScaleBarText)));

        //Get scale bar tex value after zoom in action
        String zoomedInScaleBarText = scaleBar.getText();

        //Verify scale bar value changed and zoom in was successful
        Assert.assertNotEquals(initialScaleBarText, zoomedInScaleBarText);

        //Zoom out a map
        zoomOut();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(scaleBar, zoomedInScaleBarText)));

        //Get scale bar text value after zoom out action
        String zoomedOutScaleBarText = scaleBar.getText();

        //Verify scale bar value changed and zoom out was successful
        Assert.assertNotEquals(zoomedInScaleBarText, zoomedOutScaleBarText);

    }

}









