import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String cssQuery_money = "span[data-qa=vacancy-serp__vacancy-compensation]";
//        String cssQuery_urls_vakans = "a[class=bloko-link]";
        String xpath_search = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[1]/div/input";
        String xpath_button = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[2]/button/span[2]";

        ChromeStart chr = new ChromeStart();
        Parser parser = new Parser();
        ArrayList<String> list_money = new ArrayList<>();

        chr.openSite("https://hh.ru/");
        chr.sendTextSearch("Ruby", xpath_search);
        chr.clickButtonXpath(xpath_button);

        try {
            // получаем урл страницы
            while (true) {
                // получаем урл страницы
//                chr.timeOutdriver();
                String currentUrl = chr.currentUrl();
                // парсим страницу
                Document page_parse = parser.getPage(currentUrl);
                list_money = parser.createListMoney(page_parse, cssQuery_money);
                // переходим дальше
                chr.clickButtonByLinkText("дальше");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("end page");
        }

        // отоброжение масива
        String s = list_money.toString();
        System.out.println(s);


//        // вызываем парсер и создаем список гиперссылкок со страницы
//        Parser parser = new Parser();
//        Document page_pars = parser.getPage(currentUrl);
//        HashSet<String> listUrl = parser.createListUrl(page_pars, cssQuery_urls_vakans);
////        for (String url :
////                listUrl) {
////            chr.openNewTab(url);
////        }
//        // проверяем урл на валидность и проходимся по ним
//        for (String url :
//                listUrl) {
//            String element = url.substring(0, 5);
//            if (element.equals("https")) {
//                System.out.println(url);
//                chr.openNewTab(url);
//            } else {
//                System.out.println("error URL");
//            }
//        }
    }
}
