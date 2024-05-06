import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

public class CheckBoxTest extends BaseSetup{
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
    public void CheckBoxVerify(){
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Readio_CheckBox.html");
        WebElement radio2Checked = d.findElement(By.xpath("//input[@value='option2']"));
        WebElement radio4Unchecked = d.findElement(By.xpath("//input[@value='option4']"));
        WebElement radio5Disabled = d.findElement(By.xpath("//input[@value='option5']"));
        WebElement checkBoxDisabled = d.findElement(By.xpath("//input[@value='checkbox3']"));
        WebElement checkBoxChecked = d.findElement(By.xpath("//input[@value='checkbox4']"));
        WebElement checkBoxUnChecked = d.findElement(By.xpath("//input[@value='checkbox5']"));
        Assert.assertTrue(radio2Checked.isSelected());
        Assert.assertFalse(radio4Unchecked.isSelected());
        Assert.assertFalse(radio5Disabled.isEnabled());
        Assert.assertFalse(checkBoxDisabled.isEnabled());
        Assert.assertTrue(checkBoxChecked.isSelected());
        Assert.assertFalse(checkBoxUnChecked.isSelected());
    }

    @Test
    public void dropDown(){
        d.get("file:///C:/Users/priya/IdeaProjects/SeleniumDemo/src/test/java/Readio_CheckBox.html");
        WebElement dropDown = d.findElement(By.id("selectOption"));
        Select dropDownSelect = new Select(dropDown);
        System.out.println(dropDownSelect.getOptions().get(1).getText());
        Assert.assertEquals("Option 2",dropDownSelect.getOptions().get(1).getText());
        dropDownSelect.selectByVisibleText("Option 3");
        Assert.assertEquals("Option 3",dropDownSelect.getAllSelectedOptions().get(0).getText());
    }

    @SneakyThrows
    @Test
    public void jobServeAutomation(){
        d.get("https://jobserve.com/gb/en/Job-Search/");
        d.findElement(By.xpath("//input[@id='txtKey']")).sendKeys("selenium");
        new Select(d.findElement(By.xpath("//select[@id='selRad']"))).selectByValue("75");
        new Select(d.findElement(By.xpath("//select[@id='selJType']"))).selectByValue("3");
        new Select(d.findElement(By.xpath("//select[@id='selAge']"))).selectByValue("6");
        Thread.sleep(5000);
        d.findElement(By.xpath("//input[@id='btnSearch']")).click();
    }

}