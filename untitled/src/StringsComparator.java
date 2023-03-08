import java.util.Comparator;

public class StringsComparator  implements Comparator<String> {

    int columnIndex;

    public StringsComparator(int columnIndex) {
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
        return obj1.split(",")[this.columnIndex].compareTo(obj2.split(",")[this.columnIndex]);
    }
}