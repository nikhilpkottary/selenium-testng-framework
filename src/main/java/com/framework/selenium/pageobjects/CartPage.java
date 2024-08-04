package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    By cartProductsLocator = By.cssSelector(".cartSection h3");
    By checkoutBtn = By.xpath("//button[contains(text(),'Checkout')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCartProducts() {
        return waitForVisibilityOfElements(cartProductsLocator);
    }

    public boolean isProductExistsInCart(String productName) {
        List<WebElement> cartProducts = getCartProducts();
        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
    }

    public CheckoutPage checkout() {
        clickElement(checkoutBtn);
        return new CheckoutPage(driver);
    }
}
