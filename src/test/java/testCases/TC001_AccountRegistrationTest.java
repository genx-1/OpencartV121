package testCases;


import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;



public class TC001_AccountRegistrationTest extends BaseClass  {
	//public WebDriver driver;
	@Test (groups={"Regression", "Master"})
	void veryfy_account_registration(){
	logger.info("**** staring TC001_AccountRegistrationTest ****");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info("*** clicked on MyAccount link ***");
		
		hp.clickRegister();
		logger.info("*** clicked on Register link ***");
		
	AccountRegistrationPage	regpage = new AccountRegistrationPage(driver); 
	
	logger.info("*** providing customer detail ***");
	regpage.setFirstname(randomString().toUpperCase());
	regpage.setLastname(randomString().toUpperCase());
	regpage.setEmail(randomString()+"@gmail.com");	
	regpage.setTelephone(randomeNumber());	
	
	String password = randomalphanumeric();
	regpage.setPassword(password);	
	regpage.setConfirmPassword(password);	
	regpage.SetPrivacyPolicy();
	regpage.clickContinue();
	
	logger.info("*** validating expected message ***");
	
	String confmsg = regpage.getConfirmationMsg();  
	
	if (confmsg.equals("Your Account Has Been Created!"))
	{Assert.assertTrue(true);
	
	}
	else
	{
		logger.error("Test failed..");
		logger.debug("debud log");
		Assert.assertTrue(false);
	}
	//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
	}
	catch(Exception e) {
		
		Assert.fail();
	}
	
		logger.info("*** finished TC001_AccountRegistrationTest ****");
	
	
	}	
}
