package signin_page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignInPage;
import pages.UserHomePage;
import utils.BrowsersEnum;
import utils.DataProperties;

public class SignInTest {

	WebDriver driver;

	HomePage homePage;

	SignInPage signInPage;

	UserHomePage userHomePage;

	DataProperties dataProperties;

	@BeforeTest
	public void init() {
		dataProperties = new DataProperties();

		String browser = dataProperties.getBrowser();
		driver = BrowsersEnum.getWebDriver(browser);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		userHomePage = new UserHomePage(driver);

		driver.get(homePage.getUrl());
	}

	@Test(dependsOnMethods = "shouldNavigateToLoginPageSuccessfully")
	public void shouldSignedInSuccessfully() {
		signInPage.signIn(dataProperties.getUserName(), dataProperties.getPassword());
 
		assertEquals(userHomePage.getSignInAsAccount(), "Signed in as " + dataProperties.getUserName());
	}

	@Test
	public void shouldNavigateToLoginPageSuccessfully() {
		homePage.navigateToLoginPage();

		assertTrue(driver.getTitle().startsWith("Sign in to GitHub"));
	}

	@AfterTest
	public void tearnDown() {
		driver.quit();
	}

}
