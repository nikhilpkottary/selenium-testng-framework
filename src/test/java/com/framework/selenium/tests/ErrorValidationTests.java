package com.framework.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest{
    @Test
    public void testIncorrectLoginCredentialsErrors(){
        loginPage.login("nikhil.p.kottary@gmail.com","$Sel1234");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),"Incorrect email or password.");
    }

}
