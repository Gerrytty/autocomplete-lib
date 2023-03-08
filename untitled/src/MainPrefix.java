public class MainPrefix {
    public static void main(String[] args) {
        PrefixTree pt = new PrefixTree();

        pt.addWord("abcd", 1);
        pt.addWord("abdd", 2);
        pt.addWord("defr", 3);
        pt.addWord("abno", 4);

        System.out.println(pt.getPrefixLines("ab"));

        String s = "\"aa\",b,c";

        String[] columns = s.split(",");

        System.out.println(columns[0].replace("\"", ""));

    }
}
