package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage extends BasePage {

    By selectCountryTxt = By.cssSelector("input[placeholder='Select Country']");
    By selectCountryOptionLocator = By.cssSelector(".ta-item:nth-of-type(2)");
    By placeOrderBtn = By.cssSelector(".action__submit");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectCountry(String country) {
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(selectCountryTxt), country).build().perform();
        clickElement(selectCountryOptionLocator);
    }

    public ConfirmationPage placeOrder() {
        clickElement(placeOrderBtn);
        return new ConfirmationPage(driver);
    }

}
