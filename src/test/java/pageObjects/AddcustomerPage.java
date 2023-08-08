package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddcustomerPage {

	public WebDriver driver;
	
	public AddcustomerPage(WebDriver rdriver)
	{
		driver=rdriver;
		PageFactory.initElements(driver, this);
	}
	
	//By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	//By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	//By lnkCustomers_menu=By.xpath("//a[contains(@href,'List')]//descendant::p[contains(text(),' Customers')]");
	By lnkCustomers_menu=By.xpath("//p[text()[normalize-space()='Customers']]");
	
	
	
	@FindBy(xpath = "//p[text()[normalize-space()='Customers']]")
	WebElement lnkCustomers_Menu;
	
	
	
	//By lnkCustomers_menu=By.xpath("//p[text()[normalize-space()='Customers']]");
	By lnkCustomers_menuitem=By.xpath("//p[text()=' Customers']");
	//By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	By btnAddnew=By.xpath("//a[@href='/Admin/Customer/Create']"); //Add new
		
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
	
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFeMaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
		
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	
	
	//Actions Methods
			
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void clickOnCustomersMenu() throws InterruptedException {
		
		
		//driver.findElement(lnkCustomers_menu).click();
		
		lnkCustomers_Menu.click();
		Thread.sleep(1000);
	}

	public void clickOnCustomersMenuItem() throws InterruptedException {
		driver.findElement(lnkCustomers_menuitem).click();
		Thread.sleep(1000);
	}
	
	public void clickOnAddnew() throws InterruptedException {
		driver.findElement(btnAddnew).click();
		Thread.sleep(3000);
	}
	
	public void setEmail(String email)
	{
		driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		driver.findElement(txtPassword).sendKeys(password);
	}
	
	
	public void setCustomerRoles(String role) throws InterruptedException 
		{
			if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
			{
			driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			}
			
			driver.findElement(txtcustomerRoles).click();
						
			WebElement listitem;
			
			Thread.sleep(3000);
						
			if(role.equals("Administrators"))
			{
				listitem=driver.findElement(lstitemAdministrators); 
			}
			else if(role.equals("Guests"))
			{
				listitem=driver.findElement(lstitemGuests);
			}
			else if(role.equals("Registered"))
			{
				listitem=driver.findElement(lstitemRegistered);
			}
			else if(role.equals("Vendors"))
			{
				listitem=driver.findElement(lstitemVendors);
			}
			else
			{
				listitem=driver.findElement(lstitemGuests);
			}
						
			//listitem.click();
			//Thread.sleep(3000);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", listitem);
			
	}

	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(driver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			driver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			driver.findElement(rdFeMaleGender).click();
		}
		else
		{
			driver.findElement(rdMaleGender).click();//Default
		}
		
	}
	
	public void setFirstName(String fname)
	{
		driver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		driver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		driver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		driver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		driver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave()
	{
		driver.findElement(btnSave).click();
	}
	
	public void verifyCustURL(String expURL) throws InterruptedException {

		Assert.assertEquals(driver.getCurrentUrl(),expURL);

	}
	
}
