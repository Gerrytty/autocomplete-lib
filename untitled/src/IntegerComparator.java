import java.util.Comparator;

public class IntegerComparator  implements Comparator<String> {

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

        int i1 = Integer.parseInt(obj1.split(",")[columnIndex]);
        int i2 = Integer.parseInt(obj2.split(",")[columnIndex]);

        if (i1 == i2) {
            return 0;
        }
        else if (i1 > i2) {
            return 1;
        }
        else {
            return -1;
        }
    }
}