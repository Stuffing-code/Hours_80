import java.io.IOException;
import java.util.*;


/*
Требуется разработать программу для определения медианной зарплаты в Москве и Московской области по данным
портала hh.ru. Для этого потребуется разработать программу извлечения зарплаты из отдельной вакансии, а также сделать
 программу, которая будет обрабатывать результаты поиска, извлекать ссылки на вакансии и далее ранее реализованным
  методом извлекать зарплаты в список, а также просмотр ключевых навыков. В дальнейшем потребуется вычислить медианное значение из списка.
  Просомотр зарплат с учетом ключевых навыков.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String cssQuery_money = "span[data-qa=vacancy-serp__vacancy-compensation]";
        String cssQuery_title_and_url = "a[data-qa=vacancy-serp__vacancy-title]";
        String cssQuery_tag_list = "span[data-qa=bloko-tag__text]";
        String cssQuery_tag_zp = "span[class=bloko-header-2 bloko-header-2_lite]";

        String xpath_search = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[1]/div/input";
        String xpath_button = "/html/body/div[5]/div[3]/div/div/div[3]/div/div/form/div/div[2]/button/span[2]";


        WorkDriver chr = new WorkDriver();
        ArrayList<String> list_money_solve_median = new ArrayList<>();
        HashMap<String, List<String>> dir_key_and_value = new HashMap<>();
//        ArrayList<String> list_urls_vacancy = new ArrayList<>();
//        ArrayList<String> list_money_and_null_value = new ArrayList<>();
//        ArrayList<String> list_title_vacancy = new ArrayList<>();
//        HashSet<String> tag_link = new HashSet<>();

        // open browser
        chr.openSite("https://hh.ru/");
        chr.sendTextSearch("C# developer", xpath_search);
//        chr.sendTextSearch("Junior java developer", xpath_search);
        chr.clickButtonXpath(xpath_button);

        try {
            // получаем урл страницы
            while (true) {
                // парсим страницу для задания
                list_money_solve_median = chr.createListMoney(cssQuery_money);
                String correctUrl = chr.correctUrl();

                // парсим ссылоки на вакансии и создаем тэг листы и формируем слвоарь с ключами тэгами и
                // значениями их зарплатами
                long start_pars = System.currentTimeMillis();
                chr.createListsTagsTitlesUrls(cssQuery_title_and_url, cssQuery_tag_zp, cssQuery_tag_list, correctUrl);
//                list_urls_vacancy = WorkDriver.getUrl_list();
//                chr.createDir(list_urls_vacancy, cssQuery_tag_list, cssQuery_tag_zp);
//                tag_link = WorkDriver.getTag_link();
//                System.out.println(tag_link.toString());
//                chr.createDir(tag_link, list_urls_vacancy, cssQuery_tag_list, cssQuery_tag_zp);
                dir_key_and_value = WorkDriver.getDir_tag_and_value();

                long stop_pars = System.currentTimeMillis();
                System.out.println("я тут время затраченое на проход страницы затраченно " + (stop_pars - start_pars) + " миллисекунд");
//                list_title_vacancy = WorkDriver.getTitle_list();

                // парсим для получения списка зп включая их отсутсвие
//                list_money_and_null_value = chr.createListMoneAndNullValue(cssQuery_all);

                // переходим дальше
                chr.clickButtonByLinkText("дальше");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("end page");
        }

        // отоброжение масива и медианы зарплат
        String s = list_money_solve_median.toString();
        System.out.println(s);
        Median.printMedianValue(list_money_solve_median);
        System.out.println();
        chr.printDirKeyAndValue(dir_key_and_value, list_money_solve_median);

        // визуализация вакансий ссылок на них и зарплат, а также список ключевых навыков
//        for (int i = 0; i < list_urls_vacancy.size(); i++) {
////            System.out.println((i + 1) + ") " + list_title_vacancy.get(i));
////            System.out.println("\tURL: " + list_urls_vacancy.get(i));
////            System.out.println("\tЗаработная плата: " + list_money_and_null_value.get(i));
////            System.out.print("\tКлючевые навыки: ");
//            Elements elements_key = chr.createElements(list_urls_vacancy.get(i), cssQuery_tag_list);
//            String value = chr.createElements(list_urls_vacancy.get(i), cssQuery_tag_zp).text();
//            if (!value.equals("з/п не указана")) {
//                chr.createTagList(elements_key);
//            }
////            System.out.println("\n");
//        }
//        chr.quit();
    }
}
