package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	WebDriver driver;

	@FindBy(css = "input[id='login_field']")
	WebElement inputUsername;

	@FindBy(css = "input[id='password']")
	WebElement inputPassword;
	
	@FindBy(css = "input[name='commit']")
	WebElement btnSignIn;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setInputUsername(String strUsername) {
		inputUsername.sendKeys(strUsername);
	}

	public void setInputPassword(String strPassword) {
		inputPassword.sendKeys(strPassword);
	}
	
    public void clickLoginButton() {
    	btnSignIn.click();
    }

	public void signIn(String strUsername, String strPassword) {
		setInputUsername(strUsername);
		setInputPassword(strPassword);
		clickLoginButton();
	}
}
