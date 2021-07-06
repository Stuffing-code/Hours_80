import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {

    private static ArrayList<String> money_list = new ArrayList<>();

    public static ArrayList<String> getMoney_list() {
        return money_list;
    }

//    todo дописать парсер
    public String getPageString(String url_search) {
        //        return Jsoup.connect(url_search).maxBodySize(Integer.MAX_VALUE).get().text();
        return url_search;
    }

    public Document getPage(String url_search) throws IOException {
//        return Jsoup.connect(url_search).maxBodySize(Integer.MAX_VALUE).get().text();
        return Jsoup.parse(new URL(url_search), 3000);
    }

    public void addResult(Integer result) {
        getMoney_list().add(String.valueOf(result));
    }

    public ArrayList<String> createListMoney(Document page, String cssQuery) {

        Elements elements_page = page.select(cssQuery);

        for (Element el :
                elements_page) {
            String[] arrays_strings = el.text().split(" ");
            ArrayList<String> list_value = AtherMethod.createArray(arrays_strings);
            addResult(AtherMethod.checkStringSizeArray(list_value));

        }
        return getMoney_list();
    }

//    public  HashSet<String> createListUrl(Document page, String cssQuery) {
//        HashSet<String> urls = new HashSet<>();
//
//        Elements urls_page = page.select(cssQuery);
//        for (Element s:
//                urls_page) {
////            System.out.println(s.attr("href"));
//            urls.add(s.attr("href"));
//        }
//        return urls;
//    }
}
