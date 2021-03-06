package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.InitEditPage;
import model.entity.view.InitViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InitPage extends EntityBaseTablePage<InitPage, InitEditPage, InitViewPage> {

    @FindBy(xpath = "//td[.='User 1 Demo']")
    private WebElement defaultUser;

    @FindBy(xpath = "//td/i")
    private List<WebElement> switchQty;

    public InitPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InitEditPage createEditPage() {
        return new InitEditPage(getDriver());
    }

    @Override
    protected InitViewPage createViewPage() {
        return new InitViewPage(getDriver());
    }

    public String getDefaultUser() {
        return defaultUser.getText();
    }

    public int checkSwitchQty() {
        return switchQty.size();
    }
}
