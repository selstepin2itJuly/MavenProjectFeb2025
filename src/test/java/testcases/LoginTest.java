package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	static final Logger logger = LogManager.getLogger(LoginTest.class); 
	TestBase tb;
	WebDriver dr;
	LoginPage lp;
	DashboardPage dp;
  @Test(priority=-1, description="Verify the login is successful",groups = { "Sanity", "Regression" })
  public void TC001_Login_Successful() throws IOException {
	  lp.loginToApplication("user1", "demouserpwd");
	  Assert.assertTrue(dp.isLoggedInDisplayed());
	  dp.logout();
	  Assert.assertTrue(lp.isLogOutIsSuccessful());
  }
  
  @Test(priority=0,description="Verify the login is unsuccessful", groups = { "Sanity" })
  public void TC002_Login_UnSuccessful() throws IOException {
	  lp.loginToApplication("user", "demouserpwd");
	  Assert.assertFalse(dp.isLoggedInDisplayed());
	  
  }
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  tb = new TestBase();
	  logger.info("Initializing Brower");
	  dr = tb.getDriverInstance();
	  lp = new LoginPage(dr);
	  dp = new DashboardPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dr.quit();
  }

}
