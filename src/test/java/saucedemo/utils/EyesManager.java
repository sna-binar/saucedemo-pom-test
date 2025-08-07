package saucedemo.utils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;

public class EyesManager {
    private Eyes eyes;

    public EyesManager() {
        eyes = new Eyes();
        eyes.setApiKey("jzKg2Ck81vWPcWzFwWO6jA097kZnj6n7AkQJgLXLVjM110");  // Ganti dengan API key Applitools kamu
        eyes.setBatch(new BatchInfo("SauceDemo Visual Test with POM"));
    }

    public Eyes getEyes(WebDriver driver, String testName) {
        eyes.open(driver, "SauceDemo App", testName);
        return eyes;
    }

    public void closeEyes() {
        eyes.closeAsync();
    }

    public void abortEyesIfNotClosed() {
        eyes.abortIfNotClosed();
    }
}
