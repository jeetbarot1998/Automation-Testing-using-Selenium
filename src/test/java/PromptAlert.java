import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.Properties;

public class PromptAlert extends BaseSetup{

    private Logger logger = Logger.getLogger(TestShadowDom.class);
    private WebDriver d;
    private Properties p;

    @BeforeClass
    public static void BeforeClass() {
        System.out.println("BeforeClass in child");
    }

    @Before
    public void setUp() {
        if (d == null) {
            super.init();
        }
        d = getWebDriver();
        p = getObjRepo();
        logger.info("Child class before called");
    }

    @Test
    public void testPopUp(){
        d.get("https://demoqa.com/alerts");
        d.manage().window().maximize();
        // This step will result in an alert on screen
        d.findElement(By.id("alertButton")).click();
        Alert simpleAlert = d.switchTo().alert();
        System.out.println(simpleAlert.getText());
//        Assert.assertEquals("You clicked a button", simpleAlert.getText());
        simpleAlert.accept();
    }

    @SneakyThrows
    @Test
    public void testPopUpWithArgument(){
        d.get("https://demoqa.com/alerts");
        d.manage().window().maximize();
        // This step will result in an alert on screen
        WebElement element = d.findElement(By.id("promtButton"));
        ((JavascriptExecutor) d).executeScript("arguments[0].click()", element);
        Alert promptAlert  = d.switchTo().alert();
        String alertText = promptAlert.getText();
        System.out.println("Alert text is ''''" + alertText + "'''");
        //Send some text to the alert
        Assert.assertEquals("Please enter your name", alertText);
        Thread.sleep(6000);
        promptAlert.sendKeys("Test User");
        promptAlert.accept();
    }

    @Test
    public void RediffNoUserNameAndPassword(){
        d.get("https://mail.rediff.com/cgi-bin/login.cgi");
        d.findElement(By.xpath("//input[@name='proceed']")).click();
        Alert simpleAlert = d.switchTo().alert();
        System.out.println(simpleAlert.getText());
        Assert.assertEquals("Please enter a valid user name",simpleAlert.getText());
        simpleAlert.dismiss();
    }

    @Test
    public void RediffNoPassword(){
        d.get("https://mail.rediff.com/cgi-bin/login.cgi");
        d.findElement(By.xpath("//input[@id='login1']")).sendKeys("ABC");
        d.findElement(By.xpath("//input[@name='proceed']")).click();
        Alert simpleAlert = d.switchTo().alert();
        System.out.println(simpleAlert.getText());
        Assert.assertEquals("Please enter your password",simpleAlert.getText());
        simpleAlert.dismiss();
    }

    @Test
    public void RediffNoUserName(){
        d.get("https://mail.rediff.com/cgi-bin/login.cgi");
        d.findElement(By.xpath("//input[@id='password']")).sendKeys("ABC");
        d.findElement(By.xpath("//input[@name='proceed']")).click();
        Alert simpleAlert = d.switchTo().alert();
        System.out.println(simpleAlert.getText());
        Assert.assertEquals("Please enter a valid user name",simpleAlert.getText());
        simpleAlert.dismiss();
    }

    @SneakyThrows
    @Test
    public void IciciBand(){
        d.get("https://www.icicibank.com/digital-banking/retail-digital-banking?ITM=nli_cms_lB_digital_banking_topnavigation");
        d.findElement(By.xpath("//a[contains(text(),'login now')]")).click();
        System.out.println(d.getCurrentUrl());
        Thread.sleep(1000);
        d.findElement(By.xpath("//input[@id='DUMMY1']")).click();
        d.findElement(By.xpath("//input[@id='VALIDATE_CREDENTIALS1']")).click();
        Alert simpleAlert = d.switchTo().alert();
        System.out.println(simpleAlert.getText());
        Assert.assertEquals("Please enter your User ID",simpleAlert.getText());
    }

    @Test
    public void testBasicAuthenticationWithCredentials(){
//        d.get("https://the-internet.herokuapp.com/basic_auth");
        d.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals("Congratulations! You must have the proper credentials.",
                d.findElement(By.tagName("p")).getText());

    }

}

// Alerts, pass params to alerts, modal