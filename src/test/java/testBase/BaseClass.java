  package testBase;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; //log4j
	public Properties p; 
	
	

	@Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity", "Regression", "Master", "Datadriven"})
	public void setup(String os, String br) throws IOException {
		//loading config.properties file
		FileReader file = new FileReader (".//src//test/resources//config.properties");
		p = new Properties();
        p.load(file);  
	
		logger = LogManager.getLogger(this.getClass());
	
		if(p.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
		
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);	
			}
			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.WIN10);
			}
			else {
				System.out.println("No matching found os");
				return;
			}
			
			//browser

			switch (br.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			default :System.out.println("Nothing browser get match");return;
			}
			 driver = new RemoteWebDriver(new URL("http://192.168.1.47:4444/wd/hub"), capabilities);
		}
		
		
		
		if(p.getProperty("execution_env").equals("local")) {
		
		switch (br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default :System.out.println("Invalid browser name ...");return;
		}
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));  //reading url from config.properties file
		driver.manage().window().maximize();
		
	}
	
		
	
	/*
	lang 3 dependenci add keli tyatla RandomStringUtils package import kel so RandomStringUtil Class chya
	//method use karun aapan random sring , randon number , sringnumber combination banau shakkato methods 
	 randomAlphabetic(), randomNumeric() aani tyamadhe kiti nuber pahije te mention karaych like 4, 7, 8
	 */
	public	String randomString() {
			String generatestring = RandomStringUtils.randomAlphabetic(5);
			return generatestring;
		}
		
	public	String randomeNumber(){
			 String generatenumber = RandomStringUtils.randomNumeric(10);
			return generatenumber;
		}
		
		
	public	String randomalphanumeric() {
			String generatestring = RandomStringUtils.randomAlphabetic(3);
			 String generatenumber = RandomStringUtils.randomNumeric(3);
			 return (generatestring+"@"+generatenumber);
		}
	
	public String captureScreen(String tname)throws IOException {
		String timestamp =	new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //to get a current date
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	     File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	      String targetfilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "-" + timestamp + ".png";
	      File targetFile = new File(targetfilePath);
	      sourcefile.renameTo(targetFile);
	      return targetfilePath;
		
		
	}
	
	
		
	@AfterClass (groups= {"Sanity", "Regression", "Master", "Datadriven"})
	  public void tearout() {
		driver.quit();
	}
	
	
	
}
