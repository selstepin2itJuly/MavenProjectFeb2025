package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DashboardTest {
	static final Logger logger = LogManager.getLogger(DashboardTest.class); 
	TestBase tb;
	WebDriver dr;
	LoginPage lp;
	DashboardPage dp;
  @Test(enabled=true, priority=2, description="Verify the Personal Count",groups = { "Sanity","Regression" })
  public void TC005_VerifyMenuItemCountPersoalInfo() throws IOException {
	  Assert.assertEquals(dp.getPersonalItemCount(),5); 
  }
  
  @Test(dependsOnMethods="TC005_VerifyMenuItemCountPersoalInfo", enabled=true, priority=1,
		  description="Verify Personal Info items",groups = { "Regression" })
  public void TC006_VerifyMenuItemsPersoalInfo() throws IOException {
	  List<String> act = dp.getPersonalItems();
	  List<String> exp = new ArrayList<String>();
	  exp.add("Dashboard");
	  exp.add("Basic Informations");
	  exp.add("Qualifications");
	  exp.add("Dependents");
	  exp.add("Emergency Contact");
	  SoftAssert sf = new SoftAssert();
	  for(int i=0;i<exp.size();i++)
		  sf.assertEquals(act.get(i), exp.get(i));
	  //Assert.assertEquals(act, exp, "Persnal Info");
	  sf.assertAll();
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

  @AfterClass(alwaysRun=true)
  public void afterClass() throws IOException {
	  dp.logout();
	  dr.quit();
  }

}
