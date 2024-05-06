import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.util.Properties;

public class JavaScriptExecutorExample extends BaseSetup {
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
    public void testUsingJsExec(){
        d.get("https://www.facebook.com/");
        JavascriptExecutor js = (JavascriptExecutor) d;
        js.executeScript("document.getElementById('email').value='abc@gmail.com'");
    }

    @SneakyThrows
    @Test
    public void googleTest(){
        d.get("https://www.google.com/");
        JavascriptExecutor js = (JavascriptExecutor) d;
        js.executeScript("document.querySelector(\"#APjFqb\").value = \"ABC\"\n");
        WebElement elem = (WebElement) js.executeScript("return document.querySelector(\"body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.FPdoLc.lJ9FBc > center > input.gNO89b\")");
        elem.click();
        Thread.sleep(1000);
        WebElement el = (WebElement) js.executeScript("return document.querySelector(\"#APjFqb\")");
        System.out.println(el.getText());
        Assert.assertEquals(el.getText(),"ABC");
    }

    public static void flash(WebDriver driver, WebElement elem ){
        String bgColor = elem.getCssValue("backgroundColor");
        System.out.println(bgColor);
        for (int i = 0; i<1000; i++){
            changeColor(bgColor,driver,elem);
            changeColor("#000000",driver,elem);
        }
    }


    static void changeColor(String color, WebDriver driver, WebElement elem ){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor=" + color + "'", elem);
        try {
            Thread.sleep(500);
        }
        catch (Exception e){}
    }

    @Test
    @SneakyThrows
    public void testFlaskUsingJSExecutor(){
        d.get("https://www.orangehrm.com/");
        WebElement element = d.findElement(By.xpath("//body/nav[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/a[1]/button[1]"));
//        JavaScriptExecutorExample.flash(d, element);
        File screenshotAs = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        File target = new File("C:\\Users\\priya\\IdeaProjects\\SeleniumDemo\\src\\test\\java\\HRM.jpg");
        FileUtils.copyFile(screenshotAs,target);
    }

    @Test
    public void getTitle(){
        d.get("https://www.orangehrm.com/");
        System.out.println(d.getTitle());
        JavascriptExecutor js = (JavascriptExecutor) d;
        String elem = js.executeScript("return document.title").toString();
        System.out.println(elem);
    }

    @Test
    public void getURL(){
        d.get("https://www.orangehrm.com/");
        System.out.println(d.getCurrentUrl());
        JavascriptExecutor js = (JavascriptExecutor) d;
        String elem = js.executeScript("return document.URL").toString();
        System.out.println(elem);
    }

    @Test
    public void TestElementClick(){
        d.get("https://www.orangehrm.com/");
        JavascriptExecutor js = (JavascriptExecutor) d;
        WebElement element = d.findElement(By.xpath("//body/nav[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/a[1]/button[1]"));
//        js.executeScript("arguments[0].click()", element);
        js.executeScript("arguments[1].click()", element, element);
    }

    @Test
    public void TestElementAlert(){
        d.get("https://www.orangehrm.com/");
        JavascriptExecutor js = (JavascriptExecutor) d;
        WebElement element = d.findElement(By.xpath("//body/nav[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/a[1]/button[1]"));
//        js.executeScript("arguments[0].click()", element);
        js.executeScript("arguments[1].click()", element, element);
        js.executeScript("alert('clicked')");
    }

    @Test
    public void TestScroll(){
        d.get("https://www.orangehrm.com/");
        WebElement elem = d.findElement(By.xpath("//body/div[3]/div[1]/div[1]/div[1]/section[3]/div[4]/div[1]/div[1]/div[1]/div[1]/img[1]"));
        JavascriptExecutor js = (JavascriptExecutor) d;
        js.executeScript("arguments[0].scrollIntoView(true)",elem);
    }
}
