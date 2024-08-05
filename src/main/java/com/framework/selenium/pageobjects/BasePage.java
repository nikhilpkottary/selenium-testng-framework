package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    By cartHeader = By.xpath("//button[@routerlink='/dashboard/cart']");
    By ordersHeader = By.xpath("//button[@routerlink='/dashboard/myorders']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public void sendKeysToElement(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public List<WebElement> waitForVisibilityOfElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForVisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForInvisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public CartPage navigateToCart() {
        clickElement(cartHeader);
        return new CartPage(driver);
    }

    public OrderHistoryPage navigateToOrdersPage(){
        clickElement(ordersHeader);
        return new OrderHistoryPage(driver);
    }
}
