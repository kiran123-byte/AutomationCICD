package netflix.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import netflix.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement>CartElements;
	
	
public Boolean CartPageElements(String productName){
		
		Boolean match=CartElements.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		return match;
	}

}
