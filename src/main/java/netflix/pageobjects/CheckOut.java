package netflix.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import netflix.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent {
	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css="input[placeholder='Select Country']")
	WebElement inputCountry;
	
	@FindBy(css=".ta-results.list-group.ng-star-inserted button")
	List<WebElement> selectCountry;
	
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	By countryDisplay=By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String Countryname) {
		
	    inputCountry.sendKeys(Countryname);
	    waitForElementToAppear(countryDisplay);
		
		List<WebElement>options=selectCountry;
		
		for(WebElement option:options) {
			if(option.getText().equalsIgnoreCase("India"))
			{
				option.click();
				break;
			}
	}
		
		}
	
	public ConfirmationPage goToConfirmationPage() {
		submit.click();
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
}
}	
	

