package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class LaunchBrowser3 {
    public static void main(String[] args) throws IOException {
        new LaunchBrowser3().CheckWebDriver();
    }

    @Test
    public void CheckWebDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver d = new ChromeDriver();
        System.out.println("Downloaded at : " + new ChromeDriverManager().getDownloadedDriverPath());

        System.out.println(System.getProperties());
        d.get("https://www.bbc.co.uk");
        if(d.getTitle().startsWith("BBC")){
            System.out.println("Passed FROM LaunchBrowser3");
        }
        else {
            System.out.println("Failed");
        }
        d.quit();
    }
}
