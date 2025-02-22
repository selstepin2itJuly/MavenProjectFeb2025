package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class VerifyMultupleUsers {
	static final Logger logger = LogManager.getLogger(VerifyMultupleUsers.class); 
	TestBase tb;
	WebDriver dr;
	LoginPage lp;
	DashboardPage dp;
	
	@DataProvider(name="mulitple users")
	public String[][] getUsers()
	{
		String[][] str = {{"admin","admin"},
		{"manager","demouserpwd"},
		{"user1","demouserpwd"},
		{"user2","demouserpdw"}
		};
		return str;	
	}
	
  
  @Test(dataProvider="mulitple users",description="Verify Mulitple User", groups = { "Regression" })
  public void TC001_MulitpleUser_Login_Successful(String user, String pass) throws IOException {
	  lp.loginToApplication(user, pass);
	  Assert.assertTrue(dp.isLoggedInDisplayed());
	  dp.logout();
	  Assert.assertTrue(lp.isLogOutIsSuccessful());
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
