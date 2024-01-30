import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.offset.PointOption;

public class test {
	AppiumDriverLocalService service;
	
	@BeforeTest()
	public void startServer() {
//		AppiumServiceBuilder.APPIUM_PATH ="/usr/local/lib/node_modules/appium/build/lib/main.js";
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//		service.start();
		
	}
	
	@BeforeMethod
	public void setup() {
		
	}
	
	@AfterTest
	public void stopServer() {
//		service.stop();
		
	}
	
	
	public static void scrollDown() {
		WebElement ele = driver.findElement(AppiumBy.id(""));
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height*0.30);
		int centre = size.width/2;
		System.out.println(centre);
		System.out.println("Start postion: "+startY+" End position: "+endY);
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 0);
		swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),Origin.viewport(), centre, (int)startY));
		swipe.addAction(finger.createPointerDown(0));
		swipe.addAction(finger.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), centre, (int)endY));
		swipe.addAction(finger.createPointerUp(0));
		
		PointerInput finger2 = new PointerInput(Kind.TOUCH, "finger2");
		Sequence swipe2 = new Sequence(finger, 0);
		swipe.addAction(finger2.createPointerMove(Duration.ofSeconds(0),Origin.viewport(), centre, (int)startY));
		swipe.addAction(finger2.createPointerDown(0));
		swipe.addAction(finger2.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), centre, (int)endY));
		swipe.addAction(finger2.createPointerUp(0));
		
		driver.perform(Arrays.asList(swipe, swipe2));
		
		// Older version
		
//		TouchAction finger1 = new TouchAction(driver);
//		finger1.press(PointOption.point(centre, startY))
//		.waitAction().moveTo(PointOption.point(centre, endY))
//		.release().perform();
//		
//		TouchAction finger2 = new TouchAction(driver);
//		finger1.press(PointOption.point(centre, startY))
//		.waitAction().moveTo(PointOption.point(centre, endY))
//		.release().perform();
//		
//		MultiTouchAction zoom = new MultiTouchAction(driver);
//		zoom.add(finger1).add(finger2).perform();
		
		
	}
	
	@Test
	public void launchApp() throws MalformedURLException, InterruptedException {
		URL url = new URL("http://127.0.0.1:4723/");
		UiAutomator2Options uio = new UiAutomator2Options();
		uio.setPlatformName("android");
		uio.setDeviceName("Samsung");
		uio.setPlatformVersion("13");
		uio.setAutomationName("UIAutomator2");
		uio.setAppPackage("com.solodroid.solomerce");
		uio.setAppActivity("com.solodroid.solomerce.activities.MainActivity");
		driver = new AndroidDriver(url, uio);
		
		Thread.sleep(5000);
//		for (int i = 0; i < 5; i++) {
//			scrollDown();
//		}
		String container = "new UiSelector().resourceId(\"com.solodroid.solomerce:id/recycler_view\")";
//		driver.findElement(AppiumBy
//				.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.solodroid.solomerce:id/recycler_view\")).scrollForward()"));
		
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable("+container+")"
						+ ".scrollIntoView(new UiSelector().textContains(\"Money Counter\"))")).click();
		
	}
	static AndroidDriver driver = null;
	
	
//	public static void main(String[] args) throws MalformedURLException, InterruptedException {
//		URL url = new URL("http://localhost:4723/");
//		
//		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setCapability("platformName", "android");
//		cap.setCapability("deviceName", "Samsung");
//		cap.setCapability("appPackage", "com.solodroid.solomerce");
//		cap.setCapability("appActivity", ".activities.MainActivity");
//		cap.setCapability("automationName", "UIAutomator2");
//		cap.setCapability("platformVersion", "13");
//		
//		UiAutomator2Options uio = new UiAutomator2Options();
//		uio.setPlatformName("android");
//		uio.setDeviceName("Samsung");
//		uio.setPlatformVersion("13");
//		uio.setAutomationName("UIAutomator2");
//		uio.setAppPackage("com.solodroid.solomerce");
//		uio.setAppActivity("com.solodroid.solomerce.activities.MainActivity");
////		uio.setUdid("192.168.1.2:37755");
////		uio.noReset();
//		uio.setUnlockType("pattern");
//		uio.setUnlockKey("2589");
//		
//		driver = new AndroidDriver(url, uio);
//		Thread.sleep(5000);
//		
////		Map<String, Object> args2 = new HashMap<>();
////		args2.put("duration", 1.5);
////		args2.put("fromX", 100);
////		args2.put("fromY", 100);
////		args2.put("toX", 300);
////		args2.put("toY", 600);
////		driver.executeScript("mobile: dragFromToForDuration", args2);
//		
//		
////		driver.findElement(By.id("com.solodroid.solomerce:id/search")).click();
////		driver.findElement(By.id("com.solodroid.solomerce:id/search_src_text")).sendKeys("LED TV");
////		driver.hideKeyboard();
//		
////		if(driver.getOrientation().equals(ScreenOrientation.PORTRAIT)) {
////			driver.rotate(ScreenOrientation.LANDSCAPE);
////		}
////		driver.rotate(ScreenOrientation.PORTRAIT);
//		
////		driver.runAppInBackground(Duration.ofSeconds(5));
////		WebElement baby = driver.findElement(AppiumBy.id(""));
//		
////		HashMap<String, String> args1 = new HashMap<String, String>();
////		args1.put("direction", "down");
////		args1.put("name", "Animal");
//
////		args1.put("name", "Robot Vaccum");
////		driver.executeScript("mobile: scroll", args1);
//		
////		driver.navigate().back();
////		driver.lockDevice();
//		
//		
//		
////		driver.findElement(AppiumBy.androidUIAutomator(""));
////		driver.lockDevice();
//		
////		driver.unlockDevice();
//	}

}
