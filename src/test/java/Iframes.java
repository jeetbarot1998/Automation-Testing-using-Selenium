import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Iframes extends BaseSetup {

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
    public void IframeTes() {
        d.get("https://demoqa.com/frames");
//        try {
//            WebElement element = d.findElement(By.id("sampleHeading"));
//        } catch (NoSuchElementException nswe) {
//            System.out.println("NoSuchElementException");
//        }
        List<WebElement> elem = d.findElements(By.tagName("iframe"));
        System.out.println("There are " + elem.size() + " iframes");
        WebElement frame1 = d.findElement(By.id("frame1"));
        d.switchTo().frame(frame1);
        System.out.println(d.findElement(By.id("sampleHeading")).getText());
    }

    @Test
    public void NestedIframe(){
        d.get("https://demoqa.com/nestedframes");
        List<WebElement> elem = d.findElements(By.tagName("iframe"));
        System.out.println("There are " + elem.size() + " iframes in base page");
        WebElement frame1 = d.findElement(By.id("frame1"));
        d.switchTo().frame(frame1);
        WebElement element = d.findElement(By.tagName("body"));
        System.out.println(element.getText());

        List<WebElement> elem2 = d.findElements(By.tagName("iframe"));
        System.out.println("There are " + elem2.size() + " iframes in child page");
//        Switch of parent.
        d.switchTo().parentFrame();
        d.switchTo().parentFrame();

//        Switch of default content
        d.switchTo().defaultContent();
    }

}
