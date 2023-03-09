package comparators;

import java.util.Comparator;

/***
 * Comparator class for comparing nums in specific given column
 */
public class NumberComparator implements Comparator<String> {

    private int columnIndex;
    private String separator;

    public NumberComparator(int columnIndex, String separator) {
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

        double i1 = Double.parseDouble(obj1.split(separator)[columnIndex]);
        double i2 = Double.parseDouble(obj2.split(separator)[columnIndex]);

        return Double.compare(i1, i2);
    }
}