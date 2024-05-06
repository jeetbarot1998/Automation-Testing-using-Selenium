import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@Getter
@Setter
public class BaseSetup {
    private static Logger logger =Logger.getLogger(BaseSetup.class);
    private WebDriver webDriver = null;
    private static Properties objRepo = null;
    private static DriverManagerType browser = null;

    @BeforeClass
    public static void setUpForAllTests(){
        PropertyConfigurator.configure("log4j.properties");
        logger.info("before class");
        objRepo = loadObjectRepo();
//        Download the webDrivers of the type dynamically
        browser = DriverManagerType.valueOf(objRepo.get("browser").toString().toUpperCase());
        WebDriverManager.getInstance(browser).setup();
    }

    @SneakyThrows
    public static Properties loadObjectRepo(){
        objRepo = new Properties();
        objRepo.load(new FileInputStream(new File("OR.properties")));
        return objRepo;
    }


    @Before
    @SneakyThrows
    public void init(){
        if(webDriver == null){
//        Load the webDrivers of the type dynamically
            Class<?> chromeClass = Class.forName(browser.browserClass());
            webDriver = (WebDriver) chromeClass.newInstance();
        }
        logger.info("Before a parent class is called");
    }

    public Properties getObjRepo(){
        return objRepo;
    }

    @After
    public void cleanUp(){
        webDriver.quit();
    }

}
