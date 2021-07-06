import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeStart {

    WebDriver driver;

    public void openSite(String text_site) {
        String webDriverKey = "webdriver.chrome.driver";
        String webDriverValue = "C:\\Users\\stuff\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe";
        System.setProperty(webDriverKey, webDriverValue);
        driver = new ChromeDriver();
        driver.get(text_site);
    }

    public void openNewTab(String url) {
        driver.get(url);
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public void quit() {
        driver.quit();
    }

    public void timeOutdriver() {
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
    }

    public void clickButtonByLinkText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public void clickButtonXpath(String button_xpath) {

        driver.findElement(By.xpath(button_xpath)).click();
    }

    public void sendTextSearch(String text_search, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(text_search);
    }

    public void clearTest() {
        driver.findElement(By.className("gLFyf")).clear();
    }

}