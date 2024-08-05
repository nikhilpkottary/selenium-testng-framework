package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderHistoryPage extends BasePage {

    By ordersProductNameLocator = By.cssSelector(".ng-star-inserted td:nth-of-type(2)");

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyOrdersDisplay(String productName){
        List<WebElement> productNameList=waitForVisibilityOfElements(ordersProductNameLocator);
        boolean isProductExistsInMyOrder=productNameList.stream().anyMatch(product->product.getText().equals(productName));
        return isProductExistsInMyOrder;
    }
}
