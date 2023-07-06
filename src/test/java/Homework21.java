import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest {
    String playlistUpdated = "Updated playlist";
    String newPlaylistName = "Edited Playlist";

    @Test

    public void renamePlaylist() {

        provideEmail("diana.vysotsky@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(getNotificationText().contains(playlistUpdated));
    }

    public void doubleClickPlaylist(){
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='playlists']/ul/li[3]/a")));
        actions.doubleClick(playlist).perform();
    }

    public void enterNewPlaylistName(){
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getNotificationText(){
        WebElement notificationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationElement.getText();
    }
}
