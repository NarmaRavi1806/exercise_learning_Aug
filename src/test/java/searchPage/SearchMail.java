package searchPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.readConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class SearchMail extends BaseClass{
	
	@BeforeTest
	public void setup() throws IOException
	{
//		//Logging
//		logger=Logger.getLogger("nopCommerceDemoRun");
//		PropertyConfigurator.configure("Log4j.properties");
//		logger.setLevel(Level.DEBUG);
		
		//Load properties file
		configProp= new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
//		String br=configProp.getProperty("browser"); //getting the browser name from config.properties file
		

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

//			driver.manage().window().maximize();
			
//			lp=new LoginPage(driver);
//			String app=configProp.getProperty("nop_appURL");
//			driver.get(app);
//			 driver.manage().window().maximize();
//			 
//			 String user=configProp.getProperty("userName");
//				String pass=configProp.getProperty("password");
//				lp.setUserName(user);
//				lp.setPassword(pass);
//				lp.clickLogin();
		
		
	
	}
	
	
	public void user_Launch_Chrome_browser() {
		lp=new LoginPage(driver);
		String app=configProp.getProperty("nop_appURL");
		driver.get(app);
		 driver.manage().window().maximize();
	}
	
	public void user_enters_Email_as_and_Password_as() {
		String user=configProp.getProperty("userName");
		String pass=configProp.getProperty("password");
		lp.setUserName(user);
		lp.setPassword(pass);
		lp.clickLogin();
	}


public void addCustomer() throws Exception {
	addCust=new AddcustomerPage(driver);
	Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	Thread.sleep(3000);
	addCust.clickOnCustomersMenu();
	Thread.sleep(2000);
	addCust.clickOnCustomersMenuItem();
}

@Test(dataProvider = "regressionTestData")
public void enter_customer_EMail(String string) throws Exception {
	
	user_Launch_Chrome_browser();
	
	user_enters_Email_as_and_Password_as();
	
	addCustomer();
	
	addCust.verifyCustURL("https://admin-demo.nopcommerce.com/Admin/Customer/List");
	
	searchCust=new SearchCustomerPage(driver);
	searchCust.setEmail(string);
	
	searchCust.clickSearch();
	Thread.sleep(3000);
	
	boolean status=searchCust.searchCustomerByEmail(string);
	
	Assert.assertEquals(true, status);
}



@DataProvider
public Object[][] regressionTestData() {
	
	
	Object [][] data = { {"victoria_victoria@nopCommerce.com"},{"arthur_holmes@nopCommerce.com"}, {"james_pan@nopCommerce.com"}, {"admin@yourStore.com"}};
	
	return data;
	
}

}
