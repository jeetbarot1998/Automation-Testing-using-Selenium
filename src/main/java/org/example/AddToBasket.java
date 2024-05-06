package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AddToBasket {
    private static WebDriver d = null;
    private static Properties p = new Properties();

    @BeforeClass
    public static void beforeClass() throws IOException {
        System.out.println("When starting the class");
        FileInputStream fis = new FileInputStream(new File("selenium.properties"));
        p.load(fis);
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
    }

    @Before
    public void Setup(){
//        Every time before a test gets executed.
        System.out.println(d);
    }

    @Test
    public void addToBasked(){
        d.get(p.get("driver.url").toString());
        WebElement uname = d.findElement(By.xpath(p.get("user.name.xpath").toString()));
        uname.sendKeys(p.get("valid.username").toString());
        WebElement upassword = d.findElement(By.xpath(p.get("password.xpath").toString()));
        upassword.sendKeys(p.get("valid.password").toString());
        WebElement loginButton = d.findElement(By.xpath(p.get("login.btn.xpath").toString()));
        loginButton.click();
        WebElement addToCartBtn1 = d.findElement(By.xpath(p.get("addToCart1.btn.xpath").toString()));
        addToCartBtn1.click();
        WebElement addToCartBtn2 = d.findElement(By.xpath(p.get("addToCart2.btn.xpath").toString()));
        addToCartBtn2.click();
        WebElement cartCount = d.findElement(By.xpath(p.get("cartCount.xpath").toString()));
        WebElement removeFromCartBtn1 = d.findElement(By.xpath(p.get("removeFromCart1.btn.xpath").toString()));
        removeFromCartBtn1.click();
        Assert.assertEquals("1",cartCount.getText());
    }

    @After
    public void cleanUp(){
//        Every time after a `test` gets executed.
        System.out.println("Clean up in @After");
    }

    @AfterClass
    public static void AfterClass(){
        System.out.println("When closing the class");
        d.quit();
    }

}
