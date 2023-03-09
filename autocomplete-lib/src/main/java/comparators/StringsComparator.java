package comparators;

import java.util.Comparator;

/***
 * Comparator class for comparing strings in lexicographic in specific given column
 */
public class StringsComparator implements Comparator<String> {

    private int columnIndex;
    private String separator;

    public StringsComparator(int columnIndex, String separator) {
        this.columnIndex = columnIndex;
        this.separator = separator;
    }

    public int compare(String obj1, String obj2) {
        if (obj1 == obj2) {
            return 0;
        }
        if (obj1 == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        return obj1.split(",")[this.columnIndex].compareTo(obj2.split(",")[this.columnIndex]);
    }
}