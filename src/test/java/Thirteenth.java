import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

public class Thirteenth extends BaseSetup{
    private Logger logger = Logger.getLogger(AutomationPractice.class);
    private WebDriver d;
    private Properties p;

    @BeforeClass
    public static void BeforeClass(){
        System.out.println("BeforeClass in child");
    }

    @Before
    public void setUp(){
        if(d == null){
            super.init();
        }
        d = getWebDriver();
        p = getObjRepo();
        logger.info("Child class before called");
    }

    //a[contains(text(),'Senco Gold')]/self::a/parent::td/child::a
    @Test
    public void TestXpath(){
        d.get("https://money.rediff.com/gainers");
        d.manage().window().maximize();
//        get details using self
        System.out.println(d.findElement(By.xpath("//a[contains(text(),'Senco Gold')]/self::a")).getText());
//        get parent tag
        System.out.println(d.findElement(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/parent::td")).getText());
//        using ancestor to find child and its number
        List<WebElement> elements = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/child::*"));
        System.out.println("Number of child elements are : " + elements.size());
        Assert.assertEquals(5, elements.size());

//        Finding ancestor parent and grandparent of current node
        List<WebElement> allAncestors = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr"));
        System.out.println("allAncestors has : " + allAncestors.size() + " tags.");

//        Finding the descendants or child.
        List<WebElement> AllDescendants = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/descendant::*"));
        System.out.println("AllDescendants has : " + AllDescendants.size() + " tags.");

//        Following-sibling => Whatever comes after. Need not be Child necessarily.
        List<WebElement> elementsFollowingSiblings = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/following-sibling::tr"));
        System.out.println("Following-sibling has : " + elementsFollowingSiblings.size() + " tags.");

//        Preceding-sibling => Whatever comes after. Need not be Child necessarily.
        List<WebElement> elementsPrecedingSiblings = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/preceding-sibling::tr"));
        System.out.println("Preceding-sibling has : " + elementsPrecedingSiblings.size() + " tags.");

//        All tags preceding the current tag
        List<WebElement> elementsAllPreceding = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/preceding::*"));
        System.out.println("Preceding All elements has : " + elementsAllPreceding.size() + " tags.");

//        All tags following the current tag
        List<WebElement> elementsAllFollowing = d.findElements(By.xpath("//a[contains(text(),'Senco Gold')]/self::a/ancestor::tr/following::*"));
        System.out.println("Following All elements has : " + elementsAllFollowing.size() + " tags.");
//
////        Traverse from one tag to another: facebook from create account to Enter your email:
//        List<WebElement> TraverseDOM = d.findElements(By.xpath("//a[@id='u_0_0_n7']/parent::div/preceding-sibling::div/child::div[1]/child::input"));
//        System.out.println("Traverse DOM : " + TraverseDOM.size() + " tags.");

    }
}
