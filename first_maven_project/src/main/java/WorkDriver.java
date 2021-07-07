import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkDriver {

    WebDriver driver;
    private static ArrayList<String> money_list = new ArrayList<>();

    public static ArrayList<String> getMoney_list() {
        return money_list;
    }

    public void addResult(Integer result) {
        getMoney_list().add(String.valueOf(result));
    }

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickButtonByLinkText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public void clickButtonXpath(String button_xpath) {

        driver.findElement(By.xpath(button_xpath)).click();
    }

    public ArrayList<String> createListMoney(String cssQuery) {

        List<WebElement> list_link = driver.findElements(By.cssSelector(cssQuery));

        for (WebElement el :
                list_link) {

            String[] arrays_strings = el.getText().split(" ");

            ArrayList<String> list_value = AtherMethod.createArray(arrays_strings);

            addResult(AtherMethod.checkStringSizeArray(list_value));
        }
        return getMoney_list();
    }

    public void scrollPage(int value) {
        for (int i = 0; i < value; i++) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
        }
    }

    public void sendTextSearch(String text_search, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(text_search);
    }

    public void clearTest() {
        driver.findElement(By.className("gLFyf")).clear();
    }

}