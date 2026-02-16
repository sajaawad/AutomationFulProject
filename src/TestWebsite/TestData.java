package TestWebsite;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestData {

	
	String websiteName="https://automationteststore.com/";
	WebDriver driver=new ChromeDriver();
	
	Random rand=new Random();
	
	String passAndConfirmPass="Saja124@12";
	
	String expectedLogoutMsg="You have been logged off your account";
	boolean expectedLogoutValue=true;
	
	
	String[] femaleNames = {"Sara", "Maryam","Layan", "Noor", "Tala", "Aya", "Rana", "Jana", "Hiba","Dana"};
	int femaleArrSize=femaleNames.length;
	
	int randomIndex=rand.nextInt(femaleArrSize);
	
	String firstName=femaleNames[randomIndex];
	
	
	String[] maleNames = {"ahmad", "ali","khaled", "sami", "Talal"};
	
	int maleArrSize=maleNames.length;
	
	int randomIndexMale=rand.nextInt(maleArrSize);
	
	String LastName=maleNames[randomIndexMale];
	
	int randomNum1=rand.nextInt(0,778);
	int randomNum2=rand.nextInt(0,145);
	
	String FullEmail=firstName + "_" + LastName + randomNum1 + randomNum2;
	
	
	
	
	public void MySetup() {
		driver.get(websiteName);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	
	
	
	
}
