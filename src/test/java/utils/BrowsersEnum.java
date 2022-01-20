package utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowsersEnum {

	CHROME {
		@Override
		WebDriver getBrowser() {
			Path driverPath = Paths.get("src/test/resources/browsers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", driverPath.toFile().getAbsolutePath());
			return new ChromeDriver();
		}
	},

	FIREFOX {
		@Override
		WebDriver getBrowser() {
			Path driverPath = Paths.get("src/test/resources/browsers/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", driverPath.toFile().getAbsolutePath());
			return new FirefoxDriver();
		}
	},

	EDGE {
		@Override
		WebDriver getBrowser() {
			Path driverPath = Paths.get("src/test/resources/browsers/msedgedriver.exe");
			System.setProperty("webdriver.edge.driver", driverPath.toFile().getAbsolutePath());
			return new EdgeDriver();
		}
	},

	DEFAULT {
		@Override
		WebDriver getBrowser() {
			return CHROME.getBrowser();
		}
	};

	abstract WebDriver getBrowser();

	public static WebDriver getWebDriver(String strWebDriver) {

		return Arrays.stream(values())
				.filter(browser -> browser.name().equalsIgnoreCase(strWebDriver))
				.findFirst()
				.orElse(DEFAULT)
				.getBrowser();
	}
}
