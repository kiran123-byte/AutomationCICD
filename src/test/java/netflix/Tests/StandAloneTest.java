package netflix.Tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import netflix.pageobjects.LandingPage;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("example007@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Example@123");
		driver.findElement(By.id("login")).click();
	List<WebElement>products=driver.findElements(By.cssSelector(".card-body"));
		
	WebElement prod=products.stream().filter(product->
	product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
	
	prod.findElement(By.cssSelector("button:last-of-type")).click();
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	driver.findElement(By.cssSelector("[routerLink*='cart']")).click();
	
	List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".subtotal button")).click();
	
	driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Pol");
	Thread.sleep(3000);
	
	List<WebElement>options=driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted button"));
	
	for(WebElement option:options) {
		if(option.getText().equalsIgnoreCase("Poland"))
		{
			option.click();
			break;
		}
	}
	driver.findElement(By.cssSelector(".btnn")).click();
   String Expected=driver.findElement(By.cssSelector(".hero-primary")).getText();
   Assert.assertTrue(Expected.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
}

}
