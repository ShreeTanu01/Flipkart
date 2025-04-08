package GeneaWebAutomation.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import GeneaWebAutomation.PageObject.Flipkart_ACComaprePage;
import GeneaWebAutomation.PageObject.Flipkart_HomePage;
import GeneaWebAutomation.PageObject.Flipkart_WindowACPage;
import GeneaWebAutomation.PageObject.Flipkart_cartPage;
import GeneaWebAutomation.PageObject.GooglePage;
import GeneaWebAutomation.TestComponents.BaseTest;

public class GeneaAssignment extends BaseTest {

	@Test
	public void GeneaFlipkartAssignment() throws IOException, InterruptedException {

		GooglePage googlePage = new GooglePage(driver);
		String FLIPKART = "Flipkart";
		String WINDOWACTEXT = "Window Air Conditioners";
		String PINCODE = "380058";
		String NEWPINCODE = "400051";

		/*
		 * Launch browser > Open Google.com > Type 'Flipkart' > Print all the
		 * suggestions displayed in the google search field before user hits Enter > Hit
		 * Enter > Print all the search results displayed in the console.
		 * 
		 */
		googlePage.TypeFlipkartInGoogleSearchBox(FLIPKART);
		googlePage.PrintFlipkartSearchedSuggestionList();
		googlePage.PressEnterInGoogleSearchBox();
		googlePage.PrintFlipkartSearchedList();

		/*
		 * Click on the first link > Flipkart website should get opened up > Close the
		 * login popup (If available)
		 */
		Flipkart_HomePage flipkartHomePage = googlePage.SelectFirstOption();
		flipkartHomePage.CheckLoginPopup();

		/*
		 * Click on 'Appliances' > Hover over 'TV & Appliances' > Click on 'Window ACs'
		 * and ensure window ACs list is displayed.
		 */
		flipkartHomePage.HoverAppliancesSelectWindowAC();
		Assert.assertEquals(flipkartHomePage.ValidateIfLandedInWindowsAC(), WINDOWACTEXT);
		Flipkart_WindowACPage windowACPage = flipkartHomePage.FlipkartWindowACPage();
		windowACPage.PrintListOfWndowsAC();

		/*
		 * Click on 'Add to Compare' checkbox for the 2nd, 5th, 7th, 8th, 9th products
		 * displayed > Print the message displayed on addition of the 9th product.
		 */
		windowACPage.ClickCompareCheckbox(2);
		windowACPage.ClickCompareCheckbox(5);
		windowACPage.ClickCompareCheckbox(7);
		windowACPage.ClickCompareCheckbox(8);
		windowACPage.ClickCompareCheckbox(9);
		windowACPage.PrintLimitMessage();

		/*
		 * Click on Compare
		 */
		Flipkart_ACComaprePage acComparepage = windowACPage.ClickOnCompareButton();

		/*
		 * Print Name and Price of all products in console
		 */
		acComparepage.printNamePriceOfAllProductInComapreList();

		/*
		 * Add all 4 products to cart one after the other.
		 */
		Flipkart_cartPage cartPage = acComparepage.clickAvailableAddToCartButtons();

		/*
		 * Go to the cart and add your area Pincode and check the availability of the
		 * product in the console if not available change the pincode > Print the
		 * message displayed for the availability/delivery of the product in the
		 * console.
		 */
		cartPage.ClickDeliveryPincodeButton();
		cartPage.EnterPinCode(PINCODE);
		cartPage.ClickSubmitButton();
		cartPage.CheckStatus();
		cartPage.ChangePin(NEWPINCODE);
		cartPage.CheckStatus();
		
	}
}
