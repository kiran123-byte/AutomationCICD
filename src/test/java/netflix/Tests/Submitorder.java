package netflix.Tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import netflix.TestComponents.BaseTest;
import netflix.pageobjects.CartPage;
import netflix.pageobjects.CheckOut;
import netflix.pageobjects.ConfirmationPage;
import netflix.pageobjects.LandingPage;
import netflix.pageobjects.OrderPage;
import netflix.pageobjects.ProductCatalouge;


public class Submitorder extends BaseTest{
	String productName="ADIDAS ORIGINAL";
	

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException {
		
		
		
		
		ProductCatalouge productCatalouge=landingPage.loginApp(input.get("email"),input.get("password"));
	
		List<WebElement>products=productCatalouge.getProductsList();
		productCatalouge.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalouge.goToCartPage();
		
	   
	    Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
	    Assert.assertTrue(match);
        CheckOut checkOut=cartPage.goToCheckout();	
        checkOut.selectCountry("Ind"); 
        ConfirmationPage confirmationPage=checkOut.goToConfirmationPage();
        
	    String Expected=confirmationPage.cofirmMessage();
	    
	 Assert.assertTrue(Expected.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}
	

	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() 
	{
		ProductCatalouge productCatalouge=landingPage.loginApp("example007@gmail.com", "Example@123");
    	OrderPage orderPage=productCatalouge.VerifyCartPage();
		Assert.assertTrue(orderPage.CartPageElements(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("email","example007@gmail.com");
		//map.put("password", "Example@123");
		//map.put("productName","ADIDAS ORIGINAL");
		
		//HashMap<String,String> map1=new HashMap<String,String>();
		//map1.put("email","example010@gmail.com");
		//map1.put("password", "Example@1234");
		//map1.put("productName","ZARA COAT 3");
		 
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//java//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	//@DataProvider
	//public Object[][] getData() 
	
	//{
		
		//return new Object[][] {{"example007@gmail.com", "Example@123","ADIDAS ORIGINAL"},{"example010@gmail.com","Example@1234","ZARA COAT 3"}};
	//}
	
}
