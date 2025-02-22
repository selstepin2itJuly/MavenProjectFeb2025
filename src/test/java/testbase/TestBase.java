package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase{
	static final Logger logger = LogManager.getLogger(TestBase.class); 
	Properties prop;
	WebDriver driver;
	public WebDriver getDriverInstance() throws IOException
	{
		String conf="./src/test/resources/config/config.properties";
		FileInputStream inStream = new FileInputStream(new File(conf));
		logger.info("Reading Config File");
		prop = new Properties();
		prop.load(inStream);
		logger.info("Loaded Config File");
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) 
		{
			logger.info("Launching {}",browser);
		    driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			logger.info("Launching {}",browser);
		    driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge"))
		{
			logger.info("Launching {}",browser);
		    driver = new EdgeDriver();
		}else
		{
			logger.info("No Browser Define! Browser found {}",browser);
			new Throwable().initCause(null);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //implicit timeout
		//driver.manage().window().setSize(new Dimension(344, 882));
		driver.manage().window().maximize();
		logger.info("Maximizing Browser");
		driver.get(prop.getProperty("url"));
		logger.info("Opened URL {}",prop.getProperty("url"));
		return driver;
	}
	
	public WebDriver getDriverInstance(String option) throws IOException
	{
		String conf="./src/test/resources/config/config.properties";
		FileInputStream inStream = new FileInputStream(new File(conf));
		prop = new Properties();
		prop.load(inStream);
	
		if(option.equalsIgnoreCase("chrome")) 
		{
		    driver = new ChromeDriver();
		}else if(option.equalsIgnoreCase("firefox"))
		{
		    driver = new FirefoxDriver();
		}else if(option.equalsIgnoreCase("edge"))
		{
		    driver = new EdgeDriver();
		}else
		{
			new Throwable().initCause(null);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //implicit timeout
		//driver.manage().window().setSize(new Dimension(344, 882));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	}
