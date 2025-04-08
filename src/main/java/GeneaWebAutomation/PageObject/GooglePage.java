package GeneaWebAutomation.PageObject;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
	WebDriver driver;
	WebDriverWait wait;

	public GooglePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@FindBy(xpath = "//textarea[@id='APjFqb']")
	WebElement GoogleSearchBox;

	@FindBy(xpath = "(//h3)[1]")
	WebElement SelectFirstOptionFromFlipkartSearchedList;

	@FindBy(xpath = "(//ul[@role='listbox'])[1]/li")
	List<WebElement> ListBoxForFlipkartlists;

	@FindBy(css = "h3")
	List<WebElement> ListOfWebsiteSearchedLists;

	public void EnterValueInGoogleTextBox(String textContent) {

		GoogleSearchBox.sendKeys(textContent);
		;
	}

	public void PressEnterInGoogleSearchBox() {

		GoogleSearchBox.sendKeys(Keys.ENTER);
	}

	public void TypeFlipkartInGoogleSearchBox(String text) throws InterruptedException {
		for (char c : text.toCharArray()) {
			GoogleSearchBox.sendKeys(String.valueOf(c));
			Thread.sleep(100 + new Random().nextInt(200));
		}
	}

	public void GoTo(String url) {
		driver.get(url);
	};

	public void PrintFlipkartSearchedSuggestionList() {
		System.out.println("\033[1mLIST OF GOOGLE SUGGESTIONS\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(ListBoxForFlipkartlists));
		wait.until(driver -> ListBoxForFlipkartlists.size() > 1);
		for (WebElement suggestion : ListBoxForFlipkartlists) {
			System.out.println(suggestion.getText());
		}
		System.out.println("*****************************************************************************************");
	}

	public void PrintFlipkartSearchedList() {
		System.out.println("\033[1mLIST OF FLIPKART OPTIONS\033[0m");
		wait.until(ExpectedConditions.visibilityOfAllElements(ListOfWebsiteSearchedLists));
		wait.until(driver -> ListOfWebsiteSearchedLists.size() > 1);
		for (WebElement suggestion : ListOfWebsiteSearchedLists) {
			System.out.println(suggestion.getText());
		}
		System.out.println("*****************************************************************************************");
	}

	public Flipkart_HomePage SelectFirstOption() {
		SelectFirstOptionFromFlipkartSearchedList.click();
		Flipkart_HomePage home = new Flipkart_HomePage(driver);
		return home;
	}

}
