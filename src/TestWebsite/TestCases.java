package TestWebsite;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCases extends TestData {

	
	@BeforeTest
	public void WebsiteSetup() {
		MySetup();
	}
	
	
	@Test(priority=1,enabled = false)
	public void Signup() throws InterruptedException {
		WebElement signupAndLoginBtn=driver.findElement(By.linkText("Login or register"));
		signupAndLoginBtn.click();
		
		// xpath>>>> //tagname[@attribute='text']
		WebElement continueBtn=driver.findElement(By.xpath("//button[@title = 'Continue']"));
		continueBtn.click();
		
		
		//================First Name ====================

		WebElement firstNameInputTxt=driver.findElement(By.id("AccountFrm_firstname"));
		
		firstNameInputTxt.sendKeys(firstName);
				
		//================Last Name ====================
		

		WebElement lastNameInputTxt=driver.findElement(By.id("AccountFrm_lastname"));
		
		lastNameInputTxt.sendKeys(LastName);
		
		//================Email  ======================
		
		WebElement emailInputTxt=driver.findElement(By.id("AccountFrm_email"));
		emailInputTxt.sendKeys(FullEmail + "@gmail.com");
		
		//================Address  ======================
		
		WebElement addressInputTxt=driver.findElement(By.id("AccountFrm_address_1"));
		addressInputTxt.sendKeys("marka");
		
		//================City  ======================
		
		WebElement cityInputTxt=driver.findElement(By.id("AccountFrm_city"));
		cityInputTxt.sendKeys("amman");
		
		//================Country  ======================
		
		WebElement countrySlct=driver.findElement(By.id("AccountFrm_country_id"));
		Select countrySelection=new Select(countrySlct);
		
		int randomCountry=rand.nextInt(1,countrySlct.findElements(By.tagName("option")).size());
		countrySelection.selectByIndex(randomCountry);
		
		//countrySelection.selectByContainsVisibleText("Jordan");
		//countrySelection.selectByValue("108");
		
		//================State  ======================
		
		Thread.sleep(5000);
		WebElement stateSlct=driver.findElement(By.id("AccountFrm_zone_id"));
		Select stateSelection=new Select(stateSlct);
		
		int randomState=rand.nextInt(1,stateSlct.findElements(By.tagName("option")).size());
		stateSelection.selectByIndex(randomState);
		
		//================Post Code  ======================
		
		WebElement postCodeInputTxt=driver.findElement(By.id("AccountFrm_postcode"));
		postCodeInputTxt.sendKeys("1111");
		
		
		//================Login Name ======================
		WebElement loginNameInputTxt=driver.findElement(By.id("AccountFrm_loginname"));
		loginNameInputTxt.sendKeys(FullEmail);
		
		
		//================Password and Confirm Password ======================
		WebElement passInputTxt=driver.findElement(By.id("AccountFrm_password"));
		passInputTxt.sendKeys(passAndConfirmPass);
		
		WebElement passConfirmInputTxt=driver.findElement(By.id("AccountFrm_confirm"));
		passConfirmInputTxt.sendKeys(passAndConfirmPass);
		
		
		//================ Privacy Policy Check Box ======================
		WebElement conditionsAndTermsChk = driver.findElement(By.id("AccountFrm_agree"));
		conditionsAndTermsChk.click();
		
		
		//================ Continue SignUp Button ======================
		WebElement continueSignUpBtn = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		continueSignUpBtn.click();
		
		//================ Assertions ======================
		Assert.assertEquals(driver.getCurrentUrl().contains("success"), true);

		Assert.assertEquals(driver.getPageSource().contains("Congratulations"), true);
		WebElement WelcomeMessageArea = driver.findElement(By.id("customernav"));

		Assert.assertEquals(WelcomeMessageArea.getText().contains(firstName), true);
		
		
	}
	
	@Test(priority=2,enabled = false)
	public void LogOff() {
		driver.navigate().to("https://automationteststore.com/index.php?rt=account/logout");
		WebElement contentPanel=driver.findElement(By.xpath("//div[@class='contentpanel']"));
		boolean actualLogoutValue = contentPanel.getText().contains("expectedLogoutMsg");    //Actual Result >>> رح يطلعي الactual 
		
		
		//==============Assertions ======================
		
		Assert.assertEquals(actualLogoutValue, expectedLogoutValue);
		
	}
	
	@Test(priority=3)
	public void Login() {
		WebElement signupAndLoginBtn=driver.findElement(By.linkText("Login or register"));
		signupAndLoginBtn.click();
		
		String username="Dana_sami48277";
		
		
		WebElement loginTextInput=driver.findElement(By.id("loginFrm_loginname"));
		//loginTextInput.sendKeys(FullEmail);
		loginTextInput.sendKeys(username);
		
		WebElement passwordLoginInputText=driver.findElement(By.id("loginFrm_password"));
		passwordLoginInputText.sendKeys(passAndConfirmPass);
		
		WebElement loginBtn=driver.findElement(By.xpath("//button[@title='Login']"));
		loginBtn.click();
	}
	
	@Test(priority=4,invocationCount=5)
	public void AddToCart() {
		WebElement homePageByLogo=driver.findElement(By.className("logo"));
		homePageByLogo.click();
		
		List<WebElement> imgs=driver.findElements(By.cssSelector(".thumbnail img"));
		int randomItem=rand.nextInt(imgs.size());
		
		
		imgs.get(randomItem).click();
		
		if (driver.getCurrentUrl().contains("product_id=116")) {
			WebElement checboxkSize=driver.findElement(By.id("option344747"));
			checboxkSize.click();
		}
		
		
		WebElement addToCartBtn=driver.findElement(By.className("cart"));
		addToCartBtn.click();
		
	}
	
	@Test(priority = 5,invocationCount = 5,enabled = false)
	public void RemoveFromCart() throws InterruptedException {
		//Thread.sleep(5000);
		WebElement delBtn=driver.findElement(By.className("btn-sm"));
		delBtn.click();
		//Thread.sleep(2000);

	}
	
	@Test(priority=6)
	public void ChangeQuantityAndCheckTotal() {
		//check Total = quantity * price 
		//Expected >>> Total = quantity * price 
		List <WebElement> qtyTxt=driver.findElements(By.cssSelector(".input-group.input-group-sm input"));
		qtyTxt.get(0).clear();
		qtyTxt.get(0).sendKeys("4");
			
		qtyTxt.get(2).clear();
		qtyTxt.get(2).sendKeys("3");
		
		WebElement updateTableBtn=driver.findElement(By.id("cart_update"));
		updateTableBtn.click();
		
		//طلعت من صفحة السلة للصفحة الرئيسسة ورجعت للسلة عشان اشوف انو  الكميات ما تغيرت وضلت زي ما انا طلبتها  
		driver.navigate().to("https://automationteststore.com/");
		
		//must change
		WebElement cartBtn=driver.findElement(By.cssSelector(".nav.topcart.pull-left .dropdown.hover a")); //as dropdownlist
		cartBtn.click();
		//find table 
		//WebElement table=driver.findElement(By.xpath("//div[@class='container-fluid cart-info product-list']//table[@class='table table-striped table-bordered']"));
		WebElement table =driver.findElement(By.cssSelector(".product-list table"));
		
		//find rows
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) { // skip header

		    WebElement row = rows.get(i);

		    
		    //XPath باستخدام index   ???????????????????????????????????????????????????????????????
		   WebElement priceCell = row.findElement(By.xpath("./td[4]"));
		    double  priceData = Double.parseDouble( priceCell.getText().replace("$", " "));

		    //System.out.println("price >>> " + priceData);
		    
		    
		    /*
		    
				 الفرق بين / و // في XPath
			✅ /
			
			يعني: ابن مباشر (direct child)
			
			✅ //
			
			يعني: أي عنصر داخل العنصر حتى لو كان متداخل (descendant)
			الاحفاد
					    
		    */
		    WebElement qtyInput = row.findElement(By.xpath("./td[5]//input"));
		    double qtyData = Double.parseDouble(qtyInput.getAttribute("value"));

		    //System.out.println("qty >>> " + qtyData);
		    
		    
		    //Expected Result
		    double expectedTotal=priceData * qtyData;
		    
		    //Actual Result
		    WebElement totalCell = row.findElement(By.xpath("./td[6]"));
		    double  actualData = Double.parseDouble( totalCell.getText().replace("$", " "));
	    
		    
		    //Assertions
		    Assert.assertEquals(actualData, expectedTotal);
		    
		}

		
	}
}







