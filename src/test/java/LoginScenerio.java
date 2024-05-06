import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class LoginScenerio extends BaseSetup{
    private Logger logger = Logger.getLogger(LoginScenerio.class);
    private WebDriver d;
    private Properties p;

    @BeforeClass
    public static void BeforeClass(){
        System.out.println("BeforeClass in child");
    }

    @Before
    public void setUp(){
        d = getWebDriver();
        p = getObjRepo();
        logger.info("Child class before called");
    }

    @Test
    public void testSuccessfulLogin(){
        logger.info(".... start testSuccessfulLogin ....");
        d.get(p.get("driver.url").toString());
        WebElement uname = d.findElement(By.xpath(p.get("user.name.xpath").toString()));
        uname.sendKeys(p.get("valid.username").toString());
        WebElement upassword = d.findElement(By.xpath(p.get("password.xpath").toString()));
        upassword.sendKeys(p.get("valid.password").toString());
        WebElement loginButton = d.findElement(By.xpath(p.get("login.btn.xpath").toString()));
        loginButton.click();
        logger.info(".... end testSuccessfulLogin ....");
    }
    @Test
    public void testSuccessfulLogin2(){
        logger.info(".... start testSuccessfulLogin2 ....");
        d.get(p.get("driver.url").toString());
        WebElement uname = d.findElement(By.id("user-name"));
        uname.sendKeys(p.get("valid.username").toString());
        WebElement upassword = d.findElement(By.name("password"));
        upassword.sendKeys(p.get("valid.password").toString());
        WebElement loginButton = d.findElement(By.xpath(p.get("login.btn.xpath").toString()));
        loginButton.click();
        logger.info(".... end testSuccessfulLogin2 ....");
    }
}
