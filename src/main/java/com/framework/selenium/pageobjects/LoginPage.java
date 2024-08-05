package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By emailTxt = By.id("userEmail");
    By passwordTxt = By.id("userPassword");
    By loginBtn = By.id("login");
    By incorrectLoginErrorLocator=By.cssSelector("#toast-container");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ShopPage login(String email, String password) {
        sendKeysToElement(emailTxt, email);
        sendKeysToElement(passwordTxt, password);
        clickElement(loginBtn);
        return new ShopPage(driver);
    }

    public String getLoginErrorMessage(){
        waitForVisibilityOfElement(incorrectLoginErrorLocator);
        return getText(incorrectLoginErrorLocator);
    }


}
