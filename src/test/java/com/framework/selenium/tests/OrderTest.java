package com.framework.selenium.tests;

import com.framework.selenium.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class OrderTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String productName = "ADIDAS ORIGINAL";
        String expectedConfirmMessage = "THANKYOU FOR THE ORDER.";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        ShopPage shopPage = loginPage.login("nikhil.p.kottary@gmail.com", "$Sel12345");

        shopPage.addToCart(productName);
        CartPage cartPage = shopPage.navigateToCart();

        Boolean isProductInCartExists = cartPage.isProductExistsInCart(productName);
        Assert.assertTrue(isProductInCartExists);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmMessage, expectedConfirmMessage);

        driver.close();
    }
}
