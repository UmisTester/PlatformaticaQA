import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Group_lets_do_it_Test extends BaseTest {
    @Test
    public void tatianaChueva() throws InterruptedException {

        WebDriver browser = getDriver();
        browser.get("https://docs.oracle.com/en/java");
        WebElement name = browser.findElement(By.xpath("//div[@class='ohc-sidebar hidden-xs'] //a[contains(text(), 'Java')]"));

        Assert.assertEquals(name.getText(), "Java");

        Thread.sleep(3000);
    }

    @Ignore
    @Test
    public void secondTestChuevaT() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.get("https://docs.oracle.com/en/java");

        WebElement button = driver.findElement(By.xpath("//div[@class='ohc-sidebar hidden-xs'] //a[contains(text(), 'Cloud')]"));
        button.click();

        WebElement name = driver.findElement(By.xpath("//span[contains(text(),'How can Oracle Cloud Infrastructure products help?')]"));
        Assert.assertEquals(name.getText(), "How can Oracle Cloud Infrastructure products help?");

        Thread.sleep(3000);
    }

    @Test
    public void volgaStaravoitavaTest() {
        WebDriver driver = getDriver();
        driver.get("https://www.amazon.com/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchInput.sendKeys("ball");
        searchInput.submit();
        WebElement searchResults = driver.findElement(By.xpath("//span[contains(text(),'\"ball\"')]"));
        Assert.assertTrue(searchResults.isDisplayed());
    }

}
