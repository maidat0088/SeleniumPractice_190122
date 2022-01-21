package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage {
	WebDriver driver;

	@FindBy(css = "summary[aria-label$='View profile and more']")
	WebElement btnProfile;
		
	@FindBy(xpath = "//a[text()='Signed in as ']")
	WebElement divCurrentUser;

	public UserHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
