package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	static AppiumDriver driver = null;
	AppiumServiceBuilder service;

	@BeforeTest
	public void startServer() {
		// Code to check the status of server and close/skip starting if its running
		 service = new AppiumServiceBuilder();
		 service.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
		 .build().start();
	}
	
	@AfterTest
	public void tearDown() {
		service.build().stop();
	}
	
	public static AppiumDriver getDriver(String driverName) throws MalformedURLException {

		String name = driverName.toLowerCase();
		switch (name) {
		case "android":
			URL url = new URL("http://127.0.0.1:4723/");
			UiAutomator2Options uio = new UiAutomator2Options();
			uio.setPlatformName("android");
			uio.setDeviceName("Samsung");
			uio.setPlatformVersion("13");
			uio.setAutomationName("UIAutomator2");
			uio.setAppPackage("com.solodroid.solomerce");
			uio.setAppActivity("com.solodroid.solomerce.activities.MainActivity");
			driver = new AndroidDriver(url, uio);
			break;

		case "ios":
			URL url1 = new URL("http://127.0.0.1:4723/");
			XCUITestOptions xct = new XCUITestOptions();
			xct.setPlatformName("android");
			xct.setDeviceName("Samsung");
			xct.setPlatformVersion("13");
			xct.setAutomationName("XCUITDriver");
			xct.setApp("");
			driver = new IOSDriver(url1, xct);
			break;

		default:
			break;
		}
		return driver;
	}

}
