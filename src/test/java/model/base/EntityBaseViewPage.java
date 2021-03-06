package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EntityBaseViewPage extends BasePage {

    @FindBy(css = ".pa-view-field")
    private List<WebElement> labeledValues;

    @FindBy (xpath = "//div[@class='form-group']/p")
    private WebElement user;

    public EntityBaseViewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getValues() {
        return labeledValues.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getUser(){
        return user.getText();
    }
}
