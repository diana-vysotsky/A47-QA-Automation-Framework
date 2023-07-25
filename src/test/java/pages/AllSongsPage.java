package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSongsPage extends BasePage{

    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By firstSongElement = By.cssSelector(".all-songs tr.song-item:nth-child(1)");

    By soundBarVisualizerElement = By.cssSelector("[data-testid = 'sound-bar-play']");

    public void contextClickFirstSong() {
        WebElement firstSong = findElement(firstSongElement);
        actions.contextClick(firstSong).perform();
    }

    public void playFromContextMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playback"))).click();
    }

    public boolean isSongPlaying() {
        WebElement soundBarVisualizer = findElement(soundBarVisualizerElement);
        return soundBarVisualizer.isDisplayed();
    }

}