package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LaunchBrowser {
    public static void main(String[] args) {
        testChromeDriver();
    }

    private static void testChromeDriver(){
        String driverPath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.println(System.getProperties());
        WebDriver d = new ChromeDriver();
        d.get("https://www.bbc.co.uk");
        if(d.getTitle().startsWith("BBC")){
            System.out.println("Passed FROM LaunchBrowser");
        }
        else {
            System.out.println("Failed");
        }
    }
}

class LaunchBrowser2 {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        File f = new File("selenium.properties");
        FileInputStream fis = new FileInputStream(f);
        p.load(fis);
        String driverPath = p.get("driver.path").toString();
        System.out.println(driverPath);
        testChromeDriver(driverPath);
    }

    private static void testChromeDriver(String driverPath){
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver d = new ChromeDriver();
        System.out.println(System.getProperties());
        d.get("https://www.bbc.co.uk");
        if(d.getTitle().startsWith("BBC")){
            System.out.println("Passed FROM LaunchBrowser2");
        }
        else {
            System.out.println("Failed");
        }
    }
}


