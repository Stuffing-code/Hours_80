package dz3;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Logic {
    private String TEXT;
    private final int FIRST = 1;
    private final int INCREASED = 1;

    public int getINCREASED() {
        return INCREASED;
    }

    public int getFIRST() {
        return FIRST;
    }

    public Logic(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public LinkedList<Character> createListChars(String text) {
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < text.length(); i++) {
//            System.out.println("Logic(): createListChars(): " + text.toLowerCase().charAt(i));
            list.add(text.toLowerCase().charAt(i));
        }
        return list;
    }

    public Map<Character, Integer> createMapCharInt(LinkedList<Character> listChars) {
        Map<Character, Integer> dictionary = new LinkedHashMap<>();
        for (char objectKey :
                listChars) {
            if (objectKey != ' '){
                if (dictionary.isEmpty()) {
                    dictionary.put(objectKey, getFIRST());
                } else if (dictionary.containsKey(objectKey)) {
                    dictionary.put(objectKey, dictionary.get(objectKey) + getINCREASED());
                } else {
                    dictionary.put(objectKey, getFIRST());
                }
//                System.out.println("Logic(): createMapCharInt(): " + objectKey + " ");

            }
        }
        return dictionary;
    }
}
