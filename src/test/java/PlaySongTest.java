import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.AllSongsPage;
public class PlaySongTest extends BaseTest{
    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getDriver());
        AllSongsPage songsPage = new AllSongsPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();

        homePage.chooseAllSongsList();

        songsPage.contextClickFirstSong();

        songsPage.playFromContextMenu();

        Assert.assertTrue(songsPage.isSongPlaying());
    }
}
