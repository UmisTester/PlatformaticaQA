package test.entity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Run(run = RunType.Multiple)
public class EntityParentChildViewTest extends BaseTest {

    private static final String STRING = "String for the First Test";
    private static final String TEXT = "Text for the First Test";
    private static final String INT = "12345";
    private static final String DECIMAL = "100.12";
    private static final String DATE = "02/03/2021";
    private static final String DATETIME = "02/03/2021 21:15:00";

    @Test
    public void createParentTest() {

        WebDriver driver = getDriver();

        WebElement parentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        parentMenu.click();

        WebElement createNewParent = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewParent.click();

        WebElement inputString = driver.findElement(By.xpath("//input[@id='string']"));
        inputString.sendKeys(STRING);

        WebElement inputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        inputText.sendKeys(TEXT);

        WebElement inputInt = driver.findElement(By.xpath("//input[@id='int']"));
        inputInt.sendKeys(INT);

        WebElement inputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        inputDecimal.sendKeys(DECIMAL);

        WebElement inputDate = driver.findElement(By.xpath("//input[@id='date']"));
        inputDate.sendKeys(DATE);

        WebElement inputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        inputDateTime.sendKeys(DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> parentRecord = driver.findElements(By.xpath("//tbody/tr[1]"));
        Assert.assertEquals(parentRecord.size(), 1);
    }

    @Test(dependsOnMethods = "createParentTest")
    public void createChild1Test() {

        WebDriver driver = getDriver();

        WebElement parentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        parentMenu.click();

        WebElement parentRecord = driver.findElement(By.xpath("//tbody/tr[1]"));
        parentRecord.click();

        WebElement createNewChild = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewChild.click();

        WebElement inputString = driver.findElement(By.xpath("//input[@id='string']"));
        inputString.sendKeys(STRING);

        WebElement inputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        inputText.sendKeys(TEXT);

        WebElement inputInt = driver.findElement(By.xpath("//input[@id='int']"));
        inputInt.sendKeys(INT);

        WebElement inputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        inputDecimal.sendKeys(DECIMAL);

        WebElement inputDate = driver.findElement(By.xpath("//input[@id='date']"));
        ProjectUtils.fill(getWebDriverWait(),inputDate, DATE);

        WebElement inputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        ProjectUtils.fill(getWebDriverWait(),inputDateTime, DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr[1]"));
        Assert.assertEquals(childRecord.size(), 1);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[1]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"","user132@tester.com"));
    }

    @Test(dependsOnMethods = "createChild1Test")
    public void createChild2Test() {

        WebDriver driver = getDriver();

        WebElement parentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        parentMenu.click();

        WebElement parentRecord = driver.findElement(By.xpath("//tbody/tr[1]"));
        parentRecord.click();

        WebElement createNewChild = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewChild.click();

        WebElement inputString = driver.findElement(By.xpath("//input[@id='string']"));
        inputString.sendKeys(STRING);

        WebElement inputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        inputText.sendKeys(TEXT);

        WebElement inputInt = driver.findElement(By.xpath("//input[@id='int']"));
        inputInt.sendKeys(INT);

        WebElement inputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        inputDecimal.sendKeys(DECIMAL);

        WebElement inputDate = driver.findElement(By.xpath("//input[@id='date']"));
        ProjectUtils.fill(getWebDriverWait(),inputDate, DATE);

        WebElement inputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        ProjectUtils.fill(getWebDriverWait(),inputDateTime, DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(childRecord.size(), 2);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[2]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"","user132@tester.com"));

    }
}
