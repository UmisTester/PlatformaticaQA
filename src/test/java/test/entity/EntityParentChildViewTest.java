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
    public void CreateParentTest() {

        WebDriver driver = getDriver();

        WebElement ParentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ParentMenu.click();

        WebElement CreateNewParent = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        CreateNewParent.click();

        WebElement InputString = driver.findElement(By.xpath("//input[@id='string']"));
        InputString.sendKeys(STRING);

        WebElement InputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        InputText.sendKeys(TEXT);

        WebElement InputInt = driver.findElement(By.xpath("//input[@id='int']"));
        InputInt.sendKeys(INT);

        WebElement InputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        InputDecimal.sendKeys(DECIMAL);

        WebElement InputDate = driver.findElement(By.xpath("//input[@id='date']"));
        InputDate.sendKeys(DATE);

        WebElement InputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        InputDateTime.sendKeys(DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement SaveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        SaveButton.click();

        List<WebElement> parentRecord = driver.findElements(By.xpath("//tbody/tr[1]"));
        Assert.assertEquals(parentRecord.size(), 1);

    }

    @Test(dependsOnMethods = "CreateParentTest")
    public void CreateChild1Test() {

        WebDriver driver = getDriver();

        WebElement ParentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ParentMenu.click();

        WebElement parentRecord = driver.findElement(By.xpath("//tbody/tr[1]"));
        parentRecord.click();

        WebElement createNewChild = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewChild.click();

        WebElement InputString = driver.findElement(By.xpath("//input[@id='string']"));
        InputString.sendKeys(STRING);

        WebElement InputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        InputText.sendKeys(TEXT);

        WebElement InputInt = driver.findElement(By.xpath("//input[@id='int']"));
        InputInt.sendKeys(INT);

        WebElement InputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        InputDecimal.sendKeys(DECIMAL);

        WebElement InputDate = driver.findElement(By.xpath("//input[@id='date']"));
        ProjectUtils.fill(getWebDriverWait(),InputDate, DATE);

        WebElement InputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        ProjectUtils.fill(getWebDriverWait(),InputDateTime, DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement SaveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        SaveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr[1]"));
        Assert.assertEquals(childRecord.size(), 1);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[1]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"","user132@tester.com"));
    }



    @Test(dependsOnMethods = "CreateChild1Test")
    public void CreateChild2Test() {

        WebDriver driver = getDriver();

        WebElement ParentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ParentMenu.click();

        WebElement parentRecord = driver.findElement(By.xpath("//tbody/tr[1]"));
        parentRecord.click();

        WebElement createNewChild = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewChild.click();

        WebElement InputString = driver.findElement(By.xpath("//input[@id='string']"));
        InputString.sendKeys(STRING);

        WebElement InputText = driver.findElement(By.xpath("//textarea[@id='text']"));
        InputText.sendKeys(TEXT);

        WebElement InputInt = driver.findElement(By.xpath("//input[@id='int']"));
        InputInt.sendKeys(INT);

        WebElement InputDecimal = driver.findElement(By.xpath("//input[@id='decimal']"));
        InputDecimal.sendKeys(DECIMAL);

        WebElement InputDate = driver.findElement(By.xpath("//input[@id='date']"));
        ProjectUtils.fill(getWebDriverWait(),InputDate, DATE);

        WebElement InputDateTime = driver.findElement(By.xpath("//input[@id='datetime']"));
        ProjectUtils.fill(getWebDriverWait(),InputDateTime, DATETIME);

        Select Dropdown = new Select(driver.findElement(By.xpath("//select[@name='entity_form_data[user]']")));
        Dropdown.selectByVisibleText("user132@tester.com");

        WebElement SaveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        SaveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(childRecord.size(), 2);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[2]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"","user132@tester.com"));

    }
}
