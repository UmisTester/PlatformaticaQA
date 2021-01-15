package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class Chain1Page extends BaseTablePage<Chain1Page, Chain1EditPage> {

    public Chain1Page(WebDriver driver) {
        super(driver);

    }

    @Override
    protected Chain1EditPage createEditPage() {
        return new Chain1EditPage(getDriver());

    }




}