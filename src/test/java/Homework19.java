import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Homework19 extends BaseTest {
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

    public void clickPlaylist() throws InterruptedException{
        WebElement playlist = driver.findElement(By.xpath("//*[@id='playlists']/ul/li[4]/a"));
        playlist.click();
        Thread.sleep(2000);
    }

    public void removePlaylist() throws InterruptedException{
        WebElement deleteButton = driver.findElement(By.xpath("//*[@id='playlistWrapper']/header/div[3]/span/button"));
        deleteButton.click();
        Thread.sleep(2000);
    }

    public String getNotificationText(){
        WebElement notificationElement = driver.findElement(By.xpath("/html/body/div[2]"));
        return notificationElement.getText();
    }
}
