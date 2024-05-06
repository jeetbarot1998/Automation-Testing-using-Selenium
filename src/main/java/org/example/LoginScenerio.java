package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class LoginScenerio {
    public static void main(String[] args) throws IOException {
        new LoginScenerio().CheckWebDriver();
    }

    @Test
    public void CheckWebDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver d = new ChromeDriver();
        d.get("https://www.saucedemo.com/");
        WebElement uname = d.findElement(By.xpath("//*[@id=\"user-name\"]"));
        uname.sendKeys("standard_user");
        WebElement upassword = d.findElement(By.xpath("//*[@id=\"password\"]"));
        upassword.sendKeys("secret_sauce");
        WebElement loginButton = d.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
        WebElement elem = d.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        System.out.println(elem.isDisplayed());
//        Assert.assertTrue(elem.isDisplayed());
        Assert.assertTrue(elem.getText().equalsIgnoreCase("Products"));
        d.quit();
    }
}
