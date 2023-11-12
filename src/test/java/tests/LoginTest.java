package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import util.DataUtil;
import util.MyXLSReader;

public class LoginTest extends Base{
	
	WebDriver driver;
	MyXLSReader excelReader;
	
	@Test(dataProvider = "dataSupplier")
	public void testLogin(HashMap<String,String> hMap) {
		
		if(!DataUtil.isRunnable(excelReader, "LoginTest", "Testcases")|| hMap.get("Runmode").equals("N")) {
			throw new SkipException("Run mode is set to N, hence not executed");
		}
		
		driver=openBrowserAndApplicationURL(hMap.get("Browser"));
		
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		LoginPage loginPage=homepage.clickOnLogin();
		loginPage.enterEmailAddress(hMap.get("Username"));
		loginPage.enterPassword(hMap.get("Password"));
		AccountPage accountPage=loginPage.clickLoginButton();
		
		String expectedResult=hMap.get("ExpectedResult");
		boolean expectedConvertedResult = false;
		
		if(expectedResult.equalsIgnoreCase("Success")){
			expectedConvertedResult=true;
		}else if(expectedResult.equalsIgnoreCase("Failure")){
			expectedConvertedResult=false;
		}
		boolean actualResult=false;
		try {
			
			 actualResult=accountPage.accountInformationLink.isDisplayed();
		}catch(Throwable e) {
			actualResult=false;
		}
		
		Assert.assertEquals(actualResult, expectedConvertedResult);
		
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] dataSupplier(){
		
		Object[][] data=null;
		
		try {
		
			excelReader=new MyXLSReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TutorialsNinja.xlsx");
		
		data=DataUtil.getTestData(excelReader,"LoginTest","Data" );
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	

}
