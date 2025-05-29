package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")//getting dataprovider from different class
	public void verify_loginDDT(String email, String pwd, String exp ) {
	logger.info("*******starting TC003_LoginDDT ****");
	try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailaddress(email);
		
		lp.setpassword(pwd);
	
		lp.clickLogin();
		//MyAccount
		MyAccountPage myacc = new MyAccountPage(driver);
		
		boolean targetPage = myacc.isMyAccountPageExist();
	
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage == true) {
			myacc.clickLogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage == true) {
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("******* Finished TC003_LoginDDT ****");	
		
		
		
		
	}
}
