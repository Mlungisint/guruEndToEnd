package JoeClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Data {
	public WebDriver driver;
	
	public String pathToLogin = "C:\\Users\\mlungisi.ntshingila\\eclipse-workspace\\VPC_BO\\Data\\CustData.csv"; 
	public String Name =ReadDataFromCSV("Customer Name", pathToLogin);
	public String Address =ReadDataFromCSV("Address", pathToLogin);
	public String City =ReadDataFromCSV("City", pathToLogin);
	public String State =ReadDataFromCSV("State", pathToLogin);
	public String PIN =ReadDataFromCSV("PIN", pathToLogin);
	public String Mobile =ReadDataFromCSV("Mobile Numbe", pathToLogin);
	public String Email =ReadDataFromCSV("E-mail", pathToLogin);
	public String Password =ReadDataFromCSV("Password", pathToLogin);
	public Calendar birthday =new GregorianCalendar(2020,10,07);
	String CU=driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();
	
	
	
	public CharSequence[] date()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		return null;
		
	}
	
	public String customerNumber()
	{
		//String no=driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();
		return driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();				
	}
	
	
	//Method to read from the file
		public String ReadDataFromCSV(String datatoberead, String datafilepath) {

			String line = "";
			String splitBy = ",";
			String datatobereturned = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(datafilepath));
				while ((line = br.readLine()) != null) // returns a Boolean value
				{
					String[] employee = line.split(splitBy); // use comma as separator

					if (employee[0].equals(datatoberead)) {
						// Column that you want to
						datatobereturned = employee[1];
					}
				}

				return datatobereturned;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return "";
			}

		}
	
	
	

}
