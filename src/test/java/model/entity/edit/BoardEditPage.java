package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.common.CalendarEntityPage;
import model.entity.common.BoardPageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

public final class BoardEditPage extends EntityBaseEditPage<BoardPageEntityBase> {

    @FindBy(id = "string")
    private WebElement dropDownStatus;

    @FindBy(id = "text")
    private WebElement textInput;

    @FindBy(xpath = "//input[@name='entity_form_data[int]']")
    private WebElement intInput;

    @FindBy(id = "decimal")
    private WebElement decimalInput;

    @FindBy(id = "date")
    private WebElement datePlaceholder;

    @FindBy(id = "datetime")
    private WebElement dateTimePlaceholder;

    @FindBy(xpath = "//td[7]")
    private WebElement time;

    @FindBy(xpath = "//button[@data-id='user']/div/div")
    private WebElement dropdownUser;

    @FindBy(id = "user")
    private WebElement appTester1;

    public BoardEditPage(WebDriver driver) {
        super(driver);
    }

    public BoardEditPage selectDropOption (String status) {
        Select drop = new Select(dropDownStatus);
        drop.selectByVisibleText(status);
        return  this;
    }

    public BoardEditPage fillText (String text) {
        ProjectUtils.fill(getWait(), textInput, text);
        return  this;
    }

    public BoardEditPage fillInt (String number)  {
        ProjectUtils.fill(getWait(), intInput, number);
        return this;
    }

    public BoardEditPage fillDecimal (String decimal)  {
        ProjectUtils.fill(getWait(), decimalInput, decimal);
        return this;
    }

    public BoardEditPage selectUser (String user) {
        ProjectUtils.scroll(getDriver(), dropdownUser);
        Select dropUser = new Select(dropdownUser);
        dropUser.selectByVisibleText(user);
        return this;
    }

    public String[] getCreatedTime() {
        return  time.getText().split(" ");
    }

    public BoardEditPage fillform(String status, String text, String number, String decimal, String user) {
        Select drop = new Select(dropDownStatus);
        drop.selectByVisibleText(status);
        ProjectUtils.fill(getWait(), textInput, text);
        ProjectUtils.fill(getWait(), intInput, number);
        ProjectUtils.fill(getWait(), decimalInput, decimal);

        CalendarEntityPage calendar = new CalendarEntityPage(getDriver());
        dateTimePlaceholder.click();
        calendar.clickOnCalendarDate(getDriver());

        datePlaceholder.click();
        calendar.clickOnCalendarDate(getDriver());

        ProjectUtils.scroll(getDriver(), dropdownUser);
        ProjectUtils.click(getDriver(), dropdownUser);
        Select dropUser = new Select(appTester1);
        dropUser.selectByVisibleText(user);
        return this;
    }

    @Override
    protected BoardPageEntityBase createPage() {
        return new BoardPageEntityBase(getDriver());
    }
}
