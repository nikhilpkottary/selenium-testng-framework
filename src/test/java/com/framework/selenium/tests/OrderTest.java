package com.framework.selenium.tests;

import com.framework.selenium.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OrderTest extends BaseTest {
    @Test
    public void placeOrderTest() throws IOException {
        String productName = "ADIDAS ORIGINAL";
        String expectedConfirmMessage = "THANKYOU FOR THE ORDER.";

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
    }

    @Test(dependsOnMethods = {"placeOrderTest"})
    public void verifyOrdersHistory(){
        String productName = "ADIDAS ORIGINAL";

        loginPage.goToLoginPage();
        ShopPage shopPage = loginPage.login("nikhil.p.kottary@gmail.com", "$Sel12345");

        OrderHistoryPage orderHistoryPage=shopPage.navigateToOrdersPage();
        Assert.assertTrue(orderHistoryPage.verifyOrdersDisplay(productName));
    }
}
