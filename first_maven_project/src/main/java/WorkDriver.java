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
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WorkDriver {

    WebDriver driver;
    private static ArrayList<String> money_list_solve_median = new ArrayList<>();
    private static ArrayList<String> url_list = new ArrayList<>();
    private static ArrayList<String> title_list = new ArrayList<>();
    private static ArrayList<String> money_list = new ArrayList<>();
    private static ArrayList<String> tag_list = new ArrayList<>();
    private static final String ZP = "Не указана.";
    private static HashSet<String> tag_link = new HashSet<>();
    private static HashMap<String, List<String>> dir_tag_and_value = new HashMap<>();
    private static ArrayList<String> tag_money = new ArrayList<>();

    public static HashMap<String, List<String>> getDir_tag_and_value() {
        return dir_tag_and_value;
    }

    public static HashSet<String> getTag_link() {
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

    // create Tag List и вывод сообщения на консоль при необходимости
    public void createTagList(Elements elements_page) {

        for (int i = 0; i < elements_page.size(); i++) {
//            if (i < elements_page.size() - 1) {
//                System.out.print(elements_page.get(i).text() + ", ");
//            } else {
//                System.out.print(elements_page.get(i).text() + ".");
//            }
            tag_link.add(elements_page.get(i).text());

        }
    }

    public HashMap<String, List<String>> createDir(ArrayList<String> urls, String cssQuery, String cssQuery_tag_zp) throws IOException {

        for (String url :
                urls) {
            tag_money.clear();
            Elements select_tags = Jsoup.connect(url).get().select(cssQuery);
            Elements select_zp = Jsoup.connect(url).get().select(cssQuery_tag_zp);
            String value = select_tags.text();
            String[] value_tag_zp = Jsoup.connect(url).get().select(cssQuery_tag_zp).text().split("до");
//            System.out.println("WebDriver() createTagListCssQuery(): " + value);
            createTagList(select_tags);
            for (Element el :
                    select_tags) {
                if (!Jsoup.connect(url).get().select(cssQuery_tag_zp).text().equals("з/п не указана")) {
                        ArrayList<String> array = AtherMethod.createArray(value_tag_zp);
                    dir_tag_and_value.computeIfAbsent(el.text(), k -> new ArrayList<>()).addAll(array);
                }
//                System.out.println(dir_tag_and_value.toString());
            }
        }
        return dir_tag_and_value;
    }

//    public HashMap<String, List<String>> createDir(HashSet<String> hashSet, ArrayList<String> urls, String cssQuery_tag_list_contains, String cssQuery_tag_zp) throws IOException {
//        for (String tag :
//                hashSet) {
////            System.out.println("WebDriver() createDir(): начало проверки тэга " + tag);
////            System.out.println("Начало проверки тэга " + tag);
//            ArrayList<String> tag_money = new ArrayList<>();
//            for (int i = 0; i < urls.size(); i++) {
//                boolean flag = createElements(urls.get(i), cssQuery_tag_list_contains).text().contains(tag);
//                if (flag) {
//                    String[] value = Jsoup.connect(urls.get(i)).get().select(cssQuery_tag_zp).text().split("до");
////                    String[] value = createElements(urls.get(i), cssQuery_tag_zp).text().split("до");
//                    if (!Jsoup.connect(urls.get(i)).get().select(cssQuery_tag_zp).text().equals("з/п не указана")) {
//                        ArrayList<String> array = AtherMethod.createArray(value);
//
////                        System.out.println("WebDriver() createDir() value_list :" + array.toString());
//                        tag_money.addAll(Collections.singleton(String.valueOf(AtherMethod.checkStringSizeArray(array))));
////                        System.out.println("WebDriver() createDir():"  + tag_money.toString());
//
//                        dir_tag_and_value.put(tag, tag_money);
//                    }
//                }
//            }
//        }
//        return dir_tag_and_value;
//    }

    public void printDirKeyAndValue(HashMap<String, List<String>> dir, ArrayList<String> arrayMedian) {
        dir.forEach((key, value) -> {
            System.out.print("У ключевого навыка " + key + " выборка зарплат зарплаты: ");
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
//            for (String string :
//                    arrays_strings) {
////                System.out.println("WebDriver() createListMoney(): arrays_strings = " + string);
//            }
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

    public void sendTextSearch(String text_search, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(text_search);
    }

}