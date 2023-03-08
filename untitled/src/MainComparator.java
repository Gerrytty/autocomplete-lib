import java.util.ArrayList;

public class MainComparator {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("ann,ben");
        list.add("moris,aen");
        list.add("boris,levi");
        list.add("den,kol");

        list.sort(new StringsComparator(1));

        System.out.println(list);

        ArrayList<String> integers = new ArrayList<>();

        integers.add("1,6");
        integers.add("3,2");
        integers.add("2,1");
        integers.sort(new IntegerComparator(1));
        System.out.println(integers);

    }
}
