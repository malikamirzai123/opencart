package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
		WebDriver driver;
		
		public HomePage(WebDriver driver) 
		{
			super(driver);
		}
		

	@FindBy(xpath="//span[normalize-space()='My Account']") @CacheLookup 
	private WebElement myAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") @CacheLookup 
	private WebElement register;

	@FindBy(xpath="//a[normalize-space()='Login']") @CacheLookup 
	private WebElement login;
	
	public void clickMyAccounts()
	{
		myAccount.click();
	}
	
	public void clickregister()
	{
		register.click();
	}
	
	public void clicklogin()
	{
		login.click();
	}



}
