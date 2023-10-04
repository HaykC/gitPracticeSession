package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
	

	public static WebDriver driver;
	static ChromeOptions chromeOptions;
	static URL url;

	public static WebDriver getDriver() {
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = TestDataReader.getProperty("browser_type");
		}
		if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
			switch (browser) {
			case "chrome-headless":
				chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "chrome":
				chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "firefox-headless":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "saucelabs":
				FirefoxOptions browserOptions = new FirefoxOptions();
				browserOptions.setPlatformName("macOS 13");
				browserOptions.setBrowserVersion("116");
				Map<String, Object> sauceOptions = new HashMap<>();
				sauceOptions.put("username", "oauth-haykchilingaryan1-7ab9e");
				sauceOptions.put("accessKey", "ef4de1d3-1b38-43e8-ae56-3a4c06da57d1");
				sauceOptions.put("build", "<haykTest>");
				sauceOptions.put("name", "<myself>");
				browserOptions.setCapability("sauce:options", sauceOptions);
				try {
					url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				RemoteWebDriver remoteDriver = new RemoteWebDriver(url, browserOptions);
				driver = remoteDriver;
				break;
			default:
				ChromeOptions Options = new ChromeOptions();
				Options.addArguments("--headless");
				driver = new ChromeDriver(Options);
				break;
			}
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}



