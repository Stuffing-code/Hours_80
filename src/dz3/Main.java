package dz3;

import java.util.*;

public class Main {
    /*
    "Требуется сделать поиск частовстречающихся символов в массиве строк книги Война́ и мир Л.Н. Толстого.
На выходе ожидаю получить распечатанные коллекции уникальных сиволов, отсортированных в порядке убывания частоты
для каждого случая, когда был добавлен новый символ или символы поменяли свою позицию в рейтинге частовстречающихся
сиволов.
Опционально желательно построить визуализацию графа переключений ""рейтингов"" часто используемых символов
"
     */
    public static void main(String[] args) {
        Text text = new Text();
        Logic logic = new Logic(text.getText());

        LinkedList<Character> listChars = logic.createListChars(logic.getTEXT());
        Map<Character, Integer> mapCharInt = logic.createMapCharInt(listChars);

        ValueComparator valueComparator = new ValueComparator(mapCharInt);
        TreeMap<Character, Integer> sorted_map = new TreeMap<>(valueComparator);

        sorted_map.putAll(mapCharInt);
        System.out.println("sorted = " + sorted_map);

    }
}

