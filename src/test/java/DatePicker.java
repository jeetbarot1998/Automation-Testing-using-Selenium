import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DatePicker extends BaseSetup{

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

    @SneakyThrows
    @Test
    public void DatePicker(){
        String eyear = "2024";
        String emonth = "December";
        String eday = "22";
        d.get("https://www.spicejet.com/");
        d.findElement(By.xpath("//div[normalize-space()='Departure Date']")).click();
        WebElement element = d.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div[2]/div[3]/div[2]/div/div[1]/div/div[1]/div"));
        System.out.println(element.getText());
//        WebElement element1 = d.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div[2]/div[3]/div[2]/div/div[1]/div/div[3]/div[4]/div[4]/div/div"));
//        System.out.println(element1.getText());
        String[] s = element.getText().split(" ");
        String year = s[1].trim();
        String month = s[0].trim();
        d.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement nextElem = d.findElement(By.xpath("//body/div[@id='react-root']/div[@id='main-container']/div[1]/div[1]/div[3]/div[2]/div[4]/div[1]/div[2]/div[2]/div[1]"));
        while (!emonth.equalsIgnoreCase(month) || !eyear.equalsIgnoreCase(year)){
            nextElem.click();
            WebElement element1 = d.findElement(By.xpath("//*[@id=\"main-container\"]/div/div[1]/div[3]/div[2]/div[4]/div/div[2]/div[2]/div[3]/div[2]/div/div[1]/div/div[1]/div"));
            s = element1.getText().split(" ");

            year = s[1].trim();
            month = s[0].trim();
            System.out.println();
            Thread.sleep(2000);
            System.out.println(year + " " + month);
        }
//        types of wait, Date picker, timeout, FluentWait, Implicit wait and Explicit wait

    }
}
