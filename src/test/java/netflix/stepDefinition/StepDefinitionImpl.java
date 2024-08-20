package netflix.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import netflix.TestComponents.BaseTest;
import netflix.pageobjects.CartPage;
import netflix.pageobjects.CheckOut;
import netflix.pageobjects.ConfirmationPage;
import netflix.pageobjects.LandingPage;
import netflix.pageobjects.ProductCatalouge;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	
	@Given("^Logged in with username(.+) and password (.+)$")
	
	  public void logged_in_username_and_password(String username,String password)
	  {
		productCatalouge=landingPage.loginApp(username,password);
	  }
	
	@When("^I add product(.+) to Cart$")
	
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement>products=productCatalouge.getProductsList();
		productCatalouge.addProductToCart(productName);
		
	}
	
	 @When("^Checkout (.+) and submit the order$")
	 
	 public void Checkout_and_submit_the_order(String productName)
	 {
		 CartPage cartPage=productCatalouge.goToCartPage();
			
		   
		    Boolean match=cartPage.VerifyProductDisplay(productName);
		    Assert.assertTrue(match);
	        CheckOut checkOut=cartPage.goToCheckout();	
	        checkOut.selectCountry("Ind"); 
	        confirmationPage=checkOut.goToConfirmationPage();
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {

		    String Expected=confirmationPage.cofirmMessage();
		    
		 Assert.assertTrue(Expected.equalsIgnoreCase(string));
	 }

}
