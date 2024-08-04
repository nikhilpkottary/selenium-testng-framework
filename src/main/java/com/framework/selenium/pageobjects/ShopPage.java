package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopPage extends BasePage {

    By productCardLocator = By.cssSelector(".col-lg-4");
    By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
    By addToCartMsgLocator = By.cssSelector("#toast-container");
    By addToCartAnimationLocator = By.cssSelector(".ng-animating");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String productName) {
        List<WebElement> products = waitForVisibilityOfElements(productCardLocator);
        WebElement selectedProduct = products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
        selectedProduct.findElement(addToCartBtn).click();
        waitForVisibilityOfElement(addToCartMsgLocator);
        //waitForInvisibilityOfElement(addToCartAnimationLocator);
    }
}
