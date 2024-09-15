package com.framework.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest{
    @Test
    public void testIncorrectLoginCredentialsErrors(){
        loginPage.login("shoppinguser@gmail.com","$Test12");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),"Incorrect email or password.");
    }

}
