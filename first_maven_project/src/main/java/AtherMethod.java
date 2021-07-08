import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class AtherMethod {


    //среднеарифмет из строки с проверкой на возможное равенство
    public static Integer average(ArrayList<String> value) {
        int result;
        if (Integer.parseInt(value.get(1)) == Integer.parseInt(value.get(0))) {
            result = Integer.parseInt(value.get(1));
//            System.out.println("AtherMethod() avarage(value ==): " + result);
        } else {
            result = (Integer.parseInt(value.get(1)) - Integer.parseInt(value.get(0))) / 2;
//            System.out.println("AtherMethod() avarage(): " + result);
        }
        return result;
    }

    //фильтр на совпадение только цифровых строчек
    public static boolean checkMatchesNumerical(String text) {
        return text.matches("\\d+");
    }

    // фильтр без пробельный и только цифры
    public static String replaceAllStringFilterNoSpace(String text) {
        String check_string = text.replaceAll("[^0-9]", "");
        return check_string.replaceAll("\\s+", "");
    }

    public static ArrayList<String> createArray(String[] array) {
        ArrayList<String> list_value = new ArrayList<>();


        for (int i = 0; i < array.length; i++) {

            String value = replaceAllStringFilterNoSpace(array[i]);
            if (checkMatchesNumerical(value)) {
                list_value.add(valueCheck(array, value));
            }
        }
//        System.out.println("AtherMethod() createArray(): " + list_value.toString());
        return list_value;

    }

    // проверка длины строки и вызов среднеарифмет если надо
    public static Integer checkStringSizeArray(ArrayList<String> array) {
//        System.out.println("AtherMethod() checkStringSizeArray() " + array.toString());
        if (array.size() > 1) {
            return average(array);
        } else {
            return Integer.parseInt(array.get(0));
        }
    }

    // конвертор валюты

    private static String convertUSD(String text) {
        int usd = 74;
        int result;
        result = Integer.parseInt(text) * usd;
//        System.out.println("AtherMethod() CONVERT_USD()  = " + result);
//        System.out.println("result = " + result);
        return String.valueOf(result);
    }

    // конвертор валюты
    private static String convertEur(String text) {
        int eur = 87;
        int result;
        result = Integer.parseInt(text) * eur;
//        System.out.println("AtherMethod() CONVERT_EUR()" + result);
//        System.out.println("result = " + result);
        return String.valueOf(result);
    }


    //проверка какая валюта и если необходимо конвертация в рубли
    public static String valueCheck(String[] array_string, String value) {
        String result = null;

        for (String s : array_string) {
            String check_string = s.replaceAll("\\d+", "");
            String no_space_number_string = replaceAllStringFilterNoSpace(s);
            if (check_string.contains("USD")) {
//                System.out.println("AtherMethod() valueCheck(): USD");
                result = convertUSD(value);
                break;
            } else if (check_string.contains("EUR")) {
//                System.out.println("AtherMethod() valueCheck(): EUR");
                result = convertEur(value);
                break;
            } else if (check_string.contains("руб.")) {
//                System.out.println("AtherMethod() valueCheck(): RUB");
                result = value;
                break;
            }
        }
        return result;
    }

    public static boolean ifElseContains(String arg, Elements elements) {
        boolean flag = false;
        for (int i = 0; i < elements.size(); i++) {
            if (arg.equals(elements.get(i).text())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
