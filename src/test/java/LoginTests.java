import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void LoginValidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Test
    public void LoginEmptyPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("diana.vysotsky@testpro.io");

        loginPage.providePassword("");

        loginPage.clickSubmit();

        Assert.assertTrue(loginPage.isPageOpened());
    }

    @Test
    public void LoginInvalidEmailPasswordTest()  {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("diana.vysotsky@testpro.io");

        loginPage.providePassword("1234");

        loginPage.clickSubmit();

        Assert.assertTrue(loginPage.isPageOpened());
    }
}