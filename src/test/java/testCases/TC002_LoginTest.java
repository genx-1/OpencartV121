package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void Verify_Login()
	{
		logger.info("Execution is started ...");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailaddress(p.getProperty("email"));
		
		lp.setpassword(p.getProperty("password"));
	
		lp.clickLogin();
		//MyAccount
		MyAccountPage myacc = new MyAccountPage(driver);
		
		boolean targetPage = myacc.isMyAccountPageExist();
		
		Assert.assertEquals(targetPage, true);
		}
		catch (Exception e) {
			
		Assert.fail();	
		}
		logger.info("execution finished ...");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
