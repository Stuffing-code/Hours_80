import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String cssQuery_money = "span[data-qa=vacancy-serp__vacancy-compensation]";

        String xpath_search = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[1]/div/input";
        String xpath_button = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[2]/button/span[2]";

        WorkDriver chr = new WorkDriver();
        ArrayList<String> list_money = new ArrayList<>();

        chr.openSite("https://hh.ru/");
        chr.sendTextSearch("Шеф повар", xpath_search);
        chr.clickButtonXpath(xpath_button);

        try {
            // получаем урл страницы
            while (true) {
                // парсим страницу
                list_money = chr.createListMoney(cssQuery_money);
                // переходим дальше
                chr.clickButtonByLinkText("дальше");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("end page");
        }

        // отоброжение масива
        String s = list_money.toString();
        System.out.println(s);

        Median.printMedianValue(list_money);


    }
}
