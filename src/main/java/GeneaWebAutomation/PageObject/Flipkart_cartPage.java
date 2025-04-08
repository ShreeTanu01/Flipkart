package GeneaWebAutomation.PageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart_cartPage {

	WebDriver driver;
	WebDriverWait wait;

	public Flipkart_cartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//button[text()='Enter Delivery Pincode']")
	WebElement EnterDeliveryPincodeBtn;

	@FindBy(xpath = "//div[@class='hEkdHX']")
	WebElement PrintPin;

	@FindBy(xpath = "//input[@placeholder='Enter pincode']")
	WebElement pincodeInput;

	@FindBy(xpath = "//div[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//div[@class='eGXlor pk3Guc']/div/child::div[@class=\"x9LoV+\"]/child::div[@class=\"Yxlaw0\"]")
	List<WebElement> outofStock;

	@FindBy(xpath = "//button[@class='KlGwJl']")
	WebElement changePin;

	@FindBy(xpath = "//div[@class='eGXlor pk3Guc']/div/child::div[@class=\"x9LoV+\"]/div/a")
	List<WebElement> productname;

	@FindBy(xpath = "//div[@class='eGXlor pk3Guc']/child::div[@class=\"_8X-K8p\"]")
	List<WebElement> rows;

	@FindBy(xpath = "//div[@class='eGXlor pk3Guc']/div/child::div[@class=\"p04umj\"]/ul/li/div")
	List<WebElement> delivery;

	public void EnterPinCode(String Pincode) {

		pincodeInput.sendKeys(Pincode);
	}

	public void ClickSubmitButton() {

		submitButton.click();
	}

	public void ClickDeliveryPincodeButton() {
		try {
			wait.until(ExpectedConditions.visibilityOf(EnterDeliveryPincodeBtn));
			wait.until(ExpectedConditions.elementToBeClickable(EnterDeliveryPincodeBtn));
			EnterDeliveryPincodeBtn.click();
		} catch (StaleElementReferenceException e) {
			System.err.println("Stale element reference, retrying for click ");
			wait.until(ExpectedConditions.visibilityOf(EnterDeliveryPincodeBtn));
			EnterDeliveryPincodeBtn.click();
		}
	}

	public void ChangePin(String Newpin) {

		WebElement changeButton = changePin;

		wait.until(ExpectedConditions.elementToBeClickable(changeButton));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", changeButton);

		try {

			changeButton.click();
		} catch (ElementClickInterceptedException e) {
			System.err.println("Click intercept error, trying JavaScript click: " + e.getMessage());

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", changeButton);
		}

		pincodeInput.clear();
		pincodeInput.sendKeys(Newpin);
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(PrintPin));
		wait.until(ExpectedConditions.textToBePresentInElement(PrintPin, Newpin));

	}

	public void CheckStatus() {
		System.out.println("*****************************************************************************************");
		System.out.println("\033[1mAVAILABILITY STATUS OF PRODUCT ADDED\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(productname));
		wait.until(driver -> productname.size() > 1);
		for (int i = 0; i < productname.size(); i++) {
			try {
				WebElement productElement = productname.get(i);
				wait.until(ExpectedConditions.visibilityOf(productElement));
				String product = productElement.getText();
				System.out.println("Product " + (i) + " : " + product);

				if (i < outofStock.size() && outofStock.get(i).isDisplayed()) {
					wait.until(ExpectedConditions.visibilityOf(PrintPin));
					System.out.println("Status: " + outofStock.get(i).getText());
				} else {
					System.out.println("Status: Available");

					if (i < delivery.size()) {
						System.out.println("Delivery Info: " + delivery.get(i).getText());
					} else {
						System.out.println("Delivery Info: Not Available");
					}
				}

			} catch (StaleElementReferenceException e) {
				System.err.println("Stale element reference, retrying for product " + i);
				WebElement productElement = productname.get(i);
				wait.until(ExpectedConditions.visibilityOf(productElement));
				String product = productElement.getText();
				System.out.println("Product " + (i) + " : " + product);

				if (i < outofStock.size() && outofStock.get(i).isDisplayed()) {
					System.out.println("Status: " + outofStock.get(i).getText());
				} else {
					System.out.println("Status: Available");

					if (i < delivery.size()) {
						System.out.println("Delivery Info: " + delivery.get(i).getText());
					} else {
						System.out.println("Delivery Info: Not Available");
					}
				}

			} catch (ElementClickInterceptedException e) {
				System.err.println("Click intercept error for product " + i + ": " + e.getMessage());
				WebElement changeButton = changePin;
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", changeButton);
			} catch (Exception e) {
				System.err.println("Error processing product: " + e.getMessage());
			}

			System.out.println(
					"-----------------------------------------------------------------------------------------");
		}
	}

}
