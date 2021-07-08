import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkDriver {

    WebDriver driver;
    private static ArrayList<String> money_list_solve_median = new ArrayList<>();
    private static ArrayList<String> url_list = new ArrayList<>();
    private static ArrayList<String> title_list = new ArrayList<>();
    private static ArrayList<String> money_list = new ArrayList<>();
    private static ArrayList<String> tag_list = new ArrayList<>();
    private static final String ZP = "Не указана.";


    public static ArrayList<String> getUrl_list() {
        return url_list;
    }

    public static ArrayList<String> getTitle_list() {
        return title_list;
    }

    public static ArrayList<String> getMoney_list_solve_median() {
        return money_list_solve_median;
    }

    public void addResult(Integer result) {
        money_list_solve_median.add(String.valueOf(result));
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

    public Document getPage(String url_search) throws IOException {
        return Jsoup.parse(new URL(url_search), 3000);

    }

    public void createTagList(String url_search, String cssQuery) throws IOException {
        Elements elements_page = getPage(url_search).select(cssQuery);

        for (int i = 0; i < elements_page.size(); i++) {
            if (i < elements_page.size() - 1) {
                System.out.print(elements_page.get(i).text() + ", ");
            } else {
                System.out.print(elements_page.get(i).text() + ".");
            }
        }
//
//        for (Element el :
//                elements_page) {
//            System.out.print(el.text() + " ");
//        }
    }

    // массив зарплат
    public ArrayList<String> createListMoney(String cssQuery) {

        List<WebElement> list_link = driver.findElements(By.cssSelector(cssQuery));

        for (WebElement el :
                list_link) {

            String[] arrays_strings = el.getText().split(" ");

            ArrayList<String> list_value = AtherMethod.createArray(arrays_strings);

            addResult(AtherMethod.checkStringSizeArray(list_value));
        }
        return money_list_solve_median;
    }

    // массив зарплат включая их отсутсвие
    public ArrayList<String> createListMoneAndNullValue(String cssQuery) {
        List<WebElement> list_link = driver.findElements(By.cssSelector(cssQuery));

        for (WebElement el :
                list_link) {
//            System.out.println("WorkDriver() createListMoneAndNullValue(): " + list_value.toString());
            if (!el.getText().equals("")) {
                money_list.add(el.getText());
            } else {
                money_list.add(ZP);
            }
        }
        return money_list;
    }

    // массив ссылок и названий вакансий
    public void createListUrlAndTitleVacancy(String cssQuery) {
        List<WebElement> urls_page = driver.findElements(By.cssSelector(cssQuery));
        for (WebElement s :
                urls_page) {
//            System.out.println("WorkDriver() createListUrl(): " + s.getAttribute("href"));
            url_list.add(s.getAttribute("href"));
            title_list.add(s.getText());
        }
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