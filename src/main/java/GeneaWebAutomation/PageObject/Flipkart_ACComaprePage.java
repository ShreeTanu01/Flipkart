package GeneaWebAutomation.PageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart_ACComaprePage {

	WebDriver driver;
	WebDriverWait wait;

	public Flipkart_ACComaprePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@FindBy(xpath = "//div[@class='col col-1-5 _0sxO1q']/a")
	List<WebElement> productName;

	@FindBy(xpath = "(((//div[@class='col col-1-5 _0sxO1q'])/a/parent::div/parent::div/following-sibling::div)[1]/div/div/div)/div[1]")
	List<WebElement> productPrice;

	@FindBy(xpath = "//button[normalize-space(text())='Add to cart']")
	List<WebElement> addToCartButtons;

	public void printNamePriceOfAllProductInComapreList() {
		System.out.println("\033[1mLIST OF PRODUCT NAME AND PRICE\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(productName));
		wait.until(driver -> productName.size() > 0 && productPrice.size() > 0);

		for (int i = 0; i < productName.size() && i < productPrice.size(); i++) {
			System.out.println("Product " + (i + 1) + ": " + productName.get(i).getText() + " | Price: "
					+ productPrice.get(i).getText());
		}

		System.out.println("*****************************************************************************************");
	}

	public Flipkart_cartPage clickAvailableAddToCartButtons() {
		System.out.println("\033[1mLIST OF PRODUCT ADDED TO CART\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(addToCartButtons));
		int index = 1;

		for (WebElement button : addToCartButtons) {
			if (button.isEnabled()) {
				System.out.println("Add to Cart " + index + " is enabled. Clicked and Added To cart.");
				button.click();
			} else {
				System.err.println("Add to Cart " + index + " is disabled. Cannot Add to Cart");
			}
			index++;
		}
		Flipkart_cartPage cartPage = new Flipkart_cartPage(driver);
		return cartPage;
	}

}
