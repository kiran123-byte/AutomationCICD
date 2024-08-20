package netflix.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import netflix.TestComponents.BaseTest;
import netflix.pageobjects.CartPage;
import netflix.pageobjects.CheckOut;
import netflix.pageobjects.ConfirmationPage;
import netflix.pageobjects.ProductCatalouge;

public class ErrorValidations extends BaseTest {
	
	
		@Test(groups={"ErrorHandling"})
		public void LoginErrorValidation() throws IOException {
			
			String productName="ADIDAS ORIGINAL";
			
			
			
			landingPage.loginApp("example007@gmail.com", "hxample@123");
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
			
		
			

	}

		
		@Test 
		public void ProductErrorValidation() throws IOException {
			
			String productName="ADIDAS ORIGINAL";
			
			
			
			ProductCatalouge productCatalouge=landingPage.loginApp("example007@gmail.com", "Example@123");
		
			List<WebElement>products=productCatalouge.getProductsList();
			productCatalouge.addProductToCart(productName);
			CartPage cartPage=productCatalouge.goToCartPage();
			
		   
		    Boolean match=cartPage.VerifyProductDisplay("ADIDAS ORIGINAL");
		    Assert.assertTrue(match);
	        
	}


}
