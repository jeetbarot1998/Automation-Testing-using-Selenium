import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import java.util.*;

public class Fourteen extends BaseSetup {
    private Logger logger = Logger.getLogger(AutomationPractice.class);
    private WebDriver d;
    private Properties p;

    @BeforeClass
    public static void BeforeClass() {
        System.out.println("BeforeClass in child");
    }

    @Before
    public void setUp() {
        if (d == null) {
            super.init();
        }
        d = getWebDriver();
        p = getObjRepo();
        logger.info("Child class before called");
    }

    @Test
    public void testPage() {
        d.get("https://www.makemytrip.com/");
        System.out.println("d.getCurrentUrl() -> " + d.getCurrentUrl());
        System.out.println("d.getTitle() -> " + d.getTitle());
        System.out.println("d.getWindowHandle() ->  " + d.getWindowHandle());
        System.out.println("d.getWindowHandles() -> " + d.getWindowHandles());
        System.out.println("d.getPageSource() -> " + d.getPageSource());
    }

    @Test
    public void testPage2() {
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Test.html");
        System.out.println("d.getWindowHandles() -> " + d.getWindowHandles());
        System.out.println(d.getWindowHandles().size() + " windows are open. Attempting to close 1 of them.");
        d.findElement(By.partialLinkText("W3")).click();
        System.out.println("d.getWindowHandles() -> " + d.getWindowHandles());
        System.out.println(d.getWindowHandles().size() + " windows are open. Attempting to close 1 of them.");
        d.close();
        System.out.println("d.getWindowHandles() -> " + d.getWindowHandles());
        try {
            d.close();
        } catch (NoSuchWindowException nswe) {
            System.out.println("No tabs are open so that it can be closed hence NoSuchWindowException");
        }
        System.out.println("d.getWindowHandles() -> " + d.getWindowHandles());
        System.out.println(d.getWindowHandles().size() + " windows are open. Attempting to close 1 of them.");
    }


    @Test
    public void testWindowHandle() {
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Test.html");
        System.out.println("Parent ref = " + d.getWindowHandle());
        d.findElement(By.partialLinkText("W3")).click();
        Set<String> windowHandles = d.getWindowHandles();
        String parentWindow = d.getWindowHandle();
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String childRef = iterator.next();
            d.switchTo().window(childRef);
            System.out.println(d.getTitle());
            System.out.println("URL of current tab : " + d.getCurrentUrl());
        }
        d.switchTo().window(parentWindow);
        System.out.println("URL of current tab : " + d.getCurrentUrl());
    }

    @Test
    public void closeAndQuit() {
        d.get("https://demoqa.com/browser-windows");
        String parentWindow = d.getWindowHandle();
        d.findElement(By.id("tabButton")).click();
        d.findElement(By.id("windowButton")).click();
        d.findElement(By.id("messageWindowButton")).click();
        Set<String> windowHandles = d.getWindowHandles();
        System.out.println("size = " + windowHandles.size());
//        TO close everything
        d.switchTo().window(parentWindow);

        Assert.assertEquals("DEMOQA", d.getTitle());
        d.quit();
    }

    @Test
    public void windowSwitchingDemo() {
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Test.html");
        System.out.println("Parent ref = " + d.getWindowHandle());
        d.findElement(By.partialLinkText("W3")).click();
        d.findElement(By.partialLinkText("Make")).click();
        d.findElement(By.partialLinkText("Goo")).click();

        Set<String> windowHandles = d.getWindowHandles();
        System.out.println("size = " + windowHandles.size());
        String GoogleWindowObj = getWindowObjectByTitle("Google", windowHandles);
        System.out.println(GoogleWindowObj);
        Assert.assertEquals("Google", d.getTitle());

        Map<String, String> stringStringMap = makeObjectToWebsiteTitleMap(windowHandles);
        System.out.println(stringStringMap);
    }

    public String getWindowObjectByTitle(String title, Set<String> windowObjHandlesSet) {
        for (String each_window : windowObjHandlesSet) {
            d.switchTo().window(each_window);
            if (d.getTitle().equalsIgnoreCase(title)) {
                return each_window;
            }
        }
        return null;
    }

    public Map<String, String> makeObjectToWebsiteTitleMap(Set<String> windowObjHandlesSet) {
        Map<String, String> obj = new HashMap<>();
        for (String each_window : windowObjHandlesSet) {
            d.switchTo().window(each_window);
            obj.put(d.getTitle(), each_window);
        }
        return obj;
    }

    @Test
    public void NavigateExample(){
        d.get("https://www.facebook.com/");
        System.out.println("URL via get = " + d.getCurrentUrl());
        d.navigate().to("https://www.google.com/");
        System.out.println("URL via navigate.to() = " + d.getCurrentUrl());
        d.navigate().back();
        System.out.println("URL back = " + d.getCurrentUrl());
        d.navigate().forward();
        System.out.println("URL forward = " + d.getCurrentUrl());
        d.navigate().refresh();
        System.out.println("URL refresh = " + d.getCurrentUrl());
    }
}
