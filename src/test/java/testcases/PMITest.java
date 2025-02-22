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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class PMITest {
	
	static final Logger logger = LogManager.getLogger(DashboardTest.class); 
	TestBase tb;
	WebDriver dr;
	LoginPage lp;
	DashboardPage dp;
  @Test
  public void verifyPMIMenuCount() {
	 
  }
  @BeforeClass(alwaysRun=true)
  public void beforeClass() throws IOException {
	  tb = new TestBase();
	  logger.info("Initializing Brower");
	  dr = tb.getDriverInstance();
	  lp = new LoginPage(dr);
	  dp = new DashboardPage(dr);
	  lp.loginToApplication("user1", "demouserpwd");
	  Assert.assertTrue(dp.isLoggedInDisplayed());
  }

  /*
   * clean up after very testcase
   */
  @AfterClass(alwaysRun=true)
  public void afterClass() throws IOException {
	  dp.logout();
	  dr.quit();
  }
}
