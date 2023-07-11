package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    @FindBy(xpath = "//*[@id='playlists']/ul/li[3]/a")
    WebElement firstPlaylist;

    @FindBy(css = "[name='name']")
    WebElement playlistNameField;

    @FindBy(xpath = "//*[@id='playlists']")
    WebElement newPlaylist;

    public void doubleClickPlaylist(){
        doubleClick(firstPlaylist);
    }

    public void enterNewPlaylistName(String playlistName){
        playlistNameField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        playlistNameField.sendKeys(playlistName);
        playlistNameField.sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist(String playlistName){
        return findElement(newPlaylist).getText().toString().contains(playlistName);
    }
}