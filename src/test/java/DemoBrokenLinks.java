import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class DemoBrokenLinks extends BaseSetup{

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
    public void getAllaTagsUsingTagName(){
        d.get("https://demoqa.com/");
        d.manage().window().maximize();
        List<WebElement> aTags = d.findElements(By.tagName("a"));
        for(WebElement each_a : aTags){
            String href = each_a.getAttribute("href");
            verifyLink(href);
        }
    }

    public static void verifyLink(String hyperLink){
        try {
            URL url = new URL(hyperLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() >= 400){
                System.out.println(hyperLink + " - " + httpURLConnection.getResponseMessage() + " is broken.");
            }
            else {
                System.out.println(hyperLink + " - " + httpURLConnection.getResponseMessage());
            }
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}
