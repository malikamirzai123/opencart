package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups= {"regression","master"})
	public void verify_account_registration()
	
	{
		logger.info(">>>>startin TC_001_AccountRegistrationTest>>>>>");
		
		logger.debug("applicaton log started");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccounts();
		logger.info("clicking on my account");
		hp.clickregister();
		logger.info("Clicked on registration link");
		
		
		logger.info("entering customer details");
		AccountRegisterationPage ar=new AccountRegisterationPage(driver);
		
		ar.setFirstName(randomString().toUpperCase());
		ar.setLastName(randomString().toUpperCase());
		ar.setEmail(randomString()+"@gmail.com");
		ar.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		ar.setPassword(password);
		ar.setConfirmPassword(password);
		
		ar.setPrivacyPolicy();
		ar.clickContinue();
		
		 String confmsg=ar.getConfirmationMsg();
		 logger.info("validating expected msg");
		 
		 if(confmsg.equals("Your Account Has Been Created!"))
		 {
			 logger.info("test is passed");
			 Assert.assertTrue(true);
		 }
		 else 
		 {
			 logger.error("test is failed");
			 Assert.fail();
		 }
		 
		}
		catch(Exception e)
		{
			logger.error("test is failed");
			logger.debug("debug log");
			Assert.fail();
		}
		logger.debug("application log end");
		logger.info("<<<<finished TC_001_AccountRegistrationTest>>>");
		
		
	}

}
