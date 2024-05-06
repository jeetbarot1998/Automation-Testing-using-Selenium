import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.Properties;

public class TestShadowDom extends BaseSetup{
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
    public void testShadowDom(){
        d.get("https://shop.polymer-project.org/");
        JavascriptExecutor js = (JavascriptExecutor) d;
        WebElement data = (WebElement)js.executeScript("return " +
                "document.querySelector(\"body > shop-app\").shadowRoot." +
                "querySelector(\"iron-pages > shop-home\").shadowRoot.q" +
                "uerySelector(\"div:nth-child(2) > shop-button > a\")");
        Assert.assertEquals("SHOP NOW", data.getText());
        data.click();
        Assert.assertEquals("https://shop.polymer-project.org/list/mens_outerwear",
                d.getCurrentUrl());
//        WebElement shopApp = d.findElement(By.tagName("shop-app"));
////        get first shadow DOM or shadow Root
//        SearchContext firstShadowDom = (SearchContext) (js.executeScript("return arguments[0].shadowRoot", shopApp));
//        WebElement ironPages = firstShadowDom.findElement(By.tagName("iron-pages"));
//        WebElement shopHome = ironPages.findElement(By.name("home"));
//        SearchContext secondShadowDom = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shopHome);
//        secondShadowDom.findElement(By.partialLinkText("Now")).click();
    }

    @Test
    public void testByElement(){
        d.get("https://www.guru99.com/");
        WebElement element = d.findElement(By.id("java_technologies"));
        System.out.println(element.findElement(By.xpath(".//a[@title='JUnit']")).getText());
        System.out.println(element.findElement(By.xpath("//a[@title='NumPy']")).getText());
//        find the given element inside the 'element' only using "."
//        System.out.println(element.findElement(By.xpath(".//a[@title='NumPy']")).getText());
        System.out.println(element.findElements(By.xpath(".//a")).size());
        System.out.println(element.findElements(By.xpath("//a")).size());
    }


}
