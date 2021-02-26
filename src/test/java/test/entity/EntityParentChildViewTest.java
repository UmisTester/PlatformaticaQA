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
    private static final String USER = "user132@tester.com";

    @Test
    public void createParentTest() {

        WebDriver driver = getDriver();

        WebElement parentMenu = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        parentMenu.click();

        WebElement createNewParent = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewParent.click();

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
        Dropdown.selectByVisibleText(USER);

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> parentRecord = driver.findElements(By.xpath("//tbody/tr"));
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
        Dropdown.selectByVisibleText(USER);

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(childRecord.size(), 1);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[1]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"",USER));
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
        Dropdown.selectByVisibleText(USER);

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        List<WebElement> childRecord = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(childRecord.size(), 2);

        List<String> childRecordList = driver.findElement(By.xpath("//tbody/tr[2]"))
                .findElements(By.tagName("td"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList()).subList(1,9);
        Assert.assertEquals(childRecordList, Arrays.asList(STRING,TEXT,INT,DECIMAL,DATE,DATETIME,"",USER));

    }
}
