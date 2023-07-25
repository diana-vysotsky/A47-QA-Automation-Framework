package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    By firstPlaylist = By.xpath("//*[@id='playlists']/ul/li[3]/a");
    By playlistNameField = By.cssSelector("[name='name'");

    By userAvatarIcon = By.cssSelector("img.avatar");

    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }

    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li a.songs"))).click();
    }

    public void doubleClickPlaylist(){
        doubleClick(firstPlaylist);
    }

    public void enterNewPlaylistName(String playlistName){
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist(String playlistName){
        By newPlaylist = By.xpath("//a[text()='"+playlistName+"']");
        return findElement(newPlaylist).isDisplayed();
    }

}