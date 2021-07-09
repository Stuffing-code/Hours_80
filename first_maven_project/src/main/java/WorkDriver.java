import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WorkDriver {

    WebDriver driver;
    private static ArrayList<String> money_list_solve_median = new ArrayList<>();
    private static ArrayList<String> url_list = new ArrayList<>();
    private static ArrayList<String> money_list = new ArrayList<>();
    private static HashSet<Elements> tag_link = new HashSet<Elements>();
    private static HashMap<String, List<String>> dir_tag_and_value = new HashMap<>();

    private static ArrayList<String> tag_money = new ArrayList<>();
    private static ArrayList<String> tag_list = new ArrayList<>();
    private static ArrayList<String> title_list = new ArrayList<>();

    private static final String ZP = "Не указана.";

    public static HashMap<String, List<String>> getDir_tag_and_value() {
        return dir_tag_and_value;
    }

    public static HashSet<Elements> getTag_link() {
        return tag_link;
    }

    public static ArrayList<String> getUrl_list() {
        return url_list;
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

    public void quit() {
        driver.quit();
    }

    public String correctUrl() {
        return driver.getCurrentUrl();
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

    public Elements createElements(String url_search, String cssQuery) throws IOException {
        return getPage(url_search).select(cssQuery);
    }


    public void createDir(Elements select_tags, String[] value_tag_zp) {

        for (Element el :
                select_tags) {
            dir_tag_and_value.computeIfAbsent(el.text(), k -> new ArrayList<>()).addAll(AtherMethod.createArray(value_tag_zp));
        }
    }


    public void printDirKeyAndValue(HashMap<String, List<String>> dir, ArrayList<String> arrayMedian) {
        dir.forEach((key, value) -> {
            System.out.print("У вакансий с ключевым навыком " + key + " выборка зарплат: ");
            for (int i = 0; i < value.size(); i++) {
                if (i < value.size() - 1) {
                    System.out.print(value.get(i) + ", ");
                } else {
                    System.out.print(value.get(i) + " рублей.");
                }
            }
            System.out.println();
            System.out.print("\tПри учете медианы зарплат на данный запрос = ");
            Median.printMedianValue(arrayMedian);
            System.out.println();
        });
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
    public void createListsTagsTitlesUrls(String cssQuery, String cssQuery_tag_zp, String cssQuery_tag_list, String correctUrl) throws IOException {
//        List<WebElement> urls_page = driver.findElements(By.cssSelector(cssQuery));
        Elements urls_page = Jsoup.connect(correctUrl).maxBodySize(Integer.MAX_VALUE).get().select(cssQuery);

        for (Element url :
                urls_page) {
//                url_list.add(s.getAttribute("href"));
//                title_list.add(s.getText());
            System.out.println(url.text());
            long start_pars = System.currentTimeMillis();
            Elements select_tags = Jsoup.connect(url.attr("href")).get().select(cssQuery_tag_list);
            String[] value_tag_zp = Jsoup.connect(url.attr("href")).get().select(cssQuery_tag_zp).text().split("до");
//                tag_link.add(select_tags);
            createDir(select_tags, value_tag_zp);
            long stop_pars = System.currentTimeMillis();
            System.out.println("createListsTagsTitlesUrls(): проход по url " + (stop_pars - start_pars));
        }
    }

    public void sendTextSearch(String text_search, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(text_search);
    }

}