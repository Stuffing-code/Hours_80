import java.util.ArrayList;

public class AtherMethod {


    //среднеарифмет из строки
    public static Integer average(ArrayList<String> value) {
        int result;
        result = (int) (Integer.parseInt(value.get(1)) - Integer.parseInt(value.get(0))) / 2;
        return result;
    }


    //фильтр на совпадение только цифровых строчек
    public static boolean checkMatchesNumerical(String text) {
        return text.matches("\\d+");
    }

    // фильтр без пробельный и только цифры
    private static String replaceAllStringFilterNoSpace(String text) {
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
        return list_value;

    }

    // проверка длины строки и вызов среднеарифмет если надо
    public static Integer checkStringSizeArray(ArrayList<String> array) {
//        System.out.println("list_value  =  " + list_value.toString());
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
        System.out.println("CONVERT_USD()");
        System.out.println("result = " + result);
        return String.valueOf(result);
    }

    // конвертор валюты
    private static String convertEur(String text) {
        int usd = 87;
        int result;
        result = Integer.parseInt(text) * usd;
        System.out.println("CONVERT_EUR()");
        System.out.println("result = " + result);
        return String.valueOf(result);
    }



    //проверка какая валюта и если необходимо конвертация в рубли
    public static String valueCheck(String[] array_string, String value) {
        String result = null;

        for (int i = 0; i < array_string.length; i++) {
            String check_string = array_string[i].replaceAll("\\d+", "");
            String no_space_number_string = replaceAllStringFilterNoSpace(array_string[i]);
            if (check_string.equals("USD")) {
                System.out.println("USD");
                result = convertUSD(value);
                break;
            } else if (check_string.equals("EUR")) {
                System.out.println("EUR");
                result = convertEur(value);
                break;
            } else if (check_string.equals("руб.")) {
                System.out.println("RUB");
                result = value;
                break;
            }
        }
        return result;
    }
}
