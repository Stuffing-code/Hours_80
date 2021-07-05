import com.google.common.base.CharMatcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static ArrayList<String> money_list = new ArrayList<>();

    public  Document getPage(String url_search) throws IOException {
        return Jsoup.parse(new URL(url_search), 3000);
    }


    public ArrayList<String> createListMoney(Document page, String cssQuery) {

        Elements elements_page = page.select(cssQuery);

        for (Element el :
                elements_page) {
//            System.out.println("Parser: createListMoney() " + el.text());
            //todo Нужно придумат ькоректное регулярное выражение
            money_list.add(el.text().replaceAll("[^0-9]", ""));
        }
        return money_list;
    }

    public  HashSet<String> createListUrl(Document page, String cssQuery) {
        HashSet<String> urls = new HashSet<>();

        Elements urls_page = page.select(cssQuery);
        for (Element s:
                urls_page) {
//            System.out.println(s.attr("href"));
            urls.add(s.attr("href"));
        }
        return urls;
    }



//        test
//        Document page = getPage();
//        Elements ssilki = page.select("a[class=bloko-link]");
//        Elements zp = page.select("span[data-qa=vacancy-serp__vacancy-compensation]");
//        String table = page.select("span[data-qa=vacancy-serp__vacancy-compensation]").first().text();
////        String zp_int = getNumerical(table);
//        System.out.println(table);
//        String numberOnly= table.replaceAll("[^0-9]", "");
//        System.out.println(numberOnly);
//
//        for (Element s:
//             ssilki) {
//            System.out.println(s.attr("href"));
//            urls.add(s.attr("href"));
//        }
//        List<String> list_zp = new ArrayList<>();
//        for (Element string:
//             zp) {
//            System.out.println(string.text().replaceAll("[^0-9]", ""));
//            String new_zp = string.text().replaceAll("[^0-9]", "");
//            list_zp.add(new_zp);
//        }
//        System.out.println();

}
