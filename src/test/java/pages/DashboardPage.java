package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.Base;

public class DashboardPage extends Base{
	static final Logger logger = LogManager.getLogger(DashboardPage.class); 
	WebDriver local;
	public DashboardPage(WebDriver driver)
	{
		super(driver);
		this.local=driver;
		PageFactory.initElements(local, this);
	}
	
	@FindBy(xpath="//a[contains(text(),' Logged In')]")
	WebElement loggedIn;
	
	//logout element
	@FindBy(css=".caret")
	WebElement caret;
	
	@FindBy(linkText="Sign out")
	WebElement signOut;
	
	@FindBy(xpath="//ul[@id='module_Personal_Information'][contains(@style,'none')]/parent::li")
	WebElement personalInfoTabClose;
	
	@FindBy(xpath="//ul[@id='module_Personal_Information'][contains(@style,'block')]/li/a")
	List<WebElement> personalInfoItems;
	
	public boolean isLoggedInDisplayed() throws IOException
	{
		logger.info("check if dashbaord is displayed");
		boolean b = false;
		try {
			b = loggedIn.isDisplayed();
		}catch(Exception e) {
			e.printStackTrace();
		}
		attachScreenToReport();
		return b;
	}
	
	public void logout() throws IOException
	{
		logger.info("Logout");
		caret.click();
		attachScreenToReport();
		waitForElement(signOut);
		signOut.click();
		attachScreenToReport();
	}
	
	public int getPersonalItemCount() throws IOException
	{
		try {
		    personalInfoTabClose.click();
		    logger.info("Persinal Info Tab Displayed");
		}catch(Exception e)
		{
			logger.info(e);
		}
		int i =personalInfoItems.size();
		logger.info("Persinal Info Tab Displayed {}",i);
		attachScreenToReport();
		attachDataToReport(String.valueOf(i));
		return i;
	}
	
	public List<String> getPersonalItems() throws IOException
	{
		List<String> temp = new ArrayList<String>();
		try {
		    personalInfoTabClose.click();
		    logger.info("Persinal Info Tab Displayed {}");
		}catch(Exception e)
		{
			logger.info(e);
		}
		int i = personalInfoItems.size();
		if(i>0)
		{
			logger.info("Persinal Info Tab Displayed {}",i);
			for(WebElement e:personalInfoItems)
			{
				temp.add(e.getText());
			}
		}else
		{
			logger.info("Size: {}", i);
		}
		logger.info("Persinal items {}",temp);
		attachScreenToReport();
		attachDataToReport(temp);
		return temp;
	}
}
