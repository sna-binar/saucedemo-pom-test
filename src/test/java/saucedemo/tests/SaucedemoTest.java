package saucedemo.tests;

import org.junit.Assert;
import org.junit.Test;
import saucedemo.pages.*;
import saucedemo.utils.BaseTest;

public class SaucedemoTest extends BaseTest {

    @Test
    public void testSaucedemoLoginAndCheckout() {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillInformation("QA", "Tester", "12345");

        CheckoutPage overviewPage = new CheckoutPage(driver);
        overviewPage.finishOrder();

        // Assertion: Thank You message and button
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String thankYouText = confirmationPage.getThankYouText();
        Assert.assertEquals("Thank you for your order!", thankYouText);

        boolean isBackBtnVisible = confirmationPage.isBackToProductsVisible();
        Assert.assertTrue("Back to Products button is not visible", isBackBtnVisible);
    }
}