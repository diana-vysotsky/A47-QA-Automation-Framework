package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    @FindBy(css = "input[type='email']")
    WebElement emailField;

    @FindBy(css = "input[type='password']")
    WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    public void provideEmail(String email){
        emailField.sendKeys(email);
    }

    public void providePassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickSubmit(){
        submitBtn.click();
    }

    public void login(){
        provideEmail("diana.vysotsky@testpro.io");
        providePassword("te$t$tudent");
        clickSubmit();
    }
}