package docker.grid;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class ChromeRemote {

    @SneakyThrows
    public void chromeRemote(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

//        port number of the selenium HUB
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
        remoteWebDriver.get("https://www.bbc.com/");
        Assert.assertEquals("BBC", remoteWebDriver.getTitle());
        System.out.println(remoteWebDriver.getTitle());
        remoteWebDriver.quit();

    }
}
