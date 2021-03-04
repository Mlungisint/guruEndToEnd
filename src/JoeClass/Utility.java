package JoeClass;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;



public class Utility {
	
	//Method to generate random numbers and it takes an argument to say how many numbers you want to return
		public int RandomNumberGenerator(int RandomNum ) {
			Random randomGenerator = new Random(); //Create an instance of Random Class  
			int randomInt = randomGenerator.nextInt(RandomNum);//Generates a random number of int datatype up to a 1000
	        return randomInt;
	        
		}
		
		//Method to generate characters and it takes an argument to say how many characters you want to be return
		public String RandomCharactersGenerator(int RandomChar) {
			String RandomCharacters = RandomStringUtils.randomAlphabetic(RandomChar);
		//	RandomStringUtils.randomAlphanumeric(stringLength);
			return RandomCharacters;
			
			
		}
		
		
		//Take screen shot method
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
		
		
		public static void captureScreenshot(String screenshotName, WebDriver driver)
				throws Exception {
			// GET SYSTEM CURRENT DATE
			Date dt = new Date();
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String scrnShotName = screenshotName + " " + dtFormat.format(dt);

			// Convert web driver object to TakeACreenshot

			TakesScreenshot ts = (TakesScreenshot) driver;
			// Call getScreenshotAs method to create image file
			File src = ts.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination and copy file at destination -

			FileHandler.copy(src, new File(".\\Screenshots/" + scrnShotName
					+ ".png"));

			System.out.print("Screenshot taken");

		}   
		

}


