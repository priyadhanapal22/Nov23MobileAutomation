package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	
	BasePage(AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
