package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BrowsersEnum;
import utils.LoadProperties;

public class SignInPageTest {

	WebDriver driver;

	HomePage homePage;

	SignInPage loginPage;

	UserHomePage userHomePage;

	@BeforeTest
	@Parameters("browser")
	public void init(@Optional String browser) {
		driver = BrowsersEnum.getWebDriver(browser);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		homePage = new HomePage(driver);

		driver.get(homePage.getUrl());

		assertTrue(driver.getTitle().startsWith("GitHub: Where the world builds software"));
	}

	@Test(dataProvider = "userData", 
			dependsOnMethods = "testLoginBtn_clickLoginBtn_NavigateToLoginPage")
	public void testLogin_FillLoginForm_NavigateToUserHomePage(String username, String password) {

		loginPage = new SignInPage(driver);
		loginPage.signIn(username, password);

		userHomePage = new UserHomePage(driver);
		userHomePage.btnProfile.click();

		assertEquals(userHomePage.divCurrentUser.getText(), "Signed in as " + username);
	}

	@Test
	public void testLoginBtn_clickLoginBtn_NavigateToLoginPage() {
		homePage.btnLogin.click();
		assertTrue(driver.getTitle().startsWith("Sign in to GitHub"));
	}

	@DataProvider(name = "userData")
	public Object[][] getDataFromProperties() throws IOException {
		Properties dataProps = new LoadProperties().getData();

		return new Object[][] { { dataProps.getProperty("user"), dataProps.getProperty("password") } };

	}

	@AfterTest
	public void cleanup() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
