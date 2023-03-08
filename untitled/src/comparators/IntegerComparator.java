package comparators;

import java.util.Comparator;

/***
 * Comparator class for comparing nums in specific given column
 */
public class IntegerComparator implements Comparator<String> {

    int columnIndex;

    public IntegerComparator(int columnIndex) {
        this.columnIndex = columnIndex;
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

        double i1 = Double.parseDouble(obj1.split(",")[columnIndex]);
        double i2 = Double.parseDouble(obj2.split(",")[columnIndex]);

        return Double.compare(i1, i2);
    }
}