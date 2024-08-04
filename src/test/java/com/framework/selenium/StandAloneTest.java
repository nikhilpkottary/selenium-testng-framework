package com.framework.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        String productName="ADIDAS ORIGINAL";
        String expectedConfirmMessage="THANKYOU FOR THE ORDER.";

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("nikhil.p.kottary@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("$Sel12345");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
        List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
        WebElement selectedProduct=products.stream().filter(product->product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
        selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean isProductInCartExists=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));

        Assert.assertTrue(isProductInCartExists);

        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

        Actions actions=new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmMessage);
        Assert.assertEquals(confirmMessage,expectedConfirmMessage);

        Thread.sleep(500);
        driver.quit();



    }
}
