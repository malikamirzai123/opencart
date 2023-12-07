package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	
	@Test (groups= {"sanity","master"})
	public void verify_login()
	{
		logger.info("**** Starting TC_002_LoginTest  ****");
		logger.debug("capturing application debug logs....");
		
		//home page
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccounts();
		logger.info("clicking on MyAccount");
		hp.clicklogin();
		logger.info("clicking on login link under My Account");
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My Account page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
		if(targetpage==true)
		{
			logger.info("test is passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed");
			Assert.assertTrue(false);
		}
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC_002_LoginTest  ****");
	}
}
