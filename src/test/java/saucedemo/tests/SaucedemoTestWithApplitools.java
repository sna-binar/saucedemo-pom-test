package saucedemo.tests;

import com.applitools.eyes.selenium.Eyes;
import org.junit.Assert;
import org.junit.Test;
import saucedemo.pages.*;
import saucedemo.utils.BaseTest;
import saucedemo.utils.EyesManager;

public class SaucedemoTestWithApplitools extends BaseTest {

    @Test
    public void testSaucedemoCheckoutWithApplitools() {
        EyesManager eyesManager = new EyesManager();
        Eyes eyes = null;

        try {
            driver.get("https://www.saucedemo.com/");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");

            ProductsPage inventoryPage = new ProductsPage(driver);
            inventoryPage.addItemToCart();
            inventoryPage.goToCart();

            CartPage cartPage = new CartPage(driver);
            cartPage.proceedToCheckout();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.fillInformation("QA", "Tester", "12345");
            checkoutPage.finishOrder();

            ConfirmationPage completePage = new ConfirmationPage(driver);

            // Integrasi visual testing Applitools
            eyes = eyesManager.getEyes(driver, "Visual Check - Confirmation Page");
            completePage.visualCheckThankYouPage(eyes);
            eyesManager.closeEyes();

            // Assertion biasa
            String thankYouText = completePage.getThankYouText();
            Assert.assertEquals("Thank you for your order!", thankYouText);

            boolean isBackBtnVisible = completePage.isBackToProductsVisible();
            Assert.assertTrue("Back to Products button is not visible", isBackBtnVisible);

        } finally {
            if (eyes != null) {
                eyesManager.abortEyesIfNotClosed();
            }
        }
    }
}
