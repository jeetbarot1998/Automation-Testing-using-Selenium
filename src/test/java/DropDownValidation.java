import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class DropDownValidation extends BaseSetup{
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
    public void jsEvaluate(){
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Readio_CheckBox.html");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) d;
        String script = "var select = document.evaluate('//select[@id=\"selectOption\"]', document, null, "
                + "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                + "select.innerHTML = '<option value=\"audi\">Audi</option>' + "
                + "'<option value=\"toyota\">Toyota</option>' + "
                + "'<option value=\"honda\">Honda</option>' + "
                + "'<option value=\"tesla\">Tesla</option>' + "
                + "'<option value=\"jaguar\">Jaguar</option>';";

        jsExecutor.executeScript(script);
    }

    @Test
    public void DropDownsSequence(){
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Readio_CheckBox.html");
        Select s = new Select(d.findElement(By.xpath("//select[@id='selectOption2']")));
        List<WebElement> options = s.getOptions();
        List<String> toBeSortedList = new ArrayList<>();
        List<String> orignalCarList = new ArrayList<>();
        for (WebElement each_elem : options){
            toBeSortedList.add(each_elem.getText());
            orignalCarList.add(each_elem.getText());
        }
        List<String> sortedList1 = options.stream().
                sorted((elem1,elem2) -> {
                    return elem1.getText().compareTo(elem2.getText());
                }).
                map(elem -> elem.getText()).collect(Collectors.toList());
        List<String> sortedList2 = toBeSortedList.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println("orignalCarList => "+ orignalCarList);
        System.out.println("sortedList2 => "+ sortedList2);
        System.out.println("sortedList1 => "+ sortedList1);
        System.out.println(orignalCarList.equals(sortedList2));
        Assert.assertNotEquals(orignalCarList, sortedList2);
    }

    @Test
    public void DropDownsSequence2(){
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Readio_CheckBox.html");
        Select s = new Select(d.findElement(By.xpath("//select[@id='selectOption3']")));
        List<WebElement> options = s.getOptions();
        List<String> toBeSortedList = new ArrayList<>();
        List<String> orignalCarList = new ArrayList<>();
        for (WebElement each_elem : options){
            toBeSortedList.add(each_elem.getText());
            orignalCarList.add(each_elem.getText());
        }
        List<String> sortedList1 = options.stream().
                sorted((elem1,elem2) -> {
                    return elem1.getText().compareTo(elem2.getText());
                }).
                map(elem -> elem.getText()).collect(Collectors.toList());
        List<String> sortedList2 = toBeSortedList.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println("orignalCarList => "+ orignalCarList);
        System.out.println("sortedList2 => "+ sortedList2);
        System.out.println("sortedList1 => "+ sortedList1);
        System.out.println(orignalCarList.equals(sortedList2));
        Assert.assertEquals(orignalCarList, sortedList2);
    }

    @SneakyThrows
    @Test
    public void testPopulateDropDown(){
        d.get("https://www.makemytrip.com/");
        d.findElement(By.xpath("//label[@for='fromCity']")).click();
        d.findElement(By.xpath("//input[@placeholder=\"From\" ]")).sendKeys("Mumbai");
        Thread.sleep(3000);
        String text = d.findElement(By.xpath("//span[contains(text(),'Mumbai')]")).getText();
        Assert.assertEquals(text, "Mumbai");
        Thread.sleep(1000);
    }

    @Test
    public void testPopulateDropDown2(){
        d.get("https://www.makemytrip.com/");
        d.findElement(By.xpath("//label[@for='fromCity']")).click();
        d.findElement(By.xpath("//input[@placeholder=\"From\" ]")).sendKeys("Mumbai");
        String text = d.findElement(By.xpath("//span[contains(text(),'Mumbai')]")).getText();
        Assert.assertEquals(text, "Mumbai");
    }
}
