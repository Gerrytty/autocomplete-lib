import java.util.ArrayList;
import java.util.List;

public class Node {

    private String val;
    private List<Node> children;
    private List<Integer> lines;

    public Node(String val) {
        this.val = val;
        this.children = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getVal() {
        return val;
    }

    public List<Integer> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
