package docker.grid;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class FrieFoxRemote {


    @SneakyThrows
    public void firefoxRemote(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
//        port number of the selenium HUB
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, capabilities);
        remoteWebDriver.get("https://www.bbc.com/");
        Assert.assertEquals("BBC", remoteWebDriver.getTitle());
        System.out.println(remoteWebDriver.getTitle());
        remoteWebDriver.quit();
    }
}
