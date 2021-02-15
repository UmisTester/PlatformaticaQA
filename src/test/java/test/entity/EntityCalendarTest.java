package test.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import model.entity.table.CalendarPage;
import model.entity.common.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;

@Run(run = RunType.Multiple)
public class EntityCalendarTest extends BaseTest {

    private static final String STRING = UUID.randomUUID().toString();
    private static final String NUMBER = "25";
    private static final String NUMBER1 = "56.23";
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    private static final String TEXT_COMMENTS = "DON'T WORRY, BE HAPPY!";
    private static final String TITLE_FIELD = UUID.randomUUID().toString();
    private static final String TITLE_FIELD_NEW = UUID.randomUUID().toString();

    @Test
    public void newCalendar() {

        CalendarPage calendarPage = new MainPage(getDriver())
                .clickMenuCalendar()
                .clickNewFolder()
                .sendKeys(STRING, NUMBER, NUMBER1, DATE)
                .clickDataTime()
                .clickSaveButton()
                .clickThisList();

        Assert.assertEquals(calendarPage.getTitleText(), STRING);
        Assert.assertEquals(calendarPage.getNumberText(), NUMBER);
        Assert.assertEquals(calendarPage.getNumber1Text(), NUMBER1);
        Assert.assertEquals(calendarPage.getDataText(), DATE);
        Assert.assertEquals(calendarPage.getRowCount(), 1);
    }

    @Test(dependsOnMethods = "newCalendar")
    public void editCalendar() {

        WebDriver driver = getDriver();

        WebElement calendar = driver.findElement(By.xpath("//p[contains(text(),'Calendar')]"));
        ProjectUtils.click(driver, calendar);

        WebElement list = driver.findElement(By.xpath("//div[@class='content']//li[2]"));
        list.click();

        WebElement editList = driver.findElement(By.xpath("//td/div/button"));
        editList.click();

        WebElement clickEdit =
                getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='edit']")));
        ProjectUtils.click(driver, clickEdit);

        WebElement str =
                getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='string']")));
        ProjectUtils.fill(getWebDriverWait(), str, "New Record");

        WebElement text = driver.findElement(By.xpath("//textarea[@id='text']"));
        text.clear();
        text.sendKeys(TEXT_COMMENTS);

        WebElement number = driver.findElement(By.xpath("//input[@id='int']"));
        number.sendKeys("777");

        WebElement save = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
        ProjectUtils.click(driver, save);

        WebElement resultEdit = driver.findElement(By.xpath("//tr//td[3]"));
        Assert.assertEquals(resultEdit.getText(), TEXT_COMMENTS);
    }

    public void setValue(WebDriver driver, String title, String text, int num, double decimal) {

        WebElement titleField = driver.findElement(By.xpath("//input[@name='entity_form_data[string]']"));
        titleField.clear();
        titleField.sendKeys(title);

        WebElement textPlaceholder = driver.findElement(By.xpath("//textarea[@name='entity_form_data[text]']"));
        textPlaceholder.clear();
        textPlaceholder.sendKeys(text);

        WebElement fieldInt = driver.findElement(By.xpath("//input[@name='entity_form_data[int]']"));
        fieldInt.clear();
        fieldInt.sendKeys(String.valueOf(num));

        WebElement fieldDecimal = driver.findElement(By.xpath("//input[@name='entity_form_data[decimal]']"));
        fieldDecimal.clear();
        fieldDecimal.sendKeys(String.valueOf(decimal));

        WebElement saveBtn = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver, saveBtn);

        WebElement listBtn = driver.findElement(By.xpath("//ul[@role='tablist']//i[contains(text(),'list')]"));
        listBtn.click();
    }

    @Test
    public void newRecord() {
        WebDriver driver = getDriver();

        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Calendar')]"));
        ProjectUtils.click(driver, tab);

        WebElement createNewFolder = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFolder.click();

        setValue(driver, TITLE_FIELD, "test_1", 342, 0);

        getWebDriverWait().until(driver1 -> driver.findElement(By.xpath("//tr[@data-index]")).isDisplayed());
    }

    @Test(dependsOnMethods = "newRecord")
    public void editRecord() {
        WebDriver driver = getDriver();

        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Calendar')]"));
        ProjectUtils.click(driver, tab);

        WebElement listBtn = driver.findElement(By.xpath("//ul[@role='tablist']//i[contains(text(),'list')]"));
        listBtn.click();

        WebElement dropdown = driver.findElement(By.xpath("//div[@class='dropdown pull-left']"));
        dropdown.click();

        WebElement editBtn = driver.findElement(By.xpath("//a[contains(text(),'edit')]"));
        ProjectUtils.click(driver, editBtn);

        setValue(driver, TITLE_FIELD_NEW, "test test test", 256, 0.1);

        WebElement nameString = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", TITLE_FIELD_NEW)));
        Assert.assertEquals(nameString.getText(), TITLE_FIELD_NEW);

        WebElement nameText = driver.findElement(By.xpath("//td/a[contains(text(),'test test test')]"));
        Assert.assertEquals(nameText.getText(), "test test test");

        WebElement intField = driver.findElement(By.xpath("//td/a[contains(text(),'256')]"));
        Assert.assertEquals(intField.getText(), "256");

        WebElement decimalField = driver.findElement(By.xpath("//td/a[contains(text(),'0.10')]"));
        Assert.assertEquals(decimalField.getText(), "0.10");
    }

    @Test(dependsOnMethods = {"newRecord", "editRecord"})
    public void deleteRecord() {
        WebDriver driver = getDriver();

        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Calendar')]"));
        ProjectUtils.click(driver, tab);

        WebElement listBtn = driver.findElement(By.xpath("//ul[@role='tablist']//i[contains(text(),'list')]"));
        listBtn.click();

        WebElement dropdownDelete = driver.findElement(By.xpath("//div[@class='dropdown pull-left']"));
        dropdownDelete.click();

        WebElement deleteBtn = driver.findElement(By.xpath("//a[contains(text(),'delete')]"));
        ProjectUtils.click(driver, deleteBtn);

        WebElement RecycleBin = driver.findElement(By.xpath("//i[contains(text(),'delete_outline')]"));
        RecycleBin.click();

        WebElement deleteRecord = driver.findElement(By.xpath(String.format("//b[contains(text(), '%s')]", TITLE_FIELD_NEW)));
        getWebDriverWait().until(driver1 -> deleteRecord.isDisplayed());
    }
}