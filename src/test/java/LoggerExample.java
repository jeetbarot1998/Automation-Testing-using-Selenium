import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoggerExample {
    static Logger logger = Logger.getLogger(LoggerExample.class);
    private WebDriver d;

    @BeforeClass
    public static void init(){
        PropertyConfigurator.configure("log4j.properties");
        logger.info("performing initialisation");
        WebDriverManager.chromedriver().setup();
        logger.trace("This is trace");
        logger.debug("This is debug");
        logger.info("This is info");
        logger.warn("This is warn");
        logger.error("This is error");
        logger.fatal("This is fatal");
    }

    @Test
    public void launchBrowser(){
        logger.info("Launching browser");

        d = new ChromeDriver();
        d.get("https://www.bbc.com");
    }

    @After
    public void cleanUp(){
       logger.info("Clean up tasks");
       d.quit();
    }
}
