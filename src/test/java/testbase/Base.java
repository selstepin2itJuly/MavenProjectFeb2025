package testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.TestUtilities;

public class Base {

	public WebDriver driver;
	
	public Base(WebDriver dr)
	{
		this.driver=dr;
	}
	
	public void scrollToElementJS(WebElement ele)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(false);", ele); 
		je.executeScript("scrollBy(0,400);", ""); 
	}
	
	public void sendKeysUsingJS(WebElement ele, String s)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].input("+s+");", ele, ele, ele, ele); 
	}
	
	public void clickOnElementUsingJS(WebElement ele)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", ele); 
    }
	
	public void scrollToElementActions(WebElement e)
	{
		Actions ac = new Actions(driver);
		ac.scrollToElement(e).perform();
		((JavascriptExecutor) driver).executeScript("scrollBy(0,400);","");
	}
	
	public void waitForElement(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	
	
	public void captureScreen() throws IOException
	{
		//directory
		String folder = "Screenshot/"+new SimpleDateFormat("YYYY_MMM_dd").format(new Date());
		File file = new File(folder);
		if(!file.isDirectory())
		{
			System.out.println("Directory Doesn't exist!");
			file.mkdirs();
		}else
		{
			System.out.println("Directory exists!");
		}
		TakesScreenshot tc = (TakesScreenshot) driver;
		File src = tc.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("./"+folder+"/"+TestUtilities.getDateAndTime()+"_image.png"));
	}
	
	public void attachScreenToReport()
	{
		TakesScreenshot tc = (TakesScreenshot) driver;
		String src = tc.getScreenshotAs(OutputType.BASE64);
		String image ="<img src=\"data:image/png;base64,"+src+"\" height=\"600\" width=\"900\" />";
		Reporter.log(image);
	}
	
	public void attachDataToReport(String data)
	{
		Reporter.log(data);
	}
	public void attachDataToReport(List<String> data)
	{
		Reporter.log(data.toString());
	}
}
