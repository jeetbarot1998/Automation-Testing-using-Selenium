import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AutomationPractice extends BaseSetup{
    private Logger logger = Logger.getLogger(AutomationPractice.class);
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
    public void testLocatorUsingId(){
        d.get(p.get("automationPractice.url").toString());
        d.manage().window().maximize();
        WebElement searchQueryTop = d.findElement(By.id(p.get("automationPractice.search.xpath").toString()));
        searchQueryTop.sendKeys("T-Shirt");
    }

    @Test
    public void testLocatorUsingName(){
        d.get(p.get("automationPractice.url").toString());
        d.manage().window().maximize();
        WebElement searchQueryTop = d.findElement(By.id(p.get("automationPractice.search.xpath").toString()));
        searchQueryTop.sendKeys("T-Shirt");
        WebElement searchBtn = d.findElement(By.name(p.get("automationPractice.searchBtn.xpath").toString()));
        searchBtn.click();
    }

@Test
    public void testLocatorUsingLinkText(){
        d.get(p.get("automationPractice.url").toString());
        d.manage().window().maximize();
        // search for the elements and  if not found then wait for 10 seconds
        // and search again. If still not found then throw exception as element not found
        d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement searchQueryTop = d.findElement(By.id(p.get("automationPractice.search.xpath").toString()));
        searchQueryTop.sendKeys("T-Shirt");
        WebElement searchBtn = d.findElement(By.name(p.get("automationPractice.searchBtn.xpath").toString()));
        searchBtn.click();
        WebElement partialLinkTExtElement = d.findElement(By.partialLinkText("Chiffon Dress"));
        WebElement linkTExtElement = d.findElement(By.linkText("Printed Chiffon Dress"));
        linkTExtElement.click();
    }

    @Test
    public void getAllImageListUsingClassName(){
        d.get(p.get("automationPractice.url").toString());
        d.manage().window().maximize();
        System.out.println(d.findElements(By.className("homeslider-container")).size());
    }

    @Test
    public void testLocatorUsingTagName(){
        d.get(p.get("automationPractice.url").toString());
        d.manage().window().maximize();
        System.out.println(d.findElements(By.tagName("img")).size());
    }

}
