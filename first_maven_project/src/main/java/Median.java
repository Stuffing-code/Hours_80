import java.util.ArrayList;

public class Median {

    //todo сделать медианный расчет
    // solve median array
    public static double solveMedianDouble(int number_1, int number_2) {
        return (double) (number_1 + number_2) / 2;
    }

    public static int solveMedianInt(int number_1, int number_2) {
        return (number_1 + number_2) / 2;
    }

    // check double or int type
    public static boolean checkMedian_type(int number_1, int number_2) {
        return (number_1 + number_2) % 2 == 1;
    }

    // if size buffer not even
    public static String returnMedianArray(ArrayList<String> list) {
        int index = (list.size() / 2);
        return list.get(index);
    }

    public static void printMedianValue(ArrayList<String> arrayList) {
        int index_1 = arrayList.size() / 2;
        int index_2 = (arrayList.size() / 2) - 1;
        if (arrayList.size() % 2 == 0) {
            boolean check = checkMedian_type(Integer.parseInt(arrayList.get(index_1)), Integer.parseInt(arrayList.get(index_2)));
            if (check) {
                //            System.out.println("List_creator: fifo(): " + solveMedianDouble(list_temp.get(index_1), list_temp.get(index_2)));
                System.out.println(solveMedianDouble(Integer.parseInt(arrayList.get(index_1)), Integer.parseInt(arrayList.get(index_2))) + " рублей.");
            } else {
                //            System.out.println("List_creator: fifo(): " + solveMedianInt(list_temp.get(index_1), list_temp.get(index_2)));
                System.out.println(solveMedianInt(Integer.parseInt(arrayList.get(index_1)), Integer.parseInt(arrayList.get(index_2))) + " рублей.");
            }
        } else {
            System.out.println(returnMedianArray(arrayList) + " рублей.");
        }
    }

}
