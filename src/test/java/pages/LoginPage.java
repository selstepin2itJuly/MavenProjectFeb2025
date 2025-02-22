package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.Base;

public class LoginPage extends Base{
	static final Logger logger = LogManager.getLogger(LoginPage.class); 
	WebDriver local;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.local = driver;
		PageFactory.initElements(local, this);
	}
	
	@FindBy(id="username")
	WebElement user;
	
	@FindBy(name="password")
	WebElement pass;
	
	@FindBy(xpath="//button[@type='button'][contains(text(),'Log in')]") 
	WebElement login;
	
	public void loginToApplication(String username, String password) throws IOException
	{
		logger.info("Username is {}", username);
		user.sendKeys(username);
		logger.info("Password is {}", password);
		pass.sendKeys(password);
		logger.info("Clicked on Login Button");
		attachScreenToReport();
		login.click();
	}
	
	public boolean isLogOutIsSuccessful() throws IOException
	{
		logger.info("check if logout is successful");
		boolean b = false;
		try {
			b = user.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		attachScreenToReport();
		return b;
	}
}
