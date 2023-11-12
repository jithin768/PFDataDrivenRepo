package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement emailAddress;
	
	
	public void enterEmailAddress(String emailText) {
		emailAddress.sendKeys(emailText);
	}
	
	
	@FindBy(id="input-password")
	private WebElement Password;
	
	public void enterPassword(String passwordText) {
		Password.sendKeys(passwordText);
	}
	
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	public AccountPage clickLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	

}
