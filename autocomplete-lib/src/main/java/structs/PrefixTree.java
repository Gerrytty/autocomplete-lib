package structs;

import java.util.ArrayList;
import java.util.List;

/***
 * Prefix tree implementation
 */
public class PrefixTree {

    private Node root;

    public PrefixTree() {
        this.root = new Node("root");
    }

    /***
     * add to prefix tree word path with it's prefixes
     * @param word (word to add)
     * @param lineIndex (index of line that contain this word)
     */

    public void addWord(String word, int lineIndex) {

        int i = 0;

        Node currNode = this.root;

        while (i < word.length()) {

            boolean findChild = false;

            for (Node child : currNode.getChildren()) {
                if (child.getVal().equals("" + word.charAt(i))) {
                    findChild = true;
                    currNode = child;
                    break;
                }
            }

            if (!findChild) {
                Node newNode = new Node("" + word.charAt(i));
                currNode.getChildren().add(newNode);
                currNode = newNode;
            }

            currNode.getLines().add(lineIndex);

            i += 1;
        }

    }

    /***
     * @param prefix (prefix that we search)
     * @return the lines of most deep node given prefix
     * if no path found return empty list
     */

    public List<Integer> getPrefixLines(String prefix) {

        Node currNode = this.root;

        int i = 0;
        while (i < prefix.length()) {

            boolean findChild = false;

            for (Node child : currNode.getChildren()) {
                if (child.getVal().equals("" + prefix.charAt(i))) {
                    findChild = true;
                    currNode = child;
                    break;
                }
            }

            if (!findChild) {
                return new ArrayList<>();
            }

            i += 1;
        }

        return currNode.getLines();
    }

    public List<Node> getRootChildren() {
        return root.getChildren();
    }
}
