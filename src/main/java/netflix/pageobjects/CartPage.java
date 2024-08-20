package netflix.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import netflix.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".subtotal button")
	WebElement ele;
	
	public Boolean VerifyProductDisplay(String productName){
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
	public CheckOut goToCheckout() {
		
	   ele.click();
	   CheckOut checkOut=new CheckOut(driver);
	    return checkOut;
	}
	
	
	//List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	//Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));

}
