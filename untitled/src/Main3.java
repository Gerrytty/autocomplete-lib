import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main3 {
    public static void main(String[] args) throws IOException {
        FileAnalyser fileAnalyser = new FileAnalyser("/home/julia/Downloads/airports.csv", 5);
        fileAnalyser.buildTree();
        List<Line> lines = fileAnalyser.getLines();

        LinesWalker linesWalker = new LinesWalker("/home/julia/Downloads/airports.csv");

        System.out.println(lines);

        for (int i = 0; i < 100; i++) {
            System.out.println(linesWalker.getLine(lines.get(i)));
        }
    }
}
