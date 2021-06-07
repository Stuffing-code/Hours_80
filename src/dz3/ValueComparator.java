package dz3;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Character> {
    Map<Character, Integer> base;

    public ValueComparator(Map<Character, Integer> base) {
        this.base = base;
    }

    @Override
    public int compare(Character a, Character b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }// returning 0 would merge keys    }
    }

}
