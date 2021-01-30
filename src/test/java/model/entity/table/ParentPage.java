package model.entity.table;

import model.BaseTablePage;
import model.entity.edit.ParentEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public final class ParentPage extends BaseTablePage<ParentPage, ParentEditPage> {

    @FindBy(xpath = "//table/tbody/tr/td[9]")
    private WebElement demoUser;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> rowList;

    @FindBy(xpath = "//table[@id = 'pa-all-entities-table']/tbody")
    private WebElement table;

    public ParentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ParentEditPage createEditPage() {
        return new ParentEditPage(getDriver());
    }

    public String getDefaultUser() { return demoUser.getText(); }

    public int getRowCount(){
        return rowList.size();
    }

    public String getTitleText(){
        return table.findElement(By.xpath("//tr/td[2]/a/div")).getText();
    }

    public String getCommentsText(){
        return table.findElement(By.xpath("//tr/td[3]/a/div")).getText();
    }

    public String getNumberText(){
        return table.findElement(By.xpath("//tr/td[4]/a/div")).getText();
    }

    public String getNumber1Text(){
        return table.findElement(By.xpath("//tr/td[5]/a/div")).getText();
    }

    public String getDataText(){
        return table.findElement(By.xpath("//tr/td[6]/a/div")).getText();
    }
}

