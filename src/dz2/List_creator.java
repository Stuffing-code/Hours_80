package dz2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class List_creator {
    private int SIZE_BUFFER;
    private int INDEX_MAX;
    private static int count = 0;

    public List_creator(int SIZE_BUFFER) {
        this.SIZE_BUFFER = SIZE_BUFFER;
        this.INDEX_MAX = SIZE_BUFFER - 1;
    }

    public  int getSizeBuffer() {
        return SIZE_BUFFER;
    }

    public int getIndexMax() {
        return INDEX_MAX;
    }

    public void setSizeBuffer(int sizeBuffer) {
        SIZE_BUFFER = sizeBuffer;
        INDEX_MAX = sizeBuffer - 1;
    }

    public static int randomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }

    // if size buffer not even
    public static int returnMedianArray(LinkedList<Integer> list) {
        int index = (list.size() / 2);
        return list.get(index);
    }

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

    // display array on the console
    public  void displayList(LinkedList<Integer> list) {
//        System.out.print("List_creator: displayList(): [");
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i < getIndexMax()) {
                System.out.print(list.get(i) + ",");
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.print("] -> ");
    }

    // TODO: насколько это целесообразно?
    public void printUnsortedPart(LinkedList<Integer> list) {
        displayList(list);
    }

    public void printSortedPart(LinkedList<Integer> list_temp) {
        Collections.sort(list_temp); // sort list
        displayList(list_temp);

    }

    public  void printMedianValue(LinkedList<Integer> list_temp, int number) {
        if (getSizeBuffer() % 2 == 0) {
            boolean check = checkMedian_type(list_temp.get(4), list_temp.get(3));
            if (check) {
                //            System.out.println("List_creator: fifo(): " + solveMedianDouble(list_temp.get(4), list_temp.get(3)));
                System.out.println("[" + solveMedianDouble(list_temp.get(4), list_temp.get(3)) + "]");
            } else {
                //            System.out.println("List_creator: fifo(): " + solveMedianInt(list_temp.get(4), list_temp.get(3)));
                System.out.println("[" + solveMedianInt(list_temp.get(4), list_temp.get(3)) + "]");
            }
        } else {
            System.out.println(returnMedianArray(list_temp));
        }
    }

    //    main method
    public  void fifo(LinkedList<Integer> list, int number) {
        if (list.size() == getSizeBuffer()) {
            LinkedList<Integer> list_temp = new LinkedList<>(list); // create temp array
            printUnsortedPart(list);
            printSortedPart(list_temp);
            printMedianValue(list_temp, number);
            list.pop(); // remove first element
        }
        list.add(count, number);

        if (count < getIndexMax()) {
            count++;
        }
    }
}
