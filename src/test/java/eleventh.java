import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class eleventh extends BaseSetup{

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
    public void testLocatorByCssSelector(){
        d.get("https://www.facebook.com/");
        d.manage().window().maximize();
        WebElement userElement = d.findElement(By.cssSelector("input#email"));
        userElement.sendKeys("Abc@gmail.com");

    }

    @Test
    public void testLocatorByCssSelector2(){
        d.get("https://www.facebook.com/");
        d.manage().window().maximize();
        WebElement userElement = d.findElement(By.cssSelector(".inputtext"));
        userElement.sendKeys("Abc@gmail.com");
    }

    @Test
    public void testLocatorByCssSelector3(){
        d.get("https://www.facebook.com/");
        d.manage().window().maximize();
        WebElement userElement = d.findElement(By.cssSelector("input[name=email]"));
        userElement.sendKeys("Abc@gmail.com");
    }

    @Test
    public void testLocatorByCssSelector4(){
        d.get("https://www.facebook.com/");
        d.manage().window().maximize();
        WebElement userElement = d.findElement(By.cssSelector("input.inputtext[name=email]"));
        WebElement userElement2 = d.findElement(By.cssSelector(".inputtext[name=email]"));
        userElement.sendKeys("Abc@gmail.com");
        userElement2.sendKeys("Abc@gmail.com");
    }

    @Test
    public void testXpath(){
        d.get("https://www.facebook.com/");
        d.manage().window().maximize();
        WebElement userElement = d.findElement(By.cssSelector("input.inputtext[name=email]"));
        userElement.sendKeys("Abc@gmail.com");
    }

    @Test
    public void testXpathAbsol1(){
        d.get("https://opensource-demo.orangehrmlive.com/");
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement userName = d.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]"));
        WebElement password = d.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/input[1]"));
        userName.sendKeys("Admin");
        password.sendKeys("admin123");
        WebElement loginBtn = d.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]"));
        loginBtn.click();
    }

    @Test
    public void testXpathRelative(){
        d.get("https://opensource-demo.orangehrmlive.com/");
        d.manage().window().maximize();
        d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement userName = d.findElement(By.xpath("//input[@name=\"username\"]"));
        WebElement password = d.findElement(By.xpath("//input[@name=\"password\"]"));
        userName.sendKeys("Admin");
        password.sendKeys("admin123");
        WebElement loginBtn = d.findElement(By.xpath("//button[@type=\"submit\"]"));
        loginBtn.click();
    }
}
