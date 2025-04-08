package GeneaWebAutomation.PageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart_WindowACPage {
	WebDriver driver;
	WebDriverWait wait;

	public Flipkart_WindowACPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//h1[contains(text(),'Window Air Conditioners')]/parent::div/parent::div/parent::div/following-sibling::div/div/div/div/a/div/child::div[@class=\"qaR90o\"]/child::div[@class=\"A8uQAd\"]/child::span[@class=\"Lni97G\"]")
	List<WebElement> comparisonBlocks;

	@FindBy(xpath = "//div[contains(text(),'You have already selected 4 products')]")
	WebElement selectionLimitMessage;

	@FindBy(xpath = "//span[contains(text(),'COMPARE')]")
	WebElement compareButton;

	@FindBy(xpath = "//a[@class='CGtC98']/child::div[@class=\"yKfJKb row\"]/div/child::div[@class=\"KzDlHZ\"]")
	List<WebElement> WindowsACList;

	@FindBy(xpath = "//div[contains(text(),'You have already selected 4 products')]")
	WebElement ErrorMessage;

	public void ClickCompareCheckbox(int index) {
		try {
			WebElement block = comparisonBlocks.get(index);
			block.click();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid index provided: " + index);
		} catch (Exception e) {
			System.out.println("Error clicking checkbox at index " + index + ": " + e.getMessage());
		}
	}

	public void PrintLimitMessage() {
		try {
			WebDriverWait waitForpopup = new WebDriverWait(driver, Duration.ofSeconds(1));
			waitForpopup.until(ExpectedConditions.visibilityOf(ErrorMessage));
			System.out.println("Popup Message: " + ErrorMessage.getText());
			wait.until(ExpectedConditions.invisibilityOf(ErrorMessage));

		} catch (Exception e) {
			System.out.println("Popup did not appear or was missed.");
		}
		System.out.println("*****************************************************************************************");
	}

	public Flipkart_ACComaprePage ClickOnCompareButton() {
		compareButton.click();
		Flipkart_ACComaprePage comparepage = new Flipkart_ACComaprePage(driver);
		return comparepage;
	}

	public void PrintListOfWndowsAC() {
		System.out.println("\033[1mLIST OF WINDOWS AC\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(WindowsACList));
		wait.until(driver -> WindowsACList.size() > 1);
		for (WebElement list : WindowsACList) {
			System.out.println(list.getText());
		}
		System.out.println("*****************************************************************************************");
	}

}
