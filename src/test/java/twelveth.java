import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class twelveth extends BaseSetup{
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

    @Test
    public void XpathAndOr(){
        d.get("http://www.automationpractice.pl/index.php");
        d.manage().window().maximize();
        d.findElement(By.xpath("//input[@id='search_query_top' or @name='search_query']")).sendKeys("T-shirt");
        d.findElement(By.xpath("//button[@name=\"submit_search\" and @type=\"submit\"]")).click();
        String myHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "<p>My first paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        String myJson = "{  \n" +
                "    \"employee\": {  \n" +
                "        \"name\":       \"sonoo\",   \n" +
                "        \"salary\":      56000,   \n" +
                "        \"married\":    true  \n" +
                "    }  \n" +
                "}  ";
    }

    @Test
    public void XpathConatins(){
        d.get("http://www.automationpractice.pl/index.php");
        d.manage().window().maximize();
        d.findElement(By.xpath("//input[contains(@id,'query_to')]")).sendKeys("T-shirt");
        d.findElement(By.xpath("//button[contains(@name,'submit_sear')]")).click();
    }

    @Test
    public void XpathStartsWith(){
        d.get("http://www.automationpractice.pl/index.php");
        d.manage().window().maximize();
        d.findElement(By.xpath("//input[starts-with(@id,'search')]")).sendKeys("T-shirt");
        d.findElement(By.xpath("//button[contains(@name,'submit_sear')]")).click();
    }

    @Test
    public void XpathText(){
        d.get("http://www.automationpractice.pl/index.php");
        d.manage().window().maximize();
        d.findElement(By.xpath("//a[text()=\"Ecommerce software by PrestaShop™\"]")).click();
    }

    @Test
    public void XpathChained(){
        d.get("http://www.automationpractice.pl/index.php");
        d.manage().window().maximize();
        d.findElement(By.xpath("//form[@id='searchbox']//input[4]")).click();
    }


}
