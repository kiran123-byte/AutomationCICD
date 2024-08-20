package netflix.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import netflix.AbstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {
	
WebDriver driver;
	
	public ProductCatalouge(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);	
		
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(css=".card-body")
	List<WebElement>products;
	
	@FindBy(css=".ng-animating")
	WebElement spiner;
	
	By productsBy= By.cssSelector(".card-body");
	By addToCart=By.cssSelector("button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod=getProductsList().stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod=getProductByName(productName);
			
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spiner);
		
		
	}
	
}
