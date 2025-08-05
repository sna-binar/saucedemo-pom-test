package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    WebDriver driver;
    By thankYouMessage = By.cssSelector("h2.complete-header");
    By backBtn = By.id("back-to-products");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getThankYouText() {
        return driver.findElement(thankYouMessage).getText();
    }

    public boolean isBackToProductsVisible() {
        return driver.findElement(backBtn).isDisplayed();
    }
}
