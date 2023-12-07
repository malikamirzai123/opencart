package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.print.DocFlavor.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass  {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass(groups= {"regression","sanity","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	
	{	
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
	
		
		
		logger= LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("not matching os");
				
				return;
			}
			
			switch(br.toLowerCase())
			{
			case "chorme":capabilities.setBrowserName("chrome");break;
			case"edge":capabilities.setBrowserName("edge");break;
			default:System.out.println("no matching browser");return;
																			
			}
			
			driver = new RemoteWebDriver( (CommandExecutor) new URL("http://localhost:4444/wd/hub"), capabilities);
			
				
			
		
		}
		
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chorme":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			default:System.out.println("no matching browser");
			
			return;
			}
		}
		
		
		
		
		
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "Edge":driver=new EdgeDriver();break;
		default:System.out.println("no matching browser");
			return;	
		
		}
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"regression","sanity","master"})
	public void tearDown()
	{
		driver.close();
		
	}
	
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
	
	
	

}
