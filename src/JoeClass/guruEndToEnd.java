package JoeClass;


import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class guruEndToEnd {
	
	// Declare Webdriver as global variable 	
		WebDriver driver;
		
		  //Instantiation of an object of Data class - instantiation = creating
			public readData dataobject = new readData();
			public Utility Util = new Utility();
			
		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub

	      //Creating/Instantiation an object  (This will allow us to call the method)
			guruEndToEnd tstObj = new guruEndToEnd();
			tstObj.Setup();
			tstObj.LoginPage();
			tstObj.NewCustomerPage();
	              //Call the get customer number
			String CustomerID = tstObj.getCustNumber();
			System.out.println(CustomerID);
			tstObj.EditCustomerPage(CustomerID);
			tstObj.DeleteCustomerPage(CustomerID);
//			tstObj.NewAccountPage();
//			tstObj.EditAccountPage();
//			tstObj.DeleteAccountPage();
//			tstObj.DepositPage();
//			tstObj.WithdrawalPage();
			tstObj.LogOut();
			
				 
		}
		
	//  This method is for setting up our application
	 public void Setup() throws InterruptedException {	
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mlungisi.ntshingila\\Documents\\Mlungisi\\selenium\\chromedriver.exe");
		//System.out.println("user.dir");
		//Initialize
	    driver = new ChromeDriver();
	//  Launch browser    
		driver.get("http://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		
	}
	  
	 //This method is for Logging to guru99 Application
	public void LoginPage() throws InterruptedException {
		
	  //Read username and password from CSV
		String filepath = "C:\\Users\\mlungisi.ntshingila\\eclipse-workspace\\VPC_BO\\Data\\Login.csv";
		String username = dataobject.ReadDataFromCSV("USERNAME", filepath);
		String password = dataobject.ReadDataFromCSV("PASSWORD", filepath);
		
		Thread.sleep(2000);
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
	}

	// Create a new customer
	public void NewCustomerPage() throws InterruptedException {
		
	      //Read customer inputs from CSV
		String filepath = "C:\\Users\\mlungisi.ntshingila\\eclipse-workspace\\VPC_BO\\Data\\CustData.csv";
		String Name = dataobject.ReadDataFromCSV("CustomerName", filepath);
		String DateofBirth = dataobject.ReadDataFromCSV("Date of Birth", filepath);
		String Address = dataobject.ReadDataFromCSV("Address", filepath);
		String City = dataobject.ReadDataFromCSV("City", filepath);
		String State = dataobject.ReadDataFromCSV("State", filepath);
		String Pin = dataobject.ReadDataFromCSV("PIN", filepath);
		String Mobilenumber = dataobject.ReadDataFromCSV("Mobile Number", filepath);
		String EmailAddress = dataobject.ReadDataFromCSV("E-mail", filepath);
		String Password = dataobject.ReadDataFromCSV("Password", filepath); 
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("New Customer")).click();
		driver.findElement(By.name("name")).sendKeys(Name);
		driver.findElement(By.xpath("//input[2]")).click();
		driver.findElement(By.xpath("//tr[6]/td[2]/input")).sendKeys("0019840101");
		driver.findElement(By.name("addr")).sendKeys(Address);
		driver.findElement(By.name("city")).sendKeys(City);
		driver.findElement(By.name("state")).sendKeys(State);
		driver.findElement(By.name("pinno")).sendKeys(Pin);
		Thread.sleep(2000);
		driver.findElement(By.name("telephoneno")).sendKeys((Mobilenumber));	
		int randomInt = Util.RandomNumberGenerator(10);
		Thread.sleep(2000);
		driver.findElement(By.name("emailid")).sendKeys(Util.RandomCharactersGenerator(5)+ randomInt +EmailAddress); 
		driver.findElement(By.name("password")).sendKeys((Password));
		driver.findElement(By.name("sub")).click();
		Thread.sleep(3000);	
	}

	public String getCustNumber() {
		String CustNumber = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
		return CustNumber;
	}

	public void EditCustomerPage(String CustNumber) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Edit Customer")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("cusid")).sendKeys(CustNumber);
		driver.findElement(By.name("AccSubmit")).click();
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(("11 Mandarin Road Honeydew GroveExt.8 2170"));
		driver.findElement(By.name("sub")).click();
		Thread.sleep(3000);
	}
		
	public void DeleteCustomerPage(String CustNumber) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Delete Customer")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("cusid")).sendKeys(CustNumber);
		driver.findElement(By.name("AccSubmit")).click();
		Thread.sleep(2000);
		String PopUpMsg = driver.switchTo().alert().getText();
		System.out.println(PopUpMsg);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
	}

	// Logout & Close the current browser
	public void LogOut() throws InterruptedException{
		
		driver.findElement(By.partialLinkText("Log ou")).click();
		Thread.sleep(3000);
//		driver.switchTo().alert().accept();
		driver.close();
	}

}

