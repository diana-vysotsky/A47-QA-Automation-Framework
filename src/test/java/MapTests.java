import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

        WebElement BaseMapMenuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#webmap-basemap_label")));
        BaseMapMenuButton.click();

        WebElement BaseMapMenuOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#basemap-gallery")));

        WebElement navigationMap = BaseMapMenuOptions.findElement(By.cssSelector("#galleryNode_61ffcf610f314933916e4b2c0e477b29 > a > img"));
        navigationMap.click();
    }
    @Test
    public void zoomMap() throws InterruptedException{
        openMap();

        WebElement zoomInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.esriSimpleSliderIncrementButton")));

            zoomInButton.click();


        WebElement zoomOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.esriSimpleSliderDecrementButton")));

            zoomOutButton.click();


        WebElement scaleBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.esriScalebarLabel.esriScalebarFirstNumber")));
        String initialScale = getScaleValue(scaleBar);

        zoomInButton.click();


        String updatedScale = getScaleValue(scaleBar);

        Assert.assertNotEquals(initialScale, updatedScale);

      }

      @Test
      public void testCityDistanceMeasurement() throws InterruptedException {
          openMap();

          WebElement measureTool = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#webmap-measure_label"))); // Replace with the actual element selector
          measureTool.click();
          WebElement measureOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#measureLabel")));
          WebElement distanceTool = measureOptions.findElement(By.cssSelector("#dijit_form_ToggleButton_1 > span.dijitReset.dijitInline.dijitIcon.distanceIcon"));

          Actions actions = new Actions(driver);

          // Click and hold the measuring tool
          actions.clickAndHold(distanceTool).perform();

          // Move to the first point on the map (e.g., coordinates)
          WebElement point1 = driver.findElement(By.id("point1")); // Replace with the actual element selector
          actions.moveToElement(point1).perform();

          // Release the mouse to set the first point
          actions.release().perform();

          // Move to the second point on the map (e.g., coordinates)
          WebElement point2 = driver.findElement(By.id("point2")); // Replace with the actual element selector
          actions.moveToElement(point2).perform();

          // Release the mouse to set the second point
          actions.release().perform();

          // Get the distance displayed on the screen
          WebElement distanceLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#dijit_layout_ContentPane_4"))); // Replace with the actual element selector
          String distance = distanceLabel.getText();

          // Expected distance (replace with the actual expected value)
          String expectedDistance = "585.1 Kilometers";

          // Assert that the measured distance matches the expected distance
          Assert.assertEquals(distance, expectedDistance, "Measured distance doesn't match the expected distance");

      }



    private String getScaleValue(WebElement scaleBar) {
        return scaleBar.getText();
    }

    }

