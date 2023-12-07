package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegisterationPage extends BasePage {
	WebDriver driver;
	
	public AccountRegisterationPage(WebDriver driver)
	{
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-firstname']") @CacheLookup 
	private WebElement firstName;
	@FindBy(xpath="//input[@id='input-lastname']") @CacheLookup 
	private WebElement lastName;
	@FindBy(xpath="//input[@id='input-email']") @CacheLookup 
	private WebElement eMail;
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") @CacheLookup 
	private WebElement password;
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") @CacheLookup 
	private WebElement agree;
	@FindBy(xpath="//input[@value='Continue']") 
	WebElement btnContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		eMail.sendKeys(email);
	}
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		agree.click();
	}
public void clickContinue()
{
	//sol1 
		btnContinue.click();
		
		//sol2 
		//Continue.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(Continue).click().perform();
					
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", Continue);
		
		//Sol 5
		//Continue.sendKeys(Keys.RETURN);
		
		//Sol6  
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10)); 
}
	


	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());

		}
	}

}
