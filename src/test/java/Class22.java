import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Class22 extends BaseSetup{

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
    public void flyDubaiAutomation() throws InterruptedException {
        d.get("https://www.flydubai.com/en/");
        d.findElement(By.xpath("//label[@for='airport-origin']")).sendKeys("Mumbai");
        JavascriptExecutor js = (JavascriptExecutor) d;
        WebElement sourceElem = (WebElement) js.executeScript("return document.getElementById('mCSB_1_container')");
        WebElement element = sourceElem.findElement(By.xpath("//a"));
        System.out.println(element);
        System.out.println("document.querySelector(\"#makeBooking > form > div.vAlign.w-100.make-booking-tab.mb-xl-0.mb-lg-0.mb-md-3.mb-sm-3.mb-0 > div.mat-form-group.makeBookingFrom.airportPickerFrom.source-to-des-grid.mb-xl-0.mb-lg-0.mb-md-0.mb-sm-3.mb-3.mr-grid-space.input-valid > label.bookingTo-wrapper.make-booking-desktop.widgetFocusedOut\")");
        WebElement source = d.findElement(By.xpath("//div[@id=\"mCSB_1_container\"]"));
        System.out.println(source.getText());
        source.click();
        d.findElement(By.xpath("//label[@for='airport-destination']")).sendKeys("Delhi");
        WebElement destination = d.findElement(By.xpath("//*[@id=\"mCSB_2_container\"]/ul/li/a/div[1]/h4/lebel"));
        System.out.println(destination.getText());
        destination.click();
        Thread.sleep(10000);
        //div[@class='lightpick__day is-disabled is-today is-strike']/preceding-sibling::*
    }

    @SneakyThrows
    @Test
    public void testKeyEventShift(){
        d.get("https://demoqa.com/auto-complete");
        WebElement el = d.findElement(By.xpath("//*[@id=\"autoCompleteSingleContainer\"]/div/div[1]"));
        Actions actions = new Actions(d);
        actions.keyDown(el, Keys.SHIFT);
        actions.sendKeys("White");
        actions.keyUp(Keys.SHIFT);
        Action act = actions.build();
        act.perform();
    }


    @SneakyThrows
    @Test
    public void testCopyPaste(){
        d.get("https://demoqa.com/text-box");
        d.manage().window().maximize();
        WebElement currAdd = d.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currAdd.sendKeys("ABCDEFGHIJKLMONPQRSTUVWXYZ");
        WebElement permAdd = d.findElement(By.xpath("//textarea[@id='permanentAddress']"));


//        JavascriptExecutor jsExecutor = (JavascriptExecutor) d;

        // Scroll the element into view using JavaScript
//        jsExecutor.executeScript("arguments[0].scrollIntoView();", permAdd);
        Actions actions = new Actions(d);
//        select all
        actions.keyDown(currAdd, Keys.CONTROL);
        actions.sendKeys("a");
        actions.keyUp(currAdd, Keys.CONTROL);
        actions.build().perform();

//        copy
        actions.keyDown(currAdd, Keys.CONTROL);
        actions.sendKeys("c");
        actions.keyUp(currAdd, Keys.CONTROL);
        actions.build().perform();
//        actions.sendKeys(Keys.chord(Keys.CONTROL, "c")).build().perform();


//        Move to permAdd elem
        actions.sendKeys(currAdd, Keys.TAB);
        actions.build().perform();

//        paste
        actions.keyDown(permAdd, Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(permAdd, Keys.CONTROL);
        actions.build().perform();
//        actions.sendKeys(Keys.chord(Keys.CONTROL, "v")).build().perform();

        Assert.assertEquals(currAdd.getText(), permAdd.getText());
    }

    @Test
    public void MakeMap(){
        String s = "AFGHANISTAN";
        Map<Character, Integer> m = new HashMap<>();
        int max = 0;
        char max_c = ' ';
        int curr_value = 0;
        for(Character c : s.toCharArray()){
            if(m.containsKey(c)){
                curr_value = m.get(c)+1;
                m.put(c,curr_value);
                if(curr_value > max){
                    max = curr_value;
                    max_c = c;
                }
            }
            else {
                m.put(c,1);
            }
        }
        System.out.println(m);
        System.out.println(max_c);
        System.out.println(max);
    }
}
