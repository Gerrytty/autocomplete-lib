import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main3 {
    public static void main(String[] args) throws IOException {
        FileAnalyser fileAnalyser = new FileAnalyser("/home/julia/Downloads/airports.csv");
        fileAnalyser.buildTree(5);
        List<Line> lines = fileAnalyser.getLines();

        LinesWalker linesWalker = new LinesWalker("/home/julia/Downloads/airports.csv");

        System.out.println(lines);

        for (Line line : lines) {
            System.out.println(linesWalker.getLine(line));
        }
    }
}
