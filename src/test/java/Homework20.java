import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework20 extends BaseTest {
    @Test

    public void deletePlaylist() throws InterruptedException {
        String playlistDeleted = "Deleted playlist";

        provideEmail("diana.vysotsky@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        clickPlaylist();
        removePlaylist();
        Assert.assertTrue(getNotificationText().contains(playlistDeleted));
    }

    public void clickPlaylist() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='playlists']/ul/li[4]/a")));
        playlist.click();
    }

    public void removePlaylist() {
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='playlistWrapper']/header/div[3]/span/button")));
        deleteButton.click();
    }

    public String getNotificationText(){
        WebElement notificationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));

        return notificationElement.getText();
    }
}
