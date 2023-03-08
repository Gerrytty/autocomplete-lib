import structs.PrefixTree;

public class MainPrefix {
    public static void main(String[] args) {

        // Example of working prefix tree
        PrefixTree pt = new PrefixTree();

        pt.addWord("abcd", 1);
        pt.addWord("abdd", 2);
        pt.addWord("defr", 3);
        pt.addWord("abno", 4);

        // prints indexes with prefix "ab"
        System.out.println(pt.getPrefixLines("ab"));

    }
}
