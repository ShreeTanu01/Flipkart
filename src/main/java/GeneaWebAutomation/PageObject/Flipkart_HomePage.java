package GeneaWebAutomation.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart_HomePage {
	WebDriver driver;
	WebDriverWait wait;

	public Flipkart_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@FindBy(xpath = "(//span[contains(text(),'Mobiles')])[1]")
	// @FindBy(xpath = "(//span[contains(text(),'Appliances')])[1]")
	WebElement AppliancesOption;

	@FindBy(css = ".bpjkJb span:nth-of-type(2)")
	WebElement appliancesMenu;

	@FindBy(xpath = "//a[contains(text(),'Window ACs')]")
	WebElement WindowsACOption;

	@FindBy(xpath = "//h1[contains(text(),'Window Air Conditioners')]")
	WebElement WindowsACTextCheck;

	@FindBy(xpath = "//p[contains(text(),'Get access to your Orders, Wishlist and Recommendations')]")
	WebElement PopupText;

	@FindBy(xpath = "//span[@class='_30XB9F']")
	WebElement CloseX;

	public void HoverAppliancesSelectWindowAC() {
		AppliancesOption.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(appliancesMenu).perform();
		WindowsACOption.click();
	}

	public String ValidateIfLandedInWindowsAC() {
		return WindowsACTextCheck.getText();
	}

	public Flipkart_WindowACPage FlipkartWindowACPage() {
		Flipkart_WindowACPage home = new Flipkart_WindowACPage(driver);
		return home;
	}

	public void CheckLoginPopup() {
		try {
			wait.until(ExpectedConditions.visibilityOf(PopupText));
			wait.until(ExpectedConditions.elementToBeClickable(CloseX)).click();
			System.out.println("Login popup present.Closed Flipkart login popup.");
			System.out.println(
					"*****************************************************************************************");
		} catch (Exception e) {
			System.out.println("Login popup not present.");
			System.out.println(
					"*****************************************************************************************");
		}
	}

}
